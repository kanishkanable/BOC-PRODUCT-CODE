package com.ett.fxrates;

import java.math.BigDecimal;

public class FXRateReqModel {

	String currencyCode;
	BigDecimal bookExchangeRate;
	BigDecimal applTransferBuyRate;
	BigDecimal marginImportTransaction;

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public BigDecimal getBookExchangeRate() {
		return bookExchangeRate;
	}

	public void setBookExchangeRate(BigDecimal bookExchangeRate) {
		this.bookExchangeRate = bookExchangeRate;
	}

	public BigDecimal getApplTransferBuyRate() {
		return applTransferBuyRate;
	}

	public void setApplTransferBuyRate(BigDecimal applTransferBuyRate) {
		this.applTransferBuyRate = applTransferBuyRate;
	}

	public BigDecimal getMarginImportTransaction() {
		return marginImportTransaction;
	}

	public void setMarginImportTransaction(BigDecimal marginImportTransaction) {
		this.marginImportTransaction = marginImportTransaction;
	}

}
