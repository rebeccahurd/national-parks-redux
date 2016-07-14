package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public interface SiteDAO {

	public List<Site> getSitesBySearchCriteria(LocalDate fromDate, LocalDate toDate);
	
}
