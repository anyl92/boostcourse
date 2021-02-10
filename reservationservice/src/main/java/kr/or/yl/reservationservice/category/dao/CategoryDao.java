package kr.or.yl.reservationservice.category.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.yl.reservationservice.category.domain.Category;

import static kr.or.yl.reservationservice.category.dao.CategoryDaoSqls.SELECT_ALL_CATEGORY;

@Repository
public class CategoryDao {
	
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Category> selectAllCategory() { 
		return jdbc.query(SELECT_ALL_CATEGORY, rowMapper);
	}

}