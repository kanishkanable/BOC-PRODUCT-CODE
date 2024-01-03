package com.encore.extracts.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.encore.extracts.utility.CommonMethods;
import com.encore.extracts.utility.DBConnectionUtility;
import com.encore.extracts.utility.PropertyUtility;
import com.encore.extracts.vo.CustomerVO;

public class CustDAO {

	private static Logger logger = Logger.getLogger(CustDAO.class.getName());

	static CustDAO custDAO;

	public static CustDAO getDAO() {
		if (custDAO == null) {
			custDAO = new CustDAO();
		}
		return custDAO;
	}

	public ArrayList<CustomerVO> getCustomerDetails(CustomerVO custVO) {
		logger.info("Process started - Getting customer details");
		ArrayList<CustomerVO> custDetailsList = new ArrayList<CustomerVO>();
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		String custDtlsQuery = null;
		try {
			connection = DBConnectionUtility.getConnection();
			custDtlsQuery = getCustomerDetailsQuery(custVO);
			logger.info("custDtlsQuery --->" + custDtlsQuery);
			prepareStatement = connection.prepareStatement(custDtlsQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				CustomerVO customerVO = new CustomerVO();
				customerVO.setCustMnemonic(resultSet.getString("GFCUS1"));
				customerVO.setCustFullName(resultSet.getString("GFCUN"));
				customerVO.setCustNumber(resultSet.getString("GFCPNC"));
				customerVO.setCustGroup(resultSet.getString("GFGRP"));
				customerVO.setCustCountry(resultSet.getString("GFCNAL"));
				customerVO.setCustAccountOfficer(resultSet.getString("GFACO"));
				customerVO.setCustBlocked(resultSet.getString("GFCUB"));
				customerVO.setCustLocation(resultSet.getString("TICUSTLOC"));
				custDetailsList.add(customerVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, resultSet);
		}
		String sbb = custVO.getSbb();
		String cust_corp = custVO.getCust_corp();
		String correspondent = custVO.getCorrespondent();
		String bank = custVO.getBank();
		String mnemonic = custVO.getMnemonic();
		String fullName = custVO.getFullName();
		String number = custVO.getNumber();
		String location = custVO.getLocation();
		String country = custVO.getCountry();
		String accountOfficer = custVO.getAccountOfficer();
		String mainLocalCustomers = custVO.getMainLocalCustomers();

		logger.debug("sbb :" + sbb);
		logger.debug("cust_corp :" + cust_corp);
		logger.debug("correspondent :" + correspondent);
		logger.debug("bank :" + bank);
		logger.debug("mnemonic :" + mnemonic);
		logger.debug("fullName :" + fullName);
		logger.debug("number :" + number);
		logger.debug("location :" + location);
		logger.debug("country :" + country);
		logger.debug("accountOfficer :" + accountOfficer);
		logger.debug("mainLocalCustomers :" + mainLocalCustomers);
		logger.info("Process completed - Getting customer details");

		return custDetailsList;
	}

	private String getCustomerDetailsQuery(CustomerVO custVO) {
		logger.info("Process started - framing customer details fetch query");
		CommonMethods commonMethods = new CommonMethods();
		Properties aProperties = new Properties();
		String custDtlsQuery = null;
		try {
			aProperties = PropertyUtility.getPropertiesValue();
			String SQL_AND = "AND";
			String SQL_OR = "OR";
			custDtlsQuery = "SELECT a.GFCUS1,a.GFCUN,a.GFCPNC,a.GFGRP,a.GFCNAL,a.GFACO,a.GFCUB,a.GFCUS1_SBB,a.GFCUS1,b.C4CBQ,a.SOURCE,a.TICUSTLOC "
					+ "FROM GFPF  a INNER JOIN C4PF   b ON a.GFCTP1 = b.C4CTP1" + " WHERE( ( ( a.GFCUS1_SBB = '"
					+ aProperties.getProperty("SBB") + "' ) )  AND ( ( ( a.SOURCE = 'h' )";

			if (commonMethods.isNotNull(custVO.getMnemonic()))
				custDtlsQuery += SQL_AND + " ( upper(a.GFCUS1) LIKE '" + custVO.getMnemonic().trim().toUpperCase()
						+ "%') ";
			else
				custDtlsQuery += SQL_AND + " ( 1 = 1 ) ";
			if (commonMethods.isNotNull(custVO.getFullName()))
				custDtlsQuery += SQL_AND + " ( upper(a.GFCUN) LIKE '" + custVO.getFullName().trim().toUpperCase()
						+ "%') ";
			else
				custDtlsQuery += SQL_AND + " ( 1 = 1 ) ";
			if (commonMethods.isNotNull(custVO.getNumber()))
				custDtlsQuery += SQL_AND + " ( upper(a.GFCPNC) LIKE '" + custVO.getNumber().trim().toUpperCase()
						+ "%') ";
			else
				custDtlsQuery += SQL_AND + " ( 1 = 1 ) ";
			if (commonMethods.isNotNull(custVO.getCountry()))
				custDtlsQuery += SQL_AND + " ( upper(a.GFCNAL) LIKE '" + custVO.getCountry().trim().toUpperCase()
						+ "%') ";
			else
				custDtlsQuery += SQL_AND + " ( 1 = 1 ) ";
			if (commonMethods.isNotNull(custVO.getAccountOfficer()))
				custDtlsQuery += SQL_AND + " ( upper(a.GFACO) LIKE '" + custVO.getAccountOfficer().trim().toUpperCase()
						+ "%') ";
			else
				custDtlsQuery += SQL_AND + " ( 1 = 1 ) ";
			if (commonMethods.isNotNull(custVO.getGroup()))
				custDtlsQuery += SQL_AND + " ( upper(a.GFGRP) LIKE '" + custVO.getGroup().trim().toUpperCase() + "%') ";
			else
				custDtlsQuery += SQL_AND + " ( 1 = 1 ) ";
			if (commonMethods.isNotNull(custVO.getBank()) && custVO.getBank().equalsIgnoreCase("TRUE"))
				custDtlsQuery += SQL_AND + "  ( ( ( b.C4CBQ = 'N' ) ";
			else
				custDtlsQuery += SQL_AND + "  ( ( ( b.C4CBQ = '' ) ";
			if (commonMethods.isNotNull(custVO.getCorrespondent())
					&& custVO.getCorrespondent().equalsIgnoreCase("TRUE"))
				custDtlsQuery += SQL_OR + " ( b.C4CBQ = 'B' ) ";
			else
				custDtlsQuery += SQL_OR + " ( b.C4CBQ = '' ) ";
			if (commonMethods.isNotNull(custVO.getCust_corp()) && custVO.getCust_corp().equalsIgnoreCase("TRUE"))
				custDtlsQuery += SQL_OR + " ( ( b.C4CBQ = 'C' ) ) ) )  ";
			else
				custDtlsQuery += SQL_OR + " ( ( b.C4CBQ = '' ) ) ) )  ";
			if (commonMethods.isNotNull(custVO.getLocation()))
				custDtlsQuery += SQL_AND + " ( upper(a.TICUSTLOC) LIKE '" + custVO.getLocation().trim().toUpperCase()
						+ "%' ) ) ) ) ";
			else
				custDtlsQuery += SQL_AND + " ( 1 = 1 ) ) ) ) ";
			custDtlsQuery += " ORDER BY a.GFCUS1 ASC";
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Process completed - framing customer details fetch query");
		return custDtlsQuery;
	}

	public ArrayList<CustomerVO> getCountryList(CustomerVO custVO) {
		ArrayList<CustomerVO> countryList = new ArrayList<CustomerVO>();
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		String countryListQuery = null;
		try {
			connection = DBConnectionUtility.getConnection();
			countryListQuery = getCountryListQuery(custVO);
			prepareStatement = connection.prepareStatement(countryListQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				CustomerVO customerVO = new CustomerVO();
				customerVO.setCntryCode(resultSet.getString("C7CNA"));
				customerVO.setCtryName(resultSet.getString("C7CNM"));
				customerVO.setCtryTenor(resultSet.getString("C7TENOR"));
				customerVO.setIsoCtryCode(resultSet.getString("ISOCNA"));
				countryList.add(customerVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, resultSet);
		}
		return countryList;
	}

	private String getCountryListQuery(CustomerVO custVO) {

		CommonMethods commonMethods = new CommonMethods();
		String countryListQuery = null;
		try {
			if (commonMethods.isNotNull(custVO.getCountryCode()) && commonMethods.isNotNull(custVO.getCountryName()))
				countryListQuery = "SELECT c7cna, c7cnm,  c7tenor, isocna FROM c7pf WHERE c7cna LIKE '"
						+ custVO.getCountryCode().trim().toUpperCase() + "%' and upper(c7cnm) LIKE '"
						+ custVO.getCountryName().trim().toUpperCase() + "%'";
			else if (commonMethods.isNotNull(custVO.getCountryCode()))
				countryListQuery = "SELECT c7cna, c7cnm,  c7tenor, isocna FROM c7pf WHERE c7cna LIKE '"
						+ custVO.getCountryCode().trim().toUpperCase() + "%'";
			else if (commonMethods.isNotNull(custVO.getCountryName()))
				countryListQuery = "SELECT c7cna, c7cnm,  c7tenor, isocna FROM c7pf WHERE upper(c7cnm) LIKE '"
						+ custVO.getCountryName().trim().toUpperCase() + "%'";
			else
				countryListQuery = "SELECT c7cna, c7cnm,  c7tenor, isocna FROM c7pf";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryListQuery;
	}

	public boolean isValidCustomer(String mnemonic) {
		logger.info("Process started - customer vallidation");

		boolean isValidCustomer = false;
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		String validateCustomerQuery = null;
		try {
			connection = DBConnectionUtility.getConnection();
			validateCustomerQuery = "select count(*) as result from gfpf where upper(gfcus1) = '"
					+ mnemonic.trim().toUpperCase() + "'";
			logger.info("validateCustomerQuery --->" + validateCustomerQuery);
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
		logger.info("Process completed - customer vallidation");

		return isValidCustomer;
	}

	public ArrayList<CustomerVO> getCustomerLimitList(CustomerVO custVO) {
		logger.info("Process started - fetching limit nodes of customer");
		ArrayList<CustomerVO> customerLimitList = new ArrayList<CustomerVO>();
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		String customerLimitListQuery = null;
		try {
			connection = DBConnectionUtility.getConnection();
			customerLimitListQuery = getcustomerLimitListQuery(custVO);
			logger.info("customerLimitListQuery --->" + customerLimitListQuery);
			prepareStatement = connection.prepareStatement(customerLimitListQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				CustomerVO customerVO = new CustomerVO();
				customerVO.setLimitCategory(resultSet.getString("CAT_DESC"));
				if (resultSet.getString("AMOUNT") != null) {
					customerVO.setLimitAmount(CommonMethods.convertCommaSeperator(resultSet.getString("AMOUNT")));
				}
				customerVO.setLimitCurrency(resultSet.getString("CURRENCY"));
				customerVO.setLimitExpiry(resultSet.getString("EXPIRY"));
				customerVO.setLimitCategoryID(resultSet.getString("CATEGOERY"));
				System.out.println("Inside the get maker checker");
				try {
					customerVO.setMakerCheckerAction(
							MakerChecker.getMakerCheckerStatus(resultSet.getString("CATEGOERY"), custVO));
				} catch (Exception e) {
					e.printStackTrace();
				}
				customerLimitList.add(customerVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, resultSet);
		}
		logger.info("Process completed - fetching limit nodes of customer");
		return customerLimitList;
	}

	private String getcustomerLimitListQuery(CustomerVO custVO) {
		logger.info("Process started - framing customer limit details fetch query");
		CommonMethods commonMethods = new CommonMethods();
		String customerLimitListQuery = null;
		if (commonMethods.isNotNull(custVO.getMnemonic())) {
			/*
			 * customerLimitListQuery =
			 * "SELECT DLTCATEGORY.DESCRIPTION AS CAT_DESC, DLTLIMITDETAIL.CURRENCY AS CURRENCY,"
			 * +
			 * "ROUND((DLTLIMITDETAIL.AMOUNT/(SELECT POWER(10,C8PF.C8CED) FROM C8PF C8PF WHERE C8PF.C8CCY= DLTLIMITDETAIL.CURRENCY ) ),"
			 * +
			 * " ( SELECT C8PF.C8CED FROM c8pf WHERE C8PF.C8CCY= DLTLIMITDETAIL.CURRENCY ) )  AS AMOUNT,"
			 * +
			 * " DLTLIMITDETAIL.EXPIRES AS EXPIRY, DLTLIMITDETAIL.LCKEY      AS LCKEY,DLTLIMITDETAIL.SDKEY      AS SDKEY , "
			 * +
			 * "TRIM(DLTLIMITCONTROL.DLCUSTOMER) || TRIM(DLTSTRUCTDETAIL.DLSTRUCTURE)|| DLTSTRUCTDETAIL.DLCATEGORY AS CATEGOERY ,"
			 * +
			 * "DLTLIMITCONTROL.DLCUSTOMER AS LMTCUST FROM DLTLIMITCONTROL, DLTLIMITDETAIL,DLTSTRUCTDETAIL, DLTCATEGORY"
			 * + " WHERE  DLTLIMITCONTROL.DLSTRUCTURE  = DLTSTRUCTDETAIL.DLSTRUCTURE AND " +
			 * "DLTLIMITDETAIL.LCKEY = DLTLIMITCONTROL.KEY97 AND " +
			 * "DLTLIMITDETAIL.SDKEY = DLTSTRUCTDETAIL.KEY97 AND " +
			 * "DLTSTRUCTDETAIL.DLCATEGORY = DLTCATEGORY.DLCATEGORY AND " +
			 * "DLTLIMITCONTROL.DLCUSTOMER = '"+custVO.getMnemonic().trim().toUpperCase()
			 * +"' AND  " + "DLTCATEGORY.DLSYSTEM      <> 'Y' AND " +
			 * "DLTSTRUCTDETAIL.ENTRYTYPE = 'C' " +
			 * "ORDER BY DLTSTRUCTDETAIL.SEQUENCER ASC";
			 * 
			 */
			customerLimitListQuery = "SELECT DLTCATEGORY.DESCRIPTION AS CAT_DESC, DLTLIMITDETAIL.CURRENCY AS CURRENCY,"
					+ "CAST(ROUND((DLTLIMITDETAIL.AMOUNT/(SELECT POWER(10,C8PF.C8CED) FROM C8PF C8PF WHERE C8PF.C8CCY= DLTLIMITDETAIL.CURRENCY ) ),2) AS DECIMAL(30,2))  AS AMOUNT,"
					+ " DLTLIMITDETAIL.EXPIRES AS EXPIRY, DLTLIMITDETAIL.LCKEY      AS LCKEY,DLTLIMITDETAIL.SDKEY      AS SDKEY , "
					+ "TRIM(DLTLIMITCONTROL.DLCUSTOMER) || TRIM(DLTSTRUCTDETAIL.DLSTRUCTURE)|| DLTSTRUCTDETAIL.DLCATEGORY AS CATEGOERY ,"
					+ "DLTLIMITCONTROL.DLCUSTOMER AS LMTCUST FROM DLTLIMITCONTROL, DLTLIMITDETAIL,DLTSTRUCTDETAIL, DLTCATEGORY"
					+ " WHERE  DLTLIMITCONTROL.DLSTRUCTURE  = DLTSTRUCTDETAIL.DLSTRUCTURE AND "
					+ "DLTLIMITDETAIL.LCKEY = DLTLIMITCONTROL.KEY97 AND "
					+ "DLTLIMITDETAIL.SDKEY = DLTSTRUCTDETAIL.KEY97 AND "
					+ "DLTSTRUCTDETAIL.DLCATEGORY = DLTCATEGORY.DLCATEGORY AND " + "DLTLIMITCONTROL.DLCUSTOMER = '"
					+ custVO.getMnemonic().trim().toUpperCase() + "' AND  " + "DLTCATEGORY.DLSYSTEM      <> 'Y' AND "
					+ "DLTSTRUCTDETAIL.ENTRYTYPE = 'C' " + "ORDER BY DLTSTRUCTDETAIL.SEQUENCER ASC";
		}
		logger.info("Process completed - framing customer limit details fetch query");

		return customerLimitListQuery;
	}
}
