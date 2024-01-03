package com.ett.util;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Properties;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.ibm.ws.util.StringUtils;
import com.misys.tiplus2.foundations.lang.logging.Loggers;
import com.misys.tiplus2.service.access.ejb.EnigmaServiceAccess;
import com.misys.tiplus2.service.access.ejb.EnigmaServiceAccessHome;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.util.UUID;

public class CommonUtil {

	private static final Logger LOG = LoggerFactory.getLogger(CommonUtil.class);

	public static Connection DBConnectionLOCAL() throws SQLException {
		Connection connection = null;
		try {

		
				String driver = "com.ibm.db2.jcc.DB2Driver";
				String url = "jdbc:db2://172.23.32.22:50000/TIZONE1";
				String userName ="TIZONE1";
				String password ="tizone1";
				
//				String driver = "com.ibm.db2.jcc.DB2Driver";
//				String url = "jdbc:db2://172.24.32.33:50000/TIZONE3";
//				String userName ="tizone3";
//				String password ="t9Z0n@3";

				Class.forName(driver);
				connection = DriverManager.getConnection(url, userName,
						password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	public static Connection DBConnection() {

		Connection connection = null;
		try {
			Properties param = new Properties();
			param.put(Context.INITIAL_CONTEXT_FACTORY, Constants.WEBSPHERE_CONTEXT);
			Context initialContext = new InitialContext(param);
			DataSource dataSource = (DataSource) initialContext.lookup("jdbc/zone");
			connection = dataSource.getConnection();
		} catch (NamingException e) {
			Loggers.general().error(LOG, e.getMessage());
		} catch (SQLException e) {
			Loggers.general().error(LOG, e.getMessage());
		}
		return connection;

	}

	public static void CloseConnection(Connection con, Statement stm, ResultSet res) {

		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static boolean NullStringCheck(String input) {

		boolean val = false;

		if (input.isEmpty() || input.trim().equals("") || input == null)
			val = true;

		return val;
	}

	public static String generateCorrelationId() {

		return UUID.randomUUID().toString();
	}

	public static BigDecimal convertTIAmount(BigDecimal Amount, String Ccy) {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.CONVERT_FROM_TI_TO_NORMAL_AMOUNT);
			pst.setBigDecimal(1, Amount);
			pst.setBigDecimal(2, Amount);
			pst.setBigDecimal(3, Amount);
			pst.setString(4, Ccy.trim());
			rst = pst.executeQuery();
			while (rst.next()) {
				Amount = rst.getBigDecimal(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		return Amount;
	}

	public static BigDecimal convertNormalAmount(BigDecimal Amount, String Ccy) {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.CONVERT_FROM_NORMAL_TO_TI_AMOUNT);
			pst.setBigDecimal(1, Amount);
			pst.setBigDecimal(2, Amount);
			pst.setBigDecimal(3, Amount);
			pst.setString(4, Ccy.trim());
			rst = pst.executeQuery();
			while (rst.next()) {
				Amount = rst.getBigDecimal(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		return Amount;
	}

	public static String getSysDate(String format) {

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();

		return formatter.format(date);
	}

	public static String GregorianDatetoSimpleDate(String format, XMLGregorianCalendar TIDate) {

		// return formatter.format(TIDate.toGregorianCalendar().getTime());
		GregorianCalendar gCalendar = TIDate.toGregorianCalendar();
		DateFormat date = new SimpleDateFormat(format);
		Date dateConvert = gCalendar.getTime();
		System.out.println("BEFORE CONVERTION ==== " + dateConvert);
		String dateformat = date.format(dateConvert);
		System.out.println("AFTER CONVERTION ==== " + dateformat);
		return dateformat;

	}

	public static XMLGregorianCalendar SimpleDatetoGregorianDate(String format, String XMLdate) {

		XMLGregorianCalendar result = null;
		Date date;
		SimpleDateFormat simpleDateFormat;
		GregorianCalendar gregorianCalendar;
		try {
			simpleDateFormat = new SimpleDateFormat(format);

			date = simpleDateFormat.parse(XMLdate);

			gregorianCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
			gregorianCalendar.setTime(date);
			result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String Dateformat(String format) {

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();

		return formatter.format(date);
	}

	public static String GetJulianDate() {
		Date date = new Date();
		StringBuilder sb = new StringBuilder();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return sb.append(cal.get(Calendar.YEAR)).append(String.format("%03d", cal.get(Calendar.DAY_OF_YEAR)))
				.toString();
	}
	public static Date getCurrentDate() {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		Date currDate=null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstants.current_Date);
			
			rst = pst.executeQuery();
			while (rst.next()) {
				currDate = rst.getDate(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonUtil.CloseConnection(aConnection, pst, rst);
		}
		return currDate;
	}
	public static String getValueDate(String date)
	{
		String valuedate=date;
		 Date date1 = getCurrentDate();
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	       String SystemDate = formatter.format(date1);
	      System.out.print("Current date: "+SystemDate);
	      SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMdd");
	      String tivaluedate=date;
	      Date d1;
	      Date d2;
		try {
			d1 = sdformat.parse(SystemDate);
			d2 = sdformat.parse(tivaluedate);
			 System.out.println("The date 1 is: " + sdformat.format(d1));
		      System.out.println("The date 2 is: " + sdformat.format(d2));
		      if(d1.compareTo(d2) < 0) {
		         System.out.println("Date 1 occurs after Date 2");
		         valuedate=SystemDate;
		      } 
		      else  {
		       
		    	  valuedate=date;
		   }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     System.out.println("valuedate----->"+valuedate);
		return valuedate;
	}

	public static String getTagValue(String tagName, String responseMessage) {

		String tagValue = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder aDocumentBuilder = dbf.newDocumentBuilder();
			InputSource aInputSource = new InputSource();
			aInputSource.setCharacterStream(new StringReader(responseMessage));
			Document aDocument = aDocumentBuilder.parse(aInputSource);
			tagValue = aDocument.getElementsByTagName(tagName).item(0).getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tagValue;
	}

	public static JAXBElement<String> getJAXBString(String value) {

		JAXBElement<String> jaxbElement = new JAXBElement<String>(new QName(String.class.getSimpleName()), String.class,
				value);
		return jaxbElement;
	}

	public static JAXBElement<Long> getJAXBLong(Long value) {

		JAXBElement<Long> jaxbElement = new JAXBElement<Long>(new QName(Long.class.getSimpleName()), Long.class, value);
		return jaxbElement;
	}

	public static JAXBElement<XMLGregorianCalendar> getJAXBGregCalendar(XMLGregorianCalendar value) {

		JAXBElement<XMLGregorianCalendar> jaxbElement = new JAXBElement<XMLGregorianCalendar>(
				new QName(XMLGregorianCalendar.class.getSimpleName()), XMLGregorianCalendar.class, value);
		return jaxbElement;
	}

	public static QueueConnection getMQConnectionFactory(String QueueConnection) {

		QueueConnection connection = null;
		try {
			Properties param = new Properties();
			param.put(Context.INITIAL_CONTEXT_FACTORY, Constants.WEBSPHERE_CONTEXT);
			InitialContext ctx = new InitialContext(param);
			QueueConnectionFactory f = (QueueConnectionFactory) ctx.lookup(QueueConnection);
			connection = f.createQueueConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;

	}

	public static Queue getMQConnection(String QueueName) {

		Queue que = null;
		try {
			Properties param = new Properties();
			param.put(Context.INITIAL_CONTEXT_FACTORY, Constants.WEBSPHERE_CONTEXT);
			InitialContext ctx = new InitialContext(param);
			que = (Queue) ctx.lookup(QueueName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return que;

	}

	public static String processEJBMessage(String tiRequestXML) {
		String result = "Unable to Process";
		try {
			System.out.println("Request before pushing " + tiRequestXML);
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, Constants.WEBSPHERE_CONTEXT);
			env.put(Context.PROVIDER_URL, BOCCommonUtil.retreiveFromProperties(Constants.EJBURL));
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
			System.out.println("EJB PROCESS COMPLETED");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in ejb process");
		}
		System.out.println("result--->"+result);
		return result;
	}
	
	public static String getValue(String requestXML, String xpath)
			throws SAXException, IOException, XPathExpressionException {

		// System.out.println(requestXML);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db;
		String attributeValue = "";
		try {
			db = dbf.newDocumentBuilder();

			Document doc = db.parse(new InputSource(
					new StringReader(requestXML)));

			XPathFactory factory = XPathFactory.newInstance();

			javax.xml.xpath.XPath oXPath = factory.newXPath();

			// get attribute Value

			attributeValue = oXPath.compile(xpath).evaluate(doc);

			// System.out.println(attributeValue);

		} catch (ParserConfigurationException e) {
			Loggers.general().error(LOG, e.getMessage());

			e.printStackTrace();
		}


		return attributeValue;
	}
	

}
