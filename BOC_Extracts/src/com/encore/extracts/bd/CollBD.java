package com.encore.extracts.bd;

import java.util.ArrayList;
//import com.boc.ett.extracts.sl_customs.SLCustomVO;
//import com.boc.ett.extracts.sl_customs.SLCustomsDAO;
import com.encore.extracts.dao.CollDAO;
import com.encore.extracts.vo.CollateralVO;
import com.encore.extracts.vo.CustomerVO;

public class CollBD {
	static CollBD collBD;

	public static CollBD getBD() {
		if (collBD == null) {
			collBD = new CollBD();
		}
		return collBD;
	}

	public String attachCollateral(CollateralVO collVO, String tableName, String makerCheckerStatus, String isMakerChecker) {
		CollDAO collDAO = null;
		String attachResult = "";
		try {
			collDAO = CollDAO.getDAO();
			attachResult = collDAO.attachCollateral(collVO,tableName,makerCheckerStatus,isMakerChecker);
			System.out.println("collDAO.attachCollateral"+attachResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return attachResult;
	}

	public CollateralVO getExistingCollateralDetails(CustomerVO custVO) {
		CollDAO collDAO = null;
		CollateralVO collVO = null;
		try {
			collDAO = CollDAO.getDAO();
			collVO = collDAO.getExistingCollateralDetails(custVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return collVO;
	}	
	public ArrayList<CustomerVO> getFetchCollateralCode(CustomerVO custVO) {
		CollDAO dao = null;
		String codeQuery = null;
		ArrayList<CustomerVO> CodeDetailsList = null;
		try {
			dao = CollDAO.getDAO();
			codeQuery = dao.codeDetailsQuery(custVO);
			CodeDetailsList = dao.getExistingCollateralCodeFetchDAO(codeQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CodeDetailsList;
	}	
}
