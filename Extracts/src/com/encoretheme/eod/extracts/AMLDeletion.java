package com.encoretheme.eod.extracts;

import org.apache.log4j.Logger;

import com.encoretheme.eod.implmentation.AMLDeletionImpl; 
import com.misys.tiplus2.enigma.job.UserExitActionStatus;
import com.misys.tiplus2.framework.async.processing.execution.step.StepContext;

public class AMLDeletion implements com.misys.tiplus2.enigma.job.BatchUserExitAction {

	public static Logger logger = Logger.getLogger(AMLDeletion.class);

	public static void main(String[] args) {
		System.out.println("Main Method in EOD");
		logger.info("Main Method in EOD");

		new AMLDeletion().run(0L, "0", (StepContext) null);

	}

	@Override
	public UserExitActionStatus checkStatus(Long arg0, String arg1, StepContext arg2) {

		logger.info("Hello AMLExtracts");

		return UserExitActionStatus.COMPLETE;
	}

	public UserExitActionStatus run(Long arg0, String arg1, StepContext arg2) {
		
		String Satus = "";
		Satus = AMLDeletionImpl.deletePreviousDayAMLEntries();
		logger.info(" inserting the data for AMLExtracts----->" + Satus);
		return (UserExitActionStatus.COMPLETE);
	}
}
