package com.ett.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ett.backofficebatch.BackOfficeBatchResponseReqModel;
import com.ett.swift.incoming.SwiftInImpl;
import com.ett.swift.outgoing.SwiftOutRequestModel;
import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import com.misys.tiplus2.apps.ti.service.messages.*;
import com.misys.tiplus2.foundations.lang.logging.Loggers;
import com.misys.tiplus2.services.control.ServiceRequest.RequestHeader;
import com.misys.tiplus2.services.control.ServiceRequest.RequestHeader.Credentials;
import com.misys.tiplus2.services.control.ServiceResponse;
import com.misys.tiplus2.services.control.ServiceResponse.ResponseHeader;
import com.misys.tiplus2.services.control.ServiceResponse.ResponseHeader.Details;
import com.misys.tiplus2.services.control.ServiceRequest;
import com.misys.tiplus2.services.control.StatusEnum;
//import com.misys.tiplus2.apps.ti.service.messages.SWOPFType;
import com.misys.tiplus2.foundations.lang.logging.Loggers;
import com.misys.tiplus2.services.control.ServiceRequest.RequestHeader;
import com.misys.tiplus2.services.control.ServiceRequest.RequestHeader.Credentials;

public class BOCCommonUtil {

	private static final Logger LOG = LoggerFactory.getLogger(BOCCommonUtil.class);

	private static JAXBContext jaxbTIContext = null;
	public static String swiftResponse = "Swift message has pushed successfully";
	public static String retreiveFromProperties(String key) {

		String value = null;
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.RETRIEVE_VALUE_FROM_PROPERTIES);
			pst.setString(1, key);
			rst = pst.executeQuery();

