package kr.or.yl.reservationservice.reservation.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.yl.reservationservice.reservation.domain.Reservation;
import kr.or.yl.reservationservice.reservation.dto.ReservationReadRequest;

public interface ReservationService {

	List<Reservation> getReservations(String email);
	void   	          addReservation(ReservationReadRequest reservationParam);
	boolean           cancleReservation(int reservationInfoId);
	void              addReservationComment(int reservationInfoId, MultipartFile attachedImage, 
											String comment, int productId, int score);

}
