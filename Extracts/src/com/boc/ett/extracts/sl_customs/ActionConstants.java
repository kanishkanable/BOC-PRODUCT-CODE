package com.boc.ett.extracts.sl_customs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface ActionConstants {

	public static final String FETCHINPUTBRANCHDETAILS = "SELECT FULLNAME,CABBN,CITY,COUNTRY FROM CAPF";

	public static final String FETCHSLCUSTOMSEXTRACTQUERY = "SELECT ID,BANK,BRANCH,NAME,ADD1,ADD2,ADD3,NIC_CR_BR,VATNO,BNAME,BADD,BCOUNTRY,ECA,FEP,CURRCODE,AMOUNT,TOP,TOD,REFNO,LCNO,DATE FROM SL_CUSTOMS_STAGING_TAB ";

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
