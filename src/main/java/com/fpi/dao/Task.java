package com.fpi.dao;

import java.util.Date;
import java.util.List;

import com.fpi.epay.entity.TaskEntity;

public interface Task {
	List<TaskEntity> findByProjId(String projId);
	
	void update(TaskEntity entity);
	
	List<Date> getHolidays();
}
