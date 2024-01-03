package com.ett.customersyncservice;

import java.util.List;

public class CustomerModel {

	String customerID;
	List<WarningMessage> warnMessage;
	String isOverridden;

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public List<WarningMessage> getWarnMessage() {
		return warnMessage;
	}

	public void setWarnMessage(List<WarningMessage> warnMessage) {
		this.warnMessage = warnMessage;
	}

	public String getIsOverridden() {
		return isOverridden;
	}

	public void setIsOverridden(String isOverridden) {
		this.isOverridden = isOverridden;
	}

}
