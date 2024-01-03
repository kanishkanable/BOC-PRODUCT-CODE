package com.encore.extracts.utility;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.misys.tiplus2.service.access.ejb.EnigmaServiceAccess;
import com.misys.tiplus2.service.access.ejb.EnigmaServiceAccessHome;

public class CommonMethods {
	public boolean isNotNull(String value) {
		boolean result = true;
		if (value == null || value.equalsIgnoreCase("")) {
			result = false;
		}
		return result;
	}

	public static boolean isNull(String value) {
		boolean result = false;
		if (value == null || value.equalsIgnoreCase("")) {
			result = true;
		}
		return result;
	}

	public static String getXmlFromObject(Object anSourceObject) {

		String requestXML = "";
		try {
			System.out.println("anSourceObject-->" + anSourceObject.toString());
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(anSourceObject.getClass());

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.marshal(anSourceObject, writer);
			requestXML = writer.toString();

		} catch (JAXBException e) {
			// logger.error("getXmlFromObject JAXBException => " + e.getMessage(),se);
			e.printStackTrace();
		}
		return requestXML;
	}

	/**
	 * @return Zone id
	 * 
	 *         default value will be BOC
	 */
	public static String getZoneID() {

		String zoneId = "BOC";
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		String zoneQuery = "select ID as ZONEID from dlyprccycl";
		try {
			connection = DBConnectionUtility.getConnection();
			preparedStatement = connection.prepareStatement(zoneQuery);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				zoneId = resultSet.getString("ZONEID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(connection, preparedStatement, resultSet);
		}
		return zoneId;

	}

	/**
	 * @param amt
	 * @return
	 */
	public static String convertCommaSeperator(String amt) {
		String amount = "";
		try {
			Locale locale = new Locale("en", "GB");
			DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(locale);
			amount = decimalFormat.format(new BigDecimal(amt));
		} catch (Exception e) {
			amount = amt;
			e.printStackTrace();
		}

		return amount;
	}
	/**
	 * @param amt
	 * @return
	 */
//	public static String convertCommaToDecimal(String amt) {
//		String amount = "";
//		try {
//			Locale locale = new Locale("en", "GB");
//			DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(locale);
//			amount = decimalFormat.parse(amt).toString();			 
//		} catch (Exception e) {
//			amount = amt;
//			e.printStackTrace();
//		}
//
//		return amount;
//	}
	public static String ejbProcess(String tiRequestXML) {
		System.out.println("--------------------"+tiRequestXML);
		String response  = processEJBMessage(tiRequestXML);
		return response;
	}
	
	public static String processEJBMessage(String tiRequestXML) {
		String result = "Unable to Process";
		try {
			System.out.println("Swiuft Before Pushing " + tiRequestXML);
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
			env.put(Context.PROVIDER_URL, retreiveFromProperties("EjbURL"));
			System.out.println("STEP 1");
			Context ctx = new InitialContext(env);
			Object ejbObject = ctx.lookup("ejb/EnigmaServiceAccess");
			/*
			 * EnigmaServiceAccessHome accessBeanHome = (EnigmaServiceAccessHome)
			 * javax.rmi.PortableRemoteObject .narrow(ejbObject,
			 * EnigmaServiceAccessHome.class);
			 */
			EnigmaServiceAccessHome accessBeanHome = (EnigmaServiceAccessHome) PortableRemoteObject.narrow(ejbObject,
					EnigmaServiceAccessHome.class);
			EnigmaServiceAccess accessB = accessBeanHome.create();
			System.out.println("STEP 2");
			result = accessB.process(tiRequestXML);
			System.out.println("Result in EJB Message"+result);
			System.out.println("EJB PROCESS COMPLETED");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	public static String retreiveFromProperties(String key) {

		String value = null;
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection =  DBConnectionUtility.getConnection();
			pst = aConnection.prepareStatement(QueryConstant.RETRIEVE_VALUE_FROM_PROPERTIES);
			pst.setString(1, key);
			rst = pst.executeQuery();

			while (rst.next())
				value = rst.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionUtility.surrenderDB(aConnection, pst, rst);
		}

		return value;

	}
}
