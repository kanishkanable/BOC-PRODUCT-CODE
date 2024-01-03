package com.boc.ett.extracts.sl_customs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.encoretheme.eod.utility.*;


import com.xsd.slcustoms.BankData;
import com.xsd.slcustoms.Table1;

public class SLCustomXml {
	private static Logger logger = Logger.getLogger(SLCustomXml.class
			.getName());
	public void setXmlStream(ByteArrayInputStream xmlStream) {
		this.xmlStream = xmlStream;
		
	}
	ByteArrayInputStream xmlStream;
	
	public String fetchRecords() throws IOException {
		String Status="Succeded";
		StringBuffer strbuffer = new StringBuffer();
		strbuffer = fetchExtractsDAO();
		System.out.println("strbuffer" +strbuffer);
		ByteArrayInputStream inputstream = new ByteArrayInputStream(strbuffer.toString().getBytes("UTF-8"));
		System.out.println("Input Stream" +inputstream);
		
		return Status;
		
	}
	
	public StringBuffer fetchExtractsDAO() {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = ActionConstants.FETCHSLCUSTOMSEXTRACTQUERY;
		BOCCommonUtil commonMethods = null;
		StringBuffer sb = new StringBuffer();
		logger.info("ENTERING_METHOD");
		try {

			commonMethods = new BOCCommonUtil();
			
			logger.info("Extracts Query : " + query);

			connection = DBConnectionUtility.getConnection();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			BankData bd = new BankData();

			while (rs.next()) {
				Table1 table = new Table1();
				table.setBank(rs.getString("BANK").trim());
				table.setBranch(rs.getString("BRANCH").trim());
				table.setName(rs.getString("NAME").trim());
				table.setAdd1(rs.getString("ADD1").trim());
				table.setAdd2(rs.getString("ADD2").trim());
				table.setAdd3(rs.getString("ADD3").trim());
				table.setNICCRBR(rs.getString("NIC_CR_BR").trim());
				table.setVatNo(rs.getString("VATNO").trim());
				table.setBName(rs.getString("BNAME").trim());
				table.setBAdd(rs.getString("BADD").trim());
				table.setBCountry(rs.getString("BCOUNTRY").trim());
				table.setECA(rs.getInt("ECA"));
				table.setFEP(rs.getInt("FEP"));
				table.setCurrCode(rs.getString("CURRCODE").trim());
				table.setAmount(rs.getBigDecimal("AMOUNT"));
				table.setToP(rs.getString("TOP").trim());
				table.setToD(rs.getString("TOD").trim());
				table.setRefNo(rs.getString("REFNO").trim());
				table.setLCNo(rs.getString("LCNO").trim());
				table.setDate(rs.getDate("DATE"));
				bd.getTable1().add(table);

				

			}

			sb.append(BOCCommonUtil.getXmlFromObject(bd));

		} catch (Exception e) {
			logger.info("Exception-->"+e);
			e.printStackTrace();
		}

		finally {
			DBConnectionUtility.surrenderDB(connection, ps, rs);
		}

		logger.info("EXITING_METHOD");
		return sb;
	}

}
