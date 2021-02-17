package kr.or.yl.reservationservice.reservation.dto;

import java.util.List;

import kr.or.yl.reservationservice.reservation.domain.Reservation;

public class ReservationInfoReadResponse {
	
	private final List<Reservation> reservations;

	public ReservationInfoReadResponse(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

}
