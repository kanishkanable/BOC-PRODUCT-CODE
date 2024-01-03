package com.ett.util;

public class QueryConstants {

	public static String GL_CHECK_QUERY = "SELECT GL_SP_CODE FROM ETT_GL_LIST WHERE GL_SP_CODE = ?";
	
	public static String INSERT_INTO_FTISTAGING = "(SEQ,FTIREQUEST,STATUS) VALUES (?,?,?)";
	
	public static String UPDATE_INTO_FTISTAGING = " SET FTIRESPONSE = ? ,STATUS = ?, PROCESSEDDATE =CURRENT TIMESTAMP WHERE SEQ = ?";

	public static String CHECK_FOR_CUSTOMER_ACCOUNT = "SELECT 'TRUE' FROM INTERFACEPROPERTIES WHERE KEY = 'CustomerAccounts' and VALUE LIKE '%";

	public static String CONVERT_FROM_TI_TO_NORMAL_AMOUNT = "SELECT CASE WHEN C8PF.C8CED = 2 THEN ? / 100 WHEN C8PF.C8CED = 3 THEN ? / 1000 ELSE ? END AS TIAMOUNT FROM C8PF WHERE C8CCY = ?";

	public static String CONVERT_FROM_NORMAL_TO_TI_AMOUNT = "SELECT CASE WHEN C8PF.C8CED = 2 THEN ? * 100 WHEN C8PF.C8CED = 3 THEN ? * 1000 ELSE ? END AS TIAMOUNT FROM C8PF WHERE C8CCY = ?";
	
	public static String RETRIEVE_VALUE_FROM_PROPERTIES = "SELECT VALUE FROM INTERFACEPROPERTIES WHERE KEY = ?";

	public static String FETCH_MASTER_KEY = "SELECT KEY97 FROM MASTER WHERE MASTER_REF = ?";

	public static String FETCH_EVENT_KEY = "SELECT KEY97 FROM BASEEVENT WHERE MASTER_KEY  = ? AND REFNO_PFIX||lpad(REFNO_SERL,3,0) = ?";

	public static String FETCH_AUTH_USER_ID = "SELECT MAX(USERID) FROM STEPHIST WHERE EVENT_KEY = ? AND STATUS = 'c' AND USERID !='** System **' AND TYPE = 'a1'";

	public static String FETCH_EXCHANGE_RATE = "SELECT SUBSTR(EXCH_RATE,1,8) FROM FXBASEDEAL WHERE ASSOCI20 =(SELECT KEY97 FROM RELITEM WHERE EVENT_KEY = ? AND TYPEFLAG = 6909)";

	public static String FETCH_SPOT_RATE = "SELECT SPOTRATE FROM SPOTRATE WHERE CURRENCY = ? AND BRANCH = ?";
	
	public static String FETCH_CURRENCY_CODE = "SELECT C8CCYN FROM C8PF WHERE C8CCY = ?";

	public static String INSERT_POSTING_ENTRIES = "INSERT INTO POSTINGTABLE (SEQ,BackOfficeKey,BranchNumber,DebitCreditCode,EffectiveDate,AccountType,AccountNumber,TransactionAmount,LCEAmount,ForeignCurrencyCode,ForeignCurrencyRate,ReferenceNumber,AlphabeticData1,AlphabeticData2,AlphabeticData3,TransactionTime,CIFKey,MadeBy,ApprovedBy,AdditionalFCYInfo,OriginationInfo,ForeignCurrencyOverride,ApplicationTranCode,AuthorisedUserID,DealerNumber,ProcessingDate,Status,Transaction_ccy,RequestFileName) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static String FETCH_SPECIAL_INSTRUCTIONS = " SELECT CS.DETAILS FROM cusspecins cs,busine21 bs,notety24 nt  WHERE cs.notetype = nt.mykey95 AND cs.bus_area = bs.key97 AND cs.customer = ? AND bs.id = ? and nt.DESCRI56 = ?";

	public static String UPDATE_SWIFTIN_TABLE = "UPDATE FTISWIFTIN SET MESSAGE_TYPE = ?,TIREQUEST = ?,TIRESPONSE = ? WHERE PKEY = ?";

	public static String getMasterEventRefbySwiftCorrID = "SELECT TRIM(MAS.MASTER_REF) AS MASTER_REF, (BEV.REFNO_PFIX||LPAD(BEV.REFNO_SERL,3,0)) FROM MASTER MAS, BASEEVENT BEV,SWOPF SW,DOCRELITEM DRI,RELITEM REL WHERE MAS.KEY97 = BEV.MASTER_KEY AND SW.OWNER = DRI.KEY97  AND DRI.KEY97 = REL.KEY97  AND REL.EVENT_KEY = BEV.KEY97 AND SW.SWORNO = '";

	public static String ifPostingAvailable = "SELECT COUNT(1) FROM GZH971 WHERE GZDLR = ? AND GZEVTREF = ?";
	
	public static String ifRTGS = "SELECT COUNT(1) FROM EXTEVENT EXT, BASEEVENT BEV, MASTER MAS WHERE MAS.MASTER_REF = ? AND MAS.KEY97 = BEV.MASTER_KEY AND (BEV.REFNO_PFIX||LPAD(BEV.REFNO_SERL,3,0)) = ? AND EXT.EVENT = BEV.KEY97 AND EXT.PAYMODE = 'RTGS'";
	
