package com.encore.extracts.bd;

import java.util.ArrayList;

import com.encore.extracts.dao.CustDAO;
import com.encore.extracts.vo.CustomerVO;

public class CustBD {

	static CustBD custBD;

	public static CustBD getBD() {
		if (custBD == null) {
			custBD = new CustBD();
		}
		return custBD;
	}

	public ArrayList<CustomerVO> getCustomerDetails(CustomerVO custVO) {
		CustDAO custDAO = null;
		ArrayList<CustomerVO> custDetailsList = null;
		try {
			custDAO = CustDAO.getDAO();
			custDetailsList = custDAO.getCustomerDetails(custVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return custDetailsList;

	}

	public ArrayList<CustomerVO> getCountryList(CustomerVO custVO) {
		CustDAO custDAO = null;
		ArrayList<CustomerVO> countryList = null;
		try {
			custDAO = CustDAO.getDAO();
			countryList = custDAO.getCountryList(custVO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return countryList;
	}

	public boolean isValidCustomer(String mnemonic) {
		boolean isValidCustomer = false;
		CustDAO custDAO = null;
		try {
			custDAO = CustDAO.getDAO();
			isValidCustomer = custDAO.isValidCustomer(mnemonic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValidCustomer;
	}

	public ArrayList<CustomerVO> getCustomerLimitList(CustomerVO custVO) {
		CustDAO custDAO = null;
		ArrayList<CustomerVO> customerLimitList = null;
		try {
			custDAO = CustDAO.getDAO();
			customerLimitList = custDAO.getCustomerLimitList(custVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerLimitList;
	}

}
