package com.encoretheme.eod.extracts; 

import com.encoretheme.eod.implmentation.DatawarehouseExtractImpl;
import com.misys.tiplus2.enigma.job.UserExitActionStatus;
import com.misys.tiplus2.framework.async.processing.execution.step.StepContext;

public class DatawarehouseExtract implements
		com.misys.tiplus2.enigma.job.BatchUserExitAction {

	public static void main(String[] args) {
		new DatawarehouseExtract().run(0L, "0", (StepContext) null);
	}

	@Override
	public UserExitActionStatus checkStatus(Long arg0, String arg1,
			StepContext arg2) {
		return UserExitActionStatus.COMPLETE;
	}

	@Override
	public UserExitActionStatus run(Long arg0, String arg1, StepContext arg2) {
		try {
			boolean isExtractSuccess = DatawarehouseExtractImpl.process();
			if (isExtractSuccess) {
				return (UserExitActionStatus.COMPLETE);
			} else {
				return (UserExitActionStatus.FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return (UserExitActionStatus.FAILED);
		}
	}
}
