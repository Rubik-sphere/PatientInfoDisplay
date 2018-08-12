package test;

import static org.quartz.JobBuilder.newJob;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerBuilder {
	private String nameStr;
	private String groupStr;
	private int interval;
	
	
	public SchedulerBuilder(String name, String group, int interval) {
		this.nameStr = name;
		this.groupStr = group;
		this.interval = interval;
	}
	
	public void startScheduler() {
		try {
	        // Grab the Scheduler instance from the Factory
	        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

	        // and start it off
	        scheduler.start();
	        
	        JobDetail job = newJob()     // insert job here
	        	      .withIdentity(nameStr, groupStr)
	        	      .build();

	        	  // Trigger the job to run now, and then repeat every 40 seconds
	        	  Trigger trigger = newTrigger()
	        	      .withIdentity("trigger1", groupStr)
	        	      .startNow()
	        	            .withSchedule(simpleSchedule()
	        	              .withIntervalInSeconds(interval)
	        	              .repeatForever())            
	        	      .build();

	        	  // Tell quartz to schedule the job using our trigger
	        	  scheduler.scheduleJob(job, trigger);
	        

//	        scheduler.shutdown();

	    } catch (SchedulerException se) {
	        se.printStackTrace();
	    }
	}
}
