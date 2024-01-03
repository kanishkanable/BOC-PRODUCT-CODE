package com.ett.backofficebatch;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

public class BackOfficeBatchTIReqModel {
	String PostingBranch;
	String DebitCreditFlag;
	XMLGregorianCalendar ValueDate;
	String AccountType;
	String BackOfficeAccountNo;
	BigDecimal PostingAmount;
	String PostingCcy;
	String MasterReference;
	String EventReference;
	String AuthorizerUser;
	String CustomerMnemonic;
	String EventKey;
	BigDecimal ForeignCurrencyRate;
	String ForeignCurrencyCode;
	String postingCode;
	String postingNarrative;
	String postingNarrative2;
	BigDecimal lcyAmount=null;

	public BigDecimal getLcyAmount() {
		return lcyAmount;
	}

	public void setLcyAmount(BigDecimal lcyAmount) {
		this.lcyAmount = lcyAmount;
	}

	public String getPostingNarrative2() {
		return postingNarrative2;
	}

	public void setPostingNarrative2(String postingNarrative2) {
		this.postingNarrative2 = postingNarrative2;
	}

	public String getPostingNarrative() {
		return postingNarrative;
	}

	public void setPostingNarrative(String postingNarrative) {
		this.postingNarrative = postingNarrative;
	}

	public String getPostingCode() {
		return postingCode;
	}

	public void setPostingCode(String postingCode) {
		this.postingCode = postingCode;
	}

	public String getPostingBranch() {
		return PostingBranch;
	}

	public void setPostingBranch(String postingBranch) {
		PostingBranch = postingBranch;
	}

	public String getDebitCreditFlag() {
		return DebitCreditFlag;
	}

	public void setDebitCreditFlag(String debitCreditFlag) {
		DebitCreditFlag = debitCreditFlag;
	}

	public XMLGregorianCalendar getValueDate() {
		return ValueDate;
	}

	public void setValueDate(XMLGregorianCalendar valueDate) {
		ValueDate = valueDate;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getBackOfficeAccountNo() {
		return BackOfficeAccountNo;
	}

	public void setBackOfficeAccountNo(String backOfficeAccountNo) {
		BackOfficeAccountNo = backOfficeAccountNo;
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

	public String getAuthorizerUser() {
		return AuthorizerUser;
	}

	public void setAuthorizerUser(String authorizerUser) {
		AuthorizerUser = authorizerUser;
	}

	public String getCustomerMnemonic() {
		return CustomerMnemonic;
	}

	public void setCustomerMnemonic(String customerMnemonic) {
		CustomerMnemonic = customerMnemonic;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public BigDecimal getForeignCurrencyRate() {
		return ForeignCurrencyRate;
	}

	public void setForeignCurrencyRate(BigDecimal foreignCurrencyRate) {
		ForeignCurrencyRate = foreignCurrencyRate;
	}

	public String getForeignCurrencyCode() {
		return ForeignCurrencyCode;
	}

	public void setForeignCurrencyCode(String foreignCurrencyCode) {
		ForeignCurrencyCode = foreignCurrencyCode;
	}

}
