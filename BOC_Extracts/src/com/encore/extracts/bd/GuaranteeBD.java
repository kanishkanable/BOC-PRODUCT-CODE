package com.encore.extracts.bd;

import java.util.ArrayList;

import com.encore.extracts.action.ActionUtility;
//import com.encore.extracts.dao.CollDAO;
import com.encore.extracts.dao.GuaranteeDAO;
//import com.encore.extracts.vo.CollateralVO;
import com.encore.extracts.vo.CustomerVO;
import com.encore.extracts.vo.GuaranteeVO;

public class GuaranteeBD {
	static GuaranteeBD guaBD;

	public static GuaranteeBD getBD() {
		if (guaBD == null) {
			guaBD = new GuaranteeBD();
		}
		return guaBD;
	}
	
	public String submitGuaranteeAttachment(GuaranteeVO guaVO,  String tableName, String makerCheckerStatus, String isMakerChecker) {
		GuaranteeDAO guaDAO = null;
		String attachResult = "";
		try {
			guaDAO = GuaranteeDAO.getDAO();
			attachResult = guaDAO.submitGuaranteeAttachment(guaVO,tableName,makerCheckerStatus,isMakerChecker);
			System.out.println("guaDAO.submitGuaranteeAttachment  "+attachResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attachResult;
	}

	public GuaranteeVO getExistingGuaranteeDetails(CustomerVO custVO) {
		GuaranteeVO guaVO = null;
		GuaranteeDAO guaDAO = null;
		try {
			guaDAO = GuaranteeDAO.getDAO();
			guaVO = guaDAO.getExistingGuaranteeDetails(custVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guaVO;
	}

	public boolean isValidCustomer(String counterpartyUniqueIdentifier) {
		boolean isValidCustomer = false;
		GuaranteeDAO guaDAO = null;
		try {
			guaDAO = GuaranteeDAO.getDAO();
			isValidCustomer = ActionUtility.isValidCustomer(counterpartyUniqueIdentifier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValidCustomer;
	}
	public ArrayList<CustomerVO> getFetchGuaranteeCode(CustomerVO custVO) {
		GuaranteeDAO dao = null;
		String codeQuery = null;
		ArrayList<CustomerVO> GuaranteeCodeDetailsList = null;
		try {
			dao = GuaranteeDAO.getDAO();
			codeQuery = dao.guaranteeDetailsQuery(custVO);
			GuaranteeCodeDetailsList = dao.getExistingGuaranteeCodeFetchDAO(codeQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GuaranteeCodeDetailsList;
	}
}
