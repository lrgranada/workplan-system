package com.fpi.service;

import java.util.Date;
import java.util.List;

import com.fpi.epay.entity.TaskEntity;

public interface TaskService {
	List<TaskEntity> findByProjId(String projId);
	
	void update(TaskEntity entity);
	
	List<Date> getHolidays();
}
