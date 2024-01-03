package com.ett.eod.util;

public class QueryConstant {
	//public static String UPDATEEODPOSTING = "update eodpostingtable set POSTINGAMOUNT=NVL( ett_spot_rate_cal(POSTINGCCY,'LocalCurrency') * POSTINGAMOUNT,'0' ), POSTINGCCY='LocalCurrency' where SUBSTR(BACKOFFICEACCOUNTNO,1,9) in ('151103006','151104001','151103003','151101026','151101032') and POSTINGCCY != 'LocalCurrency'";
	//public static String UPDATEEODPOSTING = "update eodpostingtable set POSTINGAMOUNT=NVL( (SELECT SPOTRATE FROM SPOTRATE WHERE CURRENCY = POSTINGCCY AND BRANCH = 'LocalBranch' )* POSTINGAMOUNT,'0' ), POSTINGCCY='LocalCurrency' where SUBSTR(BACKOFFICEACCOUNTNO,1,9) in ('151103006','151104001','151103003','151101026','151101032','51103006','51104001','51103003','51101026','51101032') and POSTINGCCY != 'LocalCurrency'";
	public static String UPDATEEODPOSTING = "update eodpostingtable set POSTINGAMOUNT=NVL( (SELECT SPOTRATE FROM SPOTRATE WHERE CURRENCY = POSTINGCCY AND BRANCH = 'LocalBranch' )* POSTINGAMOUNT,'0' ), POSTINGCCY='LocalCurrency' where  SUBSTR(BACKOFFICEACCOUNTNO,1,9) in (select GL_SP_ACCOUNT from ett_gl_list) and POSTINGCCY != 'LocalCurrency'";
	public static String INSERTINTOEODPOSTING_BKP = "insert into eodpostingtable_bkp select * from eodpostingtable ";
	public static String RETRIEVE_VALUE_FROM_PROPERTIES = "SELECT VALUE FROM INTERFACEPROPERTIES WHERE KEY = ?";
	public static String CONVERT_FROM_NORMAL_TO_TI_AMOUNT = "SELECT CASE WHEN C8PF.C8CED = 2 THEN ? * 100 WHEN C8PF.C8CED = 3 THEN ? * 1000 ELSE ? END AS TIAMOUNT FROM C8PF WHERE C8CCY = ?";
	public static String RETRIVEVALUEFROMEODPOSTING_BOCM = "select * from EODPOSTINGTABLE ORDER BY MasterReference, Postingamount";
	public static String RETRIVEVALUEFROMEODPOSTING = "select * from EODPOSTINGTABLE where MasterReference not in (select master_ref from ett_interestSuppress where due_date > (select procdate from dlyprccycl)) ORDER BY MasterReference, Postingamount";
	public static String RETRIVEVALUEFROMNPAEODPOSTING = "select * from EODNPAPOSTINGTABLE ORDER BY MasterReference, Postingamount";
	public static String FETCH_CURRENCY_CODE = "SELECT C8CCYN FROM C8PF WHERE C8CCY = ?";
	public static String FETCH_SPOT_RATE = "SELECT SPOTRATE FROM SPOTRATE WHERE CURRENCY = ? AND BRANCH = ?";
	public static String FETCH_EXCHANGE_RATE = "SELECT SUBSTR(EXCH_RATE,1,8) FROM FXBASEDEAL WHERE ASSOCI20 =(SELECT KEY97 FROM RELITEM WHERE EVENT_KEY = ? AND TYPEFLAG = 6909)";
	public static String INSERTINTOEODPOSTING = "insert into eodpostingtable (select  'NA' as TIREQUEST ,trim(substr(fin.FINPST_ACC,1,34)) as BACKOFFICEACCOUNTNO ,"
			+ "(select dec(dec(fin.finpst_ama)/Power(10,cpf.C8ced),15,2) from c8pf cpf where cpf.c8ccy = fin.finpst_ccy) as POSTINGAMOUNT ,fin.FINPST_CCY as POSTINGCCY ,"
			+ "fin.EXCH_RATE as EXCHANGERATE ,CASE fin.FINPST_TCD WHEN '700' THEN 'C' WHEN '200' THEN 'D' END DEBITCREDITFLAG ,to_char(fin.FINPST_DTE,'YYYY-MM-DD') as VALUEDATE  ,trim(fin.NARR_P1) as POSTINGNARRATIVE1 ,"
			+ "'1' as TRANSACTIONSEQNO ,trim(fin.FINPST_DLR) as MASTERREFERENCE ,'null' as EVENTREFERENCE,"
			+ "trim(fin.ACC_TYPE) as ACCOUNTTYPE,trim(fin.FINPST_BRN) as POSTING_BRN, trim(fin.FINPST_ANM) as INCOMESPCODE from fince_pst fin, "
			+ "dlyprccycl DLY WHERE TO_CHAR(DLY.PROCDATE, 'YYYY-MM-DD')= TO_CHAR(fin.FINPST_DTE,'YYYY-MM-DD'))";
	public static String INSERTINTONPAEOD = "insert into eodpostingtable (select  'NA' as TIREQUEST ,"
			+ "trim(substr(NPA.ACCOUNT,1,34)) as BACKOFFICEACCOUNTNO ,(select NPA.AMOUNT/Power(10,cpf.C8ced) from c8pf cpf where cpf.c8ccy = NPA.CURRENCY) as POSTINGAMOUNT ,"
			+ "NPA.CURRENCY as POSTINGCCY ,'null' as EXCHANGERATE ,CASE NPA.POSTING_CODE WHEN '700' THEN 'C' WHEN '200' THEN 'D' END DEBITCREDITFLAG ,to_char(NPA.VALUE_DATE,'YYYY-MM-DD') as VALUEDATE  ,"
			+ "trim(SUBSTR(NPA.NARRATION,1,20)) as POSTINGNARRATIVE1 ,'1' as TRANSACTIONSEQNO ,trim(NPA.MASTER_REF) as MASTERREFERENCE ,"
			+ "'null' as EVENTREFERENCE,'null' as ACCOUNTTYPE,trim( SUBSTR(NPA.ACCOUNT, LOCATE('-',NPA.ACCOUNT)+1, LOCATE('-',NPA.ACCOUNT, LOCATE('-',NPA.ACCOUNT))-5))as POSTING_BRN, trim(NPA.ACCOUNT_CATEGORY) as INCOMESPCODE "
			+ " from ETT_NPA_EOD_POSTING NPA "
			+ ",dlyprccycl DLY WHERE TO_CHAR(DLY.PROCDATE, 'YYYY-MM-DD')= TO_CHAR(NPA.value_date,'YYYY-MM-DD'))";
}
