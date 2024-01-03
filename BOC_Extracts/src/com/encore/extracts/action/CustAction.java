package com.encore.extracts.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.boc.ett.extracts.sl_customs.SLCustomBD;
import com.encore.extracts.bd.CustBD;
import com.encore.extracts.vo.CustomerVO;
import com.opensymphony.xwork2.ActionSupport;

public class CustAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(CustAction.class.getName());
	CustomerVO custVO;
	CustBD extractsBD;

	ArrayList<CustomerVO> custDetailsList;
	ArrayList<CustomerVO> countryList;
	ArrayList<CustomerVO> customerLimitList;

	public CustomerVO getCustVO() {
		return custVO;
	}

	public void setCustVO(CustomerVO custVO) {
		this.custVO = custVO;
	}

	public CustBD getExtractsBD() {
		return extractsBD;
	}

	public void setExtractsBD(CustBD extractsBD) {
		this.extractsBD = extractsBD;
	}

	public ArrayList<CustomerVO> getCustDetailsList() {
		return custDetailsList;
	}

	public void setCustDetailsList(ArrayList<CustomerVO> custDetailsList) {
		this.custDetailsList = custDetailsList;
	}

	public ArrayList<CustomerVO> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<CustomerVO> countryList) {
		this.countryList = countryList;
	}

	public ArrayList<CustomerVO> getCustomerLimitList() {
		return customerLimitList;
	}

	public void setCustomerLimitList(ArrayList<CustomerVO> customerLimitList) {
		this.customerLimitList = customerLimitList;
	}

	public String fetchCustomerDetails() {
		String result = "success";
		extractsBD = new CustBD();
		try {
			extractsBD = CustBD.getBD();
			if (custVO != null)
				custDetailsList = extractsBD.getCustomerDetails(custVO);
			setCustDetailsList(custDetailsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public String collateralDetails() {
		return "success";
	}

	public String guaranteeDetails() {
		return "success";
	}

	public String customerSelection() {
		return "success";
	}

	public String ejbClient() {
		return "success";
	}

	public String fetchCountryList() {
		extractsBD = new CustBD();
		try {
			extractsBD = CustBD.getBD();
			if (custVO != null)
				countryList = extractsBD.getCountryList(custVO);
			setCountryList(countryList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String fectchCustomerLimits() {

		extractsBD = new CustBD();
		String action = null;
		try {
			extractsBD = CustBD.getBD();
			action = custVO.getAction();
			// System.out.println("Customer Mnemonic -->" + custVO.getMnemonic());
			System.out.println("action value" + action);
			boolean isValidCustomer = false;
			if (custVO != null)
				isValidCustomer = extractsBD.isValidCustomer(custVO.getMnemonic());
			if (isValidCustomer) {
				customerLimitList = extractsBD.getCustomerLimitList(custVO);
				if (customerLimitList != null)
					setCustomerLimitList(customerLimitList);
				else
					addActionError("Error Occurred. Please contact system administrator..!");
			} else
				addActionError("Please select or input a valid customer..!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return action;
	}

}
