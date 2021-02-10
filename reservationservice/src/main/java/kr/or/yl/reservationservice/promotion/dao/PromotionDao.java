package kr.or.yl.reservationservice.promotion.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.yl.reservationservice.promotion.domain.Promotion;

import static kr.or.yl.reservationservice.promotion.dao.PromotionDaoSqls.SELECT_ALL_PROMOTION;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Promotion> selectAllPromotion() {
		return jdbc.query(SELECT_ALL_PROMOTION, rowMapper);
	}
}
