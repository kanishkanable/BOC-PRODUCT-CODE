package com.boc.usdclearing;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Field {
	@JsonProperty("Reference")
	public String reference;
	@JsonProperty("DestBnk")
	public String destBnk;
	@JsonProperty("DestBnkNm")
	public String destBnkNm;
	@JsonProperty("DestBrchNm")
	public String destBrchNm;
	@JsonProperty("DestAcc")
	public String destAcc;
	@JsonProperty("DestBrch")
	public String destBrch;
	@JsonProperty("DestName")
	public String destName;
	@JsonProperty("DestAdd1")
	public String DestAdd1;
	@JsonProperty("DestAdd2")
	public String DestAdd2;
	@JsonProperty("DestAdd3")
	public String DestAdd3;
	@JsonProperty("TxnAmount")
	public String txnAmount;
	@JsonProperty("Fee")
	public String fee;
	@JsonProperty("TxnAmtCurr")
	public String txnAmtCurr;
	@JsonProperty("TxnCurr")
	public String txnCurr;
	@JsonProperty("OrgAcc")
	public String orgAcc;
	@JsonProperty("OrgAccType")
	public String OrgAccType;
	@JsonProperty("OrgAccName")
	public String OrgAccName;
	@JsonProperty("OrgAccAdd1")
	public String OrgAccAdd1;
	@JsonProperty("OrgAccAdd2")
	public String OrgAccAdd2;
	@JsonProperty("OrgAccAdd3")
	public String OrgAccAdd3;
	@JsonProperty("OrgAccCus")
	public String orgAccCus;
	@JsonProperty("OrgAccBrch")
	public String orgAccBrch;
	@JsonProperty("OrgAccCurr")
	public String orgAccCurr;
	@JsonProperty("OrgBrch")
	public String orgBrch;
	@JsonProperty("CustDesc")
	public String custDesc;
	@JsonProperty("PurCode")
	public String purCode;
	@JsonProperty("ClrType")
	public String clrType;
	@JsonProperty("TxnType")
	public String txnType;
	@JsonProperty("OrgApp")
	public String orgApp;
	@JsonProperty("ValDate")
	public String valDate;
	@JsonProperty("SubPurposeCode")
	public String SubPurposeCode;

	public String getSubPurposeCode() {
		return SubPurposeCode;
	}

	public void setSubPurposeCode(String subPurposeCode) {
		SubPurposeCode = subPurposeCode;
	}

	public String getDestAdd1() {
		return DestAdd1;
	}

	public void setDestAdd1(String destAdd1) {
		DestAdd1 = destAdd1;
	}

	public String getDestAdd2() {
		return DestAdd2;
	}

	public void setDestAdd2(String destAdd2) {
		DestAdd2 = destAdd2;
	}

	public String getDestAdd3() {
		return DestAdd3;
	}

	public void setDestAdd3(String destAdd3) {
		DestAdd3 = destAdd3;
	}

	public String getOrgAccType() {
		return OrgAccType;
	}

	public void setOrgAccType(String orgAccType) {
		OrgAccType = orgAccType;
	}

	public String getOrgAccName() {
		return OrgAccName;
	}

	public void setOrgAccName(String orgAccName) {
		OrgAccName = orgAccName;
	}

	public String getOrgAccAdd1() {
		return OrgAccAdd1;
	}

	public void setOrgAccAdd1(String orgAccAdd1) {
		OrgAccAdd1 = orgAccAdd1;
	}

	public String getOrgAccAdd2() {
		return OrgAccAdd2;
	}

	public void setOrgAccAdd2(String orgAccAdd2) {
		OrgAccAdd2 = orgAccAdd2;
	}

	public String getOrgAccAdd3() {
		return OrgAccAdd3;
	}

	public void setOrgAccAdd3(String orgAccAdd3) {
		OrgAccAdd3 = orgAccAdd3;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDestBnk() {
		return destBnk;
	}

	public void setDestBnk(String destBnk) {
		this.destBnk = destBnk;
	}

	public String getDestBnkNm() {
		return destBnkNm;
	}

	public void setDestBnkNm(String destBnkNm) {
		this.destBnkNm = destBnkNm;
	}

	public String getDestBrchNm() {
		return destBrchNm;
	}

	public void setDestBrchNm(String destBrchNm) {
		this.destBrchNm = destBrchNm;
	}

	public String getDestAcc() {
		return destAcc;
	}

	public void setDestAcc(String destAcc) {
		this.destAcc = destAcc;
	}

	public String getDestBrch() {
		return destBrch;
	}

	public void setDestBrch(String destBrch) {
		this.destBrch = destBrch;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public String getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getTxnAmtCurr() {
		return txnAmtCurr;
	}

	public void setTxnAmtCurr(String txnAmtCurr) {
		this.txnAmtCurr = txnAmtCurr;
	}

	public String getTxnCurr() {
		return txnCurr;
	}

	public void setTxnCurr(String txnCurr) {
		this.txnCurr = txnCurr;
	}

	public String getOrgAcc() {
		return orgAcc;
	}

	public void setOrgAcc(String orgAcc) {
		this.orgAcc = orgAcc;
	}

	public String getOrgAccCus() {
		return orgAccCus;
	}

	public void setOrgAccCus(String orgAccCus) {
		this.orgAccCus = orgAccCus;
	}

	public String getOrgAccBrch() {
		return orgAccBrch;
	}

	public void setOrgAccBrch(String orgAccBrch) {
		this.orgAccBrch = orgAccBrch;
	}

	public String getOrgAccCurr() {
		return orgAccCurr;
	}

	public void setOrgAccCurr(String orgAccCurr) {
		this.orgAccCurr = orgAccCurr;
	}

	public String getOrgBrch() {
		return orgBrch;
	}

	public void setOrgBrch(String orgBrch) {
		this.orgBrch = orgBrch;
	}

	public String getCustDesc() {
		return custDesc;
	}

	public void setCustDesc(String custDesc) {
		this.custDesc = custDesc;
	}

	public String getPurCode() {
		return purCode;
	}

	public void setPurCode(String purCode) {
		this.purCode = purCode;
	}

	public String getClrType() {
		return clrType;
	}

	public void setClrType(String clrType) {
		this.clrType = clrType;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getOrgApp() {
		return orgApp;
	}

	public void setOrgApp(String orgApp) {
		this.orgApp = orgApp;
	}

	public String getValDate() {
		return valDate;
	}

	public void setValDate(String valDate) {
		this.valDate = valDate;
	}

}