			while (rst.next())
				value = rst.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return value;

	}

	public static String callCoreBankingWebService(String bankRequestXML) throws MalformedURLException, IOException {

		String responseString = "";
		String wsURL = retreiveFromProperties(Constants.COREBANKING_URL);
		System.out.println("END Point URL " + wsURL);
		PostMethod post = new PostMethod(wsURL);
		
		System.out.println("POST entry " );
		StringRequestEntity requestEntity = new StringRequestEntity(bankRequestXML, "text/xml", "UTF-8");
		post.setRequestEntity(requestEntity);
		System.out.println("POST request entity bind " );
		HttpClient httpclient = new HttpClient();
		
		try {
			System.out.println("Before execute method " );
			int result = httpclient.executeMethod(post);
			System.out.println("http status code " + result);
			if (result != 200) {
				throw new Exception("Server returned errror code " + result);
			}
			responseString = post.getResponseBodyAsString();
			System.out.println("response XML : " + responseString);
			if (responseString == null || responseString.trim().equals("")) {
				throw new Exception("Server did not return any result");
			}
		} catch (Exception e) {
			
			System.out.println("API Call Post Error " + e.getMessage() );
			e.printStackTrace();
		}

		finally {
			post.releaseConnection();
		}

		return responseString;

	}

	public static boolean insertTIReqToLogTable(String tiRequestXML, String table, String status, String pkey) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		boolean val = false;

		System.out.println("Table " + table + " Status " + status + " pkey " + pkey);

		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement("INSERT INTO " + table + QueryConstants.INSERT_INTO_FTISTAGING);
			System.out.println("Insert Query" + pst.toString());
			pst.setString(1, pkey);
			pst.setString(2, tiRequestXML);
			pst.setString(3, status);
			pst.executeUpdate();
			System.out.println("SEQ" + pkey);
			System.out.println("tiRequestXML" + tiRequestXML);
			System.out.println("status" + status);
			val = true;
		} catch (SQLException e) {
			e.printStackTrace();
			val = false;
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}
		return val;
	}

	public static String getSwiftMsgType(String swiftIn) {

		String swiftCode = "";
		int indexCode = swiftIn.indexOf("2:");
		if (indexCode >= 0 && indexCode < swiftIn.length()) {
			swiftCode = swiftIn.substring(indexCode + 3, indexCode + 6);
		}
		return swiftCode;
	}

	public static void updateSwiftInData(String pkey, String swiftMessageType, String tiRequestXML,
			String tiResponseXML) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.UPDATE_SWIFTIN_TABLE);
			System.out.println("QueryConstants.UPDATE_SWIFTIN_TABLE...." + QueryConstants.UPDATE_SWIFTIN_TABLE);
			pst.setString(1, swiftMessageType);
			System.out.println("swiftMessageType ===" + swiftMessageType);
			pst.setString(2, tiRequestXML);
			System.out.println("tiRequestXML ===" + tiRequestXML);
			pst.setString(3, tiResponseXML);
			System.out.println("tiResponseXML ===" + tiResponseXML);
			pst.setString(4, pkey);
			System.out.println("pkey ===" + pkey);
			pst.executeUpdate();
			System.out.println("SWIFT IN UPDATION" + QueryConstants.UPDATE_SWIFTIN_TABLE);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}

	}

	public static RequestHeader getRequestHeader(String name, String correlationId, String service, String operation) {

		RequestHeader requestheader = new RequestHeader();

		Credentials credentials = new Credentials();
		if (name != null && !"".equalsIgnoreCase(name)) {
			credentials.setName(name);
		} else {
			credentials.setName("");
		}
		requestheader.setCredentials(credentials);

		if (correlationId != null && !"".equalsIgnoreCase(correlationId)) {
			requestheader.setCorrelationId(correlationId);
		} else {
			requestheader.setCorrelationId("");
		}

		if (operation != null && !"".equalsIgnoreCase(operation)) {
			requestheader.setOperation(operation);
		} else {
			requestheader.setOperation("");
		}
		if (service != null && !"".equalsIgnoreCase(service)) {
			requestheader.setService(service);
		} else {
			requestheader.setService("");
		}

		return requestheader;
	}

	public static JAXBContext getJAXBTIContext() {
		if (jaxbTIContext == null) {
			synchronized (JAXBContext.class) {
				if (jaxbTIContext == null) {

					try {
						jaxbTIContext = JAXBContext.newInstance("com.misys.tiplus2.apps.ti.service.messages");
					} catch (JAXBException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return jaxbTIContext;
	}

	public static String getCustomerType(String customerType) {

		String custType = null;

		if (customerType.equalsIgnoreCase("Personal"))
			custType = "EA";
		else
			custType = "AA";

		return custType;
	}

	public static String getNextSeqNo(String seqName) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		String key97 = null;

		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement("SELECT NEXT VALUE FOR " + seqName + " FROM SYSIBM.SYSDUMMY1");
			rst = pst.executeQuery();
			while (rst.next())
				key97 = rst.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return key97;
	}

	public static boolean insertIntoCustomerlogTable(String pkey, String customerID, String bankReqXML,
			String bankResponse, String tiRequest, String tiResponse) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		boolean val = false;

		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.INSERT_INTO_CUSTOMER_LOG);
			pst.setString(1, pkey);
			pst.setString(2, customerID);
			pst.setString(3, bankReqXML);
			pst.setString(4, bankResponse);
			pst.setString(5, tiRequest);
			pst.setString(6, tiResponse);
			pst.executeUpdate();
			val = true;
		} catch (SQLException e) {
			e.printStackTrace();
			val = false;
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}
		return val;
	}

	public static boolean insertIntoAccountlogTable(String pkey, String accountID, String tiRequest,
			String tiResponse) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		boolean val = false;

		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.INSERT_INTO_ACCOUNT_LOG);
			pst.setString(1, pkey);
			pst.setString(2, accountID);
			pst.setString(3, tiRequest);
			pst.setString(4, tiResponse);
			pst.executeUpdate();
			val = true;
		} catch (SQLException e) {
			e.printStackTrace();
			val = false;
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}
		return val;
	}

	public static boolean checkIfExistsInTI(String table, String Column, String value) {

		boolean exists = false;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		int count = 0;
		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(
					"SELECT COUNT(1) as Total FROM " + table + " WHERE TRIM(" + Column + ")='" + value + "'");
			rst = pst.executeQuery();
			while (rst.next()) {
				count = rst.getInt("Total");
				System.out.println("Inside While-->" + count);
				if (count > 0)
					exists = true;
				System.out.println("Exists-->" + exists);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return exists;
	}

	public static boolean checkACCExistsInTI(String table, String Column, String value, String param4) {

		boolean exists = false;
		int count = 0;
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		if (param4.equalsIgnoreCase("DD")) {
			param4 = "CA";
		} else if (param4.equalsIgnoreCase("SV")) {
			param4 = "SA";
		}
		try {

			aConnection = CommonUtil.DBConnection();

			pst = aConnection.prepareStatement("SELECT COUNT(1) as Total FROM " + table + " WHERE trim(" + Column
					+ ")='" + value + "' AND TRIM(ACC_TYPE)='" + param4 + "'");
			rst = pst.executeQuery();
			while (rst.next()) {
				count = rst.getInt("Total");
				System.out.println("Inside While-->" + count);
				if (count > 0)
					exists = true;
				System.out.println("Exists-->" + exists);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return exists;
	}

	public static String getCurrency(String CurrencyCode) {

		String Currency = null;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.FETCH_CURRENCY);
			pst.setString(1, CurrencyCode);
			rst = pst.executeQuery();
			while (rst.next())
				Currency = rst.getString(1);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return Currency;
	}

	public static void inserttoFXLog(String pkey, String tiTTRequestXML, String ttResponse, String tiIMPRequestXML,
			String impResponse, String tiSpotRateXML, String spotRateResponse) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.INSERT_INTO_FX_LOG);
			pst.setString(1, pkey);
			pst.setString(2, tiTTRequestXML);
			pst.setString(3, ttResponse);
			pst.setString(4, tiIMPRequestXML);
			pst.setString(5, impResponse);
			pst.setString(6, tiSpotRateXML);
			pst.setString(7, spotRateResponse);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}

	}

	public static void updatePostingResponseEntries(BackOfficeBatchResponseReqModel BbR,String fileName) {
		Connection aConnection = null;
		PreparedStatement pst = null;
		try {
			System.out.println("Entering into Posting Response Updation Method");

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.UPDATE_POSTING_ENTRIES);
			if (BbR.getPostingStatus().equalsIgnoreCase("S"))
				pst.setString(1, Constants.STATUS_SUCCEEDED);
//			if (BbR.getPostingStatus()!=null)
//				pst.setString(1, Constants.STATUS_SUCCEEDED);
			else
				pst.setString(1, Constants.STATUS_FAILED);
			if (CommonUtil.NullStringCheck(BbR.getStatusDesc()))
				pst.setString(2, " ");
			else
				pst.setString(2, BbR.getStatusDesc());
			pst.setString(3, BbR.getTransactionID());
			pst.setString(4, fileName);
			pst.setString(5, BbR.getAccountNumber());
			pst.setString(6, BbR.getAccountType());
			pst.setString(7, BbR.getDebitCreditCode());
			pst.setString(8, BbR.getTranAmountTranCcy());
			pst.setString(9, BbR.getReferenceNumber());
			
			pst.executeUpdate();
			System.out.println("posting status  :" + BbR.getPostingStatus());
			
			System.out.println("Account Num    :" + BbR.getAccountNumber());
			System.out.println("status desc   :" + BbR.getStatusDesc());
			System.out.println("account type   :" + BbR.getAccountType());
			System.out.println("db cr code    :" + BbR.getDebitCreditCode());
			System.out.println("transamtccy    :" + BbR.getTranAmountTranCcy());
			System.out.println("refnum    :" + BbR.getReferenceNumber());

			System.out.println("POSTING ENTIRES UPDATED" + QueryConstants.UPDATE_POSTING_ENTRIES);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}

	}

	public static List<SwiftOutRequestModel> getHoldSwifts() {

		List<SwiftOutRequestModel> aSwiftOutRequestModel = new ArrayList<>();
		Loggers.general().info(LOG, "Swiftout hold records");
		SwiftOutRequestModel srModel = new SwiftOutRequestModel();
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.CHECK_SWIFT_HOLD_STATUS);

			pst.setString(1, Constants.STATUS_HOLD);
			Loggers.general().info(LOG, "Query" + QueryConstants.CHECK_SWIFT_HOLD_STATUS+"  __> "+ Constants.STATUS_HOLD);
			rst = pst.executeQuery();
			while (rst.next()) {
				System.out.println("rst.getString(1)--->"+rst.getString(1));
				srModel.setMasterReference(rst.getString(1));
				srModel.setEventReference(rst.getString(2));
				aSwiftOutRequestModel.add(srModel);
				srModel = new SwiftOutRequestModel();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Loggers.general().info(LOG, "Exception in Swiftout hold records" + e);
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return aSwiftOutRequestModel;
	}

	public static String checkPostingStatus(String masterReference, String eventReference) {

		String status = "";
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.CHECK_POSTING_STATUS);
			pst.setString(1, masterReference);
			pst.setString(2, eventReference);
			rst = pst.executeQuery();
			Loggers.general().info(LOG, "executeQuery " + pst);
			while (rst.next()) {
				if (rst.getString(1).equalsIgnoreCase(Constants.STATUS_SUCCEEDED))
					status = Constants.STATUS_SUCCEEDED;
				else if (rst.getString(1).equalsIgnoreCase(Constants.STATUS_FAILED))
					status = Constants.STATUS_FAILED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Loggers.general().info(LOG, "Exception--->" + e);
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return status;
	}

	public static boolean updatePostingResToLogTable(String status, String masterRef, String eventRef, String table) {
		Connection aConnection = null;
		PreparedStatement pst = null;
		boolean val = false;

		try {
			System.out.println("Entering into Posting Response Log Table");
			aConnection = CommonUtil.DBConnection();
			System.out.println("STATUS -" + status);
			System.out.println("masterRef -" + masterRef);
			System.out.println("eventRef -" + eventRef);
			System.out.println("Table Name -" + table);
			pst = aConnection.prepareStatement("UPDATE " + table + QueryConstants.UPDATE_INTO_POSTINGSTAGING);
			System.out.println("Update Query - " + "UPDATE " + table + QueryConstants.UPDATE_INTO_POSTINGSTAGING);
			pst.setString(1, status);
			pst.setString(2, masterRef);
			pst.setString(3, eventRef);
			pst.executeUpdate();
			val = true;
			System.out.println("STATUS after update -" + status);
			System.out.println("masterRef after update -" + masterRef);
			System.out.println("eventRef after update-" + eventRef);
			System.out.println(
					"POSTING ENTRY SECOND UPDATION after update--- " + QueryConstants.UPDATE_INTO_POSTINGSTAGING);
		} catch (SQLException e) {
			e.printStackTrace();
			val = false;
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}
		return val;
	}

	public static List<String> getSwiftOutMessage(String masterReference, String eventReference) {

		List<String> swiftOutMessage = new LinkedList<>();

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		List<String> Message = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.GET_SWIFTOUT_FROM_DB);
			pst.setString(1, masterReference);
			pst.setString(2, eventReference);
			rst = pst.executeQuery();
			Loggers.general().info(LOG, "Swift executeQuery-->" + pst);
			while (rst.next()) {
				// Message=(getFormattedSwiftOut(rst.getString(1)));
				swiftOutMessage.add(rst.getString(1));
				Loggers.general().info(LOG, "Swift out-->" + rst.getString(1));

			}
		} catch (Exception e) {
			e.printStackTrace();
			Loggers.general().info(LOG, "Swift Exception-->" + e);
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
 
		return swiftOutMessage;
	}

	// N-Able Pvt LTD / 20-02-2023
	// Method removed since the method is not use from any of process.
	
	
