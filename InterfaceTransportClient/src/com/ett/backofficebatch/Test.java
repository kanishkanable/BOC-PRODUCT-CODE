package com.ett.backofficebatch;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


  
public class Test { 
	 public static void main(String[] args) throws DatatypeConfigurationException 
	    {
	        GregorianCalendar cal = new GregorianCalendar();
	 
	        //Set todays date
	        cal.setTime(new Date());
	        XMLGregorianCalendar xCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	         
	        System.out.println(convertXmlGregorianToString(xCal));
	    }
	 
	    public static String convertXmlGregorianToString(XMLGregorianCalendar xc) 
	    {
	        DateFormat df = new SimpleDateFormat("YYYYMMDD");
	 
	        GregorianCalendar gCalendar = xc.toGregorianCalendar();
	 
	        //Converted to date object
	        Date date = gCalendar.getTime();
	 
	        //Formatted to String value
	        String dateString = df.format(date);
	 
	        return dateString;
	    }
} 