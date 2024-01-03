package com.encoretheme.eod.extracts;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.encoretheme.eod.implmentation.SLCustomImpl;
import com.misys.tiplus2.enigma.job.UserExitActionStatus;
import com.misys.tiplus2.framework.async.processing.execution.step.StepContext;

import com.boc.ett.extracts.sl_customs.*;

public class SLCustom implements com.misys.tiplus2.enigma.job.BatchUserExitAction{
	
	public static Logger logger = Logger.getLogger(SLCustom.class);
	public static void main(String[] args)
	{		
		System.out.println("Main Method in EOD");
		logger.info("Main Method in EOD");
	
		new SLCustom().run (0L,"0",(StepContext)null);
		
	}
	@Override
	public UserExitActionStatus checkStatus(Long arg0, String arg1,StepContext arg2)
	{
		
		System.out.println("Hello NPAEODPROCESS");
	logger.info("Hello NPAEODPROCESS");

		return UserExitActionStatus.COMPLETE;
	}

	public UserExitActionStatus run(Long arg0, String arg1, StepContext arg2){
		SLCustomXml coldt = new SLCustomXml();
		String Satus="";
		
		try {
			Satus=coldt.fetchRecords();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(" inserting the data----->"+Satus);
		return (UserExitActionStatus.COMPLETE);
	}
	}

