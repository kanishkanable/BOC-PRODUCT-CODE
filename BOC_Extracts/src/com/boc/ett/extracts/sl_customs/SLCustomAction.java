package com.boc.ett.extracts.sl_customs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

public class SLCustomAction implements ActionConstants{

	
	private static Logger logger = Logger.getLogger(SLCustomAction.class.getName());

	SLCustomBD bd = null;

	SLCustomVO slcustomVO = new SLCustomVO();
	
	

	ArrayList<SLCustomVO> branchDetailsList = null;

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public ByteArrayInputStream getXmlStream() {
		return xmlStream;
	}

	public void setXmlStream(ByteArrayInputStream xmlStream) {
		this.xmlStream = xmlStream;
	}

	String contentDisposition;
	ByteArrayInputStream xmlStream;

	public ArrayList<SLCustomVO> getBranchDetailsList() {
		return branchDetailsList;
	}

	public void setBranchDetailsList(ArrayList<SLCustomVO> branchDetailsList) {
		this.branchDetailsList = branchDetailsList;
	}

	Map<String, String> subProductType;

	public Map<String, String> getSubProductType() {
		return ActionConstants.SUB_PRODUCT_TYPE;
	}

	public void setSubProductType(Map<String, String> subProductType) {
		this.subProductType = subProductType;
	}

	public SLCustomBD getBd() {
		return bd;
	}

	public void setBd(SLCustomBD bd) {
		this.bd = bd;
	}

	public SLCustomVO getSlcustomVO() {
		return slcustomVO;
	}

	public void setSlcustomVO(SLCustomVO slcustomVO) {
		this.slcustomVO = slcustomVO;
	}

	public String fetchInputBranchDetails() {

		logger.info(ENTERING_METHOD);
		try {

			bd = SLCustomBD.getBD();
			branchDetailsList = bd.fetchInputBranchDetailsBD(slcustomVO);
			setBranchDetailsList(branchDetailsList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(EXITING_METHOD);
		return "success";

	}
	public static String getSysDate(String format) {

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();

		return formatter.format(date);
	}

	public String fetchRecords() throws IOException {
		StringBuffer strbuffer = new StringBuffer();
		logger.info(ENTERING_METHOD);
		try {
			bd = SLCustomBD.getBD();
			strbuffer = bd.fetchExtractsRecords(slcustomVO);
			//setContentDisposition("attachment; filename=\"" + "SL_Customs_" + slcustomVO.getBranchCode() + ".xml\"");
			setContentDisposition("attachment; filename=\"" + "SL_Customs_" + slcustomVO.getBranchCode() +"_"+getSysDate("yyyyMMdd-hh:mm:ss")+ ".xml\"");
			ByteArrayInputStream inputstream = new ByteArrayInputStream(strbuffer.toString().getBytes("UTF-8"));
			setXmlStream(inputstream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(EXITING_METHOD);
		return "download";
	}

	public String SLCustoms()

	{

		return "success";
	}

	public String branchSelection() {

		return "success";
	}

}
