package com.techelevator.model;

public class Campground {
	
	private int campgroundId;
	private int parkId;
	private String name;
	private String openFromMM;
	private String openToMM;
	private int dailyFee;
	public int getCampgroundId() {
		return campgroundId;
	}
	public int getParkId() {
		return parkId;
	}
	public String getName() {
		return name;
	}
	public String getOpenFromMM() {
		return openFromMM;
	}
	public String getOpenToMM() {
		return openToMM;
	}
	public int getDailyFee() {
		return dailyFee;
	}
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOpenFromMM(String openFromMM) {
		this.openFromMM = openFromMM;
	}
	public void setOpenToMM(String openToMM) {
		this.openToMM = openToMM;
	}
	public void setDailyFee(int dailyFee) {
		this.dailyFee = dailyFee;
	}
	
	
	
}
