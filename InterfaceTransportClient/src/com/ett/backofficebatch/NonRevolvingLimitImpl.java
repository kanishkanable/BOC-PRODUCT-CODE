package com.ett.backofficebatch;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ett.util.BOCCommonUtil;
import com.ett.util.CommonUtil;
import com.ett.util.QueryConstants;

/**
 * @author BA20220663
 *
 */
public class NonRevolvingLimitImpl {

	private static final Logger logger = LoggerFactory.getLogger(NonRevolvingLimitImpl.class);

	/**
	 * @param masterRef
	 * @param eventReference
	 * @param customerMnemonic
	 * @param accountType
	 * @param postingAmount
	 * @param currency
	 * @param eventCode
	 * @param productCode
	 */
	public static void addNonRevolvingLimitAmount(String masterRef, String eventReference, String customerMnemonic,
			String accountType, Long postingAmount, String currency, String productCode, String eventCode) {

		try {
			System.out.println(" Posting AccountType >> " + accountType);

			String limitCategory = getLimitCategory(accountType);

			System.out.println(" limitCategory >> " + limitCategory);

			if (BOCCommonUtil.isValidString(limitCategory)) {

				if (productCode.equalsIgnoreCase("ILC") && eventCode.equalsIgnoreCase("POC")) {

					String acceptancelimitCategory = getAcceptanceLimitCategory(limitCategory);
					System.out.println(" acceptancelimitCategory >> " + acceptancelimitCategory);
					if (BOCCommonUtil.isValidString(acceptancelimitCategory))
						limitCategory = acceptancelimitCategory;
				}
				System.out.println("Final limitCategory " + limitCategory);
				System.out.println("Final customerMnemonic " + customerMnemonic);
				System.out.println("Final postingAmount " + postingAmount);
				if (BOCCommonUtil.isValidString(limitCategory)) {
					addLimitAmountFromProcedure(customerMnemonic, limitCategory, postingAmount, currency,masterRef,eventReference);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param accountType
	 * @return {Validate account type if available then true}
	 */
	private static String getLimitCategory(String accountType) {
		String limitCategory = "";

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.LIMIT_CATEGORY_ACCOUNT_TYPE);
			pst.setString(1, accountType);
			System.out.println("Query-->" + QueryConstants.LIMIT_CATEGORY_ACCOUNT_TYPE + "--->" + accountType);
			rst = pst.executeQuery();
			if (rst.next()) {
				limitCategory = rst.getString("LIMITCATEGORY");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return limitCategory;
	}

	/**
	 * @param customerMnemonic
	 * @param limitCategory
	 * @param postingAmount
	 * @param currency
	 * @param eventReference 
	 * @param masterRef 
	 */
	public static void addLimitAmountFromProcedure(String customerMnemonic, String limitCategory, Long postingAmount,
			String currency, String masterRef, String eventReference) {
		Connection aConnection = null;
		CallableStatement aCallableStatement = null;
		logger.info("Enterted into addLimitAmountFromProcedure");
		try {
			aConnection = CommonUtil.DBConnection();
			aCallableStatement = aConnection.prepareCall("{call  COLLATERAL_LIMITAMOUNT (?, ?, ?, ?, ?, ?)}");
			aCallableStatement.setString(1, customerMnemonic);
			aCallableStatement.setString(2, limitCategory);
			aCallableStatement.setBigDecimal(3, new BigDecimal(postingAmount));
			aCallableStatement.setString(4, currency);
			aCallableStatement.setString(5, masterRef);
			aCallableStatement.setString(6, eventReference);
			aCallableStatement.execute();
			logger.info("COLLATERAL_LIMITAMOUNT  Procedure has successfully completed!!!!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while running COLLATERAL_LIMITAMOUNT Procedure" + e.getMessage());
		}

		finally {
			CommonUtil.CloseConnection(aConnection, aCallableStatement, null);
		}
	}

	public static boolean validProductEvent(String productCode, String eventCode) {
		boolean isValidProduct = false;
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.GET_ISVALID_NONREVOLVING_PRODUCT);
			pst.setString(1, productCode);
			pst.setString(2, eventCode);
			rst = pst.executeQuery();
			if (rst.next()) {
				isValidProduct = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		return isValidProduct;
	}

	public static String getAcceptanceLimitCategory(String limitCategory) {
		String acceptanceLimit = "";
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {

			aConnection = CommonUtil.DBConnection();

			pst = aConnection.prepareStatement(QueryConstants.GET_ACCEPTANCE_LIMIT_LINE);
			pst.setString(1, limitCategory);
			rst = pst.executeQuery();
			if (rst.next()) {

				acceptanceLimit = rst.getString("ACCEPTANCELIMITLINE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		System.out.println("Acceptance limitCategory >>" + acceptanceLimit);
		return acceptanceLimit;

	}

	public static void main(String[] args) {
		// addNonRevolvingLimitAmount("DSHY220660000029", "CRE001", "562", "DL068",
		// (long) 45000045, "OMR");
		// getNonRevolvingLimitAmount("11274386STR1CAT3");
	}

}
