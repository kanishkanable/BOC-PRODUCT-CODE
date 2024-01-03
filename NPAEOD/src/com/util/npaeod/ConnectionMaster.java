package com.util.npaeod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionMaster {
	
	public static Boolean isJNDIConn = true;
	
	public ConnectionMaster() {
		System.out.println("ConnectionMaster started!");
	}
	
	public static Connection getConnection() throws SQLException {
        Connection connection = null;
        if (isJNDIConn == true) {
		
        	System.out.println("JNDI Connection Encountered");
		
        	try {
            Properties param = new Properties();

			param.put(Context.INITIAL_CONTEXT_FACTORY,
                        "com.ibm.websphere.naming.WsnInitialContextFactory");
            Context initialContext = new InitialContext(param);
            DataSource dataSource = (DataSource) initialContext.lookup("jdbc/zone");
            connection = dataSource.getConnection();

	        } catch (NamingException e) {
	              e.printStackTrace();
	        }
		}
		else {
			System.out.println("ODBC Connection Encountered");
			try {
				String driver = "com.ibm.db2.jcc.DB2Driver";
				String url="jdbc:db2://13.71.95.153:50000/TIZONE2";
				String userName = "TIZONE1";
				String password = "Encore@1234";
						
				Class.forName(driver);
				connection = DriverManager.getConnection(url, userName, password);
			}
			catch (Exception e) {
	    		System.out.println("Error is "+e.getMessage());
	    	}
			
		}
        
        return connection;
  }	
	
	public static void surrenderDB(Connection con, Statement stmt, ResultSet res) {
		try {
			if (con != null) {
				con.close();
				if (stmt != null)
					stmt.close();
				if (res != null)
					res.close();
			}
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This helper method is used to close PreparedStatement object.
	 * 
	 * @param preparedStatement
	 */
	public static void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			if (null != preparedStatement) {
				preparedStatement.close();
			}
		} catch (Exception excepConnection) {
			System.out.println(excepConnection.fillInStackTrace());
		}
	}

	/**
	 * This helper method is used to close ResultSet object.
	 * 
	 * @param resultSet
	 */
	public static void closeResultSet(ResultSet resultSet) {
		try {
			if (null != resultSet) {
				resultSet.close();
			}
		} catch (Exception excepConnection) {
			System.out.println(excepConnection.fillInStackTrace());
		}
	}
	
	
	/**
	 * This method is used to close the resultSet, LoggableStatement and
	 * connection objects.
	 * 
	 * @param result
	 * @param loggableStatement
	 * @param connection
	 */
	public static void closeSqlRefferance(ResultSet result,
			PreparedStatement preparedStatement) {
		closeResultSet(result);
		closePreparedStatement(preparedStatement);
	}

}
