package kr.or.yl.reservationservice.category.dao;

public class CategoryDaoSqls {
	
	public static final String SELECT_ALL_CATEGORY
//			= "SELECT category.id AS id, "
//			+ "		category.name AS name, "
//			+ "		COUNT(category.id) AS count "
//			+ "FROM category "
//			+ "		JOIN product on(category.id = product.category_id)  "
//			+ "GROUP BY (category.id);";
			= "SELECT category.id AS id, "
			+ "		category.name AS name "
			+ "FROM category"
			+ "		JOIN product on(category.id = product.category_id)  "
			+ "GROUP BY (category.id);";
}
