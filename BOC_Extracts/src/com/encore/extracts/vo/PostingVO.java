package com.encore.extracts.vo;

public class PostingVO {

	String masterRef;
	String eventRef;
	String status;
	String accountNumber;
	String tranAmount;
	String creditDebit;
	String TransactionID;
	String requestFilename;
	String responseFilename;
	
	public String getRequestFilename() {
		return requestFilename;
	}

	public void setRequestFilename(String requestFilename) {
		this.requestFilename = requestFilename;
	}

	public String getResponseFilename() {
		return responseFilename;
	}

	public void setResponseFilename(String responseFilename) {
		this.responseFilename = responseFilename;
	}


	public String getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}

	public String getMasterRef() {
		return masterRef;
	}

	public void setMasterRef(String masterRef) {
		this.masterRef = masterRef;
	}

	public String getEventRef() {
		return eventRef;
	}

	public void setEventRef(String eventRef) {
		this.eventRef = eventRef;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTranAmount() {
		return tranAmount;
	}

	public void setTranAmount(String tranAmount) {
		this.tranAmount = tranAmount;
	}

	public String getCreditDebit() {
		return creditDebit;
	}

	public void setCreditDebit(String creditDebit) {
		this.creditDebit = creditDebit;
	}
}
