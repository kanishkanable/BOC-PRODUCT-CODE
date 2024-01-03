package com.ett.backofficebatch;

import java.util.List;

public class BackOfficeBatchBankRequestModel {

	List<BackOfficeBatchBankReqModel> BackOfficeBatchBankReqModel;
	String FileName;

	public List<BackOfficeBatchBankReqModel> getBackOfficeBatchBankReqModel() {
		return BackOfficeBatchBankReqModel;
	}

	public void setBackOfficeBatchBankReqModel(
			List<BackOfficeBatchBankReqModel> backOfficeBatchBankReqModel) {
		BackOfficeBatchBankReqModel = backOfficeBatchBankReqModel;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

}
