package com.ett.backofficeverify;

import java.math.BigDecimal;

public class BackOfficeVerifyTIReqModel {

	public String BackOfficeAccountNo;
	public String AccountType;
	public BigDecimal PostingAmount;
	public String PostingCcy;
	public String MasterReference;
	public String EventReference;
	public String DebitCreditFlag;

	public String getDebitCreditFlag() {
		return DebitCreditFlag;
	}

	public void setDebitCreditFlag(String debitCreditFlag) {
		DebitCreditFlag = debitCreditFlag;
	}

	public String getBackOfficeAccountNo() {
		return BackOfficeAccountNo;
	}

	public void setBackOfficeAccountNo(String backOfficeAccountNo) {
		BackOfficeAccountNo = backOfficeAccountNo;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public BigDecimal getPostingAmount() {
		return PostingAmount;
	}

	public void setPostingAmount(BigDecimal postingAmount) {
		PostingAmount = postingAmount;
	}

	public String getPostingCcy() {
		return PostingCcy;
	}

	public void setPostingCcy(String postingCcy) {
		PostingCcy = postingCcy;
	}

	public String getMasterReference() {
		return MasterReference;
	}

	public void setMasterReference(String masterReference) {
		MasterReference = masterReference;
	}

	public String getEventReference() {
		return EventReference;
	}

	public void setEventReference(String eventReference) {
		EventReference = eventReference;
	}

}
