package com.boc.ett.extracts.sl_customs;

import java.util.ArrayList;
import org.apache.log4j.Logger;

public class SLCustomBD implements ActionConstants {

	static SLCustomBD bd;
	private static Logger logger = Logger.getLogger(SLCustomBD.class.getName());

	public static SLCustomBD getBD() {
		if (bd == null) {
			bd = new SLCustomBD();
		}
		return bd;
	}

	public ArrayList<SLCustomVO> fetchInputBranchDetailsBD(SLCustomVO slcustomVO) {
		SLCustomsDAO dao = null;
		String branchQuery = null;
		ArrayList<SLCustomVO> branchDetailList = null;
		logger.info(ENTERING_METHOD);
		try {
			dao = SLCustomsDAO.getDAO();
			branchQuery = dao.fetchQuery(slcustomVO);
			branchDetailList = dao.fetchInputBranchDetailsDAO(branchQuery);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(EXITING_METHOD);
		return branchDetailList;

	}

	public StringBuffer fetchExtractsRecords(SLCustomVO slcustomVO) {
		SLCustomsDAO dao = null;
		StringBuffer sbbd = new StringBuffer();
		logger.info(ENTERING_METHOD);
		try {

			dao = SLCustomsDAO.getDAO();
			sbbd = dao.fetchExtractsDAO(slcustomVO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(EXITING_METHOD);
		return sbbd;
	}
}
