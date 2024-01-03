package com.ett.interace.dao;

import java.sql.SQLException;

import com.ett.util.BOCCommonUtil;

public class GetData {
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	public void param1(String param1) {
		this.param1 = param1;
	}
	public void param2(String param2) {
		this.param2 = param2;
	}
	public void param3(String param3) {
		this.param3 = param3;
	}

public void param4(String param4) {
	this.param4 = param4;
}
	
	public String GetData1() throws SQLException {
		
		
		String result = "Success";
		
		if(BOCCommonUtil.checkIfExistsInTI(param1, param2, param3)){
		
			result = "Failure";
		}
		
		
		System.out.println("Getdata1 value-------------------->"+result);
		return result;
	}
	public String GetData2() throws SQLException {
		
		
		String result = "Success";
		
		if(BOCCommonUtil.checkACCExistsInTI(param1, param2, param3,param4)){
		
			result = "Failure";
		}
		
		
		System.out.println("Getdata2 value-------------------->"+result);
		return result;
	}
	
public String GetData3() throws SQLException {
		
		
		String result = "Failure";
		
		if(BOCCommonUtil.checkIfExistsInTI(param1, param2, param3)){
		
			result = "Success";
		}
		
		
		System.out.println("Getdata3 value-------------------->"+result);
		return result;
	}
}
