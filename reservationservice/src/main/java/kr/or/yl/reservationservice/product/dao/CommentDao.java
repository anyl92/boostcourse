package kr.or.yl.reservationservice.product.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import kr.or.yl.reservationservice.product.domain.Comment;
import kr.or.yl.reservationservice.product.domain.CommentImage;
import kr.or.yl.reservationservice.product.dto.CommentReadRequest;
import kr.or.yl.reservationservice.product.dto.DisplayInfoReadRequest;

import static kr.or.yl.reservationservice.product.dao.CommentDaoSqls.SELECT_COMMENT;
import static kr.or.yl.reservationservice.product.dao.CommentDaoSqls.SELECT_COMMENT_IMAGE;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Comment> commentRowMapper = 
				BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> commentImageRowMapper = 
				BeanPropertyRowMapper.newInstance(CommentImage.class);
	
	private DisplayInfoReadRequest displayInfoReadRequest = new DisplayInfoReadRequest();
	private CommentReadRequest commentReadRequest = new CommentReadRequest();
	
	public CommentDao(DataSource datasource) {
		this.jdbc = new NamedParameterJdbcTemplate(datasource);
	}
	
	public List<Comment> selectComment(int displayInfoId) {
		displayInfoReadRequest.setdisplayInfoId(displayInfoId);
		SqlParameterSource params = new BeanPropertySqlParameterSource(displayInfoReadRequest);
		return jdbc.query(SELECT_COMMENT, params, commentRowMapper);
	}

	public List<CommentImage> selectCommentImage(int commentId) {
		commentReadRequest.setcommentId(commentId);
		SqlParameterSource params = new BeanPropertySqlParameterSource(commentReadRequest);
		return jdbc.query(SELECT_COMMENT_IMAGE, params, commentImageRowMapper);
	}
}
