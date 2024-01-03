package com.ett.interace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import com.ett.util.CommonUtil;
import com.ett.util.QueryConstants;

@Controller
public class FncallWebserviceGrid {

	//private static final Logger logger = LoggerFactory.getLogger(FncallWebserviceGrid.class);

	private String param1;
	private String spname;
	private String resubmit;

	public void resubmit(String resubmit)
	{
		this.resubmit=resubmit;
	}
	public void param1(String param1) {
		this.param1 = param1;
	}

	public void spname(String spname) {
		this.spname = spname;
	}
	public String UI_GetFncallWSGridviewresubmit() throws SQLException {

		Connection aConnection = null;

		PreparedStatement aPrepare = null;

		//ResultSet aResultSet = null;

		String xml = "";
		try {
			aConnection = com.ett.util.CommonUtil.DBConnection();
		//	String i1 = "a";

			System.out.println("param1------>" + param1);
			System.out.println("Spname------>" + spname);
			System.out.println("Resubmit------>" + resubmit);
			String masterref=param1.substring(0,16);
			String eventref=param1.substring(17,23);
			System.out.println("masterref SUBSTRING 1------>" + masterref);
			System.out.println("masterref SUBSTRING 2------>" + eventref);
			aPrepare = aConnection.prepareStatement(QueryConstants.UPDATE_BACKOFC_BATCH);

			aPrepare.setString(1, masterref);
			aPrepare.setString(2, eventref);
			aPrepare.executeUpdate();
			UI_getFncallWSGridviewupdatepostingtable(param1,resubmit);
			xml=UI_GetFncallWSGridview();
			System.out.println("Xml-->"+xml);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Occured in UI_GetFncallWSGridviewresubmit() Method" + e);
		} finally {
			if (aConnection != null) {
				try {
					aConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (aPrepare != null) {
				try {
					aPrepare.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	//	System.out.println("XML---> " + xml);

		return xml;
	}
	public void UI_getFncallWSGridviewupdatepostingtable(String param12, String resubmit2) throws SQLException
	{
		String Refnum=param12;
		Connection aConnection1 = null;
		PreparedStatement aPrepare1= null;
		System.out.println("ReferenceNumber--"+Refnum);
				try {
					aConnection1 = com.ett.util.CommonUtil.DBConnection();
	
					aPrepare1 = aConnection1.prepareStatement(QueryConstants.UPDATE_POSTINGTABLE);

					aPrepare1.setString(1, Refnum);
					aPrepare1.executeUpdate();
					
				}
				catch (Exception e) {
					e.printStackTrace();
					System.out.println("Exception Occured in UI_getFncallWSGridviewupdatepostingtable() Method" + e);
				} finally {
					if (aConnection1 != null) {
						try {
							aConnection1.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

					if (aPrepare1 != null) {
						try {
							aPrepare1.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
	}
	public String UI_GetFncallWSGridview() throws SQLException {

		Connection aConnection = null;

		PreparedStatement aPrepare = null;

		ResultSet aResultSet = null;

		String xml = "";

		try {
			aConnection = com.ett.util.CommonUtil.DBConnection();
			String i1 = "a";

			System.out.println("param1------>" + param1);
			System.out.println("Spname------>" + spname);

			aPrepare = aConnection.prepareStatement(QueryConstants.POSTINGFTECH);

			aPrepare.setString(1, param1);
			aResultSet = aPrepare.executeQuery();
			xml = xml + "<Resultset>";
			if (aResultSet != null) {
				ResultSetMetaData rsmd = aResultSet.getMetaData();
				int colCount = rsmd.getColumnCount();

				while (aResultSet.next()) {
					xml = xml + "<" + i1 + ">";
					for (int i = 1; i <= colCount; i++) {
						xml = xml + "<" + rsmd.getColumnName(i) + ">" + aResultSet.getString(i) + "</"
								+ rsmd.getColumnName(i) + ">";

					}
					xml = xml + "</" + i1 + ">";
				}

			}
			xml = xml + "</Resultset>";

			// xml="<Resultset><a><ACTION>`a~` `img style=width:20px;height:20px; title=View
			// id=GridView name=GridView value=View
			// src=Interface/Common/Images/review-icon.png ~`
			// </ACTION><MAS_REIMBSYSREF>77619RE06393</MAS_REIMBSYSREF><MAS_DOCCRNO>0480419IM0000515</MAS_DOCCRNO><MAS_PRCSID>PROCS_SYSTEM_925724</MAS_PRCSID><MAS_ACTVID>37677</MAS_ACTVID><MAS_ACTVID>37677</MAS_ACTVID><MAS_DTMODIFIED>2019-10-29
			// 08:30:55</MAS_DTMODIFIED><MAS_DTCREATED>2019-10-29
			// 08:30:55</MAS_DTCREATED><MAS_MODIFIEDBY>SYSTEM</MAS_MODIFIEDBY><MAS_CREATEDBY>SYSTEM</MAS_CREATEDBY></a></Resultset>";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Occured in UI_GetFncallWSGridview() Method" + e);
		} finally {
			CommonUtil.CloseConnection(aConnection, aPrepare, aResultSet);
		}
		System.out.println("XML---> " + xml);

		return xml;

	}

}
