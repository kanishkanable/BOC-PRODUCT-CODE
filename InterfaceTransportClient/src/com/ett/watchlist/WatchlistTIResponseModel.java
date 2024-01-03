package com.ett.watchlist;

public class WatchlistTIResponseModel {

	String code;
	String reason;
	String MatchPercentage;
	String ID;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMatchPercentage() {
		return MatchPercentage;
	}

	public void setMatchPercentage(String matchPercentage) {
		MatchPercentage = matchPercentage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
