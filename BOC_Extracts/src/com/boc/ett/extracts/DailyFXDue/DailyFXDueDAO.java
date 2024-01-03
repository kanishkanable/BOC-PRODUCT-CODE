package com.boc.ett.extracts.DailyFXDue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.encore.extracts.utility.CommonMethods;
import com.encore.extracts.utility.DBConnectionUtility;

public class DailyFXDueDAO implements ActionConstants {

	static DailyFXDueDAO dao;

	private static Logger logger = Logger.getLogger(DailyFXDueDAO.class.getName());

	public static DailyFXDueDAO getDAO() {
		if (dao == null) {
			dao = new DailyFXDueDAO();
		}
		return dao;
	}

	public StringBuffer fetchRecords(DailyFXDueVO dailyFXDueVO) {

		logger.info(ENTERING_METHOD);
		String scenario = "";
		CommonMethods commonMethods = new CommonMethods();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		StringBuffer finalString = null;
		try {

			scenario = dailyFXDueVO.getScenario();

			if (!commonMethods.isNull(scenario)) {

				if (scenario.equalsIgnoreCase("FXCI")) {
					query = ActionConstants.FXCI;

				} else if (scenario.equalsIgnoreCase("FXUI")) {
					query = ActionConstants.FXUI;

				} else if (scenario.equalsIgnoreCase("FXCE")) {
					query = ActionConstants.FXCE;

				} else if (scenario.equalsIgnoreCase("FXUE")) {

					query = ActionConstants.FXUE;
				}
			}

			connection = DBConnectionUtility.getConnection();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			finalString = prepareAsFile(rs);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(EXITING_METHOD);
		return finalString;
	}

	private static StringBuffer prepareAsFile(ResultSet resultSet) throws IOException {
		logger.info(ENTERING_METHOD);
		StringBuffer finalString = new StringBuffer();
		ResultSetMetaData metaData;
		String extractHeader = "";
		int columnCount;
		StringBuffer bf;
		StringBuffer extractFileData = new StringBuffer();
		int rowCounter = 0;
		try {
			metaData = resultSet.getMetaData();
			columnCount = metaData.getColumnCount();
			bf = new StringBuffer();
			while (resultSet.next()) {

				for (int i = 1; i < columnCount + 1; i++) {
					String columnLabel = metaData.getColumnLabel(i);
					if (rowCounter == 0) {
						extractHeader = extractHeader + columnLabel + ",";

					}

					String value = convertEmptyIfNull(resultSet.getString(metaData.getColumnName(i)));

					bf.append(value.replaceAll("\n", "").replaceAll("\r", "").trim() + ",");

				}

				rowCounter = rowCounter + 1;
				if (extractHeader == null || extractHeader.isEmpty()) {

					String defaultHeader = null;
					extractHeader = defaultHeader;
				}

				bf.append("\n");
			}
			extractFileData.append(bf.toString());
			finalString.append(extractHeader + "\n");
			finalString.append(extractFileData);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info(EXITING_METHOD);
		return finalString;
	}

	private static String convertEmptyIfNull(String value) {
		if (value == null) {
			value = "";
		}
		return value;
	}

}
