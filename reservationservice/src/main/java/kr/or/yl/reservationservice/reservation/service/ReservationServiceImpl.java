package kr.or.yl.reservationservice.reservation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.yl.reservationservice.product.dao.DisplayInfoDao;
import kr.or.yl.reservationservice.product.domain.Comment;
import kr.or.yl.reservationservice.product.domain.DisplayInfo;
import kr.or.yl.reservationservice.reservation.dao.ReservationDao;
import kr.or.yl.reservationservice.reservation.domain.Reservation;
import kr.or.yl.reservationservice.reservation.domain.ReservationPrice;
import kr.or.yl.reservationservice.reservation.dto.ReservationReadRequest;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	private final ReservationDao reservationDao;
	private final DisplayInfoDao displayInfoDao;

	public ReservationServiceImpl(ReservationDao reservationDao, DisplayInfoDao displayInfoDao) {
		this.reservationDao = reservationDao;
		this.displayInfoDao = displayInfoDao;
	}

	@Override
	public List<Reservation> getReservations(String email) {
		List<Reservation> reservations = reservationDao.selectReservations(email);
		for (Reservation reservation: reservations) {
			int displayInfoId = reservation.getDisplayInfoId();
			DisplayInfo displayInfo = displayInfoDao.selectDisplayInfo(displayInfoId);
			reservation.setDisplayInfo(displayInfo);
		}
		return reservations;
	}

	@Transactional
	@Override
	public void addReservation(ReservationReadRequest reservationParam) {
		int reservationInfoId = reservationDao.insertReservation(reservationParam);
		
		List<ReservationPrice> reservationPrice = reservationParam.getPrices();
		for (ReservationPrice price: reservationPrice) {
			price.setReservationInfoId(reservationInfoId);
			reservationDao.insertReservationPrice(price);
		};
	}

	@Override
	public boolean cancleReservation(int reservationInfoId) {
		return reservationDao.cancleReservation(reservationInfoId);
	}

	@Override
	public void addReservationComment(int reservationInfoId, MultipartFile attachedImage, String comment, int productId, int score) {
		reservationDao.insertReservationComment(reservationInfoId, attachedImage, comment, productId, score);
	}
	
}
