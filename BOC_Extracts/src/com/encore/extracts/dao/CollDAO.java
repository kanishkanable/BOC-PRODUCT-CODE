package com.encore.extracts.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.io.*;

import org.apache.log4j.Logger;
import com.boc.ett.extracts.sl_customs.ActionConstants;
//import com.boc.ett.extracts.sl_customs.SLCustomVO;
import com.encore.extracts.utility.CommonMethods;
import com.encore.extracts.utility.DBConnectionUtility;
import com.encore.extracts.utility.QueryConstant;
import com.encore.extracts.vo.CollateralVO;
import com.encore.extracts.vo.CustomerVO;

public class CollDAO {

	private static Logger logger = Logger.getLogger(CollDAO.class.getName());

	static CollDAO collDAO;

	public static CollDAO getDAO() {
		if (collDAO == null) {
			collDAO = new CollDAO();
		}
		return collDAO;
	}

	public String attachCollateral(CollateralVO collVO, String tableName, String makerCheckerStatus,
			String isMakerChecker) {
		System.out.println("Attaching collateral details process started");
		String attachResult = "";
		String checkCateroryquery = "";
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		String auditType = "";

		try {
			connection = DBConnectionUtility.getConnection();
			System.out.println("collVO.getCategory()====>" + collVO.getCategory());
			checkCateroryquery = "select count(*) as result from " + tableName + " where upper(category)='"
					+ collVO.getCategory().trim().toUpperCase() + "'";
			System.out.println("Attaching collateral checkCategoryquery" + checkCateroryquery);
			prepareStatement = connection.prepareStatement(checkCateroryquery);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println("Result -->" + resultSet.getInt("RESULT"));
				try {
					if (resultSet.getInt("RESULT") == 0) {
						System.out.println("collateral_details Insertion action started");
						prepareStatement.close();
						resultSet.close();

						attachResult = insertCollateralDetails(collVO, tableName, makerCheckerStatus, isMakerChecker,
								connection, prepareStatement, false);
						try {
							if (isMakerChecker.equalsIgnoreCase("Checker")) {
								insertCollateralDetails(collVO, tableName, makerCheckerStatus, isMakerChecker,
										connection, prepareStatement, true);
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							if (isMakerChecker != null && isMakerChecker.equalsIgnoreCase("Maker")) {
								auditType = "Create";
							} else if (isMakerChecker.equalsIgnoreCase("Checker")) {
								if (makerCheckerStatus.equalsIgnoreCase("Approved")) {
									auditType = makerCheckerStatus;
								} else {
									auditType = makerCheckerStatus;
								}
							}
							MakerChecker.insertAuditTrailLogs(collVO, auditType);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (resultSet.getInt("RESULT") == 1) {
						System.out.println("collateral_details update action started  >> " + isMakerChecker);
						prepareStatement.close();
						resultSet.close();
						attachResult = updateCollateralDetails(collVO, tableName, makerCheckerStatus, isMakerChecker,
								connection, prepareStatement, false);

						try {
							if (isMakerChecker.equalsIgnoreCase("Checker")) {
								updateCollateralDetails(collVO, tableName, makerCheckerStatus, isMakerChecker,
										connection, prepareStatement, true);
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							if (isMakerChecker != null && isMakerChecker.equalsIgnoreCase("Maker")
									&& !makerCheckerStatus.equalsIgnoreCase("Rejected")) {
								auditType = "Modified";
							} else if (isMakerChecker.equalsIgnoreCase("Checker")
									|| makerCheckerStatus.equalsIgnoreCase("Rejected")) {
								if (makerCheckerStatus.equalsIgnoreCase("Approved")) {
									auditType = makerCheckerStatus;
								} else {
									auditType = makerCheckerStatus;
								}
							}
							MakerChecker.insertAuditTrailLogs(collVO, auditType);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						attachResult = "Error Occurred! Please contact system administrator";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("attachResult --> " + attachResult);
		} catch (Exception e) {
			attachResult = "Error Occurred! Please contact system administrator";
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, resultSet);
		}
		System.out.println("Attaching collateral details process completed");
		return attachResult;
	}

	public static String insertCollateralDetails(CollateralVO collVO, String tableName, String makerCheckerStatus,
			String isMakerChecker, Connection connection, PreparedStatement prepareStatement, boolean insertMode) {
		String attachResult = "";
		String insertCollateralQuery = "";
		String auditType = "";
		System.out.println("insertMode " + insertMode);

		if (isMakerChecker != null && isMakerChecker.equalsIgnoreCase("Maker")) {
			insertCollateralQuery = QueryConstant.insertCollateralMakerQuery;
		} else if (isMakerChecker.equalsIgnoreCase("Checker")) {
			insertCollateralQuery = QueryConstant.insertCollateralCheckerQuery;
		}

		if (insertMode) {
			insertCollateralQuery = QueryConstant.insertCollateralQuery;
		}

		try {
			System.out.println("insertCollateralQuery  " + insertCollateralQuery);
			prepareStatement = connection.prepareStatement(insertCollateralQuery);

			prepareStatement.setString(1, collVO.getCollateralNumber());
			prepareStatement.setString(2, collVO.getCollateralDescription());
			prepareStatement.setString(3, collVO.getCollateralType());
			prepareStatement.setString(4, collVO.getSecurityCode());
			prepareStatement.setString(5, collVO.getClaimNature());
			prepareStatement.setString(6, collVO.getLinkNature());
			prepareStatement.setString(7, collVO.getCounterpartyUniqueId());
			prepareStatement.setString(8, collVO.getCollateralCurrency());
			if (collVO.getUnitCount() != null) {
				prepareStatement.setDouble(9, collVO.getUnitCount());
			} else
				prepareStatement.setDouble(9, 0);
			if (collVO.getUnitValue() != null) {
				prepareStatement.setDouble(10, collVO.getUnitValue());
			} else
				prepareStatement.setDouble(10, 0);
			System.out.println("collVO.getOriginalValue()" + collVO.getOriginalValue());
			System.out.println("collVO.getOriginalValue()" + collVO.getOriginalValue());
//			prepareStatement.setString(11, CommonMethods.convertCommaSeperator(collVO.getOriginalValue()));
//			prepareStatement.setString(12, CommonMethods.convertCommaSeperator(collVO.getCurrentValue()));
			prepareStatement.setString(11, collVO.getOriginalValue());
			prepareStatement.setString(12, collVO.getCurrentValue());

			prepareStatement.setString(13, collVO.getIssueDate());
			prepareStatement.setString(14, collVO.getCollateralRevaluationDate());
			prepareStatement.setString(15, collVO.getMaturityDate());
			prepareStatement.setString(16, collVO.getBranchCode());
			prepareStatement.setString(17, collVO.getNatureOfLimit());
			prepareStatement.setString(18, collVO.getNarrative());

			if (isMakerChecker != null && isMakerChecker.equalsIgnoreCase("Maker")) {
				prepareStatement.setString(19, makerCheckerStatus);
				prepareStatement.setString(20, collVO.getCategory());
				auditType = "Create";
			} else if (isMakerChecker.equalsIgnoreCase("Checker")) {
				prepareStatement.setString(19, collVO.getCategory());
				auditType = "Approve";
			}
			prepareStatement.executeUpdate();
			attachResult = "Successfully attached collateral details for the limit " + collVO.getCategoryDescription();
			if (isMakerChecker.equalsIgnoreCase("Checker")) {
				attachResult = updateCollateralDetails(collVO, "COLLATERAL_DETAILS_MAKER_CHECKER", makerCheckerStatus,
						isMakerChecker, connection, prepareStatement, false);
			}
			System.out.println("collateral_details Insertion action completed");
		} catch (Exception e) {
			attachResult = "Error Occurred! Please contact system administrator";
			e.printStackTrace();
		}
		return attachResult;

	}

	public static String updateCollateralDetails(CollateralVO collVO, String tableName, String makerCheckerStatus,
			String isMakerChecker, Connection connection, PreparedStatement prepareStatement, boolean insertMode) {

		String attachResult = "";

		String updateCollateralQuery = "";
		System.out.println("insertMode " + insertMode);
		if (isMakerChecker != null && isMakerChecker.equalsIgnoreCase("Maker")) {
			updateCollateralQuery = QueryConstant.updateCollateralMakerQuery;
		} else if (isMakerChecker.equalsIgnoreCase("Checker")) {
			System.out.println("insertMode ----------------");
			updateCollateralQuery = QueryConstant.updateCollateralCheckerQuery;
		}
		
		if (insertMode) {
			updateCollateralQuery = QueryConstant.updateCollateralQuery;
		}
		System.out.println("updateCollateralQuery>>>>>> " + updateCollateralQuery);
		try {
			prepareStatement = connection.prepareStatement(updateCollateralQuery);
			prepareStatement.setString(1, stringIfNull(collVO.getCollateralNumber()));
			prepareStatement.setString(2, collVO.getCollateralDescription());
			prepareStatement.setString(3, collVO.getCollateralType());
			prepareStatement.setString(4, collVO.getSecurityCode());
			prepareStatement.setString(5, collVO.getClaimNature());
			prepareStatement.setString(6, collVO.getLinkNature());
			prepareStatement.setString(7, collVO.getCounterpartyUniqueId());
			prepareStatement.setString(8, collVO.getCollateralCurrency());
			prepareStatement.setDouble(9, doubleIfNull(collVO.getUnitCount()));
			prepareStatement.setDouble(10, doubleIfNull(collVO.getUnitValue()));
			System.out.println("Original Amount : " + collVO.getOriginalValue());
//			prepareStatement.setString(11, CommonMethods.convertCommaToDecimal(collVO.getOriginalValue()));
			prepareStatement.setString(11, collVO.getOriginalValue());
			System.out.println("Current Amount : " + collVO.getCurrentValue());
//			prepareStatement.setString(12, CommonMethods.convertCommaToDecimal(collVO.getCurrentValue()));
			prepareStatement.setString(12, collVO.getCurrentValue());
			prepareStatement.setString(13, collVO.getIssueDate());
			prepareStatement.setString(14, collVO.getCollateralRevaluationDate());
			prepareStatement.setString(15, collVO.getMaturityDate());
			prepareStatement.setString(16, collVO.getBranchCode());
			prepareStatement.setString(17, collVO.getNatureOfLimit());
			prepareStatement.setString(18, collVO.getNarrative());

			if (isMakerChecker != null && isMakerChecker.equalsIgnoreCase("Maker")) {
				prepareStatement.setString(19, makerCheckerStatus);
				prepareStatement.setString(20, collVO.getNotes());
				prepareStatement.setString(21, collVO.getCategory());
			} else if (isMakerChecker.equalsIgnoreCase("Checker")) {
				prepareStatement.setString(19, collVO.getCategory());
			}
			System.out.println("updateCollateralQuery -->" + updateCollateralQuery);
			prepareStatement.executeUpdate();
			attachResult = "Successfully updated collateral details for the limit " + collVO.getCategoryDescription();
			System.out.println("collVO.getNotes()>>>> " + collVO.getNotes());
			if (isMakerChecker.equalsIgnoreCase("Checker")) {
				prepareStatement.close();
				updateCollateralDetailsApproveStatus(collVO.getCategory(), collVO.getNotes(),
						"COLLATERAL_DETAILS_MAKER_CHECKER", makerCheckerStatus, connection, prepareStatement);
			}

		} catch (Exception e) {
			attachResult = "Error Occurred! Please contact system administrator";
			e.printStackTrace();
		}

		return attachResult;
	}

	public static void updateCollateralDetailsApproveStatus(String category, String notes, String tableName,
			String makerCheckerStatus, Connection connection, PreparedStatement prepareStatement) {

		String updateMakerApproveQuery = QueryConstant.updateCollateralMakerApproveStatusQuery;

		try {
			prepareStatement = connection.prepareStatement(updateMakerApproveQuery);

			prepareStatement.setString(1, makerCheckerStatus);
			prepareStatement.setString(2, notes);
			prepareStatement.setString(3, category);

			System.out.println("updateCollateralQuery -->" + updateMakerApproveQuery);
			prepareStatement.executeUpdate();
			System.out.println("collateral_details update action completed");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static String stringIfNull(String value) {
		if (value == null) {
			value = "";
		}
		return value;
	}

	public static Double doubleIfNull(Double value) {
		if (value == null) {
			value = 0.0;
		}
		return value;
	}

	public CollateralVO getExistingCollateralDetails(CustomerVO custVO) {
		System.out.println("Fetching details of existing collateral process started");

		CollateralVO collVO = new CollateralVO();
		String category = "";
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			category = custVO.getLimitCategoryID();

			System.out.println("category====>" + category);
			connection = DBConnectionUtility.getConnection();
			String checkCateroryquery = "select count(*) as result from COLLATERAL_DETAILS_MAKER_CHECKER where upper(category)='"
					+ category.trim().toUpperCase() + "'";

			System.out.println("Check category availabity query -->" + checkCateroryquery);
			prepareStatement = connection.prepareStatement(checkCateroryquery);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getInt("RESULT") == 1) {
					prepareStatement.close();
					resultSet.close();
					String fecthCollateralDetailsQuery = "select COLLNUM,COLLDESC,COLLTYPE,SECURITY_CODE,CLAIM_NATURE,"
							+ "LINK_NATURE,COUNTER_PARTY_UNIQUE_ID,CURRENCY,UNIT_COUNT,UNIT_VALUE,ORIGINAL_VALUE,CURRENT_VALUE"
							+ ",ISSUE_DATE,COLLATERAL_REVALUATION_DTAE,MATURITY_DATE,BRANCH_CODE,NATURE_OF_LIMIT,NARRATIVE,MAKERCHECKERACTION,NOTES  FROM COLLATERAL_DETAILS_MAKER_CHECKER "
							+ "where CATEGORY = '" + category.trim().toUpperCase() + "'";
					System.out.println("fecthCollateralDetailsQuery -->" + fecthCollateralDetailsQuery);
					prepareStatement = connection.prepareStatement(fecthCollateralDetailsQuery);
					resultSet = prepareStatement.executeQuery();
					if (resultSet.next()) {
						collVO.setCollateralNumber(resultSet.getString("COLLNUM"));
						collVO.setCollateralDescription(resultSet.getString("COLLDESC"));
						collVO.setCollateralType(resultSet.getString("COLLTYPE"));
						collVO.setSecurityCode(resultSet.getString("SECURITY_CODE"));
						collVO.setClaimNature(resultSet.getString("CLAIM_NATURE"));
						collVO.setLinkNature(resultSet.getString("LINK_NATURE"));
						collVO.setCounterpartyUniqueId(resultSet.getString("COUNTER_PARTY_UNIQUE_ID"));
						collVO.setCollateralCurrency(resultSet.getString("CURRENCY"));
						collVO.setUnitCount(resultSet.getDouble("UNIT_COUNT"));
						collVO.setUnitValue(resultSet.getDouble("UNIT_VALUE"));

//						collVO.setOriginalValue(
//								CommonMethods.convertCommaSeperator(resultSet.getString("ORIGINAL_VALUE")));
						collVO.setOriginalValue(resultSet.getString("ORIGINAL_VALUE"));
//						collVO.setCurrentValue(
//								CommonMethods.convertCommaSeperator(resultSet.getString("CURRENT_VALUE")));
						collVO.setCurrentValue(resultSet.getString("CURRENT_VALUE"));

//						collVO.setOriginalValue(resultSet.getString("ORIGINAL_VALUE"));
//						collVO.setCurrentValue(resultSet.getString("CURRENT_VALUE"));
						if (resultSet.getDate("ISSUE_DATE") != null) {
							collVO.setIssueDate(dateFormat.format(resultSet.getDate("ISSUE_DATE")));
						}

						if (resultSet.getDate("COLLATERAL_REVALUATION_DTAE") != null) {
							collVO.setCollateralRevaluationDate(
									dateFormat.format(resultSet.getDate("COLLATERAL_REVALUATION_DTAE")));
						}

						if (resultSet.getDate("MATURITY_DATE") != null) {
							collVO.setMaturityDate(dateFormat.format(resultSet.getDate("MATURITY_DATE")));
						}

						collVO.setBranchCode(resultSet.getString("BRANCH_CODE"));
						collVO.setNatureOfLimit(resultSet.getString("NATURE_OF_LIMIT"));
						collVO.setNarrative(resultSet.getString("NARRATIVE"));
						collVO.setCollMakerCheckerAction(resultSet.getString("MAKERCHECKERACTION"));
						collVO.setNotes(resultSet.getString("NOTES"));
						collVO.setCustomerID(custVO.getCustMnemonic());

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fetching details of existing collateral process completed" + e.getMessage());
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, resultSet);
		}

		return collVO;
	}

	// Description set
//	public String descDetailsQuery(CustomerVO customerVO) {
//
//		System.out.println("Attaching collateral details process started");
//		CollateralVO collVO = new CollateralVO();
//		String checkCateroryquery = "";
//		Connection connection = null;
//		ResultSet resultSet = null;
//		PreparedStatement prepareStatement = null;
//		String DesSelectionQuery = ActionConstants.codeDetailsQuery += " CODE = '" + customerVO.getCodeType().trim()
//				+ "' ";
//		;
//		try {
//			connection = DBConnectionUtility.getConnection();
//			prepareStatement = connection.prepareStatement(DesSelectionQuery);
//			resultSet = prepareStatement.executeQuery();
//			if (resultSet.next()) {
//				collVO.setCollateralDescription(resultSet.getString(2));
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return DesSelectionQuery;
//	}

	public String codeDetailsQuery(CustomerVO customerVO) {
		CommonMethods commonMethods = null;
		String codeSelectionQuery = ActionConstants.codeDetailsQuery;
		commonMethods = new CommonMethods();
		try {
			if (customerVO != null) {
				if (!commonMethods.isNull(customerVO.getCodeType()) || !commonMethods.isNull(customerVO.getCodeDes())) {
					codeSelectionQuery += " WHERE ";
					if (!commonMethods.isNull(customerVO.getCodeType().trim())) {
						codeSelectionQuery += " CODE = '" + customerVO.getCodeType().trim() + "' ";
					}
					if (!commonMethods.isNull(customerVO.getCodeDes())
							&& !commonMethods.isNull(customerVO.getCodeDes())) {
						codeSelectionQuery += ActionConstants.SQL_AND + " TRIM(UPPER(DESCRIPTION)) LIKE '"
								+ customerVO.getCodeDes().trim().toUpperCase() + "%' ";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codeSelectionQuery;
	}

	public ArrayList<CustomerVO> getExistingCollateralCodeFetchDAO(String codeQuery) {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<CustomerVO> CodeDetailsList = new ArrayList<CustomerVO>();
		// String branchSelectionQuery = codeQuery;
		System.out.println("codeQuery----->" + codeQuery);
		try {
			connection = DBConnectionUtility.getConnection();
			ps = connection.prepareStatement(codeQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				CustomerVO customerVO = new CustomerVO();
				customerVO.setCodeType(rs.getString(1).trim());
				customerVO.setCodeDes(rs.getString(2).trim());
				CodeDetailsList.add(customerVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, ps, rs);
		}
		return CodeDetailsList;
	}
}
