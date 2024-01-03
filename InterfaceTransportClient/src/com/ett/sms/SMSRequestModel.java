package com.ett.sms;

public class SMSRequestModel {

	String MasterReference;
	String EventReference;
	String bodyContent;
	String principalparty;
	String prodsubtype;
	String MobileNumber;
	String MultiSMSID;
	String HostName;
	String PortNumber;

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

	public String getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}

	public String getPrincipalparty() {
		return principalparty;
	}

	public void setPrincipalparty(String principalparty) {
		this.principalparty = principalparty;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getMultiSMSID() {
		return MultiSMSID;
	}

	public void setMultiSMSID(String multiSMSID) {
		MultiSMSID = multiSMSID;
	}

	public String getHostName() {
		return HostName;
	}

	public void setHostName(String hostName) {
		HostName = hostName;
	}

	public String getPortNumber() {
		return PortNumber;
	}

	public void setPortNumber(String portNumber) {
		PortNumber = portNumber;
	}

	public String getProdsubtype() {
		return prodsubtype;
	}

	public void setProdsubtype(String prodsubtype) {
		this.prodsubtype = prodsubtype;
	}

}
