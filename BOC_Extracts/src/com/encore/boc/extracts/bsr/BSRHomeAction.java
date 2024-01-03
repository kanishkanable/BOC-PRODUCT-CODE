package com.encore.boc.extracts.bsr;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.encore.extracts.vo.CustomerVO;
import com.opensymphony.xwork2.ActionSupport;
import com.encore.boc.extracts.bsr.BSRActionConstants;

public class BSRHomeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(BSRHomeAction.class
			.getName());

	CustomerVO custVO;
	BSRVO bsrVo;
	BSRLookupVO bsrLookupVo;
	BSRHomeActionBD aBSRHomeActionBD;

	ArrayList<BSRLookupVO> districtList;
	ArrayList<BSRLookupVO> populationGroupList;
	ArrayList<BSRLookupVO> organisationTypeList;
	ArrayList<BSRLookupVO> occupationList;
	ArrayList<BSRLookupVO> categoryBorrowerCodeList;
	ArrayList<BSRLookupVO> assetClassBorrAccountList;
	ArrayList<BSRLookupVO> secUnsecLoanCodeList;
	ArrayList<CustomerVO> custDetailsList;
	ArrayList<CustomerVO> customerLimitList;

	public BSRLookupVO getBsrLookupVo() {
		return bsrLookupVo;
	}

	public void setBsrLookupVo(BSRLookupVO bsrLookupVo) {
		this.bsrLookupVo = bsrLookupVo;
	}

	public ArrayList<BSRLookupVO> getSecUnsecLoanCodeList() {
		return secUnsecLoanCodeList;
	}

	public void setSecUnsecLoanCodeList(
			ArrayList<BSRLookupVO> secUnsecLoanCodeList) {
		this.secUnsecLoanCodeList = secUnsecLoanCodeList;
	}

	public ArrayList<BSRLookupVO> getAssetClassBorrAccountList() {
		return assetClassBorrAccountList;
	}

	public void setAssetClassBorrAccountList(
			ArrayList<BSRLookupVO> assetClassBorrAccountList) {
		this.assetClassBorrAccountList = assetClassBorrAccountList;
	}

	public ArrayList<BSRLookupVO> getCategoryBorrowerCodeList() {
		return categoryBorrowerCodeList;
	}

	public void setCategoryBorrowerCodeList(
			ArrayList<BSRLookupVO> categoryBorrowerCodeList) {
		this.categoryBorrowerCodeList = categoryBorrowerCodeList;
	}

	public ArrayList<BSRLookupVO> getOccupationList() {
		return occupationList;
	}

	public void setOccupationList(ArrayList<BSRLookupVO> occupationList) {
		this.occupationList = occupationList;
	}

	public ArrayList<BSRLookupVO> getOrganisationTypeList() {
		return organisationTypeList;
	}

	public void setOrganisationTypeList(
			ArrayList<BSRLookupVO> organisationTypeList) {
		this.organisationTypeList = organisationTypeList;
	}

	public ArrayList<BSRLookupVO> getPopulationGroupList() {
		return populationGroupList;
	}

	public void setPopulationGroupList(
			ArrayList<BSRLookupVO> populationGroupList) {
		this.populationGroupList = populationGroupList;
	}

	public ArrayList<BSRLookupVO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList<BSRLookupVO> districtList) {
		this.districtList = districtList;
	}

	public BSRHomeActionBD getaBSRHomeActionBD() {
		return aBSRHomeActionBD;
	}

	public void setaBSRHomeActionBD(BSRHomeActionBD aBSRHomeActionBD) {
		this.aBSRHomeActionBD = aBSRHomeActionBD;
	}

	public BSRVO getBsrVo() {
		return bsrVo;
	}

	public void setBsrVo(BSRVO bsrVo) {
		this.bsrVo = bsrVo;
	}

	public CustomerVO getCustVO() {
		return custVO;
	}

	public void setCustVO(CustomerVO custVO) {
		this.custVO = custVO;
	}

	public ArrayList<CustomerVO> getCustDetailsList() {
		return custDetailsList;
	}

	public void setCustDetailsList(ArrayList<CustomerVO> custDetailsList) {
		this.custDetailsList = custDetailsList;
	}

	public ArrayList<CustomerVO> getCustomerLimitList() {
		return customerLimitList;
	}

	public void setCustomerLimitList(ArrayList<CustomerVO> customerLimitList) {
		this.customerLimitList = customerLimitList;
	}

	public String bsrHome() {
		return BSRActionConstants.SUCCESS;
	}

	public String customerSearch() {
		return BSRActionConstants.SUCCESS;
	}

	public String districtCodeSelection() {
		return BSRActionConstants.SUCCESS;
	}

	public String assetClassBorrAccountSelection() {
		return BSRActionConstants.SUCCESS;
	}

	public String populationGroupCodeSelection() {
		return BSRActionConstants.SUCCESS;
	}

	public String organisationTypeSelection() {
		return BSRActionConstants.SUCCESS;
	}

	public String occupationCodeSelection() {
		return BSRActionConstants.SUCCESS;
	}

	public String openAttachBSRPage() {
		return BSRActionConstants.SUCCESS;
	}

	public String categoryBorrowerCodeSelection() {
		return BSRActionConstants.SUCCESS;
	}

	public String secUnsecLoanCodeSelection() {
		return BSRActionConstants.SUCCESS;
	}

	public String customerDetailsSearch() {
		aBSRHomeActionBD = new BSRHomeActionBD();
		try {
			aBSRHomeActionBD = BSRHomeActionBD.getBD();
			if (custVO != null)
				custDetailsList = aBSRHomeActionBD.getCustomerDetails(custVO);

			setCustDetailsList(custDetailsList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return BSRActionConstants.SUCCESS;

	}

	public String fetchCustomerLimitDetails() {
		aBSRHomeActionBD = new BSRHomeActionBD();
		try {
			aBSRHomeActionBD = BSRHomeActionBD.getBD();
			logger.info("Customer Mnemonic -->" + custVO.getMnemonic());
			boolean isValidCustomer = false;
			if (custVO != null)
				isValidCustomer = aBSRHomeActionBD.isValidCustomer(custVO
						.getMnemonic());
			if (isValidCustomer) {
				customerLimitList = aBSRHomeActionBD
						.getCustomerLimitList(custVO);
				if (customerLimitList != null)
					setCustomerLimitList(customerLimitList);
				else
					addActionError("Error Occurred. Please contact system administrator..!");
			} else
				addActionError("Please select or input a valid customer..!");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return BSRActionConstants.SUCCESS;
	}

	public String submitBSRDetails() {
		String submitBSRDetailsResult = "";
		aBSRHomeActionBD = new BSRHomeActionBD();
		try {
			aBSRHomeActionBD = BSRHomeActionBD.getBD();
			submitBSRDetailsResult = aBSRHomeActionBD.submitBSRDetails(bsrVo);
			addActionMessage(submitBSRDetailsResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BSRActionConstants.SUCCESS;
	}

	public String deleteBSRDetails() {
		String deleteBSRDetailsResult = "";
		aBSRHomeActionBD = new BSRHomeActionBD();
		try {
			aBSRHomeActionBD = BSRHomeActionBD.getBD();
			deleteBSRDetailsResult = aBSRHomeActionBD.deleteBSRDetails(bsrVo);
			addActionMessage(deleteBSRDetailsResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BSRActionConstants.SUCCESS;
	}

	public String attachBSR() {
		aBSRHomeActionBD = new BSRHomeActionBD();
		try {
			aBSRHomeActionBD = BSRHomeActionBD.getBD();
			if (custVO != null) {
				bsrVo = aBSRHomeActionBD.getExistingBSRDetails(custVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BSRActionConstants.SUCCESS;
	}

	public String fetchDistrictDetails() {
		aBSRHomeActionBD = new BSRHomeActionBD();
		try {
			aBSRHomeActionBD = BSRHomeActionBD.getBD();
			if (bsrLookupVo != null)
				districtList = aBSRHomeActionBD
						.fetchDistrictDetails(bsrLookupVo);
			setDistrictList(districtList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BSRActionConstants.SUCCESS;

	}

	public String fetchSecUnsecLoanCodeDetails() {
		aBSRHomeActionBD = new BSRHomeActionBD();
		try {
			aBSRHomeActionBD = BSRHomeActionBD.getBD();
			if (bsrLookupVo != null)
				secUnsecLoanCodeList = aBSRHomeActionBD
						.fetchSecUnsecLoanCodeDetails(bsrLookupVo);
			setSecUnsecLoanCodeList(secUnsecLoanCodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BSRActionConstants.SUCCESS;

	}

	public String fetchAssetClassBorrAccountDetails() {
		aBSRHomeActionBD = new BSRHomeActionBD();
		try {
			aBSRHomeActionBD = BSRHomeActionBD.getBD();
			if (bsrLookupVo != null)
				assetClassBorrAccountList = aBSRHomeActionBD
						.fetchAssetClassBorrAccountDetails(bsrLookupVo);
			setAssetClassBorrAccountList(assetClassBorrAccountList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BSRActionConstants.SUCCESS;

	}

	public String fetchCategoryBorrowerCodeDetails() {
		aBSRHomeActionBD = new BSRHomeActionBD();
		try {
			aBSRHomeActionBD = BSRHomeActionBD.getBD();
			if (bsrLookupVo != null)
				categoryBorrowerCodeList = aBSRHomeActionBD
						.fetchCategoryBorrowerCodeDetails(bsrLookupVo);
			setCategoryBorrowerCodeList(categoryBorrowerCodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BSRActionConstants.SUCCESS;

	}

	public String fetchOccupationDetails() {
		aBSRHomeActionBD = new BSRHomeActionBD();
		try {
			aBSRHomeActionBD = BSRHomeActionBD.getBD();
			if (bsrLookupVo != null)
				occupationList = aBSRHomeActionBD
						.fetchOccupationDetails(bsrLookupVo);
			setOccupationList(occupationList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BSRActionConstants.SUCCESS;

	}

	public String fetchOrganisationTypeDetails() {
		aBSRHomeActionBD = new BSRHomeActionBD();
		try {
			aBSRHomeActionBD = BSRHomeActionBD.getBD();
			if (bsrLookupVo != null)
				organisationTypeList = aBSRHomeActionBD
						.fetchOrganisationTypeDetails(bsrLookupVo);
			setOrganisationTypeList(organisationTypeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BSRActionConstants.SUCCESS;

	}

	public String fetchPopulationGroupDetails() {
		aBSRHomeActionBD = new BSRHomeActionBD();
		try {
			aBSRHomeActionBD = BSRHomeActionBD.getBD();
			if (bsrLookupVo != null)
				populationGroupList = aBSRHomeActionBD
						.fetchPopulationGroupDetails(bsrLookupVo);
			setPopulationGroupList(populationGroupList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return BSRActionConstants.SUCCESS;

	}
}
