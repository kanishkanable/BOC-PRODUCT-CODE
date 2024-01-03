package com.ett.interace.controller;

import com.ett.interace.dao.*;

import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ett.accountsyncservice.*;
import com.ett.customersyncservice.CustomerModel;
import com.ett.customersyncservice.CustomerSync;
import com.ett.util.CommonUtil;
import com.ett.util.Constants;

@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "home", "" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String SRMB_FReimbHome(Locale locale, Model model) {
		System.out.println("Welcome home! The client locale is {}.");
		return "/home";
	}

	@RequestMapping(value = { "Cutomersync" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String SRMB_FReimbSummary(Locale locale, Model model) {

		return "/Cutomersync";
	}

	@RequestMapping(value = { "Close" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String Infc_Close(Locale locale, Model model) {

		return "/Summary";
	}

	@RequestMapping(value = { "Summary" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String Infc_Summary(Locale locale, Model model) {

		return "/Summary";
	}

	@RequestMapping(value = { "Accountsync" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String Infc_Accountsync(Locale locale, Model model) {

		return "/Accountsync";
	}

	@RequestMapping(value = { "SwiftOut" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String Infc_SwiftOut(Locale locale, Model model) {

		return "/SwiftOut";
	}

	@RequestMapping(value = { "Posting" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String Infc_Posting(Locale locale, Model model) {

		return "/Posting";
	}

	@RequestMapping(value = { "Custsearch" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })

	public String Infc_Custsearch(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		try {
			CustomerModel custmod = new CustomerModel();
			custmod.setCustomerID(request.getParameter("CUS_NUM"));
			System.out.println("Customer Number " + request.getParameter("CUS_NUM"));
			String CustomerNumber = request.getParameter("CUS_NUM");
			GetData getdata = new GetData();
			getdata.param1(Constants.CUSTOMER_TABLE);
			getdata.param2(Constants.CUSTOMER_COLUMN);
			getdata.param3(request.getParameter("CUS_NUM"));
			System.out.println("Param1------------->" + request.getParameter("CUS_NUM"));
			// String customerreturn=getdata.GetData1();
			// if(customerreturn.equalsIgnoreCase("Failure"))
			// {
			CustomerSync custsync = new CustomerSync();
			String Status = custsync.getCustomerDetails(custmod);
			System.out.println(">>>>Status "+Status);
			if ((!CommonUtil.NullStringCheck(Status)) && Status.equalsIgnoreCase("SUCCEEDED")) {
				return "/SyncSuccess";
			} else {
				return "/SyncFailure";
			}
		} catch (Exception e) {
			System.out.println("Inside Infc_Custsearch method  catch -->" + e);
			return "/SyncFailure";
		}

	}

	@RequestMapping(value = { "/Cust_CHK" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public String UI_GetDataCust(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			GetData getdata = new GetData();
			getdata.param1(Constants.CUSTOMER_TABLE);
			getdata.param2(Constants.CUSTOMER_COLUMN);
			getdata.param3(request.getParameter("param1"));
			System.out.println("Param1------------->" + request.getParameter("param1"));
			return getdata.GetData1();
		} catch (Exception e) {
			System.out.println("Exception in CUST_CHK" + e);
		}
		return null;
	}

	@RequestMapping(value = { "Acctsearch" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })

	public String Infc_Acctsearch(HttpServletRequest request, HttpServletResponse response) {
		String Statusres = "";
		try {
			AccountsSync accsync = new AccountsSync();
			GetData getdata = new GetData();
			accsync.setAccountID(request.getParameter("ACC_NUM"));
			accsync.setAccountType(request.getParameter("ACC_TYP"));
			System.out.println("Account nyumber ->" + request.getParameter("ACC_NUM"));
			System.out.println("Account Type ->" + request.getParameter("ACC_TYP"));
			getdata.param1(Constants.ACCOUNTS_TABLE);
			getdata.param2(Constants.ACCOUNTS_COLUMN);
			getdata.param3(request.getParameter("ACC_NUM"));
			getdata.param4(request.getParameter("ACC_TYP"));
			System.out.println("Param1------------->" + request.getParameter("param1"));
			String accountreturn = getdata.GetData2();
			/*
			 * if(accountreturn.equalsIgnoreCase("Success")) {
			 */
			System.out.println("Inside Account search -->" + request.getParameter("ACC_NUM"));
			String Status = accsync.getAccountDetails();
			if (Status != null || !Status.trim().isEmpty())

			{
				Statusres = "/SyncSuccess";
			} else {
				Statusres = "/SyncFailure";
			}

		} catch (Exception e) {
			System.out.println("Inside AcctSearch method  catch -->" + e);
			return "/SyncFailure";
		}
		return Statusres;

	}

	@RequestMapping(value = { "/Acc_CHK" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public String UI_GetDataAcc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String status = "";
		try {
			// AccountsSync accsync = new AccountsSync();
			System.out.println("Inside Account CHK -->");
			GetData getdata = new GetData();

			getdata.param1(Constants.ACCOUNTS_TABLE);
			getdata.param2(Constants.ACCOUNTS_COLUMN);
			getdata.param3(request.getParameter("param1"));
			/*
			 * accsync.setAccountID(request.getParameter("ACC_NUM"));
			 * accsync.setAccountType(request.getParameter("ACC_TYP"));
			 */
			System.out.println("Account number ->" + request.getParameter("ACC_NUM"));
			System.out.println("Account Type ->" + request.getParameter("ACC_TYP"));
			/*
			 * getdata.param1(Constants.ACCOUNTS_TABLE);
			 * getdata.param2(Constants.ACCOUNTS_COLUMN);
			 * getdata.param3(request.getParameter("ACC_NUM"));
			 */
			getdata.param4(request.getParameter("ACC_TYP"));
			System.out.println("Param1------------->" + request.getParameter("param1"));
			/*
			 * String accountreturn=getdata.GetData2();
			 * if(accountreturn.equalsIgnoreCase("Success")) {
			 * System.out.println("Inside Account search -->"+request.getParameter("ACC_NUM"
			 * )); accsync.getAccountDetails(); return "/Summary"; } else { return
			 * "/SyncFailure"; }
			 */
			status = getdata.GetData1();
		} catch (Exception e) {
			System.out.println("Inside Acct CHK method  catch -->" + e);
		}
		return status;
	}

//	@RequestMapping(value = { "/FncallWebserviceGridView" }, method = {
//			org.springframework.web.bind.annotation.RequestMethod.POST })
//	@ResponseBody
//	public String FncallWebserviceGridView(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		String gridValue = "";
//		
//		try {
//			
//			
//			FncallWebserviceGrid getdata = new FncallWebserviceGrid();
//			getdata.param1(request.getParameter("Param"));
//			getdata.param2(request.getParameter("brid"));
//			getdata.param3(request.getParameter("MnuId"));
//			getdata.DBSrc(request.getParameter("DBSrc"));
//			getdata.spname(request.getParameter("spname"));
//
//			gridValue = getdata.UI_GetFncallWSGridview();
//		} catch (Exception e) {
//			/*logger.info("FncallWebserviceGridView failed");
//			logger.info("Exception in FncallWebserviceGridView " + e.getMessage());
//			logger.error("Exception in FncallWebserviceGridView ", e);*/
//		}
//		//logger.info("FncallWebserviceGridView failed");
//		return gridValue;
//	}
	@RequestMapping(value = { "/FncallWebserviceGridView" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public String FncallWebserviceGridView(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("Entering into FncallWebserviceGridView------------->");

		String gridValue = "";
		// String resubmit="";
		try {

			System.out.println("Entering into FncallWebserviceGridView Try block------------->");
			FncallWebserviceGrid getdata = new FncallWebserviceGrid();
			getdata.param1(request.getParameter("Param"));
			getdata.spname(request.getParameter("spname"));
			// resubmit=request.getParameter("action");
			System.out.println("get parameter Param---" + request.getParameter("Param") + "get parameter request---"
					+ request.getParameter("spname"));
			// System.out.println("Resubmit
			// Value--"+getdata.resubmit(request.getParameter("action")));
			/*
			 * if(resubmit.equalsIgnoreCase("Resubmit")) {
			 * System.out.println("Inside Resubmit Block--------->");
			 * getdata.resubmit(request.getParameter("action"));
			 * gridValue=getdata.UI_GetFncallWSGridviewresubmit();
			 * 
			 * }
			 */
			// else {
			gridValue = getdata.UI_GetFncallWSGridview();
			// }
		} catch (Exception e) {
			/*
			 * logger.info("FncallWebserviceGridView failed");
			 * logger.info("Exception in FncallWebserviceGridView " + e.getMessage());
			 * logger.error("Exception in FncallWebserviceGridView ", e);
			 */

			e.printStackTrace();
		}
		// logger.info("FncallWebserviceGridView failed");
		return gridValue;
	}
}
