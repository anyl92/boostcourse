package kr.or.yl.reservationservice.product.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import kr.or.yl.reservationservice.product.domain.Product;
import kr.or.yl.reservationservice.product.dto.ProductReadRequest;

import static kr.or.yl.reservationservice.product.dao.ProductDaoSqls.SELECT_ALL_PRODUCTS;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectAll(ProductReadRequest productReadRequest) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(productReadRequest);
		return jdbc.query(SELECT_ALL_PRODUCTS, params, rowMapper);
	}
}
