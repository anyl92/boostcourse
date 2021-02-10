package kr.or.yl.reservationservice.product.service;

import java.util.List;

import kr.or.yl.reservationservice.product.domain.Comment;

public interface CommentService {
	List<Comment> getComments(int displayInfoId);
	double 		  getAverageScore(int displayInfoId);
}
