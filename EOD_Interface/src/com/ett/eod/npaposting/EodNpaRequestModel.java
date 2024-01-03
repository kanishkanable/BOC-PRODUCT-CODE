package com.ett.eod.npaposting;

import java.util.List;

import com.ett.eod.finaceposting.EodBankReqModel;

public class EodNpaRequestModel {
	List<EodBankReqModel> EodBankReqModel;

	String Filename;

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

	public List<EodBankReqModel> getEodBankReqModel() {
		return EodBankReqModel;
	}

	public void setEodBankReqModel(List<EodBankReqModel> eodBankReqModel) {
		EodBankReqModel = eodBankReqModel;
	}
}
