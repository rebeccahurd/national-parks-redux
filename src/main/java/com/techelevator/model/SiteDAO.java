package com.techelevator.model;

import java.sql.Date;
import java.util.List;

public interface SiteDAO {

	public Site getSiteById(int siteId);
//	public List<Site> getSitesBySearchCriteria(int campgroundId, Date fromDate, Date toDate);
	public List<Site> getSitesBySearchCriteria(int campgroundId, Date fromDate, Date toDate, int maxRvLength, int maxOccupancy, boolean accessible, boolean utilities);
	
}
