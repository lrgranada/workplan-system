package com.fpi.epay.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASK_HOLIDAY")
public class HolidayEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 503241264475449104L;

	@Id
	@Column(name="HOLIDAY")
	public String holiday;
	
	@Column(name="HOLIDAY_DATE")
	public Date holidayDate;

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}
	
	
}
