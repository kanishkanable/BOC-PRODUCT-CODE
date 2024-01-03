package com.encoretheme.eod.extracts;

import org.apache.log4j.Logger;


import com.encoretheme.eod.implmentation.IrmsOffBalanceSheetImpl;
import com.misys.tiplus2.enigma.job.UserExitActionStatus;
import com.misys.tiplus2.framework.async.processing.execution.step.StepContext;

public class IrmsOffBalanceSheet  implements com.misys.tiplus2.enigma.job.BatchUserExitAction{
	
	public static Logger logger = Logger.getLogger(IrmsOffBalanceSheet.class);
	public static void main(String[] args)
	{		
		System.out.println("Main Method in EOD");
		logger.info("Main Method in EOD");
	
		new IrmsOffBalanceSheet().run (0L,"0",(StepContext)null);
		
	}
	@Override
	public UserExitActionStatus checkStatus(Long arg0, String arg1,StepContext arg2)
	{
		
		System.out.println("Hello NPAEODPROCESS");
	logger.info("Hello NPAEODPROCESS");

		return UserExitActionStatus.COMPLETE;
	}

	public UserExitActionStatus run(Long arg0, String arg1, StepContext arg2){
		IrmsOffBalanceSheetImpl coldt = new IrmsOffBalanceSheetImpl();
		String Satus="";
		
		Satus=coldt.extract("OFF_BALANCE_SHEET", "OFF_BALANCE_SHEET_HEADER",
				"destinationFilePath");
		logger.info(" inserting the data----->"+Satus);
		return (UserExitActionStatus.COMPLETE);
	}
	}
