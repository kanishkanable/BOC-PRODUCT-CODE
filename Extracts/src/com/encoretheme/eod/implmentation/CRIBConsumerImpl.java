package com.encoretheme.eod.implmentation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.encoretheme.eod.utility.BOCCommonUtil;
import com.encoretheme.eod.utility.Constants;
import com.encoretheme.eod.utility.DBConnectionUtility;
import com.encoretheme.eod.utility.QueryConstant;

public class CRIBConsumerImpl {
	public static Logger logger = Logger.getLogger(CounterPartyDeductionImpl.class);
	public  String extract(String extractName, String extractHeader,
			String destinationFilePath) {
		String Satus="Succeded";
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

	//	Properties aProperties = new Properties(); Rajwin_Modified
		String destinationFilePathBKP = "";
		String fileName = "";
		try {
		//	aProperties = ExtractsPropertyUtil.getPropertiesValue();
			// In future destination file path names can be maintained in DB or
			// property files
			destinationFilePath = BOCCommonUtil.getDestinationFilePath();	
			destinationFilePathBKP = BOCCommonUtil.getDestinationFilePathBKP();// Rajwin_Modified
			fileName = BOCCommonUtil.getFileName(extractName);
			System.out.println("destinationFilePath : " + destinationFilePath);
			System.out.println("fileName : " + fileName);

			connection = DBConnectionUtility.getConnection();
			if (connection != null) {
				String query = QueryConstant.CRIBConsumer;
				System.out.println(extractName+" extract query -->" + query);
				preparedStatement = connection.prepareStatement(query);

				resultSet = preparedStatement.executeQuery();
				StringBuffer finalString = BOCCommonUtil.prepareAsFilewithPipeline(resultSet,
						Constants.CRIBConsumer_header);         // Rajwin_Modified
				System.out.println("finalString -->" + finalString);
				BOCCommonUtil.writeFile(finalString, destinationFilePath, fileName);
				BOCCommonUtil.writeFile(finalString, destinationFilePathBKP, extractName+".txt");
				post_CRIB_Extract_Update();
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
			System.out.println("Exception in "+extractName+e);
			Satus="Failed";
			return Satus;															// Rajwin_Modified
		} finally {
			DBConnectionUtility.surrenderDB(connection, preparedStatement,
					resultSet);
			
		}
		return Satus;
	}

	public  void post_CRIB_Extract_Update() {
		Connection connection = null;
		CallableStatement callableStatement = null;
		String procedure=Constants.CRIBConsumer_POST_UPDATE;
		try {
			connection = DBConnectionUtility.getConnection();
			callableStatement = connection.prepareCall("{call " + procedure + "()}");
			callableStatement.execute();
			System.out.println("Procedure->" + procedure + ",Execution->Completed");
			
		} catch (Exception e) {
			System.out.println("Procedure->" + procedure + ",Execution->Failed--->"+e.getMessage());
			
			
		} finally {
			DBConnectionUtility.surrenderDB(connection, callableStatement, null);
		}
	}
	
  public static void main(String args[])
  {System.out.println("inside main function");
  }
}
