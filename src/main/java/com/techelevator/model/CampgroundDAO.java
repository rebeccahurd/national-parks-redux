package com.techelevator.model;

import java.util.List;

public interface CampgroundDAO {

	public List<Campground> getCampgroundsByParkId(int parkId);
	
}
