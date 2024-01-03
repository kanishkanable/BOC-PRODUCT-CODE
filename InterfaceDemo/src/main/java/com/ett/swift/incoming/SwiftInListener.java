package com.ett.swift.incoming;

import java.sql.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

@WebListener
public class SwiftInListener implements Job, ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Listener Destroyed");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Entered into Swiftin Listener class");
		try {
			System.out.println("Entered into Swiftin Job Scheduling class");
			JobDetailImpl job = new JobDetailImpl();
			job.setName("SwiftInListener");
			job.setJobClass(SwiftInListener.class);
			SimpleTriggerImpl trigger = new SimpleTriggerImpl();
			trigger.setName("SwiftInListener");
			trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
			trigger.setRepeatCount(-1);
			trigger.setRepeatInterval(1L * 60L * 1000L);
			org.quartz.Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		try {
			System.out.println("Entered into SwiftIn Try block");
			SwiftInImpl aSwiftInImpl = new SwiftInImpl();
			aSwiftInImpl.readSwiftInMessage();
			System.out.println("Exited into SwiftIn Try block");
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
