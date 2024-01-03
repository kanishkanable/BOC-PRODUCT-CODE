package com.boc.themebridge.adapter.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import com.ett.util.CommonUtil;

public class GatewayProcess {

	private final static Logger logger = Logger.getLogger(GatewayProcess.class.getName());

	public static void processGateWay() {

		logger.info("----------------- Initiating Gateway Message Processing -----------------");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<TiGateWayExtractLog> gatewayList = new ArrayList<TiGateWayExtractLog>();

		try {
			String query = "SELECT * FROM TIGATEWAYEXTRACTLOG WHERE STATUS in ('R','F')";
			connection = CommonUtil.DBConnection();
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				TiGateWayExtractLog tiGateWayExtractLog = new TiGateWayExtractLog();
				logger.info("<---Gateway Message--->");

				logger.info("SystemId->" + resultSet.getString("PKID"));
				logger.info("Master Key->" + resultSet.getString("MASTER_KEY"));
				logger.info("Event Key->" + resultSet.getString("EVENT_KEY"));
				logger.info("Product Type->" + resultSet.getString("PROD_TYPE"));
				logger.info("Event Type->" + resultSet.getString("EVENT_TYPE"));

				tiGateWayExtractLog.setPkid(resultSet.getString("PKID"));
				tiGateWayExtractLog.setMasterKey(resultSet.getString("MASTER_KEY"));
				tiGateWayExtractLog.setEventKey(resultSet.getString("EVENT_KEY"));
				tiGateWayExtractLog.setProductType(resultSet.getString("PROD_TYPE"));
				tiGateWayExtractLog.setEventType(resultSet.getString("EVENT_TYPE"));

				gatewayList.add(tiGateWayExtractLog);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(connection, preparedStatement, resultSet);
		}

		try {
			for (TiGateWayExtractLog tiGateWayExtractLog : gatewayList) {

				int updateCount = updateGateWayExtractStatus(tiGateWayExtractLog.getPkid(), "I");
				logger.info("Status Intiation Update Count--->" + updateCount);

				if (updateCount > 0) {

					List<String> procedureList = new ArrayList<String>();
					procedureList.add("COLLATERAL_LINK_DETAILS_PROC");
					procedureList.add("GUARANTEE_LINK_DETAILS_PROC");
					procedureList.add("AML_COLL_PROC");
					procedureList.add("AML_LC_PROC");
					procedureList.add("COUNTERPARTY_DEDUCTION");
					procedureList.add("COUNTERPARTY_DEFAULT");
					procedureList.add("COUNTERPARTY_RECOVERY");
					procedureList.add("IRMS_LOANEXPOSURE");
					procedureList.add("MARGIN_DEPOSITS_PROC");
					procedureList.add("IRMS_OFF_BALANCE_SHEET");
					procedureList.add("SL_CUSTOMS_EXT");
					procedureList.add("FX_SALE_PURCHASE");
					procedureList.add("CRIBCORPORATESUBJECT");
					procedureList.add("CRIBCORPORATECREDITFACILITY");
					procedureList.add("CRIBCORPORATEGUARANTEESEGMENT");
					procedureList.add("CRIBCONSUMERSUBJECT");
					procedureList.add("CRIBCONSUMERCREDITFACILITY");
					procedureList.add("CRIBCONSUMERGUARANTEESEGMENT");
					procedureList.add("OVER_ONE_MILLION");

					logger.info("List of Procedure--->" + ArrayUtils.toString(procedureList));

					boolean status = callextractProcedure(tiGateWayExtractLog, procedureList);

					logger.info("Calling Procedure Status--->" + status);
					if (status) {
						updateGateWayExtractStatus(tiGateWayExtractLog.getPkid(), "C");
					} else {
						updateGateWayExtractStatus(tiGateWayExtractLog.getPkid(), "F");
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("-----------------  Gateway Message Processing completed -----------------");
	}

	private static boolean callextractProcedure(TiGateWayExtractLog tiGateWayExtractLog, List<String> procedureList) {
		logger.info("----------------- Executing Procedures List -----------------");
		boolean executionStatus = false;
		try {
			for (String procedure : procedureList) {

				logger.info("Try to Execute Procedure->" + procedure);

				String procedureStatus = getProcedureStatus(procedure, tiGateWayExtractLog.getMasterKey(),
						tiGateWayExtractLog.getEventKey());

				if ("NA".equalsIgnoreCase(procedureStatus) || "F".equalsIgnoreCase(procedureStatus)) {
					if ("NA".equalsIgnoreCase(procedureStatus)) {
						loggingExtractProcedure(procedure, tiGateWayExtractLog, "I");
					}
					Connection connection = null;
					CallableStatement callableStatement = null;
					try {
						connection = CommonUtil.DBConnection();
						callableStatement = connection.prepareCall("{call " + procedure + "(?, ?)}");
						callableStatement.setString(1, tiGateWayExtractLog.getMasterKey());
						callableStatement.setString(2, tiGateWayExtractLog.getEventKey());
						callableStatement.execute();
						logger.info("Procedure->" + procedure + ",Execution->Completed");
						loggingExtractProcedure(procedure, tiGateWayExtractLog, "C");
					} catch (Exception e) {
						logger.info("Procedure->" + procedure + ",Execution->Failed");
						loggingExtractProcedure(procedure, tiGateWayExtractLog, "F");
						e.printStackTrace();
					} finally {
						CommonUtil.CloseConnection(connection, callableStatement, null);
					}
				}
			}
			executionStatus = true;
		} catch (Exception e) {
			executionStatus = false;
			e.printStackTrace();
		}
		return executionStatus;
	}

	private static String getProcedureStatus(String procedure, String masterKey, String eventKey) {

		logger.info("----------------- Checking Procedure Execution Status -----------------");
		String Status = "NA";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			String query = "SELECT STATUS FROM EXTRACTPROCEDURELOG WHERE MASTER_KEY=? AND EVENT_KEY=? AND PROCEDURENAME=?";
			connection = CommonUtil.DBConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, masterKey);
			preparedStatement.setString(2, eventKey);
			preparedStatement.setString(3, procedure);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				logger.info("Procedure Status->" + resultSet.getString("STATUS"));
				Status = resultSet.getString("STATUS");
			}

		} catch (Exception e) {
			logger.info("Procedure Status Checking Failed->" + e.getMessage());
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(connection, preparedStatement, resultSet);
		}
		return Status;
	}

	private static void loggingExtractProcedure(String proedure, TiGateWayExtractLog tiGateWayExtractLog,
			String status) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		if ("I".equalsIgnoreCase(status)) {
			try {
				String query = "INSERT INTO EXTRACTPROCEDURELOG (GATEWAYKEY,MASTER_KEY,EVENT_KEY,PROD_TYPE,EVENT_TYPE,PROCEDURENAME,STATUS,STARTED) "
						+ " VALUES(?,?,?,?,?,?,?,SYSDATE)";

				connection = CommonUtil.DBConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, tiGateWayExtractLog.getPkid());
				preparedStatement.setString(2, tiGateWayExtractLog.getMasterKey());
				preparedStatement.setString(3, tiGateWayExtractLog.getEventKey());
				preparedStatement.setString(4, tiGateWayExtractLog.getProductType());
				preparedStatement.setString(5, tiGateWayExtractLog.getEventType());
				preparedStatement.setString(6, proedure);
				preparedStatement.setString(7, status);

				preparedStatement.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CommonUtil.CloseConnection(connection, preparedStatement, null);
			}
		} else {
			try {
				String query = "UPDATE EXTRACTPROCEDURELOG SET STATUS=?,FINISHED=SYSDATE WHERE GATEWAYKEY=? AND PROCEDURENAME=?";
				connection = CommonUtil.DBConnection();
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, status);
				preparedStatement.setString(2, tiGateWayExtractLog.getPkid());
				preparedStatement.setString(3, proedure);

				preparedStatement.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CommonUtil.CloseConnection(connection, preparedStatement, null);
			}
		}

	}

	private static int updateGateWayExtractStatus(String pkid, String status) {

		logger.info("------ Try to Update the Gateway Process status ------");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int updateCount = 0;
		logger.info("Status->" + status);
		try {
			String query = "UPDATE TIGATEWAYEXTRACTLOG SET STATUS=? WHERE PKID=?";
			connection = CommonUtil.DBConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, status);
			preparedStatement.setString(2, pkid);

			updateCount = preparedStatement.executeUpdate();
			logger.info("Update Completed->" + status);
		} catch (Exception e) {
			logger.info("Update status of Gateway Process Failed->" + e.getMessage());
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(connection, preparedStatement, null);
		}

		return updateCount;

	}

	public static void main(String[] args) {
		processGateWay();
	}

}
