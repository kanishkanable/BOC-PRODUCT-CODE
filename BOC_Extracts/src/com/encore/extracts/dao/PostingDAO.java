package com.encore.extracts.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.encore.extracts.utility.DBConnectionUtility;
import com.encore.extracts.vo.PostingVO;
import com.encore.extracts.vo.SwiftVO;

public class PostingDAO {

	private static Logger logger = Logger.getLogger(PostingDAO.class.getName());

	static PostingDAO postingDAO;

	public static PostingDAO getDAO() {
		if (postingDAO == null) {
			postingDAO = new PostingDAO();
		}
		return postingDAO;
	}

	public ArrayList<PostingVO> getPostingDetails(String masterRef,String eventRef) {
		ArrayList<PostingVO> postingList = new ArrayList<PostingVO>();
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		String postingListQuery = null;
		String masterEventRef = masterRef+"-"+eventRef;
		try {
			if(masterRef!=null && !masterRef.isEmpty() && (eventRef.isEmpty() || eventRef.equals(null) || eventRef =="")) {
				masterEventRef = masterRef +"%";
			}
			System.out.println("masterEventRef :" +masterEventRef);
			if(masterEventRef!=null && !masterEventRef.isEmpty())
			{
				connection = DBConnectionUtility.getConnection();
				postingListQuery = "SELECT (case when length(alphabeticdata1)> 18 then\r\n" + 
						"SUBSTR(alphabeticdata1, 1, 16)\r\n" + 
						"else SUBSTR(alphabeticdata1, 1, 11) end) As ACTION ,\r\n" + 
						"(case when length(alphabeticdata1)> 18 then\r\n" + 
						"SUBSTR(alphabeticdata1, 18, 6)\r\n" + 
						"else\r\n" + 
						"SUBSTR(alphabeticdata1, 13, 6)\r\n" + 
						"end) AS MAS_REIMBSYSREF,\r\n" + 
						"STATUS as MAS_DOCCRNO,accountnumber as MAS_PRCSID,transactionamount||Transaction_ccy as MAS_ACTVID,case when (debitcreditcode = 6)then"+ 
						" 'DR'else 'CR' end as MAS_DTMODIFIED , TransactionID,RequestFileName,ResponseFileName FROM postingtable WHERE alphabeticdata1 like ?";
				prepareStatement = connection.prepareStatement(postingListQuery);
				prepareStatement.setString(1, masterEventRef);
				resultSet = prepareStatement.executeQuery();
				while (resultSet.next()) {
					PostingVO postingVOTmp = new PostingVO();
					postingVOTmp.setMasterRef(resultSet.getString(1));
					postingVOTmp.setEventRef(resultSet.getString(2));
					postingVOTmp.setStatus(resultSet.getString(3));
					postingVOTmp.setAccountNumber(resultSet.getString(4));
					postingVOTmp.setTranAmount(getFormattedAmt(resultSet.getString(5)));
					postingVOTmp.setCreditDebit(resultSet.getString(6));
					postingVOTmp.setTransactionID(resultSet.getString(7));
					postingVOTmp.setRequestFilename(resultSet.getString(8));
					postingVOTmp.setResponseFilename(resultSet.getString(9));
					postingList.add(postingVOTmp);
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement,
					resultSet);
		}
		return postingList;
	}
	
	public ArrayList<SwiftVO> getSwiftDetails(String masterRef,String eventRef) {
		ArrayList<SwiftVO> swiftList = new ArrayList<SwiftVO>();
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		String swiftListQuery = null;
		try {
			connection = DBConnectionUtility.getConnection();
			swiftListQuery = "SELECT MASTER_REF,EVENT_REF,STATUS,RECEIVEDDATE,PROCESSEDDATE FROM SWIFTOUTLOG WHERE MASTER_REF = ? AND EVENT_REF = ?";
			prepareStatement = connection.prepareStatement(swiftListQuery);
			prepareStatement.setString(1, masterRef);
			prepareStatement.setString(2, eventRef);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				SwiftVO swiftVOTmp = new SwiftVO();
				swiftVOTmp.setMasterRef(resultSet.getString(1));
				swiftVOTmp.setEventRef(resultSet.getString(2));
				swiftVOTmp.setStatus(resultSet.getString(3));
				swiftVOTmp.setReceivedDate(resultSet.getString(4));
				swiftVOTmp.setProcessedDate(resultSet.getString(5));
				swiftList.add(swiftVOTmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement,
					resultSet);
		}
		return swiftList;
	}
	
	public void updatePostingForRelease(String masterRef,String eventRef) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		String updateQuery = null;
		try {
			connection = DBConnectionUtility.getConnection();
			updateQuery = "update FTIBACKOFFICEBATCH set BANKRESSTATUS='SUCCEEDED' where MASTER_REF = ? AND EVENT_REF = ?";
			prepareStatement = connection.prepareStatement(updateQuery);
			prepareStatement.setString(1, masterRef);
			prepareStatement.setString(2, eventRef);
			prepareStatement.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement,
					null);
		}
	}
	
	public void updatePostingStatus(String masterRef,String eventRef) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		String updateQuery = null;
		try {
			connection = DBConnectionUtility.getConnection();
			updateQuery = "update POSTINGTABLE set Status='SUCCEEDED_MANUAL' where AlphabeticData1=? AND status !='SUCCEEDED'";
			prepareStatement = connection.prepareStatement(updateQuery);
			prepareStatement.setString(1, masterRef+"-"+eventRef);
			prepareStatement.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement,
					null);
		}
	}
	//adding updateswiftstatus function by padhu
	public void updateSwiftStatus(String masterRef,String eventRef) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		String updateQuery = null;
		try {
			connection = DBConnectionUtility.getConnection();
			updateQuery = "update SWIFTOUTLOG set STATUS='Hold' where MASTER_REF = ? AND EVENT_REF = ? AND STATUS = 'FAILED'";
			prepareStatement = connection.prepareStatement(updateQuery);
			prepareStatement.setString(1, masterRef);
			prepareStatement.setString(2, eventRef);
			prepareStatement.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement,
					null);
		}
	}
	
	public static String getFormattedAmt(String tranAmtFromDb) {
		String result = "";
		Boolean isccy=false;
		try {
			
			String ccy=tranAmtFromDb.substring(tranAmtFromDb.length()-3);
		
			if(tranAmtFromDb.length()>17){
				tranAmtFromDb=tranAmtFromDb.substring(0,tranAmtFromDb.length()-3);
				isccy=true;
			
				
			}
			tranAmtFromDb = tranAmtFromDb.replaceFirst("^0*", "");
			BigDecimal tranAmtLong = new BigDecimal(tranAmtFromDb);
			tranAmtLong = tranAmtLong.setScale(2, BigDecimal.ROUND_HALF_UP);
			tranAmtLong = tranAmtLong.divide(new BigDecimal(100));
			result = tranAmtLong.toString();
			if(isccy)
			{
				result=result+ccy;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String []args) {
		System.out.println("amt-->"+getFormattedAmt("00000000000450000USD"));
	}
}
