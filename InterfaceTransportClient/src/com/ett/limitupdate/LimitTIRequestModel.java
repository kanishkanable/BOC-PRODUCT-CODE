package com.ett.limitupdate;

public class LimitTIRequestModel {

	private String customerNo;
	private String facilityID;
	private String currCode;
	private String expDate;
	private String approvedLineAmt;
	private String outstandingBal;
	private String availLineAmt;
	private String revolvingFlag;
	private String cancelFlag;
	private String approvedLineAmtLCY;
	private String outstandingBalLCY;

	public String getOutstandingBalLCY() {
		return outstandingBalLCY;
	}

	public void setOutstandingBalLCY(String outstandingBalLCY) {
		this.outstandingBalLCY = outstandingBalLCY;
	}

	public String getApprovedLineAmtLCY() {
		return approvedLineAmtLCY;
	}

	public void setApprovedLineAmtLCY(String approvedLineAmtLCY) {
		this.approvedLineAmtLCY = approvedLineAmtLCY;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getFacilityID() {
		return facilityID;
	}

	public void setFacilityID(String facilityID) {
		this.facilityID = facilityID;
	}

	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getApprovedLineAmt() {
		return approvedLineAmt;
	}

	public void setApprovedLineAmt(String approvedLineAmt) {
		this.approvedLineAmt = approvedLineAmt;
	}

	public String getOutstandingBal() {
		return outstandingBal;
	}

	public void setOutstandingBal(String outstandingBal) {
		this.outstandingBal = outstandingBal;
	}

	public String getAvailLineAmt() {
		return availLineAmt;
	}

	public void setAvailLineAmt(String availLineAmt) {
		this.availLineAmt = availLineAmt;
	}

	public String getRevolvingFlag() {
		return revolvingFlag;
	}

	public void setRevolvingFlag(String revolvingFlag) {
		this.revolvingFlag = revolvingFlag;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

}
