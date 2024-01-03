package com.ett.fxrates;


import java.sql.Date;
//import java.util.Collection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;

//import org.quartz.SchedulerException;
//import org.quartz.SchedulerFactory;
//import org.quartz.SimpleTrigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
//import org.quartz.Scheduler;




public class FXRateListener implements Job, ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Listener Destroyed");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Entered into class");
		try {
			System.out.println("Entered into Job Scheduling class");
			JobDetailImpl job = new JobDetailImpl(); 
			job.setName("FXRateListener");
			job.setJobClass(FXRateListener.class);
			SimpleTriggerImpl trigger = new SimpleTriggerImpl();
			trigger.setName("FXRateListener");
			trigger.setStartTime(new Date(System.currentTimeMillis()+ 1000));
			trigger.setRepeatCount(-1);
			trigger.setRepeatInterval(1l * 60l * 1000l);
			org.quartz.Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job,trigger);
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Entered into FX Rate Code Block");
		FXRatesImpl Fxrt = new FXRatesImpl();
		try {
			Fxrt.FXRateUpdate();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Exited out of Code");
		
	}
}

