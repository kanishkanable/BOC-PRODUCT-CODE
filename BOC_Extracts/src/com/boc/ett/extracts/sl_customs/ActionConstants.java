package com.boc.ett.extracts.sl_customs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface ActionConstants {

	public static final String FETCHINPUTBRANCHDETAILS = "SELECT FULLNAME,CABBN,CITY,COUNTRY FROM CAPF";
	
	public static final String codeDetailsQuery = "SELECT CODE,DESCRIPTION from BOC_COLLATERAL_CODE";
	
	public static final String guaranteeDetailsQuery = "SELECT CODE,DESCRIPTION from BOC_GUARANTEE_CODE";

	//public static final String FETCHSLCUSTOMSEXTRACTQUERY = "SELECT ID,BANK,BRANCH,NAME,ADD1,ADD2,ADD3,NIC_CR_BR,VATNO,BNAME,BADD,BCOUNTRY,ECA,FEP,CURRCODE,AMOUNT,TOP,TOD,REFNO,LCNO,DATE FROM SL_CUSTOMS_STAGING_TAB WHERE STATUS IS NULL AND ";
	public static final String FETCHSLCUSTOMSEXTRACTQUERY ="SELECT trim(ID)ID,trim(BANK)BANK,trim(BRANCH)BRANCH,trim(NAME)NAME,trim(ADD1)ADD1,trim(ADD2)ADD2,trim(ADD3)ADD3,trim(NIC_CR_BR)NIC_CR_BR,trim(VATNO)VATNO,trim(BNAME)BNAME,trim(BADD)BADD,trim(BCOUNTRY)BCOUNTRY,trim(ECA)ECA,trim(FEP)FEP,trim(CURRCODE)CURRCODE,trim(AMOUNT)AMOUNT,trim(TOP)TOP,trim(TOD)TOD,trim(REFNO)REFNO,trim(LCNO)LCNO,trim(DATE)DATE FROM SL_CUSTOMS_STAGING_TAB WHERE STATUS IS NULL AND ";
	public static final String SQL_AND = " AND ";
	
	public static final String UPDATEROWSTATUS = "UPDATE SL_CUSTOMS_STAGING_TAB SET STATUS = 'E' WHERE ID = ?";

	
	String ENTERING_METHOD = "Entering Method";
	String EXITING_METHOD = "Exiting Method";

	public static final Map<String, String> SUB_PRODUCT_TYPE = Collections
			.unmodifiableMap(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("TAP", "Advance Payment");
					put("TFC", "FCY Account Payment");
					put("TOA", "Open Account");
				}
			});
}
