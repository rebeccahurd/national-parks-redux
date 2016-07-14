package com.techelevator.model.jdbc;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Site;
import com.techelevator.model.SiteDAO;

public class JDBCSiteDAO implements SiteDAO{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSiteDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Site> getSitesBySearchCriteria(LocalDate fromDate, LocalDate toDate) {
		// TODO Auto-generated method stub
		return null;
	}
}