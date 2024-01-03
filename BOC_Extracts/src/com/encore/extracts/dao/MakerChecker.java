package com.encore.extracts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.encore.extracts.utility.DBConnectionUtility;
import com.encore.extracts.vo.CollateralVO;
import com.encore.extracts.vo.CustomerVO;
import com.encore.extracts.vo.GuaranteeVO;

public class MakerChecker {

	public static String getMakerCheckerStatus(String category, CustomerVO custVO) {
		String status = "";
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		String makerCheckerTable = "";
		if (custVO.getAction().equalsIgnoreCase("collateralDetails")) {
			makerCheckerTable = "COLLATERAL_DETAILS_MAKER_CHECKER";
		} else if (custVO.getAction().equalsIgnoreCase("guaranteeDetails")) {
			makerCheckerTable = "GUARANTEE_DETAILS_MAKER_CHECKER";
		}

		String makChkActStatus = "SELECT MAKERCHECKERACTION FROM " + makerCheckerTable + " WHERE UPPER(CATEGORY)='"
				+ category.trim().toUpperCase() + "'";
		System.out.println("----------------------getMakerCheckerStatus--------------");
		try {
			connection = DBConnectionUtility.getConnection();
			System.out.println("customerLimitListQuery --->" + makChkActStatus);
			prepareStatement = connection.prepareStatement(makChkActStatus);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				status = resultSet.getString("MAKERCHECKERACTION");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, resultSet);
		}
		System.out.println("Status of maker checker action " + status);
		return status;
	}

	public static String checkIsMakerOrChecker(String userName) {

		String isMaterChecker = "";
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		String userMatrix = "SELECT TRIM(AUTHORISATION) AS AUTHORISATION FROM MAKER_CHECKER WHERE USERID = '" + userName
				+ "'";
		System.out.println("----------------------checkIsMakerOrChecker--------------");
		try {
			connection = DBConnectionUtility.getConnection();
			System.out.println("checkIsMakerOrChecker query--->" + userMatrix);
			prepareStatement = connection.prepareStatement(userMatrix);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				isMaterChecker = resultSet.getString("AUTHORISATION");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, resultSet);
		}
		System.out.println("Logged In User is  " + isMaterChecker);
		return isMaterChecker;

	}

	public static void insertAuditTrailLogs(CollateralVO collVO, String auditType) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = DBConnectionUtility.getConnection();
			String insertCollateralQuery = "INSERT INTO AUDIT_TRIAL_LOG(USERID,USER_ROLE,TYPE,CUSTMOR_CIF,LIMIT_LINE_ID,NATURE_OF_LIMIT,LAST_UPDATED_DATE_TIME,ACTION_TYPE) VALUES(?,?,?,?,?,?,CURRENT TIMESTAMP,'COLLATERAL')";
			System.out.println("insertCollateralQuery >>> " + insertCollateralQuery);
			prepareStatement = connection.prepareStatement(insertCollateralQuery);
			prepareStatement.setString(1, collVO.getUserName());
			prepareStatement.setString(2, collVO.getUserRole());
			prepareStatement.setString(3, auditType);
			prepareStatement.setString(4, collVO.getCustomerID());
			prepareStatement.setString(5, collVO.getCategory());
			prepareStatement.setString(6, collVO.getNatureOfLimit());
			
			System.out.println("----1--" + collVO.getUserName());
			System.out.println("----2--------" + collVO.getUserRole());
			System.out.println("----3--------" + auditType);
			System.out.println("----4--------" + collVO.getCustomerID());
			System.out.println("----5--------" + collVO.getCategory());
			System.out.println("----6--------" + collVO.getNatureOfLimit());

			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, null);
		}
	}

	public static void insertAuditTrailLogs(GuaranteeVO guaVO, String auditType) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = DBConnectionUtility.getConnection();
			String insertCollateralQuery = "INSERT INTO AUDIT_TRIAL_LOG(USERID,USER_ROLE,TYPE,CUSTMOR_CIF,LIMIT_LINE_ID,NATURE_OF_LIMIT,LAST_UPDATED_DATE_TIME,ACTION_TYPE) VALUES(?,?,?,?,?,?,CURRENT TIMESTAMP,'GUARANTEE')";
			System.out.println("insertCollateralQuery >>> " + insertCollateralQuery);
			prepareStatement = connection.prepareStatement(insertCollateralQuery);
			prepareStatement.setString(1, guaVO.getUserName());
			prepareStatement.setString(2, guaVO.getUserRole());
			prepareStatement.setString(3, auditType);
			prepareStatement.setString(4, guaVO.getCustomerID());
			prepareStatement.setString(5, guaVO.getCategory());
			prepareStatement.setString(6, guaVO.getNatureOfLimit());

			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, null);
		}
	}

	public static boolean isCategoryAvailable(String category, String tableName) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBConnectionUtility.getConnection();
			String categoryQuery = "SELECT count(*) AS RESULT FROM " + tableName + " where upper(category)='" + category
					+ "'";
			System.out.println("Query>>>>>" + categoryQuery);
			prepareStatement = connection.prepareStatement(categoryQuery);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getInt("RESULT") == 1) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, resultSet);
		}
		return false;
	}

}
