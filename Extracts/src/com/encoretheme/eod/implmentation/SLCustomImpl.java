package com.encoretheme.eod.implmentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.encoretheme.eod.utility.BOCCommonUtil;
import com.encoretheme.eod.utility.Constants;
import com.encoretheme.eod.utility.DBConnectionUtility;
import com.encoretheme.eod.utility.QueryConstant;

public class SLCustomImpl {
	public static Logger logger = Logger.getLogger(SLCustomImpl.class);
	public  String extract(String extractName, String extractHeader,
			String destinationFilePath) {
		String Satus="Succeded";
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

	//	Properties aProperties = new Properties(); Rajwin_Modified

		String fileName = "";
		String destinationFilePathBKP = "";
		try {
		//	aProperties = ExtractsPropertyUtil.getPropertiesValue();
			// In future destination file path names can be maintained in DB or
			// property files
			destinationFilePath = BOCCommonUtil.getDestinationFilePath();
			destinationFilePathBKP = BOCCommonUtil.getDestinationFilePathBKP();// Rajwin_Modified
			fileName = BOCCommonUtil.getFileName(extractName);
			logger.info("destinationFilePath : " + destinationFilePath);
			logger.info("fileName : " + fileName);

			connection = DBConnectionUtility.getConnection();
			if (connection != null) {
				String query = QueryConstant.SLCustom;
				logger.info(extractName+" extract query -->" + query);
				preparedStatement = connection.prepareStatement(query);

				resultSet = preparedStatement.executeQuery();
				StringBuffer finalString = BOCCommonUtil.prepareAsFilewithPipeline(resultSet,
						Constants.SLCustom_header);         // Rajwin_Modified
				logger.info("finalString -->" + finalString);
				BOCCommonUtil.writeFile(finalString, destinationFilePath, fileName);
				BOCCommonUtil.writeFile(finalString, destinationFilePathBKP, extractName+".txt");
			}
			
		} catch (Exception e) {
			e.printStackTrace();			
			logger.info("Exception in "+extractName+"-->"+e);
			Satus="Failed";
			return Satus;															// Rajwin_Modified
		} finally {
			DBConnectionUtility.surrenderDB(connection, preparedStatement,
					resultSet);
			
		}
		return Satus;
	}


	



}
