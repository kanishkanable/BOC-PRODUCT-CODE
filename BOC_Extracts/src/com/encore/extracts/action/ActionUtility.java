package com.encore.extracts.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.encore.extracts.dao.GuaranteeDAO;
import com.encore.extracts.utility.DBConnectionUtility;

public class ActionUtility {
	private static Logger logger = Logger.getLogger(ActionUtility.class.getName());

	public static boolean isValidCustomer(String counterpartyUniqueIdentifier) {
		System.out.println("Process started - customer vallidation");

		boolean isValidCustomer = false;
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		String validateCustomerQuery = null;
		try {
			connection = DBConnectionUtility.getConnection();
			validateCustomerQuery = "select count(*) as result from gfpf where upper(gfcus1) = '"
					+ counterpartyUniqueIdentifier.trim().toUpperCase() + "'";
			System.out.println("validateCustomerQuery --->" + validateCustomerQuery);
			prepareStatement = connection.prepareStatement(validateCustomerQuery);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getInt("RESULT") == 1)
					isValidCustomer = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, resultSet);
		}
		logger.info("Process completed - customer validation");

		return isValidCustomer;
	}

	public static boolean isValidBranch(String branchCode) {
		System.out.println("Process started - Branch code vallidation");

		boolean isValidBranch = false;
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		String validateBranchQuery = null;
		try {
			connection = DBConnectionUtility.getConnection();
			validateBranchQuery = "select count(*) as result from CAPF where upper(CABRNM) = '"
					+ branchCode.trim().toUpperCase() + "'";
			System.out.println("validateBranchQuery --->" + validateBranchQuery);
			prepareStatement = connection.prepareStatement(validateBranchQuery);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getInt("RESULT") == 1)
					isValidBranch = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, resultSet);
		}
		logger.info("Process completed - customer validation");
		return isValidBranch;
	}

	public static boolean isValidCurrency(String collateralCurrency) {
		System.out.println("Process started - currency vallidation");

		boolean isValidCurrency = false;
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		String validateCurrencyQuery = null;
		try {
			connection = DBConnectionUtility.getConnection();
			//SELECT trim(C8CCY),trim(C8CUR) FROM C8PF
			validateCurrencyQuery = "select count(*) as result from C8PF where upper(C8CCY) = '"
					+ collateralCurrency.trim().toUpperCase() + "'";
			System.out.println("validateCurrencyQuery --->" + validateCurrencyQuery);
			prepareStatement = connection.prepareStatement(validateCurrencyQuery);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getInt("RESULT") == 1)
					isValidCurrency = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, resultSet);
		}
		logger.info("Process completed - currency validation");

		return isValidCurrency;
	}
}
