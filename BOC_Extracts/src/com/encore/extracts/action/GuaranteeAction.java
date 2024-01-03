package com.encore.extracts.action;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.encore.extracts.bd.GuaranteeBD;
import com.encore.extracts.dao.MakerChecker;
import com.encore.extracts.vo.CustomerVO;
import com.encore.extracts.vo.GuaranteeVO;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class GuaranteeAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(GuaranteeAction.class.getName());
	CustomerVO custVO;
	GuaranteeVO guaVO;
	GuaranteeBD guaBD;

	ArrayList<CustomerVO> GuaranteeCodeDetailsList = null;

	public ArrayList<CustomerVO> getGuaranteeCodeDetailsList() {
		return GuaranteeCodeDetailsList;
	}

	public void setGuaranteeCodeDetailsList(ArrayList<CustomerVO> guaranteeCodeDetailsList) {
		GuaranteeCodeDetailsList = guaranteeCodeDetailsList;
	}

	public CustomerVO getCustVO() {
		return custVO;
	}

	public void setCustVO(CustomerVO custVO) {
		this.custVO = custVO;
	}

	public GuaranteeVO getGuaVO() {
		return guaVO;
	}

	public void setGuaVO(GuaranteeVO guaVO) {
		this.guaVO = guaVO;
	}

	public GuaranteeBD getGuaBD() {
		return guaBD;
	}

	public void setGuaBD(GuaranteeBD guaBD) {
		this.guaBD = guaBD;
	}

	public String guaranteeDetails() {
		return "success";
	}

	public String fetchGuaranteeCodeDetails() {
		try {
			guaBD = GuaranteeBD.getBD();
			if (custVO != null) {
				GuaranteeCodeDetailsList = guaBD.getFetchGuaranteeCode(custVO);
				setGuaranteeCodeDetailsList(GuaranteeCodeDetailsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String validateGuaranteeNature() {
		if (guaVO != null) {
			if (guaVO.getGuaranteeNature() != null) {
				System.out.println("Guarantee Nature-->" + guaVO.getGuaranteeNature());
				if (guaVO.getGuaranteeNature().equalsIgnoreCase("0"))
					guaVO.setValidateGuaranteeNature("0");
				else if (guaVO.getGuaranteeNature().equalsIgnoreCase("1"))
					guaVO.setValidateGuaranteeNature("1");
			} else if (guaVO.getGuaranteeNature().equalsIgnoreCase("2"))
				guaVO.setValidateGuaranteeNature("2");
		}
		return "success";
	}

	public String submitGuaranteeAttachment() {
		String attachResult = "";
		guaBD = new GuaranteeBD();
		try {
			guaBD = GuaranteeBD.getBD();
			boolean isValidCounterParty = false;
			boolean isValidGuaranteeProvider = false;
			boolean isValidCurrency = false;
			boolean isValidBranch = false;
			boolean isAvailable = false;
			if (guaVO != null) {
				isValidCounterParty = guaBD.isValidCustomer(guaVO.getCounterpartyUniqueIdentifier());
				isValidGuaranteeProvider = guaBD.isValidCustomer(guaVO.getProviderUniqueIdentifier());
				isValidCurrency = ActionUtility.isValidCurrency(guaVO.getCurrencyCode());
				isValidBranch = ActionUtility.isValidBranch(guaVO.getBranchCode());
				if (!isValidCounterParty || !isValidGuaranteeProvider) {
					if (!isValidCounterParty)
						addActionError("Counterparty Unique Identifier is not a TI customer.");
					if (!isValidGuaranteeProvider)
						addActionError("Provider Unique Identifier is not a TI customer.");
					return "failed";
				} else if (!isValidCurrency) {
					addActionError("Entered Currency is not valid.");
					return "failed";

				} else if (!isValidBranch) {
					addActionError("Entered Branch is not available in TI.");
					return "failed";
				} else {
					try {
						isAvailable = MakerChecker.isCategoryAvailable(guaVO.getCategory(),
								"COLLATERAL_DETAILS_MAKER_CHECKER");
						if (isAvailable) {
							addActionError("Details for " + guaVO.getCategory()
									+ " was already entered in Collateral Details Page ");
						} else {
							attachResult = guaBD.submitGuaranteeAttachment(guaVO, "GUARANTEE_DETAILS_MAKER_CHECKER",
									"Approval Pending", "Maker");
							System.out.println("attachResult" + attachResult);
							// addActionMessage(attachResult);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			addActionMessage(attachResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}

	public String approveGuaranteeAttachment() {
		String attachResult = "";
		guaBD = new GuaranteeBD();
		try {
			guaBD = GuaranteeBD.getBD();
			boolean isValidCounterParty = false;
			boolean isValidGuaranteeProvider = false;
			if (guaVO != null) {
				isValidCounterParty = guaBD.isValidCustomer(guaVO.getCounterpartyUniqueIdentifier());
				isValidGuaranteeProvider = guaBD.isValidCustomer(guaVO.getProviderUniqueIdentifier());
				if (!isValidCounterParty || !isValidGuaranteeProvider) {
					if (!isValidCounterParty)
						addActionError("Counterparty Unique Identifier is not a TI customer.");
					if (!isValidGuaranteeProvider)
						addActionError("Provider Unique Identifier is not a TI customer.");
					return "failed";
				}
				attachResult = guaBD.submitGuaranteeAttachment(guaVO, "GUARANTEE_DETAILS", "Approved", "Checker");
			}
			addActionMessage(attachResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}

	public String rejectGuaranteeAttachment() {
		String attachResult = "";
		guaBD = new GuaranteeBD();
		try {
			guaBD = GuaranteeBD.getBD();
			boolean isValidCounterParty = false;
			boolean isValidGuaranteeProvider = false;
			if (guaVO != null) {
				isValidCounterParty = guaBD.isValidCustomer(guaVO.getCounterpartyUniqueIdentifier());
				isValidGuaranteeProvider = guaBD.isValidCustomer(guaVO.getProviderUniqueIdentifier());
				if (!isValidCounterParty || !isValidGuaranteeProvider) {
					if (!isValidCounterParty)
						addActionError("Counterparty Unique Identifier is not a TI customer.");
					if (!isValidGuaranteeProvider)
						addActionError("Provider Unique Identifier is not a TI customer.");
					return "failed";
				}
				attachResult = guaBD.submitGuaranteeAttachment(guaVO, "GUARANTEE_DETAILS_MAKER_CHECKER", "Rejected",
						"Maker");
			}
			addActionMessage(attachResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";

	}

	public String validateGuaranteeType() {
		if (guaVO != null) {
			// Enabling Security code field based on guarantee type
			if (guaVO.getGuaranteeType() != null) {
				if (guaVO.getGuaranteeType().equalsIgnoreCase("Security"))
					guaVO.setValidateSecurityCode("Required");
			}
		}
		return "success";
	}

	public String attachGuarantee() {
		// String attachResult = "";
		guaBD = new GuaranteeBD();
		/*
		 * try { guaBD = guaBD.getBD(); attachResult =
		 * guaBD.submitGuaranteeAttachment(guaVO); System.out.println("attachResult"
		 * +attachResult); addActionMessage(attachResult);
		 */
		try {
			guaBD = GuaranteeBD.getBD();
			if (custVO != null) {
				guaVO = guaBD.getExistingGuaranteeDetails(custVO);
				validateGuaranteeType();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String execute() {
		return "success";
	}

	public String guaranteeSelection() {
		return "success";
	}

	public String returnGuarantee() {
		return "success";
	}
}
