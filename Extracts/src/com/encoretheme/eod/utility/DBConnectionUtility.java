package com.encoretheme.eod.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBConnectionUtility {
	private static Logger logger = Logger.getLogger(DBConnectionUtility.class
			.getName());

	public static boolean isJNDIConn;
	public static String JNDIContext;
	public static String JNDIZONE;

	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		try {

			Properties aProperties = new Properties();
			aProperties = PropertyUtility.getPropertiesValue();

			isJNDIConn = Boolean.parseBoolean(aProperties
					.getProperty("JNDIConnection"));
			logger.info("isJNDIConn :" + isJNDIConn);
			if (isJNDIConn) {
				Properties param = new Properties();
				String lookupName = aProperties.getProperty("JNDIZONE");
				String context = aProperties.getProperty("JNDICONTEXT");

				param.put(Context.INITIAL_CONTEXT_FACTORY, context);
				Context initialContext = new InitialContext(param);
				DataSource dataSource = (DataSource) initialContext
						.lookup(lookupName);
				connection = dataSource.getConnection();
			} else {
				String driver = aProperties.getProperty("DriverClass");
				String url = aProperties.getProperty("Url");
				String userName = aProperties.getProperty("UserName");
				String password = aProperties.getProperty("Password");

				Class.forName(driver);
				connection = DriverManager.getConnection(url, userName,
						password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void surrenderDB(Connection con, Statement stmt, ResultSet res) {
		try {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
