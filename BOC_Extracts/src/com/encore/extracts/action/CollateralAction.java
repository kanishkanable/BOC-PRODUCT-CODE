package com.encore.extracts.action;

import java.util.ArrayList;
//import com.boc.ett.extracts.sl_customs.SLCustomVO;
import com.encore.extracts.bd.CollBD;
import com.encore.extracts.dao.MakerChecker;
import com.encore.extracts.vo.CollateralVO;
import com.encore.extracts.vo.CustomerVO;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CollateralAction extends ActionSupport {
	CollateralVO collVO;
	CustomerVO custVO;
	CollBD collBD;

	ArrayList<CustomerVO> CodeDetailsList = null;

	public ArrayList<CustomerVO> getCodeDetailsList() {
		return CodeDetailsList;
	}

	public void setCodeDetailsList(ArrayList<CustomerVO> codeDetailsList) {
		CodeDetailsList = codeDetailsList;
	}

	public CollateralVO getCollVO() {
		return collVO;
	}

	public void setCollVO(CollateralVO collVO) {
		this.collVO = collVO;
	}

	public CustomerVO getCustVO() {
		return custVO;
	}

	public void setCustVO(CustomerVO custVO) {
		this.custVO = custVO;
	}

	public CollBD getCollBD() {
		return collBD;
	}

	public void setCollBD(CollBD collBD) {
		this.collBD = collBD;
	}

	public String fetchCodeDetails() {
		try {
			collBD = CollBD.getBD();
			if (custVO != null) {
				CodeDetailsList = collBD.getFetchCollateralCode(custVO);
				setCodeDetailsList(CodeDetailsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String submitAttachment() {
		System.out.println("submitAttachment");
		String attachResult = "";
		collBD = new CollBD();
		boolean isAvailable = false;
		boolean isValidCounterParty = false;
		boolean isValidCurrency = false;
		boolean isValidBranch = false;
		try {
			collBD = CollBD.getBD();
			
			isValidCounterParty = ActionUtility.isValidCustomer(collVO.getCounterpartyUniqueId());
			isValidCurrency = ActionUtility.isValidCurrency(collVO.getCollateralCurrency());
			
			System.out.println("------branchcode------" +collVO.getBranchCode());
			isValidBranch = ActionUtility.isValidBranch(collVO.getBranchCode());
			if (!isValidCounterParty) {
				addActionError("Counterparty Unique Identifier is not a TI customer.");
				return "failed";
				
			}else if(!isValidCurrency) {
				addActionError("Entered Currency is not valid.");
				return "failed";
				
			}else if(!isValidBranch) {
				addActionError("Entered Branch is not available in TI.");
				return "failed";
				
			}else {
				try {
					isAvailable = MakerChecker.isCategoryAvailable(collVO.getCategory(), "GUARANTEE_DETAILS_MAKER_CHECKER");
					if (isAvailable) {
						addActionError(
								"Details for " + collVO.getCategory() + " was already entered in Guarantee Details Page");
					} else {
						attachResult = collBD.attachCollateral(collVO, "COLLATERAL_DETAILS_MAKER_CHECKER",
								"Approval Pending", "Maker");
						System.out.println("attachResult" + attachResult);
						addActionMessage(attachResult);
					}
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		
			System.out.println("attachResult" + attachResult);
			// addActionMessage(attachResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String approveAttachment() {
		System.out.println("approveAttachment");
		String attachResult = "";
		collBD = new CollBD();
		try {
			collBD = CollBD.getBD();

			attachResult = collBD.attachCollateral(collVO, "COLLATERAL_DETAILS", "Approved", "Checker");
			System.out.println("attachResult" + attachResult);
			addActionMessage(attachResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String rejectAttachment() {
		System.out.println("rejectAttachment");
		String attachResult = "";
		collBD = new CollBD();
		try {
			collBD = CollBD.getBD();
			System.out.println("Notest >>> " + collVO.getNotes());
			attachResult = collBD.attachCollateral(collVO, "COLLATERAL_DETAILS_MAKER_CHECKER", "Rejected", "Maker");
			System.out.println("attachResult" + attachResult);
			addActionMessage(attachResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String validateCollateralType() {
		if (collVO != null) {
			// Enabling Security code field based on collateral type
			if (collVO.getCollateralType() != null) {
				if (collVO.getCollateralType().equalsIgnoreCase("Security"))
					collVO.setValidateSecurityCode("Required");
			}
		}
		return "success";
	}

	// Collateral code
	public String attachCollateral() {
		String attachResult = "";
		collBD = new CollBD();
		try {
			collBD = CollBD.getBD();

			if (custVO != null) {
				System.out.println("--------------------Maker Action------------" + custVO.getMakerCheckerAction());
				System.out
						.println("--------------------Maker getLimitCurrency------------" + custVO.getLimitCurrency());
				collVO = collBD.getExistingCollateralDetails(custVO);
				validateCollateralType();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String execute() {
		return "success";

	}

	public String collateralSelection() {

		return "success";
	}

	public String returnCOllateral() {

		return "success";
	}
}
