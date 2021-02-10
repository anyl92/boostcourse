package kr.or.yl.reservationservice.promotion.dao;

public class PromotionDaoSqls {

	public static final String SELECT_ALL_PROMOTION
	= "SELECT  promotion.id AS id, "
	+ "		   file_info.file_name AS product_image_url "
	+ "FROM    promotion "
	+ "JOIN    product ON promotion.product_id = product.id "
	+ "JOIN    product_image ON product.id = product_image.product_id "
	+ "JOIN	   file_info ON product_image.file_id = file_info.id "
	+ "WHERE   product_image.type = 'th';";

}