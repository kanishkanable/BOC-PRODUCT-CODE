package com.ett.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.mail.internet.InternetAddress;
import javax.naming.InitialContext;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ett.backofficebatch.BackOfficeBatchBankReqModel;
import com.ett.swift.outgoing.SwiftOutRequestModel;
import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.misys.tiplus2.apps.ti.service.messages.*;
import com.misys.tiplus2.foundations.lang.logging.Loggers;
import com.misys.tiplus2.services.control.ServiceRequest.RequestHeader;
import com.misys.tiplus2.services.control.ServiceRequest.RequestHeader.Credentials;
import com.misys.tiplus2.services.control.ServiceResponse;
import com.misys.tiplus2.services.control.ServiceResponse.ResponseHeader;
import com.misys.tiplus2.services.control.ServiceResponse.ResponseHeader.Details;
import com.misys.tiplus2.services.control.ServiceRequest;
import com.misys.tiplus2.services.control.StatusEnum;

public class BOCCommonUtil {

	private static final Logger LOG = LoggerFactory.getLogger(BOCCommonUtil.class);

	private static JAXBContext jaxbTIContext = null;

	private static Object emailAddress;
	public static String swiftResponse = "Swift message has pushed successfully";

	public static String getNextSeqNo(String seqName) {

		System.out.println("Entered into  getNextSeqNo....");

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

	public static boolean insertTIReqToLogTable(String tiRequestXML, String table, String status, String pkey) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		boolean val = false;

		Loggers.general().info(LOG, "Table " + table + " Status " + status + " pkey " + pkey);

		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement("INSERT INTO " + table + QueryConstants.INSERT_INTO_FTISTAGING);
			System.out.println(
					"USD clear table fetching====>" + "INSERT INTO " + table + QueryConstants.INSERT_INTO_FTISTAGING);
			pst.setString(1, pkey);
			pst.setString(2, tiRequestXML);
			pst.setString(3, status);
			pst.executeUpdate();
			val = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Exception in insert in USD clear table--->" + e.getMessage());
			val = false;
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}
		return val;
	}

	public static boolean updateTIResToLogTable(String tiResponseXML, String table, String status, String pkey) {
		Connection aConnection = null;
		PreparedStatement pst = null;
		boolean val = false;

		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement("UPDATE " + table + QueryConstants.UPDATE_INTO_FTISTAGING);
			pst.setString(1, tiResponseXML);
			pst.setString(2, status);
			pst.setString(3, pkey);
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

	public static boolean CustomerAccountCheck(String AccountType) {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		boolean val = false;
		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.CHECK_FOR_CUSTOMER_ACCOUNT + AccountType + "%'");
			Loggers.general().info(LOG, "Query " + QueryConstants.CHECK_FOR_CUSTOMER_ACCOUNT + AccountType + "%'");
			rst = pst.executeQuery();
			while (rst.next()) {
				Loggers.general().info(LOG, "Customer Account" + rst.getString(1));
				if (rst.getString(1).equalsIgnoreCase("TRUE"))
					val = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			val = false;
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		return val;
	}

	public static String callCoreBankingWebService(String bankRequestXML) throws MalformedURLException, IOException {

		String responseString = "";
		String wsURL = retreiveFromProperties(Constants.COREBANKING_URL);
		Loggers.general().info(LOG, "END Point URL " + wsURL);
		PostMethod post = new PostMethod(wsURL);
		StringRequestEntity requestEntity = new StringRequestEntity(bankRequestXML, "text/xml", "UTF-8");
		post.setRequestEntity(requestEntity);
		HttpClient httpclient = new HttpClient();
		try {
			int result = httpclient.executeMethod(post);
			if (result != 200) {
				throw new Exception("Server returned errror code " + result);
			}
			responseString = post.getResponseBodyAsString();
			Loggers.general().info(LOG, "response XML : " + responseString);
			if (responseString == null || responseString.trim().equals("")) {
				throw new Exception("Server did not return any result");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			post.releaseConnection();
		}

		return responseString;

	}

	public static ResponseHeader getHeader(String correlationId, String Operation, String service, String failureMsg,
			String status, List<String> error, List<String> warning) {
		ResponseHeader header = new ResponseHeader();
		header.setCorrelationId(correlationId);
		header.setOperation(Operation);
		header.setService(service);

		if (status.equals("SUCCESS")) {
			header.setStatus(StatusEnum.SUCCEEDED);
		} else {
			header.setStatus(StatusEnum.FAILED);
			Details aDetail = new Details();
			if (error != null) {
				for (int i = 0; i < error.size(); i++) {
					aDetail.getError().add("[I] " + error.get(i));
				}
			}
			if (warning != null) {
				for (int i = 0; i < warning.size(); i++) {
					aDetail.getWarning().add("[I] " + warning.get(i));
				}
			}
			header.setDetails(aDetail);
		}
		Loggers.general().info(LOG, "Completed Successfully getHeader");
		return header;
	}

	public static String executeQuery(String query) {

		String result = null;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(query);
			rst = pst.executeQuery();
			while (rst.next())
				result = rst.getString(1);
			if (result == null) {
				result = " ";
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return result;
	}

	public static JAXBContext getJAXBTIContext() {
		if (jaxbTIContext == null) {
			synchronized (JAXBContext.class) {
				if (jaxbTIContext == null) {

					try {
						jaxbTIContext = JAXBContext.newInstance("com.misys.tiplus2.apps.ti.service.messages");
					} catch (JAXBException e) {
						Loggers.general().error(LOG, e.getMessage());
						System.out.println("Error in the jaxbiTI context");
					}
				}
			}
		}
		return jaxbTIContext;
	}

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
			Loggers.general().info(LOG, "Value Retreived is " + value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return value;

	}

	public static List<ServiceRequest> extractPostingEntries(String tiRequestXML, String RequestName) {

		System.out.println("Entered into extractPostingEntries for balanceverify....");

		Document document = null;
		List<ServiceRequest> bsrList = null;

		try {
			String str = tiRequestXML;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new ByteArrayInputStream(str.getBytes()));
			is.setEncoding("ISO-8859-1");
			document = db.parse(is);
			JAXBContext jaxbContext = BOCCommonUtil.getJAXBTIContext();
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			NodeList nodeList = document.getElementsByTagNameNS("*", RequestName);
			JAXBElement<BulkServiceRequest> bulkServiceJAXB = (JAXBElement<BulkServiceRequest>) jaxbUnmarshaller
					.unmarshal(nodeList.item(0), BulkServiceRequest.class);
			BulkServiceRequest bsr = bulkServiceJAXB.getValue();
			bsrList = bsr.getServiceRequest();

			Loggers.general().info(LOG, "List Size --> " + bsrList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bsrList;
	}

	public static String getAuthotizerID(String EventKey) {

		String UserName = null;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.FETCH_AUTH_USER_ID);
			pst.setString(1, EventKey);
			rst = pst.executeQuery();
			while (rst.next())
				UserName = rst.getString(1);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return UserName;
	}

	public static String AccountTypeTranslate(String AccountType) {

		String NewAccountType = null;

		if (AccountType.equalsIgnoreCase("SA")) {
			NewAccountType = "SV";
		} else if (AccountType.equalsIgnoreCase("CA")) {
			NewAccountType = "DD";
		} else {
			NewAccountType = "GL";
		}

		return NewAccountType;
	}

	public static String DebitCreditTranslate(String DrCrCode) {

		String NewAccountType = null;

		if (DrCrCode.equalsIgnoreCase("D")) {
			NewAccountType = "6";
		} else if (DrCrCode.equalsIgnoreCase("C")) {
			NewAccountType = "0";
		}

		return NewAccountType;
	}

	public static BigDecimal getFXRate(String EventKey) {
		BigDecimal ExchangeRate = BigDecimal.ZERO;
		;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.FETCH_EXCHANGE_RATE);
			pst.setString(1, EventKey);
			rst = pst.executeQuery();
			while (rst.next()) {
				if (!(rst.getString(1) == null))
					ExchangeRate = rst.getBigDecimal(1);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return ExchangeRate;
	}

	public static BigDecimal getSpotRate(String Currency, String ZoneID) {
		BigDecimal ExchangeRate = BigDecimal.ZERO;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.FETCH_SPOT_RATE);
			pst.setString(1, Currency);
			pst.setString(2, ZoneID);
			rst = pst.executeQuery();
			while (rst.next())
				ExchangeRate = rst.getBigDecimal(1);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return ExchangeRate;
	}

	public static String getCurrencyCode(String Currency) {

		String CurrencyCode = null;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.FETCH_CURRENCY_CODE);
			pst.setString(1, Currency);
			rst = pst.executeQuery();
			while (rst.next())
				CurrencyCode = rst.getString(1);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return CurrencyCode;
	}

	public static LinkedHashMap<String, String> getMetaMap(String metaData, String Utility) {
		System.out.println("Entered into get metamap " + metaData);
		StringReader strReader = new StringReader(metaData);
		BufferedReader reader = new BufferedReader(strReader);
		LinkedHashMap<String, String> metaMap = new LinkedHashMap<>();
		String body = null;
		boolean textContent = false;
		try {
			String line = reader.readLine();
			while (line != null) {
				System.out.println("Ëntered into while");
				if (textContent)
					body = String.valueOf(body) + "\n" + line.trim();
				if (line.startsWith("SUBJECT:"))
					metaMap.put("SUBJECT", line.substring(8).trim());
				if (line.startsWith("MASTERREFERENECE:"))
					metaMap.put("MASTERREFERENECE", line.substring(17).trim());
				if (line.startsWith("EVENTREFERENCE:"))
					metaMap.put("EVENTREFERENCE", line.substring(15).trim());
				if (line.startsWith("PRINCIPALPARTY:"))
					metaMap.put("PRINCIPALPARTY", line.substring(15).trim());
				if (line.startsWith("PRODSUBTYPE:"))
					metaMap.put("PRODSUBTYPE", line.substring(12).trim());

				if (line.startsWith("PARTY:"))
					metaMap.put("PARTY", line.substring(6).trim());

				if (line.startsWith("TO:")) {
					metaMap.put("PARTYMAILTO", line.substring(3).trim());
				}
				if (line.startsWith("TEXT:")) {
					body = line.substring(5).trim();
					textContent = true;
				}
				line = reader.readLine();

			}
			if (metaMap != null) {
				metaMap.put("TEXT", body);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return metaMap;
	}

	public static byte[] getDocumentFromDbd(String dmsId) {
		Loggers.general().info(LOG, "Entered into getdocument from Dbd");
		Connection aConnection = null;
		PreparedStatement aStatement = null;
		ResultSet aResultset = null;
		Blob blob = null;
		byte[] blobAsBytes = null;
		try {
			aConnection = CommonUtil.DBConnection();
			aStatement = aConnection.prepareStatement("select * from CMS_ITEM where item_id = '" + dmsId + "'");
			aResultset = aStatement.executeQuery();
			while (aResultset.next()) {
				blob = aResultset.getBlob("item");
				int blobLength = (int) blob.length();
				blobAsBytes = blob.getBytes(1, blobLength);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, aStatement, aResultset);
		}
		return blobAsBytes;
	}

	public static String getMultiplevalues(String CustomerName, String ProductType, String note) {
		String value = null;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		String businessArea = getImportExport(ProductType);
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.FETCH_SPECIAL_INSTRUCTIONS);
			pst.setString(1, CustomerName);
			pst.setString(2, businessArea);
			pst.setString(3, note);
			rst = pst.executeQuery();
			while (rst.next())
				value = rst.getString(1);
			System.out.println("2nd Method Mobile Number" + value);
			if (value == null)
				value = " ";

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return value;
	}

	public static String getSwiftMsgType(String swiftIn) {

		String swiftCode = "";
		int indexCode = swiftIn.indexOf("2:");
		if (indexCode >= 0 && indexCode < swiftIn.length()) {
			swiftCode = swiftIn.substring(indexCode + 3, indexCode + 6);
		}
		return swiftCode;
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

	public static void updateSwiftInData(String pkey, String swiftMessageType, String tiRequestXML,
			String tiResponseXML) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.UPDATE_SWIFTIN_TABLE);
			pst.setString(1, swiftMessageType);
			pst.setString(2, tiRequestXML);
			pst.setString(3, tiResponseXML);
			pst.setString(4, pkey);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}

	}

	public static String getMasterEventRef(String correlationID) {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		String masterRef = null;
		String eventRef = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.getMasterEventRefbySwiftCorrID + correlationID + "'");
			Loggers.general().info(LOG,
					"Query -> " + QueryConstants.getMasterEventRefbySwiftCorrID + correlationID + "'");
			rst = pst.executeQuery();
			while (rst.next()) {

				masterRef = rst.getString(1);
				eventRef = rst.getString(2);
				Loggers.general().info(LOG, masterRef + " " + eventRef);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		return masterRef + "," + eventRef;

	}

	public static boolean getpostings(String masterReference, String eventReference) {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		boolean postingStatus = false;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.ifPostingAvailable);
			pst.setString(1, masterReference);
			pst.setString(2, eventReference);
			rst = pst.executeQuery();
			while (rst.next()) {
				if (rst.getInt(1) > 0)
					postingStatus = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return postingStatus;
	}

	public static void inserttoSwiftOutTable(String swiftPkey, String pkey, String masterReference,
			String eventReference, String swiftOut, String swiftStatus, String swiftMessageResponse) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.INSERTINTO_SWIFTOUT_TABLE);
			pst.setString(1, swiftPkey);
			pst.setString(2, pkey);
			pst.setString(3, masterReference);
			pst.setString(4, eventReference);
			pst.setString(5, swiftOut);
			pst.setString(6, swiftStatus);
			pst.setString(7, swiftMessageResponse);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}
	}

	public static String checkPostingStatus(String masterReference, String eventReference) {

		String status = Constants.STATUS_HOLD;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.CHECK_POSTING_STATUS);
			pst.setString(1, masterReference);
			pst.setString(2, eventReference);
			rst = pst.executeQuery();
			while (rst.next()) {
				if (rst.getString(1).equalsIgnoreCase(Constants.STATUS_SUCCEEDED))
					status = Constants.STATUS_SUCCEEDED;
				else if (rst.getString(1).equalsIgnoreCase(Constants.STATUS_FAILED))
					status = Constants.STATUS_FAILED;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return status;
	}

	public static List<String> getSwiftOutMessage(String masterReference, String eventReference) {

		List<String> swiftOutMessage = new LinkedList<>();

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.GET_SWIFTOUT_FROM_DB);
			pst.setString(1, masterReference);
			pst.setString(2, eventReference);
			rst = pst.executeQuery();
			while (rst.next()) {
				swiftOutMessage.add(rst.getString(1));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return swiftOutMessage;
	}

	public static void updateSwiftOutStatus(String masterReference, String eventReference, String status) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.UPDATE_SWIFTOUT_STATUS);
			pst.setString(1, masterReference);
			pst.setString(2, eventReference);
			pst.setString(3, status);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}

	}

	public static void UpdateProperties(String value, String key) {

		Connection aConnection = null;
		PreparedStatement pst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.UPDATE_INTERFACE_PROPERTIES);
			pst.setString(1, value);
			pst.setString(2, key);
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}

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

	public static void updateReferenceinLog(String MasterRef, String EventRef, String table, String pkey) {

		System.out.println("Entered into updateReferenceinLog method....");

		Connection aConnection = null;
		PreparedStatement pst = null;
		try {

			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement("UPDATE " + table + QueryConstants.UPDATE_MASTER_EVENT_REF);
			pst.setString(1, MasterRef);
			pst.setString(2, EventRef);
			pst.setString(3, pkey);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}

	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
			Loggers.general().info(LOG, "<<<<<" + email + ">>>>>");
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public static void sendMessagetoMQ(String message, String qConnectionFactory, String queue) {
		try {

			QueueConnection con = CommonUtil.getMQConnectionFactory(qConnectionFactory);
			con.start();
			QueueSession ses = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue que = CommonUtil.getMQConnection(queue);
			QueueSender sender = ses.createSender(que);
			TextMessage msg = ses.createTextMessage();
			msg.setText(message);
			sender.send(msg);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void writeMQMessage(String inputMessage) throws IOException {

		Loggers.general().info(LOG, "Inside write Mq message");
		// Loggers.general().info(LOG, "Input message from FTI-----" + inputMessage);
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
			int openOptions = MQC.MQOO_OUTPUT + MQC.MQOO_FAIL_IF_QUIESCING;
			try {

				MQQueue queue = _queueManager.accessQueue(outputQName, openOptions, null, null, null);
				Loggers.general().info(LOG, "MQ connect started///////////_queueManager:" + _queueManager);
				Loggers.general().info(LOG, "MQWrite v1.0 connected");

				MQMessage sendmsg = new MQMessage();
				MQPutMessageOptions pmo = new MQPutMessageOptions();

				String line = inputMessage.replace("\n", "\r\n");
				Loggers.general().info(LOG, "swift out" + inputMessage.replace("\n", "\r\n"));
				System.out.println("swift out" + inputMessage.replace("\n", "\r\n"));

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

	//public static boolean pushMessage(String inputMessage) throws Exception {
		/*
		 * boolean result = false;
		 * 
		 * Connection connection = null; //String jndiName="jdbc/Interface";
		 * 
		 * //String jndiName="queue/tiSwiftOut"; String jndiName=""; String outputQName
		 * = retreiveFromProperties("SWIFTOutputQName"); jndiName =
		 * retreiveFromProperties("SwiftOutMQJndiName");
		 * System.out.println(" Theme transport client outputQName==>"+outputQName);
		 * 
		 * try {
		 * 
		 * connection = getMQJNDIConnection(jndiName); if (connection != null) {
		 * System.out.println("Connetion established==>"); // create a queue session
		 * Session session = ((javax.jms.Connection)
		 * connection).createSession(false,Session.DUPS_OK_ACKNOWLEDGE);
		 * 
		 * Queue queue = session.createQueue("queue:///" + outputQName);
		 * System.out.println("queue=1111111=>"+queue); // Create a MessageProducer from
		 * the Session to the Queue MessageProducer producer =
		 * session.createProducer(queue); System.out.println("queue=22222=>");
		 * producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		 * 
		 * // Create a message TextMessage message =
		 * session.createTextMessage(inputMessage);
		 * System.out.println("In Push Message Function... Printing Message");
		 * System.out.println(message.getText());
		 * System.out.println("Before Push message into queue....." + outputQName); //
		 * Tell the producer to send the message producer.send(message);
		 * System.out.println("Successfully pushed message into queue....." +
		 * outputQName); result = true; producer.close(); session.close(); }
		 * 
		 * } catch (JMSException e) {
		 * System.out.println("JNDI connection JMSException -->" + e.getMessage());
		 * e.printStackTrace(); } finally {
		 * 
		 * try { // close the queue connection if (connection != null)
		 * connection.close(); } catch (Exception e) { e.printStackTrace(); } }
		 * 
		 * return result;
		 */
	//}





	/*
	 * public static Connection getMQJNDIConnection(String jndiName) { Connection
	 * connection = null; try {
	 * 
	 * System.out.println("Enter into JNDI connection setup...");
	 * 
	 * InitialContext init = new InitialContext(); ConnectionFactory
	 * connectionFactory = (ConnectionFactory) init .lookup(jndiName);
	 * 
	 * System.out.println("JNDI connection fatory successfully created...");
	 * 
	 * connection = (java.sql.Connection) connectionFactory.createConnection();
	 * 
	 * System.out.println("JNDI connection successfully created for..." + jndiName);
	 * 
	 * } catch (Exception e) { System.out.println("JNDI connection Exception -->" +
	 * e.getMessage()); e.printStackTrace(); } return connection;
	 * 
	 * }
	 * 
	 */	
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

		}catch (JMSException e) {
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
			e.printStackTrace();
		}
		return connection;

	}



	
	

	public static List<SwiftOutRequestModel> getHoldSwifts() {

		List<SwiftOutRequestModel> aSwiftOutRequestModel = new LinkedList<>();

		SwiftOutRequestModel srModel = new SwiftOutRequestModel();
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.CHECK_SWIFT_HOLD_STATUS);
			rst = pst.executeQuery();
			while (rst.next()) {

				srModel.setMasterReference(rst.getString(1));
				srModel.setEventReference(rst.getString(2));
				aSwiftOutRequestModel.add(srModel);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return aSwiftOutRequestModel;
	}

	public static List<String> getFormattedSwiftOut(String swiftOut) {
		List<String> formattedSwiftOut = new LinkedList<>();
		// added by mani
		System.out.println("formattedSwiftOut list....");
		// added by mani
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			dbf.setNamespaceAware(true);
			// added by mani
			System.out.println("Document Builder Factory....");
			// added by mani
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new ByteArrayInputStream(swiftOut.getBytes()));
			JAXBContext context = BOCCommonUtil.getJAXBTIContext();
			Unmarshaller unmarshaller = context.createUnmarshaller();
			NodeList nodeList = document.getElementsByTagNameNS("*", "SwiftOut");
			JAXBElement<SWOPFType> swiftOutJAXB = (JAXBElement<SWOPFType>) unmarshaller.unmarshal(nodeList.item(0),
					SWOPFType.class);
			SWOPFType source = swiftOutJAXB.getValue();
			int swiftsize = source.getMessages().getMessage().size();
			for (int i = 0; i < swiftsize; i++) {
				formattedSwiftOut.add(source.getMessages().getMessage().get(i));
			}
			// System.out.println("Swift Out Formatinh"+formattedSwiftOut);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return formattedSwiftOut;
	}

	public static boolean rtgsTransaction(String masterReference, String eventReference) {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		boolean isRTGS = false;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.ifRTGS);
			pst.setString(1, masterReference);
			pst.setString(2, eventReference);
			rst = pst.executeQuery();
			while (rst.next()) {
				if (rst.getInt(1) > 0)
					isRTGS = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return isRTGS;
	}

	public static void insertPostingEntrytoTable(BackOfficeBatchBankReqModel brl, String pkey, String fileName) {

		String postingKey = BOCCommonUtil.getNextSeqNo(Constants.POSTING_SEQUENCE);

		System.out.println(" Entering into insertPostingEntrytoTable  ....");
		Connection aConnection = null;
		PreparedStatement pst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.INSERT_POSTING_ENTRIES);
			System.out.println("INSERT_POSTING_ENTRIES...." + QueryConstants.INSERT_POSTING_ENTRIES);
			pst.setString(1, postingKey);
			pst.setString(2, pkey);
			pst.setString(3, brl.getBranchNumber());
			pst.setString(4, brl.getDebitCreditCode());
			pst.setString(5, brl.getEffectiveDate());
			pst.setString(6, brl.getAccountType());
			int account = brl.getAccountNumber().indexOf("-");
			String bo_accountno = brl.getAccountNumber();
			System.out.println("BOAccountnumberindex  ...." + account);
			if (account > 0)
				bo_accountno = brl.getAccountNumber().substring(0, account);
			pst.setString(7, StringUtils.leftPad(bo_accountno, 12, Constants.ZERO));
			int tranAmt = brl.getTransactionAmount().toString().indexOf(".");
			String tranAmtVal = brl.getTransactionAmount().toString();
			if (tranAmt > 0)
				tranAmtVal = tranAmtVal.substring(0, tranAmt);
			tranAmtVal=StringUtils.leftPad(tranAmtVal, 17, Constants.ZERO).substring(0, 17);
           // tranAmtVal=tranAmtVal+getCurrency(brl.getForeignCurrencyCode());
			//pst.setString(8, StringUtils.leftPad(tranAmtVal, 17, Constants.ZERO).substring(0, 17));
            pst.setString(8,tranAmtVal);
			int lceAmt = brl.getLCEAmount().toString().indexOf(".");
			String lceAmtVal = brl.getLCEAmount().toString();
			if (lceAmt > 0)
				lceAmtVal = lceAmtVal.substring(0, lceAmt);

			pst.setString(9, StringUtils.leftPad(lceAmtVal, 17, Constants.ZERO).substring(0, 17));
			pst.setString(10, (StringUtils.rightPad(brl.getForeignCurrencyCode(), 3, Constants.ZERO)));
			int frgnRte = brl.getForeignCurrencyRate().toString().indexOf(".");
			String frgnRteVal = brl.getForeignCurrencyRate().toString();
			if (frgnRte > 0)
				frgnRteVal = frgnRteVal.substring(0, frgnRte);

			pst.setString(11, StringUtils.leftPad(frgnRteVal, 15, Constants.ZERO).substring(0, 15));
			pst.setString(12, brl.getReferenceNumber());
			pst.setString(13, brl.getAlphabeticData1());
			pst.setString(14, brl.getAlphabeticData2());
			pst.setString(15, brl.getAlphabeticData3());
			pst.setString(16, brl.getTransactionTime());
			pst.setString(17, brl.getCIFKey());
			pst.setString(18, brl.getMadeBy());
			pst.setString(19, brl.getApprovedBy());
			pst.setString(20, brl.getAdditionalFCYInfo());
			pst.setString(21, brl.getOriginationInfo());
			pst.setString(22, brl.getForeignCurrencyOverride());
			pst.setString(23, brl.getApplicationTranCode());
			pst.setString(24, brl.getAuthorisedUserID());
			pst.setString(25, brl.getDealerNumber());
			pst.setString(26, brl.getProcessingDate());
			pst.setString(27, Constants.STATUS_RECEIVED);
			pst.setString(28,getCurrency(StringUtils.rightPad(brl.getForeignCurrencyCode(), 3, Constants.ZERO)));
			pst.setString(29, fileName);
			System.out.println("Constants.STATUS_RECEIVED...." + Constants.STATUS_RECEIVED);
			pst.executeUpdate();
			System.out.println("pst.executeUpdate()Insertioncompleted....");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, null);
		}

	}

	public static String getMobileEmail(String principalparty, String columnName) {

		String value = null;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement("SELECT " + columnName + QueryConstants.FETCH_MOBILEEMAIL);
			pst.setString(1, principalparty);
			rst = pst.executeQuery();
			while (rst.next())
				value = rst.getString(1);
			System.out.println("1st Method Mobile Number " + value);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return value;
	}

	public static String getImportExport(String product) {
		String result = null;
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection
					.prepareStatement("SELECT KEY FROM INTERFACEPROPERTIES WHERE VALUE LIKE '%" + product + "%'");
			rst = pst.executeQuery();
			while (rst.next())
				result = rst.getString(1);
			System.out.println("Product Business is " + result);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		return result;
	}

	public static String constructTIResponseXML(String tiRequestXML) {

		List<String> error = new LinkedList<>();
		List<String> warning = new LinkedList<>();
		ByteArrayOutputStream outStream = null;
		String status = Constants.STATUS_SUCCEEDED;

		StringBuilder sb = new StringBuilder();

		InputStream inStream = new ByteArrayInputStream(tiRequestXML.getBytes());
		JAXBContext context = BOCCommonUtil.getJAXBTIContext();
		Unmarshaller unmarshaller;
		try {
			unmarshaller = context.createUnmarshaller();
			ServiceRequest serviceRequest = (ServiceRequest) unmarshaller.unmarshal(inStream);
			com.misys.tiplus2.apps.ti.service.messages.ObjectFactory of = new com.misys.tiplus2.apps.ti.service.messages.ObjectFactory();
			ServiceResponse serviceResponse = new ServiceResponse();
			serviceResponse.setResponseHeader(getHeader(serviceRequest.getRequestHeader().getCorrelationId(),
					serviceRequest.getRequestHeader().getOperation(), serviceRequest.getRequestHeader().getService(),
					sb.toString(), status, error, warning));
			List<JAXBElement<?>> batchRequestList = serviceRequest.getRequest();
			@SuppressWarnings("unchecked")
			JAXBElement<BulkServiceRequest> bsr = (JAXBElement<BulkServiceRequest>) batchRequestList.get(0);
			List<ServiceRequest> bsrlist = bsr.getValue().getServiceRequest();
			List<JAXBElement<?>> sres = serviceResponse.getResponse();
			BulkServiceResponse localResponse = new BulkServiceResponse();
			List<ServiceResponse> batchResponse = localResponse.getServiceResponse();
			for (ServiceRequest sr : bsrlist) {
				ServiceResponse bulkServiceResponse = new ServiceResponse();
				bulkServiceResponse.setResponseHeader(
						getHeader(sr.getRequestHeader().getCorrelationId(), sr.getRequestHeader().getOperation(),
								sr.getRequestHeader().getService(), sb.toString(), status, error, warning));
				batchResponse.add(bulkServiceResponse);
			}
			sres.add(of.createVerifyResponse(localResponse));
			JAXBContext jaxbContext = BOCCommonUtil.getJAXBTIContext();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			outStream = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(serviceResponse, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outStream.toString();
	}

	public static List<String> getLimitVal(String customerNo) {

		List<String> value = new LinkedList<String>();
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.limitFetch);
			pst.setString(1, customerNo);
			rst = pst.executeQuery();
			while (rst.next()) {
				value.add(rst.getString(1));
				value.add(rst.getString(2));
				value.add(rst.getString(3));
				value.add(rst.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}

		return value;

	}

	public static String constructTIResponseXML(String TiRequestXML, String status) {

		String responseXML = "<?xml version=\"1.0\" standalone=\"yes\"?>\r\n"
				+ "<ServiceResponse xmlns='urn:control.services.tiplus2.misys.com'>\r\n" + " <ResponseHeader>\r\n"
				+ " <Service>GATEWAY</Service>\r\n" + " <Operation>${Operation}</Operation>\r\n"
				+ " <Status>${Status}</Status>\r\n" + " <CorrelationId>${CorrelationID}</CorrelationId>\r\n"
				+ " </ResponseHeader>\r\n" + "</ServiceResponse>";

		try {

			String operataionTI = CommonUtil.getTagValue("Operation", TiRequestXML);
			String CorrelationId = CommonUtil.getTagValue("CorrelationId", TiRequestXML);
			responseXML = responseXML.replace("${Operation}", operataionTI);
			responseXML = responseXML.replace("${Status}", status);
			responseXML = responseXML.replace("${CorrelationID}", CorrelationId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseXML;
	}

	public static String constructWatchlistTIResponseXML(String status) {

		String responseXML = "<?xml version=\"1.0\" standalone=\"yes\"?>\r\n"
				+ "<ServiceResponse xmlns='urn:control.services.tiplus2.misys.com'>\r\n" + " <ResponseHeader>\r\n"
				+ " <Service>TI</Service>\r\n" + " <Operation></Operation>\r\n" + " <Status>${Status}</Status>\r\n"
				+ " <CorrelationId>${CorrelationID}</CorrelationId>\r\n" + " </ResponseHeader>\r\n"
				+ "</ServiceResponse>";

		try {

			// String operataionTI = CommonUtil.getTagValue("Operation", TiRequestXML);
			String CorrelationId = CommonUtil.generateCorrelationId();
			// responseXML = responseXML.replace("${Operation}", operataionTI);
			responseXML = responseXML.replace("${Status}", status);
			responseXML = responseXML.replace("${CorrelationID}", CorrelationId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseXML;
	}

	public static String getReferencefromKey(Long masterKey) {

		String masterRef = null;

		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement("SELECT TRIM(MASTER_REF) FROM MASTER WHERE KEY97 = '" + masterKey + "'");
			rst = pst.executeQuery();
			while (rst.next()) {
				masterRef = rst.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		return masterRef;
	}

	public static boolean isValidString(String String) {
		if (String == null || "".equalsIgnoreCase(String) || String.isEmpty())
			return false;

		return true;
	}

}
