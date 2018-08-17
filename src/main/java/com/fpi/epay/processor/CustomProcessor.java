package com.fpi.epay.processor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hsqldb.lib.StringUtil;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fpi.epay.entity.TaskEntity;
import com.fpi.service.TaskService;
import com.fpi.util.DateUtil;

@Component
public class CustomProcessor implements Tasklet {
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	@Value("${input.proj.id}")
	private String projId;
	
	@Autowired
	private TaskService service;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		System.out.println("My project Id is = " + projId);
		
		List<Date> holidates = this.getService().getHolidays();
		
		List<TaskEntity> entities = this.getService().findByProjId(this.projId);
		
		Map<String, TaskEntity> taskMap = new TreeMap<String, TaskEntity>();
		
		for (TaskEntity entity : entities) {
			Date limits[] = new Date[2];
			Date startDate = null;
			Date endDate = null;
			
			if (StringUtil.isEmpty(entity.getDependency())) {
				Calendar today = Calendar.getInstance();
				limits = DateUtil.getDateLimits(today.getTime(), entity.getDuration(), holidates);
				startDate = limits[0];
				endDate = limits[1];
			} else {
				TaskEntity parentTask = taskMap.get(entity.getDependency());
				
				Date parentEndDate = parentTask.getEndDate();
				
				Calendar nextTask = Calendar.getInstance();
				nextTask.setTime(parentEndDate);
				nextTask.add(Calendar.DATE, 1);
				
				limits = DateUtil.getDateLimits(nextTask.getTime(), entity.getDuration(), holidates);
				startDate = limits[0];
				endDate = limits[1];
			}
			
			this.updateEntity(entity, startDate, endDate);
			
			taskMap.put(entity.getTaskId(), entity);
		}
		
		this.printMap(taskMap);
		
		return RepeatStatus.FINISHED;
	}
	
	private void updateEntity(TaskEntity entity, Date startDate, Date endDate) {
		entity.setStartDate(startDate);
		entity.setEndDate(endDate);
	}
	
	private void printMap(Map<String, TaskEntity> taskMap) {
		
		for (Map.Entry<String, TaskEntity> entry : taskMap.entrySet()) {
			TaskEntity entity = entry.getValue();
			
			System.out.println("====================");
			System.out.println("Task Id = " + entity.getTaskId());
			System.out.println("Task Name = " + entity.getTaskName());
			System.out.println("Task Duration = " + entity.getDuration());
			System.out.println("Dependency = " + entity.getDependency());
			System.out.println("Start Date = " + sdf.format(entity.getStartDate()));
			System.out.println("End Date = " + sdf.format(entity.getEndDate()));
		}
	}

	public TaskService getService() {
		return this.service;
	}

	public void setService(TaskService service) {
		this.service = service;
	}
	
	
	
}
