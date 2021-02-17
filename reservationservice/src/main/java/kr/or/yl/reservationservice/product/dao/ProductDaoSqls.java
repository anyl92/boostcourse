package kr.or.yl.reservationservice.product.dao;

public class ProductDaoSqls {

	public static final String SELECT_ALL_PRODUCT
	= "SELECT display_info.id AS display_info_id, "
	+ "		  display_info.place_name AS place_name, "
	+ "		  product.content AS product_content, "
	+ "		  product.description AS product_description, "
	+ "		  product.id AS product_id, "
	+ "		  file_info.file_name AS product_image_url "
	+ "FROM   product "
	+ "JOIN   display_info ON product.id = display_info.product_id "
	+ "JOIN   product_image ON product.id = product_image.product_id "
	+ "JOIN   file_info ON product_image.file_id = file_info.id "
	+ "WHERE  product_image.type = 'th' "
	+ "LIMIT  4 "
	+ "OFFSET :start;";
	
	public static final String SELECT_ALL_PRODUCT_COUNT
	= "SELECT COUNT(display_info.id) AS count "
	+ "FROM   product "
	+ "JOIN	  display_info on product.id = display_info.product_id;";

	public static final String SELECT_CATEGORY_PRODUCTS
	= "SELECT display_info.id AS display_info_id, "
	+ "		  display_info.place_name AS place_name, "
	+ "		  product.content AS product_content, "
	+ "		  product.description AS product_description, "
	+ "		  product.id AS product_id, "
	+ "		  file_info.file_name AS product_image_url "
	+ "FROM   product "
	+ "JOIN   display_info ON product.id = display_info.product_id "
	+ "JOIN   product_image ON product.id = product_image.product_id "
	+ "JOIN   file_info ON product_image.file_id = file_info.id "
	+ "WHERE  product_image.type = 'th' and category_id = :categoryId "
	+ "LIMIT  4 "
	+ "OFFSET :start;";
	
	public static final String SELECT_CATEGORY_PRODUCTS_COUNT
	= "SELECT   COUNT(category_id) AS count "
	+ "FROM     category "
	+ "JOIN     product on category.id = product.category_id "
	+ "JOIN		display_info on product.id = display_info.product_id "
	+ "WHERE    category_id = :categoryId "
	+ "GROUP BY category.id;";

}
