package com.fpi.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fpi.dao.Task;
import com.fpi.epay.entity.HolidayEntity;
import com.fpi.epay.entity.TaskEntity;

public class TaskImpl implements Task {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskEntity> findByProjId(String projId) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("FROM TaskEntity WHERE projId = :projId ORDER BY projId, dependency NULLS FIRST, taskId");
		
		List<TaskEntity> result = (List<TaskEntity>) this.sessionFactory.getCurrentSession().createQuery(
				sb.toString()).setParameter("projId", projId).list();
		
		return result;
	}
	
	@Override
	public void update(TaskEntity entity) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Date> getHolidays() {
		return (List<Date>) this.sessionFactory.getCurrentSession().createQuery(
				"SELECT TO_CHAR(holidayDate, 'MM/DD/YYYY') AS HOLIDATE FROM HolidayEntity ORDER BY holidayDate").list();
	}
}
