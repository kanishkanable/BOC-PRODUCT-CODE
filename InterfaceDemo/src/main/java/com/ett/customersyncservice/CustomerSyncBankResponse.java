package com.ett.customersyncservice;

public class CustomerSyncBankResponse {

	String CustomerType;
	String FullName;
	String ShortName;
	String DefaultBranch;
	String Salutation;
	String NameAndAddress;
	String ZipCode;
	String Phone;
	String Email;
	String bankResponse;
	String Status;

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getCustomerType() {
		return CustomerType;
	}

	public void setCustomerType(String customerType) {
		CustomerType = customerType;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getShortName() {
		return ShortName;
	}

	public void setShortName(String shortName) {
		ShortName = shortName;
	}

	public String getDefaultBranch() {
		return DefaultBranch;
	}

	public void setDefaultBranch(String defaultBranch) {
		DefaultBranch = defaultBranch;
	}

	public String getSalutation() {
		return Salutation;
	}

	public void setSalutation(String salutation) {
		Salutation = salutation;
	}

	public String getNameAndAddress() {
		return NameAndAddress;
	}

	public void setNameAndAddress(String nameAndAddress) {
		NameAndAddress = nameAndAddress;
	}

	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getBankResponse() {
		return bankResponse;
	}

	public void setBankResponse(String bankResponse) {
		this.bankResponse = bankResponse;
	}

}
