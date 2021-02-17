package kr.or.yl.reservationservice.reservation.dao;

public class ReservationDaoSqls {
	
	public static final String SELECT_MY_RESERVATIONS
	= "SELECT   reservation_info.cancel_flag AS cancel_yn, "
	+ "         reservation_info.create_date, "
	+ "         reservation_info.display_info_id, "
	+ "         reservation_info.modify_date, "
	+ "         reservation_info.product_id, "
	+ "         reservation_info.reservation_date, "
	+ "         reservation_info.reservation_email, "
	+ "         reservation_info.id AS reservation_info_id, "
	+ "         reservation_info.reservation_name, "
	+ "         reservation_info.reservation_tel AS reservation_telephone, "
	+ "         SUM(product_price.price * reservation_info_price.count) AS total_price "
	+ "FROM     reservation_info "
	+ "JOIN     reservation_info_price ON reservation_info.id = reservation_info_price.reservation_info_id "
	+ "JOIN     product_price ON reservation_info_price.product_price_id = product_price.id "
	+ "WHERE    reservation_info.reservation_email = :email "
	+ "GROUP BY reservation_info.id; ";
	
	public static final String INSERT_RESERVATION
	= "INSERT INTO reservation_info(display_info_id, product_id, reservation_email, reservation_name, reservation_tel, reservation_date, create_date, modify_date) "
	+ "VALUES (:displayInfoId, :productId, :reservationEmail, :reservationName, :reservationTelephone, now(), now(), now()); ";
	
	public static final String INSERT_RESERVATION_PRICE
	= "INSERT INTO reservation_info_price(count, reservation_info_id, product_price_id) "
	+ "VALUES (:count, :reservationInfoId, :productPriceId); ";
	
	public static final String SELECT_RESERVATION
	= "SELECT id AS is_exist_id "
	+ "FROM   reservation_info "
	+ "WHERE  id = :reservationInfoId and cancel_flag = 0; ";
	
	public static final String CANCEL_RESERVATION
	= "UPDATE reservation_info "
	+ "SET    cancel_flag = 1 "
	+ "WHERE  reservation_info.id = :reservationInfoId; ";
	
	public static final String CANCEL_RESERVATION_CONFIRM
	= "SELECT cancel_flag "
	+ "FROM   reservation_info "
	+ "WHERE  id = :reservationInfoId and cancel_flag = 1; ";
	
}
