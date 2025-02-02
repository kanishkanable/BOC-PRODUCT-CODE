package com.encore.extracts.utility;

public class QueryConstant {

	public static String insertCollateralMakerQuery = "insert into  COLLATERAL_DETAILS_MAKER_CHECKER (COLLNUM,COLLDESC,COLLTYPE,SECURITY_CODE,CLAIM_NATURE,"
			+ "LINK_NATURE,COUNTER_PARTY_UNIQUE_ID,CURRENCY,UNIT_COUNT,UNIT_VALUE,ORIGINAL_VALUE,CURRENT_VALUE,ISSUE_DATE,"
			+ "COLLATERAL_REVALUATION_DTAE,MATURITY_DATE,BRANCH_CODE ,NATURE_OF_LIMIT,NARRATIVE,MAKERCHECKERACTION,CATEGORY) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String insertCollateralCheckerQuery = "insert into  COLLATERAL_DETAILS (COLLNUM,COLLDESC,COLLTYPE,SECURITY_CODE,CLAIM_NATURE,"
			+ "LINK_NATURE,COUNTER_PARTY_UNIQUE_ID,CURRENCY,UNIT_COUNT,UNIT_VALUE,ORIGINAL_VALUE,CURRENT_VALUE,ISSUE_DATE,"
			+ "COLLATERAL_REVALUATION_DTAE,MATURITY_DATE,BRANCH_CODE ,NATURE_OF_LIMIT,NARRATIVE,CATEGORY) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String insertCollateralQuery = "insert into  COLLATERAL_DETAILS_STAG (COLLNUM,COLLDESC,COLLTYPE,SECURITY_CODE,CLAIM_NATURE,"
			+ "LINK_NATURE,COUNTER_PARTY_UNIQUE_ID,CURRENCY,UNIT_COUNT,UNIT_VALUE,ORIGINAL_VALUE,CURRENT_VALUE,ISSUE_DATE,"
			+ "COLLATERAL_REVALUATION_DTAE,MATURITY_DATE,BRANCH_CODE ,NATURE_OF_LIMIT,NARRATIVE,CATEGORY) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static String updateCollateralMakerQuery = "UPDATE COLLATERAL_DETAILS_MAKER_CHECKER SET COLLNUM = ? ,	COLLDESC = ? ,	COLLTYPE = ? ,	"
			+ "SECURITY_CODE	= ? , CLAIM_NATURE	= ? , LINK_NATURE	= ? , COUNTER_PARTY_UNIQUE_ID	= ? , "
			+ "CURRENCY	= ? , UNIT_COUNT	= ? , UNIT_VALUE	= ? , ORIGINAL_VALUE	= ? , CURRENT_VALUE	= ? , "
			+ "ISSUE_DATE	= ? , COLLATERAL_REVALUATION_DTAE	= ? , MATURITY_DATE	= ? , BRANCH_CODE	 = ?,NATURE_OF_LIMIT	=?,NARRATIVE	=?, "
			+ "MAKERCHECKERACTION = ?,NOTES = ?  WHERE CATEGORY =? ";
	
	public static String updateCollateralMakerApproveStatusQuery = "UPDATE COLLATERAL_DETAILS_MAKER_CHECKER SET MAKERCHECKERACTION = ?, NOTES = ?  WHERE CATEGORY =? ";
	
	public static String updateCollateralCheckerQuery = "UPDATE COLLATERAL_DETAILS SET COLLNUM = ? ,	COLLDESC = ? ,	COLLTYPE = ? ,	"
			+ "SECURITY_CODE	= ? , CLAIM_NATURE	= ? , LINK_NATURE	= ? , COUNTER_PARTY_UNIQUE_ID	= ? , "
			+ "CURRENCY	= ? , UNIT_COUNT	= ? , UNIT_VALUE	= ? , ORIGINAL_VALUE	= ? , CURRENT_VALUE	= ? , "
			+ "ISSUE_DATE	= ? , COLLATERAL_REVALUATION_DTAE	= ? , MATURITY_DATE	= ? , BRANCH_CODE	 = ?,NATURE_OF_LIMIT	=?,NARRATIVE	=? "
			+ " WHERE CATEGORY =? ";
	public static String updateCollateralQuery = "UPDATE COLLATERAL_DETAILS_STAG SET COLLNUM = ? ,	COLLDESC = ? ,	COLLTYPE = ? ,	"
			+ "SECURITY_CODE	= ? , CLAIM_NATURE	= ? , LINK_NATURE	= ? , COUNTER_PARTY_UNIQUE_ID	= ? , "
			+ "CURRENCY	= ? , UNIT_COUNT	= ? , UNIT_VALUE	= ? , ORIGINAL_VALUE	= ? , CURRENT_VALUE	= ? , "
			+ "ISSUE_DATE	= ? , COLLATERAL_REVALUATION_DTAE	= ? , MATURITY_DATE	= ? , BRANCH_CODE	 = ?,NATURE_OF_LIMIT	=?,NARRATIVE	=? "
			+ " WHERE CATEGORY =? ";

	public static String insertGuaranteeMakerQuery = "insert into GUARANTEE_DETAILS_MAKER_CHECKER ( GUARANTEE_TYPE_CODE,GUARANTEE_NUMBER,GUARANTEE_DESC,"
			+ "COUNTER_PARTY_UNIQUE_ID,PROVIDER_UNIQUE_ID,GUARANTEE_NATURE,GUARANTEE_AMOUNT,EXPOSURE_PERCENTAGE,EXPOSURE_PERCENTAGE_CAP,"
			+ "CURRENCY_CODE,ISSUE_DATE,MATURITY_DATE,RECEIVED_DATE,BRANCH_CODE,NATURE_OF_LIMIT,NARRATIVE,MAKERCHECKERACTION,CATEGORY) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static String insertGuaranteeCheckerQuery = "insert into GUARANTEE_DETAILS ( GUARANTEE_TYPE_CODE,GUARANTEE_NUMBER,GUARANTEE_DESC,"
			+ "COUNTER_PARTY_UNIQUE_ID,PROVIDER_UNIQUE_ID,GUARANTEE_NATURE,GUARANTEE_AMOUNT,EXPOSURE_PERCENTAGE,EXPOSURE_PERCENTAGE_CAP,"
			+ "CURRENCY_CODE,ISSUE_DATE,MATURITY_DATE,RECEIVED_DATE,BRANCH_CODE,NATURE_OF_LIMIT,NARRATIVE,CATEGORY) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String insertGuaranteeQuery = "insert into GUARANTEE_DETAILS_STAG ( GUARANTEE_TYPE_CODE,GUARANTEE_NUMBER,GUARANTEE_DESC,"
			+ "COUNTER_PARTY_UNIQUE_ID,PROVIDER_UNIQUE_ID,GUARANTEE_NATURE,GUARANTEE_AMOUNT,EXPOSURE_PERCENTAGE,EXPOSURE_PERCENTAGE_CAP,"
			+ "CURRENCY_CODE,ISSUE_DATE,MATURITY_DATE,RECEIVED_DATE,BRANCH_CODE,NATURE_OF_LIMIT,NARRATIVE,CATEGORY) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static String updateGuaranteeMakerQuery = "UPDATE GUARANTEE_DETAILS_MAKER_CHECKER SET GUARANTEE_TYPE_CODE = ? ,GUARANTEE_NUMBER = ? ,"
			+ "GUARANTEE_DESC = ? ,COUNTER_PARTY_UNIQUE_ID = ? ,PROVIDER_UNIQUE_ID = ? ,GUARANTEE_NATURE = ? ,"
			+ "GUARANTEE_AMOUNT = ? ,EXPOSURE_PERCENTAGE = ? ,EXPOSURE_PERCENTAGE_CAP = ? ,CURRENCY_CODE = ? ,ISSUE_DATE = ? ,"
			+ "MATURITY_DATE = ? ,RECEIVED_DATE = ? ,BRANCH_CODE = ? ,NATURE_OF_LIMIT	=?,NARRATIVE	=?,MAKERCHECKERACTION = ?,NOTES = ? "
			+ "WHERE CATEGORY = ?";
	public static String updateGuaranteeCheckerQuery = "UPDATE GUARANTEE_DETAILS SET GUARANTEE_TYPE_CODE = ? ,GUARANTEE_NUMBER = ? ,"
			+ "GUARANTEE_DESC = ? ,COUNTER_PARTY_UNIQUE_ID = ? ,PROVIDER_UNIQUE_ID = ? ,GUARANTEE_NATURE = ? ,"
			+ "GUARANTEE_AMOUNT = ? ,EXPOSURE_PERCENTAGE = ? ,EXPOSURE_PERCENTAGE_CAP = ? ,CURRENCY_CODE = ? ,ISSUE_DATE = ? ,"
			+ "MATURITY_DATE = ? ,RECEIVED_DATE = ? ,BRANCH_CODE = ? ,NATURE_OF_LIMIT	=?,NARRATIVE	=? "
			+ "WHERE CATEGORY = ?";
	public static String updateGuaranteeQuery = "UPDATE GUARANTEE_DETAILS_STAG SET GUARANTEE_TYPE_CODE = ? ,GUARANTEE_NUMBER = ? ,"
			+ "GUARANTEE_DESC = ? ,COUNTER_PARTY_UNIQUE_ID = ? ,PROVIDER_UNIQUE_ID = ? ,GUARANTEE_NATURE = ? ,"
			+ "GUARANTEE_AMOUNT = ? ,EXPOSURE_PERCENTAGE = ? ,EXPOSURE_PERCENTAGE_CAP = ? ,CURRENCY_CODE = ? ,ISSUE_DATE = ? ,"
			+ "MATURITY_DATE = ? ,RECEIVED_DATE = ? ,BRANCH_CODE = ? ,NATURE_OF_LIMIT	=?,NARRATIVE	=? "
			+ "WHERE CATEGORY = ?";
	public static String updateGuaranteeMakerApproveStatusQuery = "UPDATE GUARANTEE_DETAILS_MAKER_CHECKER SET MAKERCHECKERACTION = ?, NOTES = ?  WHERE CATEGORY =? ";

	public static String RETRIEVE_VALUE_FROM_PROPERTIES = "SELECT VALUE FROM INTERFACEPROPERTIES WHERE KEY = ?";
}
