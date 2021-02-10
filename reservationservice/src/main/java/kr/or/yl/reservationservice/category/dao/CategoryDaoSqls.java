package kr.or.yl.reservationservice.category.dao;

public class CategoryDaoSqls {
	
	public static final String SELECT_ALL_CATEGORY
		= "SELECT  category.id AS id, "
		+ "		   category.name AS name "
		+ "FROM    category;";

}