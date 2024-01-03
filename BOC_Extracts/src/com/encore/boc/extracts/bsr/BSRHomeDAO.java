package com.encore.boc.extracts.bsr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.encore.extracts.utility.CommonMethods;
import com.encore.extracts.utility.DBConnectionUtility;
import com.encore.extracts.utility.PropertyUtility;
import com.encore.extracts.vo.CustomerVO;
import com.encore.boc.extracts.bsr.BSRActionConstants;

public class BSRHomeDAO {

	private static Logger logger = Logger.getLogger(BSRHomeDAO.class.getName());

	static BSRHomeDAO aBSRHomeDAO;

	public static BSRHomeDAO getDAO() {
		if (aBSRHomeDAO == null) {
			aBSRHomeDAO = new BSRHomeDAO();
		}
		return aBSRHomeDAO;
	}

	public ArrayList<CustomerVO> getCustomerDetails(CustomerVO custVO) {
		logger.info("Process started - Getting customer details");
		ArrayList<CustomerVO> customerDetailsList = new ArrayList<CustomerVO>();
		Connection aConnection = null;
		PreparedStatement aPreparedStatement = null;
		ResultSet aResultSet = null;
		String fetchCustomerDetailsQuery = null;
		try {
			aConnection = DBConnectionUtility.getConnection();
			fetchCustomerDetailsQuery = getCustomerDetailsQuery(custVO);
			logger.info("Getting customer details query --->"
					+ fetchCustomerDetailsQuery);
			aPreparedStatement = aConnection
					.prepareStatement(fetchCustomerDetailsQuery);
			aResultSet = aPreparedStatement.executeQuery();
			while (aResultSet.next()) {
				CustomerVO customerVO = new CustomerVO();
				customerVO.setCustMnemonic(aResultSet.getString("GFCUS1"));
				customerVO.setCustFullName(aResultSet.getString("GFCUN"));
				customerVO.setCustNumber(aResultSet.getString("GFCPNC"));
				customerVO.setCustGroup(aResultSet.getString("GFGRP"));
				customerVO.setCustCountry(aResultSet.getString("GFCNAL"));
				customerVO.setCustAccountOfficer(aResultSet.getString("GFACO"));
				customerVO.setCustBlocked(aResultSet.getString("GFCUB"));
				customerVO.setCustLocation(aResultSet.getString("TICUSTLOC"));
				customerDetailsList.add(customerVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}

		logger.info("Process completed - Getting customer details");

		return customerDetailsList;
	}

	private String getCustomerDetailsQuery(CustomerVO custVO) {
		logger.info("Process started - framing customer details fetch query");
		CommonMethods aCommonMethods = new CommonMethods();
		Properties aProperties = new Properties();
		String fetchCustomerDetailsQuery = null;
		try {
			aProperties = PropertyUtility.getPropertiesValue();

			String sqlANDOperator = "AND";
			String sqlOROperator = "OR";

			fetchCustomerDetailsQuery = BSRActionConstants.GET_CUSTOMERDETAILS_QUERY
					+ " WHERE( ( ( a.GFCUS1_SBB = '"
					+ aProperties.getProperty("SBB")
					+ "' ) )  AND ( ( ( a.SOURCE = 'h' )";
			;

			if (aCommonMethods.isNotNull(custVO.getMnemonic()))
				fetchCustomerDetailsQuery += sqlANDOperator
						+ " ( upper(a.GFCUS1) LIKE '"
						+ custVO.getMnemonic().trim().toUpperCase() + "%') ";
			else
				fetchCustomerDetailsQuery += sqlANDOperator + " ( 1 = 1 ) ";
			if (aCommonMethods.isNotNull(custVO.getFullName()))
				fetchCustomerDetailsQuery += sqlANDOperator
						+ " ( upper(a.GFCUN) LIKE '"
						+ custVO.getFullName().trim().toUpperCase() + "%') ";
			else
				fetchCustomerDetailsQuery += sqlANDOperator + " ( 1 = 1 ) ";
			if (aCommonMethods.isNotNull(custVO.getNumber()))
				fetchCustomerDetailsQuery += sqlANDOperator
						+ " ( upper(a.GFCPNC) LIKE '"
						+ custVO.getNumber().trim().toUpperCase() + "%') ";
			else
				fetchCustomerDetailsQuery += sqlANDOperator + " ( 1 = 1 ) ";
			if (aCommonMethods.isNotNull(custVO.getCountry()))
				fetchCustomerDetailsQuery += sqlANDOperator
						+ " ( upper(a.GFCNAL) LIKE '"
						+ custVO.getCountry().trim().toUpperCase() + "%') ";
			else
				fetchCustomerDetailsQuery += sqlANDOperator + " ( 1 = 1 ) ";
			if (aCommonMethods.isNotNull(custVO.getAccountOfficer()))
				fetchCustomerDetailsQuery += sqlANDOperator
						+ " ( upper(a.GFACO) LIKE '"
						+ custVO.getAccountOfficer().trim().toUpperCase()
						+ "%') ";
			else
				fetchCustomerDetailsQuery += sqlANDOperator + " ( 1 = 1 ) ";
			if (aCommonMethods.isNotNull(custVO.getGroup()))
				fetchCustomerDetailsQuery += sqlANDOperator
						+ " ( upper(a.GFGRP) LIKE '"
						+ custVO.getGroup().trim().toUpperCase() + "%') ";
			else
				fetchCustomerDetailsQuery += sqlANDOperator + " ( 1 = 1 ) ";
			if (aCommonMethods.isNotNull(custVO.getBank())
					&& custVO.getBank().equalsIgnoreCase("TRUE"))
				fetchCustomerDetailsQuery += sqlANDOperator
						+ "  ( ( ( b.C4CBQ = 'N' ) ";
			else
				fetchCustomerDetailsQuery += sqlANDOperator
						+ "  ( ( ( b.C4CBQ = '' ) ";
			if (aCommonMethods.isNotNull(custVO.getCorrespondent())
					&& custVO.getCorrespondent().equalsIgnoreCase("TRUE"))
				fetchCustomerDetailsQuery += sqlOROperator
						+ " ( b.C4CBQ = 'B' ) ";
			else
				fetchCustomerDetailsQuery += sqlOROperator
						+ " ( b.C4CBQ = '' ) ";
			if (aCommonMethods.isNotNull(custVO.getCust_corp())
					&& custVO.getCust_corp().equalsIgnoreCase("TRUE"))
				fetchCustomerDetailsQuery += sqlOROperator
						+ " ( ( b.C4CBQ = 'C' ) ) ) )  ";
			else
				fetchCustomerDetailsQuery += sqlOROperator
						+ " ( ( b.C4CBQ = '' ) ) ) )  ";
			if (aCommonMethods.isNotNull(custVO.getLocation()))
				fetchCustomerDetailsQuery += sqlANDOperator
						+ " ( upper(a.TICUSTLOC) LIKE '"
						+ custVO.getLocation().trim().toUpperCase()
						+ "%' ) ) ) ) ";
			else
				fetchCustomerDetailsQuery += sqlANDOperator
						+ " ( 1 = 1 ) ) ) ) ";
			fetchCustomerDetailsQuery += " ORDER BY a.GFCUS1 ASC";
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Process completed - framing customer details fetch query");
		return fetchCustomerDetailsQuery;
	}

	public boolean isValidCustomer(String mnemonic) {
		logger.info("Process started - customer validation");
		boolean isValidCustomer = false;
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		String validateCustomerQuery = null;
		try {
			aConnection = DBConnectionUtility.getConnection();

			validateCustomerQuery = BSRActionConstants.GET_CUSTOMERDETAILSCOUNT_QUERY;

			logger.info("Getting customer count Query --->"
					+ validateCustomerQuery);

			aPreparedStatement = aConnection
					.prepareStatement(validateCustomerQuery);
			aPreparedStatement.setString(1, mnemonic.trim().toUpperCase());
			aResultSet = aPreparedStatement.executeQuery();

			if (aResultSet.next()) {
				if (aResultSet.getInt("RESULT") == 1)
					isValidCustomer = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}
		logger.info("Process completed - customer validation");

		return isValidCustomer;
	}

	public ArrayList<CustomerVO> getCustomerLimitList(CustomerVO custVO) {
		logger.info("Process started - fetching limit nodes of customer");
		ArrayList<CustomerVO> customerLimitList = new ArrayList<CustomerVO>();
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		String fetchCustomerLimitListQuery = null;
		try {
			aConnection = DBConnectionUtility.getConnection();
			fetchCustomerLimitListQuery = BSRActionConstants.GET_CUSTOMERLIMIT_QUERY;
			logger.info("Getting customer Limit List Query --->"
					+ fetchCustomerLimitListQuery);

			aPreparedStatement = aConnection
					.prepareStatement(fetchCustomerLimitListQuery);
			aPreparedStatement.setString(1, custVO.getMnemonic().trim()
					.toUpperCase());
			aResultSet = aPreparedStatement.executeQuery();

			while (aResultSet.next()) {
				CustomerVO customerVO = new CustomerVO();
				customerVO.setLimitCategory(aResultSet.getString("CAT_DESC"));
				customerVO.setLimitAmount(aResultSet.getString("AMOUNT"));
				customerVO.setLimitCurrency(aResultSet.getString("CURRENCY"));
				customerVO.setLimitExpiry(aResultSet.getString("EXPIRY"));
				customerVO
						.setLimitCategoryID(aResultSet.getString("CATEGOERY"));
				customerLimitList.add(customerVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}
		logger.info("Process completed - fetching limit nodes of customer");
		return customerLimitList;
	}

	public String submitBSRDetails(BSRVO bsrVo) {
		logger.info("Attaching BSR details process started");
		String attachBSRDetailsResult = "";
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		int checkCategoryInDBResult = 0;

		try {
			aConnection = DBConnectionUtility.getConnection();

			checkCategoryInDBResult = checkCategoryInDB(bsrVo.getCategory());

			if (checkCategoryInDBResult == 0) {
				logger.info("BSR details Insertion action started");

				String insertBSRDetailsQuery = BSRActionConstants.GET_BSRDETAILS_INSERTION_QUERY;

				aPreparedStatement = aConnection
						.prepareStatement(insertBSRDetailsQuery);

				aPreparedStatement.setString(1,
						bsrVo.getPlaceUtilisationCreditDistCode());
				aPreparedStatement.setString(2,
						bsrVo.getPlaceUtilisationCreditPopGrpCode());
				aPreparedStatement.setString(3, bsrVo.getOrganisationCode());
				aPreparedStatement.setString(4, bsrVo.getOccupationCode());
				aPreparedStatement
						.setString(5, bsrVo.getCategoryBorrowerCode());
				aPreparedStatement.setString(6,
						bsrVo.getAssetClassiBorrowalAccoutCode());
				aPreparedStatement.setString(7,
						bsrVo.getSecuredUnsecuredLoanCode());
				aPreparedStatement.setString(8, bsrVo.getCategory());

				logger.info("insertBSRQuery -->" + insertBSRDetailsQuery);

				aPreparedStatement.executeUpdate();

				attachBSRDetailsResult = "Successfully attached BSR details for the limit "
						+ bsrVo.getCategoryDescription();

				logger.info("BSR details Insertion action completed");

			} else if (checkCategoryInDBResult == 1) {

				logger.info("BSR details update action started");

				String updateBSRDetailsQuery = BSRActionConstants.GET_BSRDETAILS_UPDATION_QUERY;

				aPreparedStatement = aConnection
						.prepareStatement(updateBSRDetailsQuery);

				aPreparedStatement.setString(1,
						bsrVo.getPlaceUtilisationCreditDistCode());
				aPreparedStatement.setString(2,
						bsrVo.getPlaceUtilisationCreditPopGrpCode());
				aPreparedStatement.setString(3, bsrVo.getOrganisationCode());
				aPreparedStatement.setString(4, bsrVo.getOccupationCode());
				aPreparedStatement
						.setString(5, bsrVo.getCategoryBorrowerCode());
				aPreparedStatement.setString(6,
						bsrVo.getAssetClassiBorrowalAccoutCode());
				aPreparedStatement.setString(7,
						bsrVo.getSecuredUnsecuredLoanCode());
				aPreparedStatement.setString(8, bsrVo.getCategory());

				logger.info("Getting update BSR Details Query -->"
						+ updateBSRDetailsQuery);

				aPreparedStatement.executeUpdate();

				attachBSRDetailsResult = "Successfully updated BSR details for the limit "
						+ bsrVo.getCategoryDescription();

				logger.info("BSR_details update action completed");
			} else {
				attachBSRDetailsResult = "Error Occurred! Please contact system administrator";
			}

			logger.info("Attach BSR Details Result --> "
					+ attachBSRDetailsResult);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}
		logger.info("Attaching BSR details process completed");
		return attachBSRDetailsResult;
	}

	public String deleteBSRDetails(BSRVO bsrVo) {
		logger.info("Deleting BSR details process started");
		String deleteBSRDetailsResult = "";
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		int checkCategoryInDBResult = 0;

		try {
			aConnection = DBConnectionUtility.getConnection();

			checkCategoryInDBResult = checkCategoryInDB(bsrVo.getCategory());

			if (checkCategoryInDBResult == 1) {

				logger.info("BSR details delete action started");

				String deleteBSRDetailsQuery = BSRActionConstants.GET_BSRDETAILS_DELETION_QUERY;

				aPreparedStatement = aConnection
						.prepareStatement(deleteBSRDetailsQuery);

				aPreparedStatement.setString(1, bsrVo.getCategory());

				logger.info("Delete BSR Details Query -->"
						+ deleteBSRDetailsQuery);

				aPreparedStatement.executeUpdate();

				deleteBSRDetailsResult = "Successfully deleted BSR details for the limit "
						+ bsrVo.getCategoryDescription();

				logger.info("BSR_details delete action completed");
			} else {
				deleteBSRDetailsResult = "Error Occurred! Please contact system administrator";
			}

			logger.info("Delete BSR Details Result --> "
					+ deleteBSRDetailsResult);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}
		logger.info("Deleting BSR details process completed");
		return deleteBSRDetailsResult;
	}

	public int checkCategoryInDB(String category) {
		int countResult = 0;
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		try {
			aConnection = DBConnectionUtility.getConnection();

			String bsrDetailsCountQuery = BSRActionConstants.GET_BSRDETAILS_COUNT_QUERY;

			aPreparedStatement = aConnection
					.prepareStatement(bsrDetailsCountQuery);
			aPreparedStatement.setString(1, category.trim().toUpperCase());
			aResultSet = aPreparedStatement.executeQuery();

			while (aResultSet.next()) {
				countResult = aResultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}
		return countResult;
	}

	public BSRVO getExistingBSRDetails(CustomerVO custVO) {
		logger.info("Fetching details of existing BSR process started");

		BSRVO bsrVo = new BSRVO();
		String category = "";
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		int checkCategoryInDBResult = 0;
		try {

			aConnection = DBConnectionUtility.getConnection();
			category = custVO.getLimitCategoryID();

			checkCategoryInDBResult = checkCategoryInDB(category);

			if (checkCategoryInDBResult == 1) {

				bsrVo.setIsBsrDetailsAvailable("Yes");

				String fecthBSRDetailsQuery = BSRActionConstants.GET_BSRDETAILS_QUERY;

				logger.info("Get BSR Details Query -->" + fecthBSRDetailsQuery);

				aPreparedStatement = aConnection
						.prepareStatement(fecthBSRDetailsQuery);
				aPreparedStatement.setString(1, category.trim().toUpperCase());
				aResultSet = aPreparedStatement.executeQuery();

				if (aResultSet.next()) {
					bsrVo.setPlaceUtilisationCreditDistCode(aResultSet
							.getString("PLACE_UTILISATION_CREDIT_DISTCODE"));
					bsrVo.setPlaceUtilisationCreditPopGrpCode(aResultSet
							.getString("PLACE_UTILISATION_CREDIT_POPGRPCODE"));
					bsrVo.setOrganisationCode(aResultSet
							.getString("ORGANISATION_CODE"));
					bsrVo.setOccupationCode(aResultSet
							.getString("OCCUPATION_CODE"));
					bsrVo.setCategoryBorrowerCode(aResultSet
							.getString("CATEGORY_BORROWER_CODE"));
					bsrVo.setAssetClassiBorrowalAccoutCode(aResultSet
							.getString("ASSET_CLASSI_BORROWAL_ACCOUNT_CODE"));
					bsrVo.setSecuredUnsecuredLoanCode(aResultSet
							.getString("SECURED_UNSECURED_LOAN_CODE"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}
		logger.info("Fetching details of existing BSR process completed");
		return bsrVo;
	}

	public ArrayList<BSRLookupVO> fetchPopulationGroupDetails(
			BSRLookupVO bsrLookupVo) {
		ArrayList<BSRLookupVO> populationGroupDetailsList = new ArrayList<BSRLookupVO>();
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		String fetchPopulationGroupDetailsQuery = null;
		try {
			aConnection = DBConnectionUtility.getConnection();

			fetchPopulationGroupDetailsQuery = getPopulationGroupDetailsQuery(bsrLookupVo);

			logger.info("Population Group Query --->"
					+ fetchPopulationGroupDetailsQuery);

			aPreparedStatement = aConnection
					.prepareStatement(fetchPopulationGroupDetailsQuery);
			aResultSet = aPreparedStatement.executeQuery();

			while (aResultSet.next()) {
				BSRLookupVO absrLookupVo = new BSRLookupVO();
				absrLookupVo
						.setPopulationGroupofCentre(aResultSet.getString(1));
				absrLookupVo.setPopulationofCentre(aResultSet.getString(2));
				absrLookupVo.setPopulationGroupCode(aResultSet.getString(3));

				populationGroupDetailsList.add(absrLookupVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}

		return populationGroupDetailsList;
	}

	public ArrayList<BSRLookupVO> fetchOrganisationTypeDetails(
			BSRLookupVO bsrLookupVo) {
		ArrayList<BSRLookupVO> organisationTypeList = new ArrayList<BSRLookupVO>();
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		String fetchOrganisationTypeDetailsQuery = null;
		try {
			aConnection = DBConnectionUtility.getConnection();

			fetchOrganisationTypeDetailsQuery = getOrganisationTypeDetailsQuery(bsrLookupVo);

			logger.info("Organisation Code Details query --->"
					+ fetchOrganisationTypeDetailsQuery);

			aPreparedStatement = aConnection
					.prepareStatement(fetchOrganisationTypeDetailsQuery);
			aResultSet = aPreparedStatement.executeQuery();

			while (aResultSet.next()) {
				BSRLookupVO absrLookupVo = new BSRLookupVO();
				absrLookupVo.setOrganisationType(aResultSet.getString(1));
				absrLookupVo.setOrganisationCode(aResultSet.getString(2));

				organisationTypeList.add(absrLookupVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}

		return organisationTypeList;
	}

	public ArrayList<BSRLookupVO> fetchDistrictDetails(BSRLookupVO bsrLookupVo) {
		ArrayList<BSRLookupVO> districtDetailsList = new ArrayList<BSRLookupVO>();
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		String fetchDistrictDetailsQuery = null;
		try {
			aConnection = DBConnectionUtility.getConnection();

			fetchDistrictDetailsQuery = getDistrictDetailsQuery(bsrLookupVo);

			logger.info("District Details Query --->"
					+ fetchDistrictDetailsQuery);

			aPreparedStatement = aConnection
					.prepareStatement(fetchDistrictDetailsQuery);
			aResultSet = aPreparedStatement.executeQuery();

			while (aResultSet.next()) {
				BSRLookupVO absrLookupVo = new BSRLookupVO();
				absrLookupVo.setDistrictName(aResultSet.getString(1));
				absrLookupVo.setDistrictCode(aResultSet.getString(2));

				districtDetailsList.add(absrLookupVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}

		return districtDetailsList;
	}

	public ArrayList<BSRLookupVO> fetchSecUnsecLoanCodeDetails(
			BSRLookupVO bsrLookupVo) {
		ArrayList<BSRLookupVO> secUnsecLoanCodeDetailsList = new ArrayList<BSRLookupVO>();
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		String fetchSecUnsecLoanCodeDetailsQuery = null;
		try {
			aConnection = DBConnectionUtility.getConnection();

			fetchSecUnsecLoanCodeDetailsQuery = getSecUnsecLoanCodeDetailsQuery(bsrLookupVo);

			logger.info("Secured Un-Secured Loan Code Details Query --->"
					+ fetchSecUnsecLoanCodeDetailsQuery);

			aPreparedStatement = aConnection
					.prepareStatement(fetchSecUnsecLoanCodeDetailsQuery);
			aResultSet = aPreparedStatement.executeQuery();

			while (aResultSet.next()) {
				BSRLookupVO absrLookupVo = new BSRLookupVO();
				absrLookupVo.setSecurityType(aResultSet.getString(1));
				absrLookupVo.setSecurityTypeCode(aResultSet.getString(2));

				secUnsecLoanCodeDetailsList.add(absrLookupVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}

		return secUnsecLoanCodeDetailsList;
	}

	public ArrayList<BSRLookupVO> fetchAssetClassBorrAccountDetails(
			BSRLookupVO bsrLookupVo) {
		ArrayList<BSRLookupVO> assetClassBorrAccountDetailsList = new ArrayList<BSRLookupVO>();
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		String fetchAssetClassBorrAccountDetailsQuery = null;
		try {
			aConnection = DBConnectionUtility.getConnection();

			fetchAssetClassBorrAccountDetailsQuery = getAssetClassBorrAccountDetailsQuery(bsrLookupVo);

			logger.info("Asset Classification of Borrowal Account Code Details Query --->"
					+ fetchAssetClassBorrAccountDetailsQuery);

			aPreparedStatement = aConnection
					.prepareStatement(fetchAssetClassBorrAccountDetailsQuery);
			aResultSet = aPreparedStatement.executeQuery();

			while (aResultSet.next()) {
				BSRLookupVO absrLookupVo = new BSRLookupVO();
				absrLookupVo.setAssetClassBorrAccount(aResultSet.getString(1));
				absrLookupVo.setAssetClassBorrAccountCode(aResultSet
						.getString(2));

				assetClassBorrAccountDetailsList.add(absrLookupVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}

		return assetClassBorrAccountDetailsList;
	}

	public ArrayList<BSRLookupVO> fetchCategoryBorrowerCodeDetails(
			BSRLookupVO bsrLookupVo) {
		ArrayList<BSRLookupVO> categoryBorrowerCodeList = new ArrayList<BSRLookupVO>();
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		String fetchCategoryBorrowerDetailsQuery = null;
		try {
			aConnection = DBConnectionUtility.getConnection();

			fetchCategoryBorrowerDetailsQuery = getCategoryBorrowerCodeDetailsQuery(bsrLookupVo);

			logger.info("Category of Borrower Code Details Query --->"
					+ fetchCategoryBorrowerDetailsQuery);

			aPreparedStatement = aConnection
					.prepareStatement(fetchCategoryBorrowerDetailsQuery);
			aResultSet = aPreparedStatement.executeQuery();

			while (aResultSet.next()) {
				BSRLookupVO absrLookupVo = new BSRLookupVO();
				absrLookupVo.setCategoryBorrower(aResultSet.getString(1));
				absrLookupVo.setCategoryBorrowerCode(aResultSet.getString(2));

				categoryBorrowerCodeList.add(absrLookupVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}

		return categoryBorrowerCodeList;
	}

	public ArrayList<BSRLookupVO> fetchOccupationDetails(BSRLookupVO bsrLookupVo) {
		ArrayList<BSRLookupVO> occupationList = new ArrayList<BSRLookupVO>();
		Connection aConnection = null;
		ResultSet aResultSet = null;
		PreparedStatement aPreparedStatement = null;
		String fetchOccupationDetailsQuery = null;
		try {
			aConnection = DBConnectionUtility.getConnection();

			fetchOccupationDetailsQuery = getOccupationDetailsQuery(bsrLookupVo);

			logger.info("Occupation Code Details Query --->"
					+ fetchOccupationDetailsQuery);

			aPreparedStatement = aConnection
					.prepareStatement(fetchOccupationDetailsQuery);
			aResultSet = aPreparedStatement.executeQuery();

			while (aResultSet.next()) {
				BSRLookupVO absrLookupVo = new BSRLookupVO();
				absrLookupVo.setOccupationGroup(aResultSet.getString(1));
				absrLookupVo.setOccupationSubgroup(aResultSet.getString(2));
				absrLookupVo.setOccupationCode(aResultSet.getString(3));
				absrLookupVo.setOccupationDescription(aResultSet.getString(4));

				occupationList.add(absrLookupVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, aPreparedStatement,
					aResultSet);
		}

		return occupationList;
	}

	private String getPopulationGroupDetailsQuery(BSRLookupVO bsrLookupVo) {
		logger.info("Process started - framing Population group details fetch query");
		CommonMethods aCommonMethods = new CommonMethods();
		String fetchPopulationGroupDetailsQuery = null;
		try {

			fetchPopulationGroupDetailsQuery = BSRActionConstants.GET_POPULATIONGROUPDETAILS_QUERY;

			if (!aCommonMethods.isNull(bsrLookupVo
					.getPopulationGroupofCentreFilter())) {

				fetchPopulationGroupDetailsQuery += " AND"
						+ " UPPER(POPULATIONGROUPOFCENTRE) LIKE '%"
						+ bsrLookupVo.getPopulationGroupofCentreFilter().trim()
								.toUpperCase() + "%'";

			}

			if (!aCommonMethods.isNull(bsrLookupVo
					.getPopulationofCentreFilter())) {

				fetchPopulationGroupDetailsQuery += " AND"
						+ " UPPER(POPULATIONOFCENTRE) LIKE '%"
						+ bsrLookupVo.getPopulationofCentreFilter().trim()
								.toUpperCase() + "%'";

			}

			if (!aCommonMethods.isNull(bsrLookupVo
					.getPopulationGroupCodeFilter())) {

				fetchPopulationGroupDetailsQuery += " AND"
						+ " UPPER(POPULATIONGROUPCODE) LIKE '%"
						+ bsrLookupVo.getPopulationGroupCodeFilter().trim()
								.toUpperCase() + "%'";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Process completed - framing framing Population group details fetch query");
		return fetchPopulationGroupDetailsQuery;
	}

	private String getDistrictDetailsQuery(BSRLookupVO bsrLookupVo) {
		logger.info("Process started - framing district details fetch query");
		CommonMethods aCommonMethods = new CommonMethods();
		String fetchDistrictDetailsQuery = null;
		try {

			fetchDistrictDetailsQuery = BSRActionConstants.GET_DISTRICTDETAILS_QUERY;

			if (!aCommonMethods.isNull(bsrLookupVo.getDistrictNameFilter())) {

				fetchDistrictDetailsQuery += " AND"
						+ " UPPER(DISTRICTNAME) LIKE '%"
						+ bsrLookupVo.getDistrictNameFilter().trim()
								.toUpperCase() + "%'";

			}

			if (!aCommonMethods.isNull(bsrLookupVo.getDistrictCodeFilter())) {

				fetchDistrictDetailsQuery += " AND"
						+ " UPPER(DISTRICTCODE) LIKE '%"
						+ bsrLookupVo.getDistrictCodeFilter().trim()
								.toUpperCase() + "%'";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Process completed - framing district details fetch query");
		return fetchDistrictDetailsQuery;
	}

	private String getSecUnsecLoanCodeDetailsQuery(BSRLookupVO bsrLookupVo) {
		CommonMethods aCommonMethods = new CommonMethods();
		String fetchSecUnsecLoanCodeDetailsQuery = null;
		try {

			fetchSecUnsecLoanCodeDetailsQuery = BSRActionConstants.GET_SECUNSECLOANCODEDETAILS_QUERY;

			if (!aCommonMethods.isNull(bsrLookupVo.getSecurityTypeFilter())) {

				fetchSecUnsecLoanCodeDetailsQuery += " AND"
						+ " UPPER(SECURITY_TYPE) LIKE '%"
						+ bsrLookupVo.getSecurityTypeFilter().trim()
								.toUpperCase() + "%'";

			}

			if (!aCommonMethods.isNull(bsrLookupVo.getSecurityTypeCodeFilter())) {

				fetchSecUnsecLoanCodeDetailsQuery += " AND"
						+ " UPPER(SECURITY_TYPE_CODE) LIKE '%"
						+ bsrLookupVo.getSecurityTypeCodeFilter().trim()
								.toUpperCase() + "%'";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Process completed - framing SecUnsecLoanCode details fetch query");
		return fetchSecUnsecLoanCodeDetailsQuery;
	}

	private String getAssetClassBorrAccountDetailsQuery(BSRLookupVO bsrLookupVo) {
		logger.info("Process started - framing AssetClassBorrAccountDetails fetch query");
		CommonMethods aCommonMethods = new CommonMethods();
		String fetchAssetClassBorrAccountDetailsQuery = null;
		try {

			fetchAssetClassBorrAccountDetailsQuery = BSRActionConstants.GET_ASSETCLASSBORRACCOUNTDETAILS_QUERY;

			if (!aCommonMethods.isNull(bsrLookupVo
					.getAssetClassBorrAccountFilter())) {

				fetchAssetClassBorrAccountDetailsQuery += " AND"
						+ " UPPER(ASSET_CLASSI_BORR_ACCT) LIKE '%"
						+ bsrLookupVo.getAssetClassBorrAccountFilter().trim()
								.toUpperCase() + "%'";

			}

			if (!aCommonMethods.isNull(bsrLookupVo
					.getAssetClassBorrAccountCodeFilter())) {

				fetchAssetClassBorrAccountDetailsQuery += " AND"
						+ " UPPER(ASSET_CLASSI_BORR_ACCT_CODE) LIKE '%"
						+ bsrLookupVo.getAssetClassBorrAccountCodeFilter()
								.trim().toUpperCase() + "%'";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Process completed - framing AssetClassBorrAccount details fetch query");
		return fetchAssetClassBorrAccountDetailsQuery;
	}

	private String getCategoryBorrowerCodeDetailsQuery(BSRLookupVO bsrLookupVo) {
		logger.info("Process started - framing categoryBorrowerCode details fetch query");
		CommonMethods aCommonMethods = new CommonMethods();
		String fetchCategoryBorrowerCodeDetailsQuery = null;
		try {

			fetchCategoryBorrowerCodeDetailsQuery = BSRActionConstants.GET_CATEGORYBORROWERCODEDETAILS_QUERY;

			if (!aCommonMethods.isNull(bsrLookupVo.getCategoryBorrowerFilter())) {

				fetchCategoryBorrowerCodeDetailsQuery += " AND"
						+ " UPPER(CATEGORY_BORROWER) LIKE '%"
						+ bsrLookupVo.getCategoryBorrowerFilter().trim()
								.toUpperCase() + "%'";

			}

			if (!aCommonMethods.isNull(bsrLookupVo
					.getCategoryBorrowerCodeFilter())) {

				fetchCategoryBorrowerCodeDetailsQuery += " AND"
						+ " UPPER(CATEGORY_BORROWER_CODE) LIKE '%"
						+ bsrLookupVo.getCategoryBorrowerCodeFilter().trim()
								.toUpperCase() + "%'";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Process completed - framing categoryBorrowerCode details fetch query");
		return fetchCategoryBorrowerCodeDetailsQuery;
	}

	private String getOccupationDetailsQuery(BSRLookupVO bsrLookupVo) {
		logger.info("Process started - framing occupation details fetch query");
		CommonMethods aCommonMethods = new CommonMethods();
		String fetchOccupationDetailsQuery = null;
		try {

			fetchOccupationDetailsQuery = BSRActionConstants.GET_GETOCCUPATIONDETAILS_QUERY;

			if (!aCommonMethods.isNull(bsrLookupVo.getOccupationGroupFilter())) {

				fetchOccupationDetailsQuery += " AND"
						+ " UPPER(OCCUPATIONGROUP) LIKE '%"
						+ bsrLookupVo.getOccupationGroupFilter().trim()
								.toUpperCase() + "%'";

			}

			if (!aCommonMethods.isNull(bsrLookupVo
					.getOccupationSubgroupFilter())) {

				fetchOccupationDetailsQuery += " AND"
						+ " UPPER(OCCUPATIONSUBGROUP) LIKE '%"
						+ bsrLookupVo.getOccupationSubgroupFilter().trim()
								.toUpperCase() + "%'";

			}

			if (!aCommonMethods.isNull(bsrLookupVo.getOccupationCodeFilter())) {

				fetchOccupationDetailsQuery += " AND"
						+ " UPPER(OCCUPATIONCODE) LIKE '%"
						+ bsrLookupVo.getOccupationCodeFilter().trim()
								.toUpperCase() + "%'";

			}

			if (!aCommonMethods.isNull(bsrLookupVo
					.getOccupationDescriptionFilter())) {

				fetchOccupationDetailsQuery += " AND"
						+ " UPPER(OCCUPATIONDESCRIPTION) LIKE '%"
						+ bsrLookupVo.getOccupationDescriptionFilter().trim()
								.toUpperCase() + "%'";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Process completed - framing occupation details fetch query");
		return fetchOccupationDetailsQuery;
	}

	private String getOrganisationTypeDetailsQuery(BSRLookupVO bsrLookupVo) {
		logger.info("Process started - framing organisation type details fetch query");
		CommonMethods aCommonMethods = new CommonMethods();
		String fetchOrganisationTypeDetailsQuery = null;
		try {

			fetchOrganisationTypeDetailsQuery = BSRActionConstants.GET_ORGANISATIONTYPEDETAILS_QUERY;

			if (!aCommonMethods.isNull(bsrLookupVo.getOrganisationTypeFilter())) {

				fetchOrganisationTypeDetailsQuery += " AND"
						+ " UPPER(TYPEOFORGANISATION) LIKE '%"
						+ bsrLookupVo.getOrganisationTypeFilter().trim()
								.toUpperCase() + "%'";

			}

			if (!aCommonMethods.isNull(bsrLookupVo.getOrganisationCodeFilter())) {

				fetchOrganisationTypeDetailsQuery += " AND"
						+ " UPPER(ORGANISATIONCODE) LIKE '%"
						+ bsrLookupVo.getOrganisationCodeFilter().trim()
								.toUpperCase() + "%'";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Process completed - framing organisation type details fetch query");
		return fetchOrganisationTypeDetailsQuery;
	}
}
