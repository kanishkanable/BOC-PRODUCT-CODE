package com.boc.ett.extracts.DailyFXDue;

import org.apache.log4j.Logger;

public class DailyFXDueBD implements ActionConstants {

	static DailyFXDueBD bd;

	private static Logger logger = Logger.getLogger(DailyFXDueBD.class.getName());

	DailyFXDueDAO dao;

	public static DailyFXDueBD getBD() {
		if (bd == null) {
			bd = new DailyFXDueBD();
		}
		return bd;
	}

	public StringBuffer fetchRecords(DailyFXDueVO dailyFXDueVO) {

		StringBuffer sbbd = new StringBuffer();
		logger.info(ENTERING_METHOD);

		try {

			dao = DailyFXDueDAO.getDAO();
			sbbd = dao.fetchRecords(dailyFXDueVO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(EXITING_METHOD);
		return sbbd;

	}

}
