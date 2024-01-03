package com.ett.backofficeverify;

import java.math.BigDecimal;

public class BackOfficeVerifyBankReqModel {

	public String AcctId;
	public String AcctType;
	public String Year;
	public String Month;
	public String Day;
	public String RqUID;
	public BigDecimal PostAmount;
	public String PostCcy;
	public String DebitCreditFlag;

	public String getDebitCreditFlag() {
		return DebitCreditFlag;
	}

	public void setDebitCreditFlag(String debitCreditFlag) {
		DebitCreditFlag = debitCreditFlag;
	}

	public String getAcctId() {
		return AcctId;
	}

	public void setAcctId(String acctId) {
		AcctId = acctId;
	}

	public String getAcctType() {
		return AcctType;
	}

	public void setAcctType(String acctType) {
		AcctType = acctType;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public String getDay() {
		return Day;
	}

	public void setDay(String day) {
		Day = day;
	}

	public String getRqUID() {
		return RqUID;
	}

	public void setRqUID(String rqUID) {
		RqUID = rqUID;
	}

	public BigDecimal getPostAmount() {
		return PostAmount;
	}

	public void setPostAmount(BigDecimal postAmount) {
		PostAmount = postAmount;
	}

	public String getPostCcy() {
		return PostCcy;
	}

	public void setPostCcy(String postCcy) {
		PostCcy = postCcy;
	}

}
