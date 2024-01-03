package com.ett.backofficebatch;

import java.net.InetAddress;
import java.sql.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import com.ett.util.BOCCommonUtil;

@DisallowConcurrentExecution
public class BackOfficeBatchResponseListener implements Job, ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Listener Destroyed");

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Entered into Posting Listener class");
		try {
			System.out.println("Entered into Posting Job Scheduling class");
			JobDetailImpl job = new JobDetailImpl();
			job.setName("BackOfficeBatchResponseListener");
			job.setJobClass(BackOfficeBatchResponseListener.class);
			SimpleTriggerImpl trigger = new SimpleTriggerImpl();
			trigger.setName("BackOfficeBatchResponseListener");
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
			System.out.println("Entereing into Posting Block Class");
			String currentHostNode = getCurrentHost();
			if(currentHostNode != null && currentHostNode.equals(BOCCommonUtil.retreiveFromProperties("LISTENERHOSTNAME"))) {
			BackOfficeBatchResponseImpl BbRI = new BackOfficeBatchResponseImpl();
			BbRI.PostingResponseUpdate();
			}
			System.out.println("Exited from posting code block");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getCurrentHost() {

		InetAddress inet;
		String hostName = null;
		try {
			inet = InetAddress.getLocalHost();
			hostName = inet.getHostName();
            System.out.println("hostname:"+hostName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hostName;
	}

}
