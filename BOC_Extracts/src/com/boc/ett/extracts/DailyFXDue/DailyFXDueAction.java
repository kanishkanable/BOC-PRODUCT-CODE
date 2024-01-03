package com.boc.ett.extracts.DailyFXDue;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.log4j.Logger;

public class DailyFXDueAction implements ActionConstants {

	private static Logger logger = Logger.getLogger(DailyFXDueAction.class.getName());

	Map<String, String> scenario;
	DailyFXDueVO dailyFXDueVO = new DailyFXDueVO();
	DailyFXDueBD bd;
	String contentDisposition;
	ByteArrayInputStream stream;
	

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public ByteArrayInputStream getStream() {
		return stream;
	}

	public void setStream(ByteArrayInputStream stream) {
		this.stream = stream;
	}

	public DailyFXDueBD getBd() {
		return bd;
	}

	public void setBd(DailyFXDueBD bd) {
		this.bd = bd;
	}

	public Map<String, String> getScenario() {
		return ActionConstants.SCENARIO;
	}

	public void setScenario(Map<String, String> scenario) {
		this.scenario = scenario;
	}

	
	
	public String dailyFXDue()

	{

		return "success";

	}
	
	
/** Fetch Extracts from view**/
	
	public String fetchExtracts() {
		StringBuffer sb = new StringBuffer();
		logger.info(ENTERING_METHOD);
		try {

			System.out.println("Scenario : " + dailyFXDueVO.getScenario());
			String fileName = "FXDailyDue_" + SCENARIO.get(dailyFXDueVO.getScenario());
			bd = DailyFXDueBD.getBD();
			sb = bd.fetchRecords(dailyFXDueVO);
			setContentDisposition("attachment; filename=\"" + fileName + ".txt\"");
			ByteArrayInputStream inputstream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
			setStream(inputstream);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(EXITING_METHOD);
		return "download";
	}

	public DailyFXDueVO getDailyFXDueVO() {
		return dailyFXDueVO;
	}

	public void setDailyFXDueVO(DailyFXDueVO dailyFXDueVO) {
		this.dailyFXDueVO = dailyFXDueVO;
	}

}
