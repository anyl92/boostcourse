package kr.or.yl.reservationservice.reservation.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import kr.or.yl.reservationservice.reservation.domain.Reservation;
import kr.or.yl.reservationservice.reservation.domain.ReservationPrice;
import kr.or.yl.reservationservice.reservation.dto.ReservationCancleReadRequest;
import kr.or.yl.reservationservice.reservation.dto.ReservationInfoReadRequest;
import kr.or.yl.reservationservice.reservation.dto.ReservationReadRequest;

import static kr.or.yl.reservationservice.reservation.dao.ReservationDaoSqls.SELECT_MY_RESERVATIONS;
import static kr.or.yl.reservationservice.reservation.dao.ReservationDaoSqls.INSERT_RESERVATION;
import static kr.or.yl.reservationservice.reservation.dao.ReservationDaoSqls.INSERT_RESERVATION_PRICE;
import static kr.or.yl.reservationservice.reservation.dao.ReservationDaoSqls.SELECT_RESERVATION;
import static kr.or.yl.reservationservice.reservation.dao.ReservationDaoSqls.CANCEL_RESERVATION;
import static kr.or.yl.reservationservice.reservation.dao.ReservationDaoSqls.CANCEL_RESERVATION_CONFIRM;

@Repository
public class ReservationDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationDao.class);

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Reservation> rowMapper = BeanPropertyRowMapper.newInstance(Reservation.class);

	private static ReservationInfoReadRequest reservationReadRequest = new ReservationInfoReadRequest();
	private static ReservationCancleReadRequest reservationCancleReadRequest = new ReservationCancleReadRequest();
	
	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Reservation> selectReservations(String email) {
		reservationReadRequest.setEmail(email);
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationReadRequest);
		
		return jdbc.query(SELECT_MY_RESERVATIONS, params, rowMapper);
	}

	public int insertReservation(ReservationReadRequest reservationParam) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationParam);
		jdbc.update(INSERT_RESERVATION, params, keyHolder);
		
		return keyHolder.getKey().intValue();
	}
	
	public int insertReservationPrice(ReservationPrice reservationPrice) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationPrice);
		
		return jdbc.update(INSERT_RESERVATION_PRICE, params);
	}

	public Boolean cancleReservation(int reservationInfoId) {
		reservationCancleReadRequest.setReservationInfoId(reservationInfoId);
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationCancleReadRequest);

		try {
			jdbc.queryForObject(SELECT_RESERVATION, params, Integer.class);
		} catch (EmptyResultDataAccessException exception) {
			LOGGER.error("reservationInfoId is not exist");
		}

		jdbc.update(CANCEL_RESERVATION, params);
		return jdbc.queryForObject(CANCEL_RESERVATION_CONFIRM, params, Boolean.class);
	}

	public Boolean insertReservationComment(int reservationInfoId, MultipartFile attachedImage, 
											String comment, int productId, int score) {

		// attachedImage가 없을 경우 SQL 따로 처리
		// INSERT Comment
		// SELECT Comment로 반환ㄴ

		return null;
	}
	
}
