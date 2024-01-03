package com.encore.extracts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.boc.ett.extracts.sl_customs.ActionConstants;
import com.encore.extracts.utility.CommonMethods;
import com.encore.extracts.utility.DBConnectionUtility;
import com.encore.extracts.utility.QueryConstant;
import com.encore.extracts.vo.CollateralVO;
import com.encore.extracts.vo.CustomerVO;
import com.encore.extracts.vo.GuaranteeVO;

public class GuaranteeDAO {

	private static Logger logger = Logger.getLogger(GuaranteeDAO.class.getName());

	static GuaranteeDAO guaDAO;

	public static GuaranteeDAO getDAO() {
		if (guaDAO == null) {
			guaDAO = new GuaranteeDAO();
		}
		return guaDAO;
	}

	public String submitGuaranteeAttachment(GuaranteeVO guaVO, String tableName, String makerCheckerStatus,
			String isMakerChecker) {
		System.out.println("Attaching guarantee details process started");
		String attachResult = "";
		String checkCateroryquery = "";
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		String auditType = "";
		try {
			System.out.println("Attaching -->");
			connection = DBConnectionUtility.getConnection();
			System.out.println("guaVO.getCategory()====>" + guaVO.getCategory());
			checkCateroryquery = "select count(*) as result from " + tableName + " where upper(category)='"
					+ guaVO.getCategory().trim().toUpperCase() + "'";
			System.out.println("Attaching guarantee checkCategoryquery" + checkCateroryquery);
			prepareStatement = connection.prepareStatement(checkCateroryquery);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println("Result -->" + resultSet.getInt("RESULT"));
				try {
					if (resultSet.getInt("RESULT") == 0) {
						System.out.println("Process started - INSERT ACTION of submitting guarantee details attached");
						prepareStatement.close();
						resultSet.close();
						attachResult = insertGuaranteeDetails(guaVO, tableName, makerCheckerStatus, isMakerChecker,
								connection, prepareStatement, false);
						try {
							if (isMakerChecker.equalsIgnoreCase("Checker")) {
								attachResult = insertGuaranteeDetails(guaVO, tableName, makerCheckerStatus,
										isMakerChecker, connection, prepareStatement, true);
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
							MakerChecker.insertAuditTrailLogs(guaVO, auditType);
						} catch (Exception e) {
							e.printStackTrace();
						}

					} else if (resultSet.getInt("RESULT") == 1) {
						try {
							System.out.println(
									"Process started - UPDATE ACTION of submitting guarantee details attached");
							prepareStatement.close();
							resultSet.close();
							attachResult = updateGuaranteeDetails(guaVO, tableName, makerCheckerStatus, isMakerChecker,
									connection, prepareStatement, false);
							try {
								if (isMakerChecker.equalsIgnoreCase("Checker")) {
									updateGuaranteeDetails(guaVO, tableName, makerCheckerStatus, isMakerChecker,
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
								MakerChecker.insertAuditTrailLogs(guaVO, auditType);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						catch (Exception e) {
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
		System.out.println("Attaching guarantee details process completed");
		return attachResult;
	}

	public static String insertGuaranteeDetails(GuaranteeVO guaVO, String tableName, String makerCheckerStatus,
			String isMakerChecker, Connection connection, PreparedStatement prepareStatement, boolean insertMode) {
		String attachResult = "";
		String insertGuaranteeQuery = "";
		try {
			if (isMakerChecker != null && isMakerChecker.equalsIgnoreCase("Maker")) {
				insertGuaranteeQuery = QueryConstant.insertGuaranteeMakerQuery;
			} else if (isMakerChecker.equalsIgnoreCase("Checker")) {
				insertGuaranteeQuery = QueryConstant.insertGuaranteeCheckerQuery;
			}

			if (insertMode) {
				insertGuaranteeQuery = QueryConstant.insertGuaranteeQuery;
			}

			prepareStatement = connection.prepareStatement(insertGuaranteeQuery);
			prepareStatement.setString(1, guaVO.getGuaranteeTypeCode());
			prepareStatement.setString(2, guaVO.getGuaranteeNumber());
			prepareStatement.setString(3, guaVO.getGuaranteeDescription());
			prepareStatement.setString(4, guaVO.getCounterpartyUniqueIdentifier());
			prepareStatement.setString(5, guaVO.getProviderUniqueIdentifier());
			prepareStatement.setString(6, guaVO.getGuaranteeNature());
			if (guaVO.getGuaranteeAmount() != null)
//				prepareStatement.setString(7, CommonMethods.convertCommaSeperator(guaVO.getGuaranteeAmount()));
				prepareStatement.setString(7, guaVO.getGuaranteeAmount());
			else
				prepareStatement.setString(7, null);
			if (guaVO.getExposurePercentage() != null)
				prepareStatement.setDouble(8, guaVO.getExposurePercentage());
			else
				prepareStatement.setString(8, null);
			if (guaVO.getExposurePercentageCAP() != null)
				prepareStatement.setDouble(9, guaVO.getExposurePercentageCAP());
			else
				prepareStatement.setString(9, null);
			prepareStatement.setString(10, guaVO.getCurrencyCode());
			prepareStatement.setString(11, guaVO.getIssueDate());
			prepareStatement.setString(12, guaVO.getMaturitydate());
			prepareStatement.setString(13, guaVO.getReceivedDate());
			prepareStatement.setString(14, guaVO.getBranchCode());
			prepareStatement.setString(15, guaVO.getNatureOfLimit());
			prepareStatement.setString(16, guaVO.getNarrative());

			if (isMakerChecker != null && isMakerChecker.equalsIgnoreCase("Maker")) {
				prepareStatement.setString(17, makerCheckerStatus);
				prepareStatement.setString(18, guaVO.getCategory());
			} else if (isMakerChecker.equalsIgnoreCase("Checker")) {
				prepareStatement.setString(17, guaVO.getCategory());
			}

			System.out.println("insertGuaranteeQuery -->" + insertGuaranteeQuery);
			prepareStatement.executeUpdate();
			attachResult = "Successfully attached guarantee details for the limit " + guaVO.getCategoryDescription();
			System.out.println("Process completed - INSERT ACTION of submitting guarantee details attached");
			if (isMakerChecker.equalsIgnoreCase("Checker")) {
				attachResult = updateGuaranteeDetails(guaVO, "GUARANTEE_DETAILS_MAKER_CHECKER", makerCheckerStatus,
						isMakerChecker, connection, prepareStatement,false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attachResult;

	}

	private static String updateGuaranteeDetails(GuaranteeVO guaVO, String string, String makerCheckerStatus,
			String isMakerChecker, Connection connection, PreparedStatement prepareStatement, boolean insertMode) {
		String attachResult = "";

		String updateGuaranteeQuery = "";
		if (isMakerChecker != null && isMakerChecker.equalsIgnoreCase("Maker")) {
			updateGuaranteeQuery = QueryConstant.updateGuaranteeMakerQuery;
		} else if (isMakerChecker.equalsIgnoreCase("Checker")) {
			updateGuaranteeQuery = QueryConstant.updateGuaranteeCheckerQuery;
		}
		
		if (insertMode) {
			updateGuaranteeQuery = QueryConstant.updateGuaranteeQuery;
		}
		try {
			prepareStatement = connection.prepareStatement(updateGuaranteeQuery);
			prepareStatement.setString(1, guaVO.getGuaranteeTypeCode());
			prepareStatement.setString(2, guaVO.getGuaranteeNumber());
			prepareStatement.setString(3, guaVO.getGuaranteeDescription());
			prepareStatement.setString(4, guaVO.getCounterpartyUniqueIdentifier());
			prepareStatement.setString(5, guaVO.getProviderUniqueIdentifier());
			prepareStatement.setString(6, guaVO.getGuaranteeNature());
			if (guaVO.getGuaranteeAmount() != null)
//				prepareStatement.setString(7, CommonMethods.convertCommaSeperator(guaVO.getGuaranteeAmount()));
				prepareStatement.setString(7, guaVO.getGuaranteeAmount());
			else
				prepareStatement.setString(7, null);
			if (guaVO.getExposurePercentage() != null)
				prepareStatement.setDouble(8, guaVO.getExposurePercentage());
			else
				prepareStatement.setString(8, null);
			if (guaVO.getExposurePercentageCAP() != null)
				prepareStatement.setDouble(9, guaVO.getExposurePercentageCAP());
			else
				prepareStatement.setString(9, null);
			prepareStatement.setString(10, guaVO.getCurrencyCode());
			prepareStatement.setString(11, guaVO.getIssueDate());
			prepareStatement.setString(12, guaVO.getMaturitydate());
			prepareStatement.setString(13, guaVO.getReceivedDate());
			prepareStatement.setString(14, guaVO.getBranchCode());
			prepareStatement.setString(15, guaVO.getNatureOfLimit());
			prepareStatement.setString(16, guaVO.getNarrative());

			if (isMakerChecker != null && isMakerChecker.equalsIgnoreCase("Maker")) {
				prepareStatement.setString(17, makerCheckerStatus);
				prepareStatement.setString(18, guaVO.getNotes());
				prepareStatement.setString(19, guaVO.getCategory());
			} else if (isMakerChecker.equalsIgnoreCase("Checker")) {
				prepareStatement.setString(17, guaVO.getCategory());
			}
			System.out.println("updateGuaranteeQuery -->" + updateGuaranteeQuery);
			prepareStatement.executeUpdate();
			attachResult = "Successfully updated guarantee details for the limit " + guaVO.getCategoryDescription();
			System.out.println("guarantee_details update action completed");
			if (isMakerChecker.equalsIgnoreCase("Checker")) {
				prepareStatement.close();
				updateGuaranteeDetailsApproveStatus(guaVO.getCategory(), guaVO.getNotes(),
						"COLLATERAL_DETAILS_MAKER_CHECKER", makerCheckerStatus, connection, prepareStatement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attachResult;
	}

	private static void updateGuaranteeDetailsApproveStatus(String category, String notes, String string,
			String makerCheckerStatus, Connection connection, PreparedStatement prepareStatement) {
		String updateMakerApproveQuery = QueryConstant.updateGuaranteeMakerApproveStatusQuery;

		try {
			prepareStatement = connection.prepareStatement(updateMakerApproveQuery);

			prepareStatement.setString(1, makerCheckerStatus);
			prepareStatement.setString(2, notes);
			prepareStatement.setString(3, category);

			System.out.println("updateGuaranteeQuery -->" + updateMakerApproveQuery);
			prepareStatement.executeUpdate();
			System.out.println("Guarantee update action completed");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public String stringIfNull(String value) {
		if (value == null) {
			value = "";
		}
		return value;
	}

	public Double doubleIfNull(Double value) {
		if (value == null) {
			value = 0.0;
		}
		return value;
	}

	public GuaranteeVO getExistingGuaranteeDetails(CustomerVO custVO) {
		System.out.println("Process started - getting details for existing guarantee");
		GuaranteeVO guaVO = new GuaranteeVO();
		String category = "";
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			category = custVO.getLimitCategoryID();
			connection = DBConnectionUtility.getConnection();
			String checkCateroryquery = "select count(*) as result from GUARANTEE_DETAILS_MAKER_CHECKER where upper(category)='"
					+ category.trim().toUpperCase() + "'";
			prepareStatement = connection.prepareStatement(checkCateroryquery);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getInt("RESULT") == 1) {
					prepareStatement.close();
					resultSet.close();
					String fecthGuaranteeDetailsQuery = "select GUARANTEE_TYPE_CODE,GUARANTEE_NUMBER,GUARANTEE_DESC,COUNTER_PARTY_UNIQUE_ID,"
							+ "PROVIDER_UNIQUE_ID,GUARANTEE_NATURE,GUARANTEE_AMOUNT,EXPOSURE_PERCENTAGE,EXPOSURE_PERCENTAGE_CAP,CURRENCY_CODE,"
							+ "ISSUE_DATE,MATURITY_DATE,RECEIVED_DATE,BRANCH_CODE,NATURE_OF_LIMIT,NARRATIVE,MAKERCHECKERACTION,NOTES  from GUARANTEE_DETAILS_MAKER_CHECKER where CATEGORY = '"
							+ category.trim().toUpperCase() + "'";
					System.out.println("fecthGuaranteeDetailsQuery -->" + fecthGuaranteeDetailsQuery);
					prepareStatement = connection.prepareStatement(fecthGuaranteeDetailsQuery);
					resultSet = prepareStatement.executeQuery();
					if (resultSet.next()) {
						guaVO.setGuaranteeTypeCode(resultSet.getString("GUARANTEE_TYPE_CODE"));
						guaVO.setGuaranteeNumber(resultSet.getString("GUARANTEE_NUMBER"));
						guaVO.setGuaranteeDescription(resultSet.getString("GUARANTEE_DESC"));
						guaVO.setCounterpartyUniqueIdentifier(resultSet.getString("COUNTER_PARTY_UNIQUE_ID"));
						guaVO.setProviderUniqueIdentifier(resultSet.getString("PROVIDER_UNIQUE_ID"));
						guaVO.setGuaranteeNature(resultSet.getString("GUARANTEE_NATURE"));

						if (resultSet.getString("GUARANTEE_AMOUNT") != null) {
//							guaVO.setGuaranteeAmount(
//									CommonMethods.convertCommaSeperator(resultSet.getString("GUARANTEE_AMOUNT")));
							guaVO.setGuaranteeAmount(resultSet.getString("GUARANTEE_AMOUNT"));
						}

//						guaVO.setGuaranteeAmount(resultSet.getString("GUARANTEE_AMOUNT"));
						guaVO.setExposurePercentage(resultSet.getDouble("EXPOSURE_PERCENTAGE"));
						guaVO.setExposurePercentageCAP(resultSet.getDouble("EXPOSURE_PERCENTAGE_CAP"));
						guaVO.setCurrencyCode(resultSet.getString("CURRENCY_CODE"));
						if (resultSet.getDate("ISSUE_DATE") != null) {
							guaVO.setIssueDate(dateFormat.format(resultSet.getDate("ISSUE_DATE")));
						}
						if (resultSet.getDate("MATURITY_DATE") != null) {
							guaVO.setMaturitydate(dateFormat.format(resultSet.getDate("MATURITY_DATE")));
						}
						if (resultSet.getDate("RECEIVED_DATE") != null) {
							guaVO.setReceivedDate(dateFormat.format(resultSet.getDate("RECEIVED_DATE")));
						}
						guaVO.setBranchCode(resultSet.getString("BRANCH_CODE"));
						guaVO.setNatureOfLimit(resultSet.getString("NATURE_OF_LIMIT"));
						guaVO.setNarrative(resultSet.getString("NARRATIVE"));
						if (guaVO.getGuaranteeNature().equalsIgnoreCase("0")) {
							System.out.println("Guarantee Nature-->" + guaVO.getGuaranteeNature());
							guaVO.setValidateGuaranteeNature("0");
						} else if (guaVO.getGuaranteeNature().equalsIgnoreCase("1")) {
							System.out.println("Guarantee Nature-->" + guaVO.getGuaranteeNature());
							guaVO.setValidateGuaranteeNature("1");
						} else if (guaVO.getGuaranteeNature().equalsIgnoreCase("2")) {
							System.out.println("Guarantee Nature-->" + guaVO.getGuaranteeNature());
							guaVO.setValidateGuaranteeNature("2");
						}

						guaVO.setMakerCheckerAction(resultSet.getString("MAKERCHECKERACTION"));
						guaVO.setNotes(resultSet.getString("NOTES"));
						guaVO.setCustomerID(custVO.getCustMnemonic());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, prepareStatement, resultSet);
		}
		System.out.println("Process completed - getting details for existing guarantee");
		return guaVO;
	}

	public String guaranteeDetailsQuery(CustomerVO customerVO) {
		CommonMethods commonMethods = null;
		String guaranteeCodeSelectionQuery = ActionConstants.guaranteeDetailsQuery;
		commonMethods = new CommonMethods();
		try {
			if (customerVO != null) {
				if (!commonMethods.isNull(customerVO.getCodeType()) || !commonMethods.isNull(customerVO.getCodeDes())) {
					guaranteeCodeSelectionQuery += " WHERE ";
					if (!commonMethods.isNull(customerVO.getCodeType().trim())) {
						guaranteeCodeSelectionQuery += " CODE = '" + customerVO.getCodeType().trim() + "' ";
					}
					if (!commonMethods.isNull(customerVO.getCodeDes())
							&& !commonMethods.isNull(customerVO.getCodeDes())) {
						guaranteeCodeSelectionQuery += ActionConstants.SQL_AND + " TRIM(UPPER(DESCRIPTION)) LIKE '"
								+ customerVO.getCodeDes().trim().toUpperCase() + "%' ";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guaranteeCodeSelectionQuery;
	}

	public ArrayList<CustomerVO> getExistingGuaranteeCodeFetchDAO(String codeQuery) {
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<CustomerVO> GuaranteeCodeDetailsList = new ArrayList<CustomerVO>();
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
				GuaranteeCodeDetailsList.add(customerVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, ps, rs);
		}
		return GuaranteeCodeDetailsList;
	}
}
