package com.ett.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.UUID;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

import com.misys.tiplus2.service.access.ejb.EnigmaServiceAccess;
import com.misys.tiplus2.service.access.ejb.EnigmaServiceAccessHome;

public class CommonUtil {
	public static Connection DBConnectionLocal() throws SQLException {
		Connection connection = null;
		try {

		
				String driver = "com.ibm.db2.jcc.DB2Driver";
				String url = "jdbc:db2://172.23.32.22:50000/TIZONE1";
				String userName ="TIZONE1";
				String password ="tizone1";

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
			DataSource dataSource = (DataSource) initialContext.lookup("jdbc/Interface");
			connection = dataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}

	public static void CloseConnection(Connection con, Statement stm, ResultSet res) {

		if (con != null) {
			try {
				con.close();
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

		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static String generateCorrelationId() {

		return UUID.randomUUID().toString();
	}

	public static String processEJBMessage(String tiRequestXML) {
		String result = "Unable to Process";
		try {
			System.out.println("Swiuft Before Pushing " + tiRequestXML);
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, Constants.WEBSPHERE_CONTEXT);
			env.put(Context.PROVIDER_URL, BOCCommonUtil.retreiveFromProperties(Constants.EJBURL));
			System.out.println("STEP 1");
			Context ctx = new InitialContext(env);
			Object ejbObject = ctx.lookup("ejb/EnigmaServiceAccess");
			/*EnigmaServiceAccessHome accessBeanHome = (EnigmaServiceAccessHome) javax.rmi.PortableRemoteObject
					.narrow(ejbObject, EnigmaServiceAccessHome.class);*/
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

	public static String stringReplaceCommonUtil(String inputString, String searchString, String replacement) {
		if (inputString != null || !"".equalsIgnoreCase(inputString)) {

			inputString = org.apache.commons.lang.StringUtils.replace(inputString, searchString, replacement);
		}

		return inputString;
	}

	public static String getSysDate(String format) {

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();

		return formatter.format(date);
	}

	public static boolean NullStringCheck(String input) {

		boolean val = false;

		if (input.isEmpty() || input.trim().equals(""))
			val = true;

		return val;
	}

}
