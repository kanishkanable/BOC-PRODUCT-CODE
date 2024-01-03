package com.ett.backofficebatch;

public class BackOfficeBatchResponseReqModel {

	String ReferenceNumber;
	String AccountNumber;
	String AccountType;
	String DebitCreditCode;
	String TranAmountTranCcy;
	String TranAmountLocalCcy;
	String PostingStatus;
	String StatusDesc;
	String TransactionID;
	
	public String getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}

	

	public String getReferenceNumber() {
		return ReferenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		ReferenceNumber = referenceNumber;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getDebitCreditCode() {
		return DebitCreditCode;
	}

	public void setDebitCreditCode(String debitCreditCode) {
		DebitCreditCode = debitCreditCode;
	}

	public String getTranAmountTranCcy() {
		return TranAmountTranCcy;
	}

	public void setTranAmountTranCcy(String tranAmountTranCcy) {
		TranAmountTranCcy = tranAmountTranCcy;
	}

	public String getTranAmountLocalCcy() {
		return TranAmountLocalCcy;
	}

	public void setTranAmountLocalCcy(String tranAmountLocalCcy) {
		TranAmountLocalCcy = tranAmountLocalCcy;
	}

	public String getPostingStatus() {
		return PostingStatus;
	}

	public void setPostingStatus(String postingStatus) {
		PostingStatus = postingStatus;
	}

	public String getStatusDesc() {
		return StatusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		StatusDesc = statusDesc;
	}

}
