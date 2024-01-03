package com.ett.util;

public class QueryConstants {

	public static String INSERT_INTO_FTISTAGING = "(SEQ,FTIREQUEST,STATUS,RECEIVEDDATE) VALUES (?,?,?,CURRENT TIMESTAMP)";

	public static String RETRIEVE_VALUE_FROM_PROPERTIES = "SELECT VALUE FROM INTERFACEPROPERTIES WHERE KEY = ?";

	public static String INSERT_INTO_CUSTOMER_LOG = "INSERT INTO CUSTOMERLOG (PKEY,CUSTOMERID,BANKREQUEST,BANKRESPONSE,TIREQUEST,TIRESPONSE) VALUES (?,?,?,?,?,?)";

	public static String INSERT_INTO_ACCOUNT_LOG = "INSERT INTO ACCOUNTLOG (PKEY,ACCOUNTID,TIREQUEST,TIRESPONSE) VALUES (?,?,?,?)";

	public static String UPDATE_SWIFTIN_TABLE = "UPDATE FTISWIFTIN SET MESSAGE_TYPE = ?,FTIREQUEST = ?,FTIRESPONSE = ? WHERE SEQ = ?";

	public static String INSERT_INTO_FX_LOG = "INSERT INTO FXLOG (PKEY,TTREQUEST,TTRESPONSE,IMPREQUEST,IMPRESPONSE,SPOTREQUEST,SPOTRESPONSE) VALUES (?,?,?,?,?,?,?)";

	public static String FETCH_CURRENCY = "SELECT C8CCY FROM C8PF WHERE C8CCYN = ?";

	public static String UPDATE_POSTING_ENTRIES = "UPDATE POSTINGTABLE SET Status = ?, ErrorMsg = ?, TransactionID = ?, ResponseFileName=? WHERE AccountNumber = ? AND AccountType = ? AND DebitCreditCode = ? AND TransactionAmount = ? AND AlphabeticData1 = ?";

	public static String CHECK_SWIFT_HOLD_STATUS = "SELECT DISTINCT MASTER_REF, EVENT_REF FROM SWIFTOUTLOG WHERE STATUS = ?";

	public static String CHECK_POSTING_STATUS = "SELECT BANKRESSTATUS FROM FTIBACKOFFICEBATCH WHERE MASTER_REF = ? AND EVENT_REF = ?";

	public static String UPDATE_INTO_POSTINGSTAGING = " SET BANKRESSTATUS = ?, BANKRESPONSEDDATE =CURRENT TIMESTAMP WHERE MASTER_REF = ? AND EVENT_REF = ?";

	public static String GET_SWIFTOUT_FROM_DB = "SELECT SWIFT_MSG FROM SWIFTOUTLOG WHERE MASTER_REF = ? AND EVENT_REF = ? and status='Hold'";

	public static String UPDATE_SWIFTOUT_STATUS = "UPDATE SWIFTOUTLOG SET STATUS = ? ,SWIFTRESPONSE = ? WHERE MASTER_REF = ? AND EVENT_REF = ? and status='Hold'";
	
	public static String POSTINGFTECH ="SELECT SUBSTR(alphabeticdata1, 1, 16) As ACTION ,substr(alphabeticdata1, 18, 6) AS MAS_REIMBSYSREF,status as MAS_DOCCRNO,accountnumber as MAS_PRCSID,"
			+ "transactionamount as MAS_ACTVID,case when (debitcreditcode = 6)then 'DR'else 'CR' end as MAS_DTMODIFIED FROM postingtable WHERE alphabeticdata1 = ?";
	public static String UPDATE_BACKOFC_BATCH="update ftibackofficebatch set bankresstatus='SUCCEEDED' where bankresstatus!='SUCCEEDED' and STATUS!='FAILED' and master_ref =? and event_ref= ?";
	public static String UPDATE_POSTINGTABLE="update POSTINGTABLE set status='SUCCEEDED' where status='FAILED' and alphabeticdata1= ?";//SUBSTR(alphabeticdata1, 1, 16)||'-'||substr(alphabeticdata1, 18, 6)
	public static String UPDATE_UNBALANCED_POSTING_ENTRIES = "UPDATE POSTINGTABLE SET Status = 'SUCCEEDED WITH UNBALANCED' WHERE AlphabeticData1 = ? AND Status = 'SUCCEEDED'";
}
