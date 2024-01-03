package com.ett.forwardcontracts;

import java.math.BigDecimal;

public class ForwardContractBankResponse {

	String DealRef;
	String ExchangeRate;
	BigDecimal AvailableAmount;
	String AvailCurrency;
	String StartDate;
	String ExpiryDate;
	String BaseCurrency;
	String CustID;

	public String getDealRef() {
		return DealRef;
	}

	public void setDealRef(String dealRef) {
		DealRef = dealRef;
	}

	public String getExchangeRate() {
		return ExchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		ExchangeRate = exchangeRate;
	}

	public BigDecimal getAvailableAmount() {
		return AvailableAmount;
	}

	public void setAvailableAmount(BigDecimal availableAmount) {
		AvailableAmount = availableAmount;
	}

	public String getAvailCurrency() {
		return AvailCurrency;
	}

	public void setAvailCurrency(String availCurrency) {
		AvailCurrency = availCurrency;
	}

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getExpiryDate() {
		return ExpiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		ExpiryDate = expiryDate;
	}

	public String getBaseCurrency() {
		return BaseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		BaseCurrency = baseCurrency;
	}

	public String getCustID() {
		return CustID;
	}

	public void setCustID(String custID) {
		CustID = custID;
	}

}
