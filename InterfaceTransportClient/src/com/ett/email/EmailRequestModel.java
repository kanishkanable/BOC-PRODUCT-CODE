package com.ett.email;

public class EmailRequestModel {

	String MasterReference;
	String EventReference;
	String bodyContent;
	String principalparty;
	String prodsubtype;
	String Subject;
	String partymailTo;
	String MultimailID;
	String HostName;
	String PortNumber;
	String UserName;
	String Password;
	String emailLabelName;



	public String getEmailLabelName() {
	return emailLabelName;
	}



	public void setEmailLabelName(String emailLabelName) {
	this.emailLabelName = emailLabelName;
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

	public String getProdsubtype() {
		return prodsubtype;
	}

	public void setProdsubtype(String prodsubtype) {
		this.prodsubtype = prodsubtype;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getPartymailTo() {
		return partymailTo;
	}

	public void setPartymailTo(String partymailTo) {
		this.partymailTo = partymailTo;
	}

	public String getMultimailID() {
		return MultimailID;
	}

	public void setMultimailID(String multimailID) {
		MultimailID = multimailID;
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

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
