package com.encoretheme.eod.implmentation;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.encoretheme.eod.utility.DBConnectionUtility;

public class DatawarehouseExtractImpl {

	public static boolean process() {
		boolean extractStatus = true;
		try {
			System.out.println("Datawarehouse Extract Process started");

			InterestIncomeUpdate();
			feeIncomeUpdate();
			categoryUpdate();

			System.out.println("Datawarehouse Extract Process ended");

		} catch (Exception e) {
			e.printStackTrace();
			extractStatus = false;
		}
		return extractStatus;
	}
	public static void InterestIncomeUpdate() {
		Connection aConnection = null;
		CallableStatement aCallableStatement = null;

		try {
			aConnection = DBConnectionUtility.getConnection();
			String interestIncomeUpdateQuery = "{ call DATAWAREHOUSE_INTERESTINCOME_UPDATE() }";
			aCallableStatement = aConnection
					.prepareCall(interestIncomeUpdateQuery);
			int countResult = aCallableStatement.executeUpdate();
			System.out
					.println("Count Result of Interet Income Update Procedure => "
							+ countResult);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBConnectionUtility.surrenderDB(aConnection, aCallableStatement, null);
		}
	}

	public static void feeIncomeUpdate() {
		Connection aConnection = null;
		CallableStatement aCallableStatement = null;

		try {
			aConnection =	DBConnectionUtility.getConnection();
			String feeIncomeUpdateQuery = "{ call DATAWAREHOUSE_FEEINCOME_UPDATE() }";
			aCallableStatement = aConnection.prepareCall(feeIncomeUpdateQuery);
			int countResult = aCallableStatement.executeUpdate();
			System.out
					.println("Count Result of Fee Income Update Procedure => "
							+ countResult);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBConnectionUtility.surrenderDB(aConnection, aCallableStatement, null);
		}
	}

	public static void categoryUpdate() {
		Connection aConnection = null;
		CallableStatement aCallableStatement = null;

		try {
			aConnection = DBConnectionUtility.getConnection();
			String categoryUpdateQuery = "{ call DATAWAREHOUSE_CATEGORY_UPDATE() }";
			aCallableStatement = aConnection.prepareCall(categoryUpdateQuery);
			int countResult = aCallableStatement.executeUpdate();
			System.out.println("Count Result of Category Update Procedure => "
					+ countResult);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBConnectionUtility.surrenderDB(aConnection, aCallableStatement, null);
		}
	}
}
