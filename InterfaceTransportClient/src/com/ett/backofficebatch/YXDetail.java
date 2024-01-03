package com.ett.backofficebatch;

import java.math.BigDecimal;

public class YXDetail {
	private String id;
	private String debitCredit;
	private String ccy;
	private BigDecimal amount;
	private BigDecimal lcyAmount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDebitCredit() {
		return debitCredit;
	}

	public void setDebitCredit(String debitCredit) {
		this.debitCredit = debitCredit;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getLcyAmount() {
		return lcyAmount;
	}

	public void setLcyAmount(BigDecimal lcyAmount) {
		this.lcyAmount = lcyAmount;
	}

}
