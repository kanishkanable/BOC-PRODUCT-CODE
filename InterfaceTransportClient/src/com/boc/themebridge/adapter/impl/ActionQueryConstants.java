package com.boc.themebridge.adapter.impl;

public class ActionQueryConstants {

	public static final String LOG_EXTRACTS_GATEWAY_QUERY = "INSERT INTO TIGATEWAYEXTRACTLOG (SERVICE,OPERATION,MASTER_KEY,EVENT_KEY,PROD_TYPE,EVENT_TYPE,STATUS,RECEIVED,TIREQUEST) VALUES (?,?,?,?,?,?,?,current timestamp,?)";
	public static final String GET_MASTER_EVENT_KEY = "select mas.key97 as MASTER_KEY, bev.key97 as EVENT_KEY from master mas, baseevent bev where mas.key97 =  bev.master_key and mas.master_ref = ?  and (bev.REFNO_PFIX||lpad(bev.REFNO_SERL,3,0)) = ?";
	public static final String IS_MASTER_EVENT_KEY_PRESENT = "SELECT 'D' AS STATUS FROM TIGATEWAYEXTRACTLOG WHERE MASTER_KEY = ? AND EVENT_KEY = ?";
	public static String EXTRACT_GATEWAY_RESPONSE = "//XML//ExtractsGatewayResponse.xml";
}
