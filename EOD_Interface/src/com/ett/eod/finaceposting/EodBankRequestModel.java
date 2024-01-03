package com.ett.eod.finaceposting;

import java.util.List;
public class EodBankRequestModel {
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
