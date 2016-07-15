package com.techelevator.model;

import java.sql.Date;

public class Reservation {

	private int reservationId;
	private int siteId;
	private String name;
	private Date fromDate;
	private Date toDate;
	private Date createDate;
	public int getReservationId() {
		return reservationId;
	}
	public int getSiteId() {
		return siteId;
	}
	public String getName() {
		return name;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
