package com.ett.accountsyncservice;

public class AccountsSyncBankResponseModel {

	String backOfficeAccount;
	String branchID;
	String custID;
	String accType;
	String currency;

	public String getBackOfficeAccount() {
		return backOfficeAccount;
	}

	public void setBackOfficeAccount(String backOfficeAccount) {
		this.backOfficeAccount = backOfficeAccount;
	}

	public String getBranchID() {
		return branchID;
	}

	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