	public static String InsertWatchlistAPI = "insert into apiserver values (APISERVER_SEQ.nextval, current_timestamp,'TI','TFWLCRSP','$Tirequest',null,'watchlist $MasterRefrence','UAT-FTI-WAS.bankofceylon.local',current_timestamp,'WAITING',1,0)";
	//public static String INSERTINTO_SWIFTOUT_TABLE = "INSERT INTO SWIFTOUTLOG (PKEY,TTREQUEST,TTRESPONSE,IMPREQUEST,IMPRESPONSE,SPOTREQUEST,SPOTRESPONSE) VALUES (?,?,?,?,?,?,?)";
	public static String INSERTINTO_SWIFTOUT_TABLE = "INSERT INTO SWIFTOUTLOG (SWIFTKEY, PKEY,MASTER_REF,EVENT_REF,SWIFT_MSG,STATUS,SWIFTRESPONSE) VALUES (?,?,?,?,?,?,?)";

	public static String CHECK_MASTER_REF = "select * from master where trim(master_ref)=?";
	
	public static String GET_ORIGINAL_REF = "select trim(master_ref) from master where key97 in (select master_key from baseevent where trim(cross_ref)=?)";
	public static String CHECK_SWIFT_HOLD_STATUS = "";

	public static String CHECK_POSTING_STATUS = "";

	public static String GET_SWIFTOUT_FROM_DB = "";

	public static String UPDATE_SWIFTOUT_STATUS = "";

	public static String UPDATE_INTERFACE_PROPERTIES = "UPDATE INTERFACEPROPERTIES SET VALUE = ? WHERE KEY = ?";
	
	public static String INSERT_INTO_FX_LOG = "INSERT INTO FXLOG (PKEY,TTREQUEST,TTRESPONSE,IMPREQUEST,IMPRESPONSE,SPOTREQUEST,SPOTRESPONSE) VALUES (?,?,?,?,?,?,?)";

	public static String FETCH_CURRENCY = "SELECT C8CCY FROM C8PF WHERE C8CCYN = ?";
	
	public static String UPDATE_MASTER_EVENT_REF = " SET MASTER_REF = ?, EVENT_REF = ? WHERE SEQ = ?";
	
	public static String GET_MAS_EVE_CODE = "select mas.refno_pfix mas_code,bev.refno_pfix event_code from master mas,baseevent bev where mas.key97=bev.master_key and mas.master_ref=? and bev.refno_pfix|| lpad(bev.refno_serl, 3, 0)=?";
	
	public static String FETCH_MOBILEEMAIL = " FROM SX20LF WHERE sxcus1 = ?";
	
	public static String DEST_BANK_NAME = "SELECT TRIM(GFCPNC) FROM GFPF WHERE GFCTP1 in ('BA','BB') AND GFCUS1 = '";
	
	
	public static String limitFetch = "SELECT DLTC.AMOUNT,DLTC.AVAILABLE,DLT.CURRENCY,TO_CHAR(DLTC.EXPIRES,'yyyyMMdd') FROM DLTLIMITCONTROL DLT,DLTSTRUCTURE DLTS , DLTLIMITDETAIL DLTC,DLTSTRUCTDETAIL DLTST WHERE DLT.DLSTRUCTURE=DLTS.DLSTRUCTURE AND DLTC.LCKEY=DLT.KEY97 AND DLTST.KEY97=DLTC.SDKEY and DLTST.DLTOTAL='GRAND' and DLT.DLCUSTOMER=?";
	
	public static String Cust_acct_brch="select TRIM(BRCH_MNM) from account where BO_acctno= '";
	
	public static String ORGCUS_NAME="select TRIM(gfcun) from GFPF where GFCUS1= '";
	public static String ORG_ACTYPE="select TRIM(ACC_TYPE) from ACCOUNT where BO_ACCTNO = '";
	public static String ORG_CURRENCY="select TRIM(CURRENCY) from account where BO_acctno= '";
	public static String ORG_CUS_MNM="select TRIM(CUS_MNM) from account where BO_acctno= '";

	
	public static String DESTADD1="select TRIM(SX.SVNA1) from  SX20LF SX where SX.SXCUS1 = '";
	public static String DESTADD2="select TRIM(SX.SVNA2) from  SX20LF SX where SX.SXCUS1 = '";
	public static String DESTADD3="select TRIM(SX.SVNA3) from  SX20LF SX where SX.SXCUS1 = '";
	
	public static String current_Date="select PROCDATE from dlyprccycl";
	
	public static String GET_ISVALID_NONREVOLVING_PRODUCT="SELECT * FROM LIMITCATEGORYPRODUCTDES WHERE PRODUCTCODE = ? AND EVENTCODE = ? ";

	public static String GET_ACCEPTANCE_LIMIT_LINE = "SELECT trim(ACCEPTANCELIMITLINE) AS ACCEPTANCELIMITLINE FROM LIMITLINECODE where SIGHTLIMITLINE = ?";
	
	public static String LIMIT_CATEGORY_ACCOUNT_TYPE="SELECT TRIM(LIMITCATEGORY) AS LIMITCATEGORY FROM LIMITCATEGORYACCOUNTTYPE WHERE ACCOUNTTYPE = ?";
}
