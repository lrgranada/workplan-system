package com.fpi.epay;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
  public static void main(String[] args) {

	ApplicationContext context = 
		new ClassPathXmlApplicationContext("applicationContext.xml");
	
	JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
	Job job = (Job) context.getBean("reportJob");

	try {

		JobExecution execution = jobLauncher.run(job, new JobParameters());
		 System.out.println("Exit Status : " + execution.getStatus());
		
		 System.out.println(execution.getAllFailureExceptions());

	} catch (Exception e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
		

	((ClassPathXmlApplicationContext) context).close();
	System.out.println("Done");

  }
}
