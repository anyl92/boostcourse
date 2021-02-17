package kr.or.yl.reservationservice.product.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import kr.or.yl.reservationservice.product.domain.DisplayInfo;
import kr.or.yl.reservationservice.product.domain.DisplayInfoImage;
import kr.or.yl.reservationservice.product.domain.ProductImage;
import kr.or.yl.reservationservice.product.domain.ProductPrice;
import kr.or.yl.reservationservice.product.dto.DisplayInfoReadRequest;

import static kr.or.yl.reservationservice.product.dao.DisplayInfoDaoSqls.SELECT_DISPLAY_INFO;
import static kr.or.yl.reservationservice.product.dao.DisplayInfoDaoSqls.SELECT_DISPLAY_INFO_IMAGE;
import static kr.or.yl.reservationservice.product.dao.DisplayInfoDaoSqls.SELECT_DISPLAY_PRODUCT_IMAGES;
import static kr.or.yl.reservationservice.product.dao.DisplayInfoDaoSqls.SELECT_PRODUCT_PRICE;
import java.util.List;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfo> displayInfoRowMapper = 
				BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> displayInfoImageRowMapper = 
				BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	private RowMapper<ProductImage> productImageRowMapper = 
				BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<ProductPrice> productPriceRowMapper = 
				BeanPropertyRowMapper.newInstance(ProductPrice.class);
	
	private DisplayInfoReadRequest displayInfoReadRequest = new DisplayInfoReadRequest();
	
	public DisplayInfoDao(DataSource datasource) {
		this.jdbc = new NamedParameterJdbcTemplate(datasource);
	}
	
	public DisplayInfo selectDisplayInfo(int displayInfoId) {
		displayInfoReadRequest.setDisplayInfoId(displayInfoId);
		SqlParameterSource params = new BeanPropertySqlParameterSource(displayInfoReadRequest);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO, params, displayInfoRowMapper);
	}

	public DisplayInfoImage selectDisplayInfoImage(int displayInfoId) {
		displayInfoReadRequest.setDisplayInfoId(displayInfoId);
		SqlParameterSource params = new BeanPropertySqlParameterSource(displayInfoReadRequest);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_IMAGE, params, displayInfoImageRowMapper);
	}
	
	public List<ProductImage> selectDisplayProductImages(int displayInfoId) {
		displayInfoReadRequest.setDisplayInfoId(displayInfoId);
		SqlParameterSource params = new BeanPropertySqlParameterSource(displayInfoReadRequest);
		return jdbc.query(SELECT_DISPLAY_PRODUCT_IMAGES, params, productImageRowMapper);
	}
	
	public List<ProductPrice> selectProductPrices(int displayInfoId) {
		displayInfoReadRequest.setDisplayInfoId(displayInfoId);
		SqlParameterSource params = new BeanPropertySqlParameterSource(displayInfoReadRequest);
		return jdbc.query(SELECT_PRODUCT_PRICE, params, productPriceRowMapper);
	}
	
}
