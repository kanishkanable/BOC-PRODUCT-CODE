package com.encore.extracts.vo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

public class CustomerVO {

	String sbb;
	String cust_corp;
	String correspondent;
	String bank;
	String mnemonic;
	String fullName;
	String number;
	String location;
	String country;
	String accountOfficer;
	String group;
	String mainLocalCustomers;

	String custMnemonic;
	String custFullName;
	String custNumber;
	String custGroup;
	String custCountry;
	String custAccountOfficer;
	String custBlocked;
	String custLocation;

	String countryCode;
	String countryName;
	String cntryCode;
	String ctryName;
	String ctryTenor;
	String isoCtryCode;

	String limitCategory;
	String limitAmount;
	String limitCurrency;
	String limitExpiry;
	String limitCategoryID;
	String action;
	String userID;
	String status;
	String makerCheckerAction;
	String eJBReq;

	public String getMakerCheckerAction() {
		return makerCheckerAction;
	}

	public void setMakerCheckerAction(String makerCheckerAction) {
		this.makerCheckerAction = makerCheckerAction;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	String codeType;

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeDes() {
		return codeDes;
	}

	public void setCodeDes(String codeDes) {
		this.codeDes = codeDes;
	}

	String codeDes;

	public String getSbb() {
		return sbb;
	}

	public void setSbb(String sbb) {
		this.sbb = sbb;
	}

	public String getCust_corp() {
		return cust_corp;
	}

	public void setCust_corp(String cust_corp) {
		this.cust_corp = cust_corp;
	}

	public String getCorrespondent() {
		return correspondent;
	}

	public void setCorrespondent(String correspondent) {
		this.correspondent = correspondent;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAccountOfficer() {
		return accountOfficer;
	}

	public void setAccountOfficer(String accountOfficer) {
		this.accountOfficer = accountOfficer;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getMainLocalCustomers() {
		return mainLocalCustomers;
	}

	public void setMainLocalCustomers(String mainLocalCustomers) {
		this.mainLocalCustomers = mainLocalCustomers;
	}

	public String getCustMnemonic() {
		return custMnemonic;
	}

	public void setCustMnemonic(String custMnemonic) {
		this.custMnemonic = custMnemonic;
	}

	public String getCustFullName() {
		return custFullName;
	}

	public void setCustFullName(String custFullName) {
		this.custFullName = custFullName;
	}

	public String getCustLocation() {
		return custLocation;
	}

	public void setCustLocation(String custLocation) {
		this.custLocation = custLocation;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public String getCustGroup() {
		return custGroup;
	}

	public void setCustGroup(String custGroup) {
		this.custGroup = custGroup;
	}

	public String getCustCountry() {
		return custCountry;
	}

	public void setCustCountry(String custCountry) {
		this.custCountry = custCountry;
	}

	public String getCustAccountOfficer() {
		return custAccountOfficer;
	}

	public void setCustAccountOfficer(String custAccountOfficer) {
		this.custAccountOfficer = custAccountOfficer;
	}

	public String getCustBlocked() {
		return custBlocked;
	}

	public void setCustBlocked(String custBlocked) {
		this.custBlocked = custBlocked;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCntryCode() {
		return cntryCode;
	}

	public void setCntryCode(String cntryCode) {
		this.cntryCode = cntryCode;
	}

	public String getCtryName() {
		return ctryName;
	}

	public void setCtryName(String ctryName) {
		this.ctryName = ctryName;
	}

	public String getCtryTenor() {
		return ctryTenor;
	}

	public void setCtryTenor(String ctryTenor) {
		this.ctryTenor = ctryTenor;
	}

	public String getIsoCtryCode() {
		return isoCtryCode;
	}

	public void setIsoCtryCode(String isoCtryCode) {
		this.isoCtryCode = isoCtryCode;
	}

	public String getLimitCategory() {
		return limitCategory;
	}

	public void setLimitCategory(String limitCategory) {
		this.limitCategory = limitCategory;
	}

	public String getLimitAmount() {
		/*
		 * Locale locale = new Locale("en", "GB"); DecimalFormat decimalFormat =
		 * (DecimalFormat) DecimalFormat.getInstance(locale); limitAmount =
		 * decimalFormat.format(new BigDecimal(limitAmount));
		 */
		return limitAmount;
	}

	public void setLimitAmount(String limitAmount) {
		this.limitAmount = limitAmount;
	}

	public String getLimitCurrency() {
		return limitCurrency;
	}

	public void setLimitCurrency(String limitCurrency) {
		this.limitCurrency = limitCurrency;
	}

	public String getLimitExpiry() {
		return limitExpiry;
	}

	public void setLimitExpiry(String limitExpiry) {
		this.limitExpiry = limitExpiry;
	}

	public String getLimitCategoryID() {
		return limitCategoryID;
	}

	public void setLimitCategoryID(String limitCategoryID) {
		this.limitCategoryID = limitCategoryID;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getEJBReq() {
		return eJBReq;
	}

	public void setEJBReq(String eJBReq) {
		this.eJBReq = eJBReq;
	}


}
