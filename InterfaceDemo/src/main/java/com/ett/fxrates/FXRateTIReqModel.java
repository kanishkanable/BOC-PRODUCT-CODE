package com.ett.fxrates;

import java.math.BigDecimal;

public class FXRateTIReqModel {

	String currency;
	BigDecimal midRate;
	BigDecimal ttBuy;
	BigDecimal ttSell;
	BigDecimal bcImpBuy;
	BigDecimal bcImpSell;
	String tiTTRequestXML;
	String tiIMPRequestXML;
	String tiSpotRateXML;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getMidRate() {
		return midRate;
	}

	public void setMidRate(BigDecimal midRate) {
		this.midRate = midRate;
	}

	public BigDecimal getTtBuy() {
		return ttBuy;
	}

	public void setTtBuy(BigDecimal ttBuy) {
		this.ttBuy = ttBuy;
	}

	public BigDecimal getTtSell() {
		return ttSell;
	}

	public void setTtSell(BigDecimal ttSell) {
		this.ttSell = ttSell;
	}

	public BigDecimal getBcImpBuy() {
		return bcImpBuy;
	}

	public void setBcImpBuy(BigDecimal bcImpBuy) {
		this.bcImpBuy = bcImpBuy;
	}

	public BigDecimal getBcImpSell() {
		return bcImpSell;
	}

	public void setBcImpSell(BigDecimal bcImpSell) {
		this.bcImpSell = bcImpSell;
	}

	public String getTiTTRequestXML() {
		return tiTTRequestXML;
	}

	public void setTiTTRequestXML(String tiTTRequestXML) {
		this.tiTTRequestXML = tiTTRequestXML;
	}

	public String getTiIMPRequestXML() {
		return tiIMPRequestXML;
	}

	public void setTiIMPRequestXML(String tiIMPRequestXML) {
		this.tiIMPRequestXML = tiIMPRequestXML;
	}

	public String getTiSpotRateXML() {
		return tiSpotRateXML;
	}

	public void setTiSpotRateXML(String tiSpotRateXML) {
		this.tiSpotRateXML = tiSpotRateXML;
	}

}
