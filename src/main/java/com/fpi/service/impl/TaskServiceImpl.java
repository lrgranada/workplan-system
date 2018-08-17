package com.fpi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fpi.dao.Task;
import com.fpi.epay.entity.TaskEntity;
import com.fpi.service.TaskService;

public class TaskServiceImpl implements TaskService {

	@Autowired
	private Task dao;
	
	@Override
	public List<TaskEntity> findByProjId(String projId) {
		return this.getDao().findByProjId(projId);
	}
	
	@Override
	public void update(TaskEntity entity) {
		this.getDao().update(entity);
	}
	
	@Override
	public List<Date> getHolidays() {
		return this.getDao().getHolidays();
	}
	
	public Task getDao() {
		return dao;
	}

	public void setDao(Task dao) {
		this.dao = dao;
	}
}
