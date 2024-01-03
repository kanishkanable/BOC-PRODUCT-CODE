package com.encore.boc.extracts.bsr;

import java.util.ArrayList;
import com.encore.extracts.vo.CustomerVO;

public class BSRHomeActionBD {

	static BSRHomeActionBD aBSRHomeActionBD;

	public static BSRHomeActionBD getBD() {
		if (aBSRHomeActionBD == null) {
			aBSRHomeActionBD = new BSRHomeActionBD();
		}
		return aBSRHomeActionBD;
	}

	public ArrayList<CustomerVO> getCustomerDetails(CustomerVO custVO) {
		BSRHomeDAO aBSRHomeDAO = null;
		ArrayList<CustomerVO> custDetailsList = null;
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			custDetailsList = aBSRHomeDAO.getCustomerDetails(custVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return custDetailsList;
	}

	public boolean isValidCustomer(String mnemonic) {
		boolean isValidCustomer = false;
		BSRHomeDAO aBSRHomeDAO = null;
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			isValidCustomer = aBSRHomeDAO.isValidCustomer(mnemonic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValidCustomer;
	}

	public ArrayList<CustomerVO> getCustomerLimitList(CustomerVO custVO) {
		BSRHomeDAO aBSRHomeDAO = null;
		ArrayList<CustomerVO> customerLimitList = null;
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			customerLimitList = aBSRHomeDAO.getCustomerLimitList(custVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerLimitList;
	}

	public String submitBSRDetails(BSRVO bsrVo) {
		BSRHomeDAO aBSRHomeDAO = null;
		String submitBSRDetailsResult = "";
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			submitBSRDetailsResult = aBSRHomeDAO.submitBSRDetails(bsrVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return submitBSRDetailsResult;
	}

	public String deleteBSRDetails(BSRVO bsrVo) {
		BSRHomeDAO aBSRHomeDAO = null;
		String deleteBSRDetailsResult = "";
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			deleteBSRDetailsResult = aBSRHomeDAO.deleteBSRDetails(bsrVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteBSRDetailsResult;
	}

	public BSRVO getExistingBSRDetails(CustomerVO custVO) {
		BSRHomeDAO aBSRHomeDAO = null;
		BSRVO bsrVo = null;
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			bsrVo = aBSRHomeDAO.getExistingBSRDetails(custVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bsrVo;
	}

	public ArrayList<BSRLookupVO> fetchDistrictDetails(BSRLookupVO bsrLookupVo) {
		BSRHomeDAO aBSRHomeDAO = null;
		ArrayList<BSRLookupVO> districtDetailsList = null;
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			districtDetailsList = aBSRHomeDAO.fetchDistrictDetails(bsrLookupVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return districtDetailsList;
	}

	public ArrayList<BSRLookupVO> fetchSecUnsecLoanCodeDetails(
			BSRLookupVO bsrLookupVo) {
		BSRHomeDAO aBSRHomeDAO = null;
		ArrayList<BSRLookupVO> secUnsecLoanCodeDetailsList = null;
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			secUnsecLoanCodeDetailsList = aBSRHomeDAO
					.fetchSecUnsecLoanCodeDetails(bsrLookupVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return secUnsecLoanCodeDetailsList;

	}

	public ArrayList<BSRLookupVO> fetchAssetClassBorrAccountDetails(
			BSRLookupVO bsrLookupVo) {
		BSRHomeDAO aBSRHomeDAO = null;
		ArrayList<BSRLookupVO> assetClassBorrAccountList = null;
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			assetClassBorrAccountList = aBSRHomeDAO
					.fetchAssetClassBorrAccountDetails(bsrLookupVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return assetClassBorrAccountList;

	}

	public ArrayList<BSRLookupVO> fetchCategoryBorrowerCodeDetails(
			BSRLookupVO bsrLookupVo) {
		BSRHomeDAO aBSRHomeDAO = null;
		ArrayList<BSRLookupVO> categoryBorrowerCodeList = null;
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			categoryBorrowerCodeList = aBSRHomeDAO
					.fetchCategoryBorrowerCodeDetails(bsrLookupVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return categoryBorrowerCodeList;

	}

	public ArrayList<BSRLookupVO> fetchOccupationDetails(BSRLookupVO bsrLookupVo) {
		BSRHomeDAO aBSRHomeDAO = null;
		ArrayList<BSRLookupVO> occupationList = null;
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			occupationList = aBSRHomeDAO.fetchOccupationDetails(bsrLookupVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return occupationList;

	}

	public ArrayList<BSRLookupVO> fetchOrganisationTypeDetails(
			BSRLookupVO bsrLookupVo) {
		BSRHomeDAO aBSRHomeDAO = null;
		ArrayList<BSRLookupVO> organisationTypeList = null;
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			organisationTypeList = aBSRHomeDAO
					.fetchOrganisationTypeDetails(bsrLookupVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return organisationTypeList;

	}

	public ArrayList<BSRLookupVO> fetchPopulationGroupDetails(
			BSRLookupVO bsrLookupVo) {
		BSRHomeDAO aBSRHomeDAO = null;
		ArrayList<BSRLookupVO> populationGroupDetailsList = null;
		try {
			aBSRHomeDAO = BSRHomeDAO.getDAO();
			populationGroupDetailsList = aBSRHomeDAO
					.fetchPopulationGroupDetails(bsrLookupVo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return populationGroupDetailsList;

	}
}
