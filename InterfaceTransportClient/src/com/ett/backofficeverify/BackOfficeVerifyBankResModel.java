package com.ett.backofficeverify;

import java.math.BigDecimal;

public class BackOfficeVerifyBankResModel {

	public BigDecimal AvailBalanceAmt;
	public String AvailBalanceCcy;
	public String AccountStatus;

	public BigDecimal getAvailBalanceAmt() {
		return AvailBalanceAmt;
	}

	public void setAvailBalanceAmt(BigDecimal availBalanceAmt) {
		AvailBalanceAmt = availBalanceAmt;
	}

	public String getAvailBalanceCcy() {
		return AvailBalanceCcy;
	}

	public void setAvailBalanceCcy(String availBalanceCcy) {
		AvailBalanceCcy = availBalanceCcy;
	}

	public String getAccountStatus() {
		return AccountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		AccountStatus = accountStatus;
	}

}
