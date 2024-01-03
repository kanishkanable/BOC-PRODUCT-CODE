package com.ett.backofficebatch;

import java.math.BigDecimal;

public class BackOfficeBatchBankReqModel {

	String BranchNumber;
	String DebitCreditCode;
	String EffectiveDate;
	String PriotoFuturdatecheck;
	public String getPriotoFuturdatecheck() {
		return PriotoFuturdatecheck;
	}

	public void setPriotoFuturdatecheck(String priotoFuturdatecheck) {
		PriotoFuturdatecheck = priotoFuturdatecheck;
	}

	String AccountType;
	String AccountNumber;
	BigDecimal TransactionAmount;
	BigDecimal LCEAmount;
	String ForeignCurrencyCode;
	BigDecimal ForeignCurrencyRate;
	String ReferenceNumber;
	String AlphabeticData1;
	String AlphabeticData2;
	String AlphabeticData3;
	String TransactionTime;
	String CIFKey;
	String MadeBy;
	String ApprovedBy;
	String AdditionalFCYInfo;
	String OriginationInfo;
	String ForeignCurrencyOverride;
	String ApplicationTranCode;
	String AuthorisedUserID;
	String DealerNumber;
	String ProcessingDate;
	String FileName;

	public String getBranchNumber() {
		return BranchNumber;
	}

	public void setBranchNumber(String branchNumber) {
		BranchNumber = branchNumber;
	}

	public String getDebitCreditCode() {
		return DebitCreditCode;
	}

	public void setDebitCreditCode(String debitCreditCode) {
		DebitCreditCode = debitCreditCode;
	}

	public String getEffectiveDate() {
		return EffectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		EffectiveDate = effectiveDate;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public BigDecimal getTransactionAmount() {
		return TransactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		TransactionAmount = transactionAmount;
	}

	public BigDecimal getLCEAmount() {
		return LCEAmount;
	}

	public void setLCEAmount(BigDecimal lCEAmount) {
		LCEAmount = lCEAmount;
	}

	public String getForeignCurrencyCode() {
		return ForeignCurrencyCode;
	}

	public void setForeignCurrencyCode(String foreignCurrencyCode) {
		ForeignCurrencyCode = foreignCurrencyCode;
	}

	public BigDecimal getForeignCurrencyRate() {
		return ForeignCurrencyRate;
	}

	public void setForeignCurrencyRate(BigDecimal foreignCurrencyRate) {
		ForeignCurrencyRate = foreignCurrencyRate;
	}

	public String getReferenceNumber() {
		return ReferenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		ReferenceNumber = referenceNumber;
	}

	public String getAlphabeticData1() {
		return AlphabeticData1;
	}

	public void setAlphabeticData1(String alphabeticData1) {
		AlphabeticData1 = alphabeticData1;
	}

	public String getAlphabeticData2() {
		return AlphabeticData2;
	}

	public void setAlphabeticData2(String alphabeticData2) {
		AlphabeticData2 = alphabeticData2;
	}

	public String getAlphabeticData3() {
		return AlphabeticData3;
	}

	public void setAlphabeticData3(String alphabeticData3) {
		AlphabeticData3 = alphabeticData3;
	}

	public String getTransactionTime() {
		return TransactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		TransactionTime = transactionTime;
	}

	public String getCIFKey() {
		return CIFKey;
	}

	public void setCIFKey(String cIFKey) {
		CIFKey = cIFKey;
	}

	public String getMadeBy() {
		return MadeBy;
	}

	public void setMadeBy(String madeBy) {
		MadeBy = madeBy;
	}

	public String getApprovedBy() {
		return ApprovedBy;
	}

	public void setApprovedBy(String approvedBy) {
		ApprovedBy = approvedBy;
	}

	public String getAdditionalFCYInfo() {
		return AdditionalFCYInfo;
	}

	public void setAdditionalFCYInfo(String additionalFCYInfo) {
		AdditionalFCYInfo = additionalFCYInfo;
	}

	public String getOriginationInfo() {
		return OriginationInfo;
	}

	public void setOriginationInfo(String originationInfo) {
		OriginationInfo = originationInfo;
	}

	public String getForeignCurrencyOverride() {
		return ForeignCurrencyOverride;
	}

	public void setForeignCurrencyOverride(String foreignCurrencyOverride) {
		ForeignCurrencyOverride = foreignCurrencyOverride;
	}

	public String getApplicationTranCode() {
		return ApplicationTranCode;
	}

	public void setApplicationTranCode(String applicationTranCode) {
		ApplicationTranCode = applicationTranCode;
	}

	public String getAuthorisedUserID() {
		return AuthorisedUserID;
	}

	public void setAuthorisedUserID(String authorisedUserID) {
		AuthorisedUserID = authorisedUserID;
	}

	public String getDealerNumber() {
		return DealerNumber;
	}

	public void setDealerNumber(String dealerNumber) {
		DealerNumber = dealerNumber;
	}

	public String getProcessingDate() {
		return ProcessingDate;
	}

	public void setProcessingDate(String processingDate) {
		ProcessingDate = processingDate;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

}
