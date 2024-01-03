package com.ett.eod.finaceposting;
import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;
public class EodBankReqModel {

	String BACKOFFICEACCOUNTNO;
	BigDecimal POSTINGAMOUNT;
	String POSTINGCCY;
	BigDecimal EXCHANGERATE;
	String AuthorizerUser;
	String DEBITCREDITFLAG;
	XMLGregorianCalendar VALUEDATE;
	String POSTINGNARRATIVE1;
	String MASTERREFERENCE;
	String POSTINGBRANCH;
	String DebitCreditCode;
	String AccountType;
	String ForeignCurrencyCode;
	BigDecimal LCEAmount;
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
	
	
	
	public BigDecimal getLCEAmount() {
		return LCEAmount;
	}
	public void setLCEAmount(BigDecimal lCEAmount) {
		LCEAmount = lCEAmount;
	}
	public String getAuthorizerUser() {
		return AuthorizerUser;
	}
	public void setAuthorizerUser(String authorizerUser) {
		AuthorizerUser = authorizerUser;
	}
	public String getForeignCurrencyCode() {
		return ForeignCurrencyCode;
	}
	public void setForeignCurrencyCode(String foreignCurrencyCode) {
		ForeignCurrencyCode = foreignCurrencyCode;
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
	String EffectiveDate;
	XMLGregorianCalendar ValueDate;
	
	public XMLGregorianCalendar getValueDate() {
		return ValueDate;
	}
	public void setValueDate(XMLGregorianCalendar valueDate) {
		ValueDate = valueDate;
	}
	public String getEffectiveDate() {
		return EffectiveDate;
	}
	public void setEffectiveDate(String string) {
		EffectiveDate = string;
	}
	public String getPOSTINGBRANCH() {
		return POSTINGBRANCH;
	}
	public void setPOSTINGBRANCH(String pOSTINGBRANCH) {
		POSTINGBRANCH = pOSTINGBRANCH;
	}
	public String getBACKOFFICEACCOUNTNO() {
		return BACKOFFICEACCOUNTNO;
	}
	public void setBACKOFFICEACCOUNTNO(String bACKOFFICEACCOUNTNO) {
		BACKOFFICEACCOUNTNO = bACKOFFICEACCOUNTNO;
	}
	public BigDecimal getPOSTINGAMOUNT() {
		return POSTINGAMOUNT;
	}
	public void setPOSTINGAMOUNT(BigDecimal pOSTINGAMOUNT) {
		POSTINGAMOUNT = pOSTINGAMOUNT;
	}
	public String getPOSTINGCCY() {
		return POSTINGCCY;
	}
	public void setPOSTINGCCY(String pOSTINGCCY) {
		POSTINGCCY = pOSTINGCCY;
	}
	public BigDecimal getEXCHANGERATE() {
		return EXCHANGERATE;
	}
	public void setEXCHANGERATE(BigDecimal eXCHANGERATE) {
		EXCHANGERATE = eXCHANGERATE;
	}
	public String getDEBITCREDITFLAG() {
		return DEBITCREDITFLAG;
	}
	public void setDEBITCREDITFLAG(String dEBITCREDITFLAG) {
		DEBITCREDITFLAG = dEBITCREDITFLAG;
	}
	public XMLGregorianCalendar getVALUEDATE() {
		return VALUEDATE;
	}
	public void setVALUEDATE(XMLGregorianCalendar vALUEDATE) {
		VALUEDATE = vALUEDATE;
	}
	public String getPOSTINGNARRATIVE1() {
		return POSTINGNARRATIVE1;
	}
	public void setPOSTINGNARRATIVE1(String pOSTINGNARRATIVE1) {
		POSTINGNARRATIVE1 = pOSTINGNARRATIVE1;
	}
	public String getMASTERREFERENCE() {
		return MASTERREFERENCE;
	}
	public void setMASTERREFERENCE(String mASTERREFERENCE) {
		MASTERREFERENCE = mASTERREFERENCE;
	}
	
}
