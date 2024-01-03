package com.ett.eod.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ett.eod.finaceposting.EodPostingImpl;


public class BOCCommonUtil {
	public static Logger logger = Logger.getLogger(BOCCommonUtil.class);
	public static String getNextSeqNo(String seqName) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		String key97 = null;

		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement("SELECT NEXT VALUE FOR " + seqName + " FROM SYSIBM.SYSDUMMY1");
			rst = pst.executeQuery();
			while (rst.next())
				key97 = rst.getString(1);
		} catch (SQLException e) {
			logger.info("Exception In Sequence");
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return key97;
	}
	
	public static String retreiveFromProperties(String key) {

		String value = null;
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstant.RETRIEVE_VALUE_FROM_PROPERTIES);
			pst.setString(1, key);
			rst = pst.executeQuery();

			while (rst.next())
				value = rst.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return value;

	}
	
	public static boolean isValidString(String String) {
		if (String == null || "".equalsIgnoreCase(String) || String.isEmpty())
			return true;

		return false;
	}
	public static String DeleteTabelEntries(String Tablename) {

		String value = null;
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement("DELETE FROM "+Tablename);
			logger.info("Query----->"+"DELETE FROM "+Tablename);
			pst.executeUpdate();

		
		} catch (Exception e) {
			logger.info("Exception in delete query");
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return value;

	}
	
	public static String DebitCreditTranslate(String DrCrCode) {

		String NewAccountType = null;

		if (DrCrCode.equalsIgnoreCase("D")) {
			NewAccountType = "6";
		} else if (DrCrCode.equalsIgnoreCase("C")) {
			NewAccountType = "0";
		}

		return NewAccountType;
	}
	
	public static String getCurrencyCode(String Currency) {

		String CurrencyCode = null;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstant.FETCH_CURRENCY_CODE);
			pst.setString(1, Currency);
			rst = pst.executeQuery();
			while (rst.next())
				CurrencyCode = rst.getString(1);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return CurrencyCode;
	}
	
	public static BigDecimal getFXRate(String EventKey) {
		BigDecimal ExchangeRate = BigDecimal.ZERO;
		;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstant.FETCH_EXCHANGE_RATE);
			pst.setString(1, EventKey);
			rst = pst.executeQuery();
			while (rst.next()) {
				if (!(rst.getString(1) == null))
					ExchangeRate = rst.getBigDecimal(1);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return ExchangeRate;
	}
	
	public static BigDecimal getSpotRate(String Currency, String ZoneID) {
		BigDecimal ExchangeRate = BigDecimal.ZERO;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstant.FETCH_SPOT_RATE);
			pst.setString(1, Currency);
			pst.setString(2, ZoneID);
			rst = pst.executeQuery();
			while (rst.next())
				ExchangeRate = rst.getBigDecimal(1);
				ExchangeRate =ExchangeRate.setScale(5, BigDecimal.ROUND_CEILING);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return ExchangeRate;
	}
	
	public static String AccountTypeTranslate(String AccountType) {

		String NewAccountType = null;

		if (AccountType.equalsIgnoreCase("SA")) {
			NewAccountType = "SV";
		} else if (AccountType.equalsIgnoreCase("CA")) {
			NewAccountType = "DD";
		} else {
			NewAccountType = "GL";
		}

		return NewAccountType;
	}

}
