package kr.or.yl.reservationservice.product.dao;

public class ProductDaoSqls {

	public static final String SELECT_ALL_PRODUCTS
	= "SELECT display_info.id AS display_info_id, "
	+ "		display_info.place_name AS place_name, "
	+ "		product.content AS product_content, "
	+ "		product.description AS product_description, "
	+ "		product.id AS product_id, "
	+ "		file_info.file_name AS product_image_url "
	+ "FROM product "
	+ "		JOIN display_info ON product.id = display_info.product_id "
	+ "		JOIN product_image ON product.id = product_image.product_id "
	+ "		JOIN file_info ON product_image.file_id = file_info.id "
	+ "WHERE product_image.type = 'th' "
	+ "LIMIT 4 "
	+ "		OFFSET :start;";

}
