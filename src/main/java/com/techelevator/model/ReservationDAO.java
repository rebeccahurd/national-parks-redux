package com.techelevator.model;

import java.util.List;

public interface ReservationDAO {

	public List<Reservation> getReservationsBySiteId(int siteId);
	
	public Reservation getReservationById(int reservationId);
	
	public void saveReservation(Reservation r);
}
