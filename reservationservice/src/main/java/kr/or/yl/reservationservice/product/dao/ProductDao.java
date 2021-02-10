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

import static kr.or.yl.reservationservice.product.dao.ProductDaoSqls.SELECT_ALL_PRODUCT;
import static kr.or.yl.reservationservice.product.dao.ProductDaoSqls.SELECT_ALL_PRODUCT_COUNT;
import static kr.or.yl.reservationservice.product.dao.ProductDaoSqls.SELECT_CATEGORY_PRODUCTS;
import static kr.or.yl.reservationservice.product.dao.ProductDaoSqls.SELECT_CATEGORY_PRODUCTS_COUNT;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	
	private ProductReadRequest productReadRequest = new ProductReadRequest();
	
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectAllProduct(int start) {
		productReadRequest.setstart(start);
		SqlParameterSource params = new BeanPropertySqlParameterSource(productReadRequest);
		return jdbc.query(SELECT_ALL_PRODUCT, params, rowMapper);
	}
	
	public int selectAllProductCount() {
		SqlParameterSource params = new BeanPropertySqlParameterSource(productReadRequest);
		return jdbc.queryForObject(SELECT_ALL_PRODUCT_COUNT, params, Integer.class);
	}
	
	public List<Product> selectCategoryProducts(int categoryId, int start) {
		productReadRequest.setCategoryId(categoryId);
		productReadRequest.setstart(start);
		SqlParameterSource params = new BeanPropertySqlParameterSource(productReadRequest);
		return jdbc.query(SELECT_CATEGORY_PRODUCTS, params, rowMapper);
	}
	
	public int selectCategoryProductsCount(int categoryId) {
		productReadRequest.setCategoryId(categoryId);
		SqlParameterSource params = new BeanPropertySqlParameterSource(productReadRequest);
		return jdbc.queryForObject(SELECT_CATEGORY_PRODUCTS_COUNT, params, Integer.class);
	}

}
