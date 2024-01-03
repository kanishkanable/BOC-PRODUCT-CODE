package com.boc.themebridge.adapter.impl;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

public class ExtractsGatewayListener implements Job, ServletContextListener {
	private static Logger logger = Logger.getLogger(ExtractsGatewayListener.class
			.getName());

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("ExtractsGatewayListener starts ...");
		try {
			JobDetailImpl job = new JobDetailImpl();
			job.setName("ExtractsGatewayListenerJob");
			job.setJobClass(ExtractsGatewayListener.class);

			SimpleTriggerImpl trigger = new SimpleTriggerImpl();
			trigger.setName("ExtractsGatewayListenerTrigger");
			trigger.setStartTime(new Date(System.currentTimeMillis() + 1000L));
			trigger.setRepeatCount(-1);
			trigger.setRepeatInterval(60L * 60L * 1000L);
//			trigger.setRepeatInterval(Long.parseLong(TransportClientProperties
//			.getTransportClientProperties().getListenerRepeatTime()));

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			logger.error("SchedulerException -> " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception -> " + e.getMessage());
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("ExtractsGatewayListener destroyed");
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			logger.info("Execution of ExtractsGatewayListener Job started");
			GatewayProcess.processGateWay();
			logger.info("ExtractsGatewayListener Job Execution Completed");
		} catch (Exception e) {
			logger.error("Exception in SpotRate Maintenance Listener Job -> "
					+ e.getMessage());

		}
	}
}