//	public static List<String> getFormattedSwiftOut(String swiftOut) {
//		List<String> formattedSwiftOut = new LinkedList<>();
//		// added by mani
//		System.out.println("formattedSwiftOut list....");
//		// added by mani
//		try {
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
//			dbf.setNamespaceAware(true);
//			// added by mani
//			System.out.println("Document Builder Factory....");
//			// added by mani
//			DocumentBuilder db = dbf.newDocumentBuilder();
//			Document document = db.parse(new ByteArrayInputStream(swiftOut.getBytes()));
//			JAXBContext context = BOCCommonUtil.getJAXBTIContext();
//			Unmarshaller unmarshaller = context.createUnmarshaller();
//			NodeList nodeList = document.getElementsByTagNameNS("*", "SwiftOut");
//			JAXBElement<SWOPFType> swiftOutJAXB = (JAXBElement<SWOPFType>) unmarshaller.unmarshal(nodeList.item(0),
//					SWOPFType.class);
//			SWOPFType source = swiftOutJAXB.getValue();
//			int swiftsize = source.getMessages().getMessage().size();
//			for (int i = 0; i < swiftsize; i++) {
//				formattedSwiftOut.add(source.getMessages().getMessage().get(i));
//			}
//			// System.out.println("Swift Out Formatinh"+formattedSwiftOut);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return formattedSwiftOut;
//	}

	public static void writeMQMessage(String inputMessage) throws IOException {

		Loggers.general().info(LOG, "Inside write Mq message");
		String errormsg = "", swiftResp = "";
		String status = "";

		String portnum = retreiveFromProperties("SWIFTPort");
		String hostname = retreiveFromProperties("SWIFTHostName");
		String channel = retreiveFromProperties("SWIFTChannel");
		String qManager = retreiveFromProperties("SWIFTQManager");
		String outputQName = retreiveFromProperties("SWIFTOutputQName");
		String userID = retreiveFromProperties("SWIFTUserID");
		String password = retreiveFromProperties("SWIFTpassword");
		String sslCipherSuite = retreiveFromProperties("SWIFTsslCipher");
        if(sslCipherSuite.equals("")){
            sslCipherSuite=null;
        }
		int port = Integer.parseInt(portnum);

		try {

			Loggers.general().info(LOG, "MQ connect started//////" + port + " " + hostname + " " + channel + " "
					+ qManager + " " + outputQName);

			MQEnvironment.hostname = hostname;
			MQEnvironment.channel = channel;
			MQEnvironment.port = port;
			MQEnvironment.userID = userID;
			MQEnvironment.password = password;
			MQEnvironment.sslCipherSuite = sslCipherSuite;
			MQQueueManager _queueManager = new MQQueueManager(qManager);

			Loggers.general().info(LOG, "MQ connect started queueManager:" + _queueManager);

			int lineNum = 0;
			@SuppressWarnings("deprecation")
			int openOptions = MQC.MQOO_OUTPUT + MQC.MQOO_FAIL_IF_QUIESCING;
			try {

				MQQueue queue = _queueManager.accessQueue(outputQName, openOptions, null, null, null);
				Loggers.general().info(LOG, "MQ connect started///////////_queueManager:" + _queueManager);
				Loggers.general().info(LOG, "MQWrite v1.0 connected");

				MQMessage sendmsg = new MQMessage();
				MQPutMessageOptions pmo = new MQPutMessageOptions();

				String line = inputMessage.replace("\n", "\r\n");
				sendmsg.clearMessage();
				sendmsg.writeString(line);
				queue.put(sendmsg, pmo);
				Loggers.general().info(LOG, "Message sending to MQueue-->" + line);
				Loggers.general().info(LOG, ++lineNum + ": " + line);
				queue.close();
				_queueManager.disconnect();
				Loggers.general().info(LOG, "MQ connection Disconnected!!!!!!!!!!!!!");
				swiftResp = status;
			} catch (com.ibm.mq.MQException mqex) {
				mqex.printStackTrace();
				Loggers.general().info(LOG, "MQException______________" + mqex);
				errormsg = mqex.getMessage();
				swiftResp = status + "|" + errormsg;
			} catch (java.io.IOException ioex) {
				ioex.printStackTrace();
				Loggers.general().info(LOG, "An MQ IO error occurred : " + ioex);
				errormsg = ioex.getMessage();
				swiftResp = status + "|" + errormsg;
			} catch (Exception e) {

				e.printStackTrace();
				Loggers.general().info(LOG, "An MQ IO error occurred : " + e);
				errormsg = "MQ Server Connection Error";
				swiftResp = status + "|" + errormsg;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Loggers.general().info(LOG, "An MQ IO error occurred : " + e);
			errormsg = "MQ Server Connection Error";
			swiftResp = status + "|" + errormsg;
		}

		Loggers.general().info(LOG, "MQ connect Ended" + swiftResp);

	}
	public static boolean pushMessage(String inputMessage) throws Exception {
		boolean result = false;

		javax.jms.Connection connection = null;
		// String jndiName="jdbc/Interface";

		// String jndiName="queue/tiSwiftOut";
		String jndiName = "";
		String outputQName = retreiveFromProperties("SWIFTOutputQName");
		jndiName = retreiveFromProperties("SwiftOutMQJndiName");
		System.out.println(" Theme transport client outputQName==>" + outputQName);

		try {

			connection = getMQJNDIConnection(jndiName);
			if (connection != null) {
				System.out.println("Connetion established==>");
				// create a queue session
				Session session = ((javax.jms.Connection) connection).createSession(false, Session.DUPS_OK_ACKNOWLEDGE);

				Queue queue = session.createQueue("queue:///" + outputQName);
				System.out.println("queue=1111111=>" + queue);
				// Create a MessageProducer from the Session to the Queue
				MessageProducer producer = session.createProducer(queue);
				System.out.println("queue=22222=>");
				producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

				// Create a message
				String line = inputMessage.replace("\n", "\r\n");
				TextMessage message = session.createTextMessage(line);
				System.out.println("In Push Message Function... Printing Message");
				System.out.println(message.getText());
				System.out.println("Before Push message into queue....." + outputQName);
				// Tell the producer to send the message
				producer.send(message);
				System.out.println("Successfully pushed message into queue....." + outputQName);
				result = true;
				producer.close();
				session.close();
			}

		} catch (JMSException e) {
			System.out.println("JNDI connection JMSException -->" + e.getMessage());
			result = false;
			swiftResponse = "Swift Message got failed while Pushing with Error message : "+ e.getMessage();
			e.printStackTrace();
		} 
		catch (Exception e) {
			System.out.println("JNDI connection Exception -->" + e.getMessage());
			swiftResponse = "Swift Message got failed while Pushing with Error message : "+ e.getMessage();
			result = false;
			e.printStackTrace();
		} finally {

			try {
				// close the queue connection
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static javax.jms.Connection getMQJNDIConnection(String jndiName) {
		javax.jms.Connection connection = null;
		try {

			System.out.println("Enter into JNDI connection setup...");

			InitialContext init = new InitialContext();
			ConnectionFactory connectionFactory = (ConnectionFactory) init.lookup(jndiName);

			System.out.println("JNDI connection fatory successfully created...");

			connection =  connectionFactory.createConnection();

			System.out.println("JNDI connection successfully created for..." + jndiName);

		} catch (Exception e) {
			System.out.println("JNDI connection Exception -->" + e.getMessage());
			swiftResponse = "Swift Message got failed while Pushing with Error message : "+ e.getMessage();
			e.printStackTrace();
		}
		return connection;

	}


	public static void updateSwiftOutStatus(String masterReference, String eventReference, String status, String swiftMessageResponse) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.UPDATE_SWIFTOUT_STATUS);
			pst.setString(1, status);
			pst.setString(2, swiftMessageResponse);
			pst.setString(3, masterReference);
			pst.setString(4, eventReference);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}

	}

	@SuppressWarnings("deprecation")
//	public static void getMQConnection() {
//		System.out.println("Readswiftinmessage started");
//
//		String portnum = BOCCommonUtil.retreiveFromProperties("SWIFTPort");
//		String hostname = BOCCommonUtil.retreiveFromProperties("SWIFTHostName");
//		String channel = BOCCommonUtil.retreiveFromProperties("SWIFTChannel");
//		String qManager = BOCCommonUtil.retreiveFromProperties("SWIFTQManager");
//		String inputQName = BOCCommonUtil.retreiveFromProperties("SWIFTInputqueue");
//		String userID = BOCCommonUtil.retreiveFromProperties("SWIFTUserID");
//		String password = BOCCommonUtil.retreiveFromProperties("SWIFTpassword");
//		String sslCipherSuite = retreiveFromProperties("SWIFTsslCipher");
//		if (sslCipherSuite.equals("")) {
//			sslCipherSuite = null;
//		}
//		int port = Integer.parseInt(portnum);
//
//		try {
//
//			System.out.println("MQ connect started//////" + port + " " + hostname + " " + channel + " " + qManager + " "
//					+ inputQName);
//			MQEnvironment.hostname = hostname;
//			MQEnvironment.channel = channel;
//			MQEnvironment.port = port;
//			MQEnvironment.userID = userID;
//			MQEnvironment.password = password;
//			MQEnvironment.sslCipherSuite = sslCipherSuite;
//			System.out.println("MQ Environment connected---");
//			MQQueueManager _queueManager = new MQQueueManager(qManager);
//
////           		Loggers.general().info(LOG, "MQ connect started queueManager:" + _queueManager);
//
//			System.out.println("MQ connect started queueManager:" + _queueManager);
////			int openOptions = MQC.MQOO_INQUIRE + MQC.MQOO_FAIL_IF_QUIESCING + MQC.MQOO_INPUT_SHARED;
//			int openOptions = MQC.MQOO_BROWSE + MQC.MQOO_INPUT_SHARED;
//			try {
//
//				MQQueue queue = _queueManager.accessQueue(inputQName, openOptions, qManager, null, null);
//				System.out.println("MQ access queue started///////////_queueManager:" + _queueManager);
//				System.out.println("MQget v1.0 connected-----");
//
////				MQMessage recvmsg = new MQMessage();
//				MQGetMessageOptions getOptions = new MQGetMessageOptions();
//
//				System.out.println("MQmessageoptions---");
////		        getOptions.options = MQC.MQGMO_NO_WAIT + MQC.MQGMO_FAIL_IF_QUIESCING +  MQC.MQGMO_CONVERT;
////		        getOptions.options = MQC.MQGMO_NO_WAIT + MQC.MQGMO_FAIL_IF_QUIESCING;
//				getOptions.options = MQC.MQOO_INPUT_AS_Q_DEF;
//				getOptions.waitInterval = MQC.MQWI_UNLIMITED; // wait up to 5 seconds
//				System.out.println("getoptions---");
//				boolean getMore = true;
//				while (getMore) {
////		            MQMessage message = new MQMessage();
////		        	receiveMsg = new MQMessage();
//					System.out.println("Entering While---");
//					MQMessage receiveMsg = new MQMessage();
//					System.out.println("receivedmessage---");
//					try {
//						System.out.println("inside try ---");
//						queue.get(receiveMsg, getOptions);
//						System.out.println("Message get Successfully---");
//						byte[] b = new byte[receiveMsg.getMessageLength()];
//						receiveMsg.readFully(b);
//						System.out.println(new String(b));
//						receiveMsg.clearMessage();
//						System.out.println("Message cleared Successfully---");
//
//						if (b != null) {
//							// onMessage(msg);
//							TextMessage m = (TextMessage) receiveMsg;
//							System.out.println("Input Message format-------" + m);
//							SwiftInImpl aSwiftInImpl = new SwiftInImpl();
//							System.out.println("SwiftIncomingmessageprocessstarted-------");
//							aSwiftInImpl.processSwiftInMessage(m.getText());
//						}
//					}
//
//					catch (Exception e) {
//						System.out.println("Exception in Getting message: " + e.getMessage());
//					}
//
//				}
//
//			} catch (MQException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//		catch (Exception e) {
//			System.out.println("Exception in connecting incoming queue----");
//		}
//
//	}

	public static String ToTIACCTypeTranslate(String AccountType) {

		String NewAccountType = null;

		if (AccountType.equalsIgnoreCase("DD")) {
			NewAccountType = "CA";
		} else if (AccountType.equalsIgnoreCase("SV")) {
			NewAccountType = "SA";
		} else {
			NewAccountType = "GL";
		}

		return NewAccountType;
	}

	public static boolean isFilesPresent(String filePath) {

		boolean result = false;

		File file = new File(BOCCommonUtil.retreiveFromProperties(filePath));
		System.out.println("GETTING FILE PATH" + filePath);
		File[] listOfFiles = file.listFiles();

		System.out.println("List of files length -->" + listOfFiles.length);
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				result = true;
			}
		}

		return result;

	}
	public static void updateUnBalancedPostingResponseEntries(String masterRef, String eventRef) {
		Connection aConnection = null;
		PreparedStatement pst = null;



		try {
		System.out.println("Entering into Posting Response Log Table");
		aConnection = CommonUtil.DBConnection();



		System.out.println("masterRef -" + masterRef);
		System.out.println("eventRef -" + eventRef);
		pst = aConnection.prepareStatement(QueryConstants.UPDATE_UNBALANCED_POSTING_ENTRIES);
		pst.setString(1, masterRef + "-" + eventRef);
		pst.executeUpdate();



		System.out.println("masterRef after update -" + masterRef);
		System.out.println("eventRef after update-" + eventRef);



		} catch (SQLException e) {
		e.printStackTrace();



		} finally {
		CommonUtil.CloseConnection(aConnection, pst, null);
		}



		}
	public static boolean isValidString(String value) {
		if (value == null || "".equalsIgnoreCase(value))
		return false;



		return true;
		}

}
