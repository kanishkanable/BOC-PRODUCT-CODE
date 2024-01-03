package com.encoretheme.eod.implmentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.encoretheme.eod.utility.BOCCommonUtil;
import com.encoretheme.eod.utility.Constants;
import com.encoretheme.eod.utility.DBConnectionUtility;

import com.encoretheme.eod.utility.QueryConstant;

/**
 * @author it177297
 *
 */
public class AMLDeletionImpl {
	public static Logger logger = Logger.getLogger(AMLDeletionImpl.class);

	/**
	 * @return Status of deletion
	 */
	@SuppressWarnings("resource")
	public static String deletePreviousDayAMLEntries() {

		String Satus = "Succeded";
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;

		try {
			
			String previousDate ="" ;
			String dateQuery = QueryConstant.SYSTEM_DATE;
			String lcQuery = QueryConstant.AML_LC_DELETION_QUERY;
			String collectionQuery = QueryConstant.AML_COLL_DELETION_QUERY;
			
			connection = DBConnectionUtility.getConnection();
			
			preparedStatement = connection.prepareStatement(dateQuery);
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				previousDate = resultSet.getString(1);
			}
			if(!previousDate.isEmpty() && previousDate!=null) {
				
				previousDate = getFormatedDate(previousDate);				
				preparedStatement = null;
				
				try {
					preparedStatement = connection.prepareStatement(lcQuery);
					preparedStatement.setString(1, previousDate);
					preparedStatement.executeUpdate();
					logger.debug("AML Previous day entries has deleted");
				} catch (Exception e) {
					logger.error("Error while deleting AML Previous day entries",e);
				}
				preparedStatement = null;
				try {
					preparedStatement = connection.prepareStatement(collectionQuery);
					preparedStatement.setString(1, previousDate);
					preparedStatement.executeUpdate();
					logger.debug("AML Previous day entries has deleted");
				} catch (Exception e) {
					logger.error("Error while deleting AML Previous day entries",e);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, preparedStatement, resultSet);

		}
		return Satus;
	}
	/**
	 * @param previousDate
	 * @return 
	 */
	private static String getFormatedDate(String previousDate) {
		SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateOutput = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = dateInput.parse(previousDate);
			previousDate = dateOutput.format(date); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return previousDate;
	}
	public static void main(String[] args) {
		deletePreviousDayAMLEntries();
	}
}
