package com.ett.email;

public class EmailResponseModel {

	String bankRequest;
	String errorMessage;

	public String getBankRequest() {
		return bankRequest;
	}

	public void setBankRequest(String bankRequest) {
		this.bankRequest = bankRequest;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
