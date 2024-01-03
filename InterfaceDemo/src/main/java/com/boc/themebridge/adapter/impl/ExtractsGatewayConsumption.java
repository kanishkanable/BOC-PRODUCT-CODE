package com.boc.themebridge.adapter.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boc.themebridge.token.util.MapTokenResolver;
import com.boc.themebridge.token.util.TokenReplacingReader;
import com.ett.util.CommonUtil;

public class ExtractsGatewayConsumption {

	private final static Logger logger = Logger
			.getLogger(ExtractsGatewayConsumption.class.getName());

	public String process(String tirequestXML) throws Exception {

		logger.info(" ************ GATEWAY.Extracts adaptee process started ************ ");
		String status = "";
		String service = "";
		String operation = "";
		String masterKey = "";
		String eventKey = "";
		String prodType = "";
		String eventType = "";
		String masterRef = "";
		String eventRef = "";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		int logResult = 0;
		String tiResponse = "";
		try {
			status = "R";
			service = XPathParsing.getValue(tirequestXML,
					ExtractsGatewayXpath.Service);
			operation = XPathParsing.getValue(tirequestXML,
					ExtractsGatewayXpath.Operation);
			String requestOperation = "/ServiceRequest/"
					+ operation.toLowerCase();
			masterRef = XPathParsing.getValue(tirequestXML, requestOperation
					+ "/MasterRef");
			eventRef = XPathParsing.getValue(tirequestXML, requestOperation
					+ "/EventRef");
			prodType = XPathParsing.getValue(tirequestXML, requestOperation
					+ "/ProdTypeCode");
			eventType = XPathParsing.getValue(tirequestXML, requestOperation
					+ "/EventCode");

		} catch (Exception e) {
			logger.error("Exception while parse request XML..!! "
					+ e.getMessage());
			e.printStackTrace();

		}

		try {
			logger.info("Getting master and event key process started");
			connection = CommonUtil.DBConnection();
			String getMasterEventKeyQuery = ActionQueryConstants.GET_MASTER_EVENT_KEY;
			prepareStatement = connection
					.prepareStatement(getMasterEventKeyQuery);
			prepareStatement.setString(1, masterRef);
			prepareStatement.setString(2, eventRef);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				masterKey = resultSet.getString("MASTER_KEY");
				eventKey = resultSet.getString("EVENT_KEY");
			}
			logger.info("master key -->" + masterKey);
			logger.info("event key -->" + eventKey);
			logger.info("Getting master and event key process completed");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(connection, prepareStatement, null);
		}
		try {
			logger.info("Checking if the master , event combination already exists - process started");
			connection = CommonUtil.DBConnection();
			String isMasterEventKeyPresentQuery = ActionQueryConstants.IS_MASTER_EVENT_KEY_PRESENT;
			prepareStatement = connection
					.prepareStatement(isMasterEventKeyPresentQuery);
			prepareStatement.setString(1, masterKey);
			prepareStatement.setString(2, eventKey);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				status = resultSet.getString("STATUS");
				logger.info("status -->" + status);
			}
			logger.info("Checking if the master , event combination already exists - process completed");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(connection, prepareStatement, null);
		}
		try {

			logger.info("Logging gateway XML process started ");
			connection = CommonUtil.DBConnection();
			String logExtractsGatewayQuery = ActionQueryConstants.LOG_EXTRACTS_GATEWAY_QUERY;
			prepareStatement = connection
					.prepareStatement(logExtractsGatewayQuery);
			prepareStatement.setString(1, service);
			prepareStatement.setString(2, operation);
			prepareStatement.setString(3, masterKey);
			prepareStatement.setString(4, eventKey);
			prepareStatement.setString(5, prodType);
			prepareStatement.setString(6, eventType);
			prepareStatement.setString(7, status);
			prepareStatement.setString(8, tirequestXML);
			logResult = prepareStatement.executeUpdate();
			logger.info("Logging gateway XML process completed ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(connection, prepareStatement, null);
		}

		logger.info(" ************ GATEWAY.Extracts adaptee process started ************ ");
//		try {
//			GatewayProcess.processGateWay();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		Map<String, String> tokens = new HashMap<String, String>();
		if (logResult > 0)
			tokens.put("Status", "SUCCEEDED");
		else
			tokens.put("Status", "FAILED");
		tiResponse = generateResponseXML("XML/ExtractsGatewayResponse.xml", tokens);
		logger.info("tiResponse XML -->" + tiResponse);
		return tiResponse;

	}

	public static String generateResponseXML(String responseXMLFile,
			Map<String, String> tokens) {
		String responseXML = null;
		try {
			String input = readPropertiesFile(responseXMLFile);

			MapTokenResolver resolver = new MapTokenResolver(tokens);

			Reader fileValue = new StringReader(input);

			Reader replacer = new TokenReplacingReader(fileValue, resolver);

			responseXML = replacer.toString();

			replacer.close();

			logger.debug("responseXML :" + responseXML);

		} catch (Exception exp) {
			exp.printStackTrace();

			logger.error(exp.getMessage());
		}
		return responseXML;
	}

	public static String readPropertiesFile(String filePath) throws Exception {
		InputStream input = ExtractsGatewayConsumption.class.getClassLoader()
				.getResourceAsStream( filePath);
		BufferedReader bufferedReader = null;
		StringBuilder propertiesFileValue = new StringBuilder();

		String line = null;
		try {

			bufferedReader = new BufferedReader(new InputStreamReader(input));
			while ((line = bufferedReader.readLine()) != null) {
				propertiesFileValue.append(line);
			}

		} catch (IOException exp) {
			logger.error("Error occured in IO exception" + exp.getMessage());

			return null;
		}

		return propertiesFileValue.toString();
	}

	public static void main(String[] args) {
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("Status", "SUCCEEDED");
		generateResponseXML("XML/ExtractsGatewayResponse.xml", tokens);
	}

}
