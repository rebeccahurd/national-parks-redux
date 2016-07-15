package com.techelevator.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
//@SessionAttributes({"")
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
									@RequestParam int parkId) {
		
		List<Campground> campgroundList = campgroundDAO.getCampgroundsByParkId(parkId);
		model.put("campgroundList", campgroundList);
		return "campgroundList";
	}
	
	@RequestMapping(path="/campsiteSearch", method=RequestMethod.GET)
	public String displayCampsiteSearchForm(@RequestParam int parkId,
											@RequestParam int campgroundId) {
		
		return "campsiteSearch";
	}
	
	@RequestMapping(path="/campsiteSearchResults", method=RequestMethod.GET)
	public String displaySearchResults(ModelMap model,
										@RequestParam int parkId,
										@RequestParam int campgroundId,
										@RequestParam LocalDate fromDate,
										@RequestParam LocalDate toDate,
										Site site) {
		
//		List<Site> siteList = siteDAO.getSitesBySearchCriteria(fromDate, toDate);
//		model.put("siteList", siteList);
//		model.put("sites", siteDAO.getSitesByCampgroundId());
		return "campsiteSearchResults";
	}
	
	@RequestMapping(path="/reservationForm", method=RequestMethod.GET)
	public String displayReservationForm(ModelMap model,
										Reservation reservation) {
		return "reservationForm";
	}
	
	@RequestMapping(path="/reservationForm", method=RequestMethod.POST)
	public String submitReservationForm(ModelMap model,
										Reservation reservation) {
		
		reservation = new Reservation();
		reservationDAO.saveReservation(reservation);

		return "redirect:/reservationConfirmation";
	}
	
	@RequestMapping(path="reservationConfirmation", method=RequestMethod.GET)
	public String displayReservationConfirmation(ModelMap model,
												@RequestParam int reservationId) {
		
		Reservation newReservation = reservationDAO.getReservationById(reservationId);
		model.put("reservation", newReservation);
		return "reservationConfirmation";
	}
	
}






