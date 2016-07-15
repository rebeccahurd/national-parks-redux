package com.techelevator.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.model.Campground;
import com.techelevator.model.CampgroundDAO;
import com.techelevator.model.Park;
import com.techelevator.model.ParkDAO;
import com.techelevator.model.Reservation;
import com.techelevator.model.ReservationDAO;
import com.techelevator.model.Site;
import com.techelevator.model.SiteDAO;

@Transactional
@Controller
@SessionAttributes("reservation")
public class ParkController {

	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private SiteDAO siteDAO;
	private ReservationDAO reservationDAO;
	
	@Autowired
	public ParkController(ParkDAO parkDAO, CampgroundDAO campgroundDAO, SiteDAO siteDAO, ReservationDAO reservationDAO) {
		this.parkDAO = parkDAO;
		this.campgroundDAO = campgroundDAO;
		this.siteDAO = siteDAO;
		this.reservationDAO = reservationDAO;
	}
	
	@RequestMapping(path={"/", "/parkList"}, method=RequestMethod.GET)
	public String displayParkList(ModelMap model) {
		List<Park> parkList = parkDAO.getAllParks();
		model.put("parkList", parkList);
		return "parkList";
	}
	
	@RequestMapping(path="/campgroundList", method=RequestMethod.GET)
	public String displayCampgroundListByPark(ModelMap model,
									@RequestParam int parkId,
									@RequestParam String name) {
		
		List<Campground> campgroundList = campgroundDAO.getCampgroundsByParkId(parkId);
		model.put("campgroundList", campgroundList);
		return "campgroundList";
	}
	
	@RequestMapping(path="/campsiteSearch", method=RequestMethod.GET)
	public String displayCampsiteSearchForm(@RequestParam int campgroundId,
											@RequestParam String name) {
		return "campsiteSearch";
	}
	
	@RequestMapping(path="/campsiteSearchResults", method=RequestMethod.GET)
	public String displaySearchResults(ModelMap model,
										@RequestParam String fromDate,
										@RequestParam String toDate,
										@RequestParam int campgroundId,
										@RequestParam String name,
										Site site) {
		
		Date sqlFromDate = Date.valueOf(fromDate);
		Date sqlToDate = Date.valueOf(toDate);
		
		List<Site> siteList = siteDAO.getSitesBySearchCriteria(campgroundId, sqlFromDate, sqlToDate, site.getMaxRvLength(), site.getMaxOccupancy(), site.isAccessible(), site.isUtilities());
		Reservation r = new Reservation();
		r.setFromDate(sqlFromDate);
		r.setToDate(sqlToDate);
		r.setCreateDate(Date.valueOf(LocalDate.now()));
		
		model.put("reservation", r);
		model.put("siteList", siteList);
		
		return "campsiteSearchResults";
//		}
	}
	
	@RequestMapping(path="/reservationForm", method=RequestMethod.GET)
	public String displayReservationForm(ModelMap model,
										@RequestParam int siteId) {
		
		Reservation r = (Reservation)model.get("reservation");
		r.setSiteId(siteId);
		model.put("reservation", r);
		return "reservationForm";
	}
	
	@RequestMapping(path="/reservationForm", method=RequestMethod.POST)
	public String submitReservationForm(ModelMap model,
										@RequestParam String name) {
		
		Reservation r = (Reservation)model.get("reservation");
		r.setName(name);
		reservationDAO.saveReservation(r);
		return "redirect:/reservationConfirmation";
	}
	
	@RequestMapping(path="reservationConfirmation", method=RequestMethod.GET)
	public String displayReservationConfirmation(ModelMap model) {
		
		int reservationId = reservationDAO.getCurrentReservationId();
		Reservation newReservation = reservationDAO.getReservationById(reservationId);
		model.put("reservation", newReservation);
		
		Site site = siteDAO.getSiteById(newReservation.getSiteId());
		model.put("site", site);
		return "reservationConfirmation";
	}
}






