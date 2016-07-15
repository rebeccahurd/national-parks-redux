package com.techelevator.model;

import java.sql.Date;
import java.util.List;

public interface SiteDAO {

	public List<Site> getSitesBySearchCriteria(Date fromDate, Date toDate);
	
	
}
