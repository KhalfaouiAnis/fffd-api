package com.tekup.restapi.models;

import java.util.Date;

public class WorkingHours {

	private Date openDate;
	private Date closeDate;

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public WorkingHours(Date openDate, Date closeDate) {
		this.openDate = openDate;
		this.closeDate = closeDate;
	}

	public WorkingHours() {
	}

}
