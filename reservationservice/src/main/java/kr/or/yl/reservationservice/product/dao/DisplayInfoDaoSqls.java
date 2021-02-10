package kr.or.yl.reservationservice.product.dao;

public class DisplayInfoDaoSqls {

	public static final String SELECT_DISPLAY_INFO
	= "SELECT category.id AS category_id, "
	+ "		  category.name AS category_name, "
	+ "       display_info.create_date, "
	+ "       display_info.id AS display_info_id, "
	+ "       display_info.email, "
	+ "       display_info.homepage, "
	+ "       display_info.modify_date, "
	+ "       display_info.opening_hours, "
	+ "       display_info.place_lot, "
	+ "       display_info.place_name, "
	+ "       display_info.place_street, "
	+ "       product.content AS product_content, "
	+ "       product.description AS product_description, "
	+ "       product.event AS product_event, "
	+ "       product.id AS product_id, "
	+ "       display_info.tel AS telephone "
	+ "FROM   display_info "
	+ "JOIN   product ON product.id = display_info.product_id "
	+ "JOIN   category ON category.id = product.category_id "
	+ "WHERE  display_info.id = :displayInfoId;";
	
	public static final String SELECT_DISPLAY_INFO_IMAGE
	= "SELECT file_info.content_type AS content_type, "
	+ "		  file_info.create_date, "
	+ "  	  file_info.delete_flag, "
	+ "       display_info.id AS display_info_id, "
	+ "       display_info_image.id AS display_info_image_id, "
	+ "       file_info.id AS file_id, "
	+ "       file_info.file_name, "
	+ "       file_info.modify_date, "
	+ "       file_info.save_file_name "
	+ "FROM   display_info_image "
	+ "JOIN   display_info ON display_info_image.display_info_id = display_info.id "
	+ "JOIN   file_info ON display_info_image.file_id = file_info.id "
	+ "WHERE  display_info.id = :displayInfoId;";
	
	public static final String SELECT_DISPLAY_PRODUCT_IMAGES
	= "SELECT file_info.content_type, "
	+ "       file_info.create_date, "
	+ "       file_info.delete_flag, "
	+ "       file_info.id AS file_info_id, "
	+ "       file_info.file_name, "
	+ "       file_info.modify_date, "
	+ "       product.id AS product_id, "
	+ "       product_image.id AS product_image_id, "
	+ "       file_info.save_file_name, "
	+ "       product_image.type "
	+ "from   product_image "
	+ "JOIN   product ON product.id = product_image.product_id "
	+ "JOIN   file_info ON file_info.id = product_image.file_id "
	+ "JOIN   display_info ON display_info.product_id = product_image.product_id "
	+ "WHERE  display_info.id = :displayInfoId AND product_image.type in ('ma', 'et') "
	+ "LIMIT  2;";
	
}
