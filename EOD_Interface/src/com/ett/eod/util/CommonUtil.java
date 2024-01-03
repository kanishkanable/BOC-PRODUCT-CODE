package com.ett.eod.util;
import java.math.BigDecimal;
import java.sql.Connection;
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
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;


public class CommonUtil {
	public static Logger logger = Logger.getLogger(CommonUtil.class);
	public static Connection DBConnection() {
		
		Connection connection = null;
		try {
			Properties param = new Properties();
			param.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
			Context initialContext = new InitialContext(param);
			DataSource dataSource = (DataSource) initialContext.lookup("jdbc/zone");
			connection = dataSource.getConnection();
		} catch (NamingException e) {
			logger.info("error in connection");
			logger.error(e.getMessage(), e);
		} catch (SQLException e) {
			logger.info("error in connection");
			logger.error(e.getMessage(), e);
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
	
	public static String GregorianDatetoSimpleDate(String format, XMLGregorianCalendar xmlGregorianCalendar) {

		// return formatter.format(TIDate.toGregorianCalendar().getTime());
		//GregorianCalendar gCalendar = TIDate.toGregorianCalendar();
		DateFormat date = new SimpleDateFormat(format);
		//Date dateConvert = TIDate;
		System.out.println("BEFORE CONVERTION ==== " + xmlGregorianCalendar);
		String dateformat = date.format(xmlGregorianCalendar);
		System.out.println("AFTER CONVERTION ==== " + dateformat);
		return dateformat;

	}
	public static Date StringtoDate(String Date)
	{
		String sDate1=Date;  
	    Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    System.out.println(sDate1+"\t"+date1); 
	    
	    return date1;
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
	/*
	 * public static String GregorianDatetoSimpleDate(String format,
	 * XMLGregorianCalendar TIDate) {
	 * 
	 * SimpleDateFormat formatter = new SimpleDateFormat(format);
	 * 
	 * return formatter.format(TIDate.toGregorianCalendar().getTime()); }
	 */
	
	public static BigDecimal convertNormalAmount(BigDecimal Amount, String Ccy) {
		Connection aConnection = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			aConnection = CommonUtil.DBConnection();
			pst = aConnection.prepareStatement(QueryConstant.CONVERT_FROM_NORMAL_TO_TI_AMOUNT);
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
	public static String GetJulianDate() {
		Date date = new Date();
		StringBuilder sb = new StringBuilder();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return sb.append(cal.get(Calendar.YEAR)).append(String.format("%03d", cal.get(Calendar.DAY_OF_YEAR)))
				.toString();
	}
	public static String GetDateFormat(String Date1)
	{
		Date Date2=null;
		String Formatedte=null;
		try {
			Date2= new SimpleDateFormat("yyyy-MM-dd").parse(Date1);
			System.out.println(Date1+"--->"+Date2);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Formatedte=formatter.format(Date2);
			System.out.println(Date2+"--->"+Formatedte);
		}catch(Exception e) {
			System.out.println("Exception n Date Format--->"+e);
			e.printStackTrace();
		}
		return Formatedte;
	}


}
