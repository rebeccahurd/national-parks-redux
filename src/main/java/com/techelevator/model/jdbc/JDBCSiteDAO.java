package com.techelevator.model.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Site;
import com.techelevator.model.SiteDAO;

@Component
public class JDBCSiteDAO implements SiteDAO{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSiteDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Site getSiteById(int siteId) {
		String sqlGetSiteById = "SELECT * " +
								"FROM site "+
								"WHERE site_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetSiteById, siteId);
		Site s = new Site();
		
		if (result.next()) {
			s.setSiteId(result.getInt("site_id"));
			s.setCampgroundId(result.getInt("campground_id"));
			s.setSiteNumber(result.getInt("site_number"));
			s.setMaxOccupancy(result.getInt("max_occupancy"));
			s.setAccessible(result.getBoolean("accessible"));
			s.setMaxRvLength(result.getInt("max_rv_length"));
			s.setUtilities(result.getBoolean("utilities"));
		}
		return s;
	}
	
	@Override
//	public List<Site> getSitesBySearchCriteria(int campgroundId, Date fromDate, Date toDate) {
//		ArrayList<Site> siteList = new ArrayList<>();
//		String sqlGetSitesBySearchCriteria = "SELECT * "+
//											 "FROM site  "+
//											 "WHERE campground_id = ? "+
//											 "AND site_id NOT IN (SELECT reservation.site_id FROM reservation WHERE ((?,?) OVERLAPS (from_date, to_date))) "+
//											 "ORDER BY site_number asc "+
//											 "LIMIT 10";
	public List<Site> getSitesBySearchCriteria(int campgroundId, Date fromDate, Date toDate, int maxRvLength, int maxOccupancy, boolean accessible, boolean utilities) {
		
		String prefix = "SELECT * "+
				 		"FROM site  "+
				 		"WHERE campground_id = ? "+
				 		"AND max_rv_length >= ? AND max_occupancy >= ? ";
		
		String suffix = "AND site_id NOT IN (SELECT reservation.site_id FROM reservation WHERE ((?,?) OVERLAPS (from_date, to_date))) "+
				 		"ORDER BY max_occupancy asc, max_rv_length desc "+
				 		"LIMIT 10";
		
		ArrayList<Site> siteList = new ArrayList<>();
		String sqlGetSitesBySearchCriteria = null;
		SqlRowSet results = null;
		if(accessible && utilities) {
			sqlGetSitesBySearchCriteria = prefix + "AND accessible = ? AND utilities = ? "+ suffix;
					 
		results = jdbcTemplate.queryForRowSet(sqlGetSitesBySearchCriteria, campgroundId, maxRvLength, maxOccupancy, accessible, utilities, fromDate, toDate);
		} 
		else if(accessible && !utilities) {
			sqlGetSitesBySearchCriteria = prefix + "AND accessible = ? "+ suffix;
					
		results = jdbcTemplate.queryForRowSet(sqlGetSitesBySearchCriteria, campgroundId, maxRvLength, maxOccupancy, accessible, fromDate, toDate);	
		}
		else if(!accessible && utilities) {
			sqlGetSitesBySearchCriteria = prefix + "AND utilities = ? " + suffix;
					
		results = jdbcTemplate.queryForRowSet(sqlGetSitesBySearchCriteria, campgroundId, maxRvLength, maxOccupancy, utilities, fromDate, toDate);	
		}
		else {
		sqlGetSitesBySearchCriteria = prefix + suffix;
		results = jdbcTemplate.queryForRowSet(sqlGetSitesBySearchCriteria, campgroundId, maxRvLength, maxOccupancy, fromDate, toDate);
		}
		
		while(results.next()) {
			Site s = new Site();
			s.setSiteId(results.getInt("site_id"));
			s.setCampgroundId(results.getInt("campground_id"));
			s.setSiteNumber(results.getInt("site_number"));
			s.setMaxOccupancy(results.getInt("max_occupancy"));
			s.setAccessible(results.getBoolean("accessible"));
			s.setMaxRvLength(results.getInt("max_rv_length"));
			s.setUtilities(results.getBoolean("utilities"));
			
			siteList.add(s);
		}
		return siteList;
	}
	
}