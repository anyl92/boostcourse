package kr.or.yl.reservationservice.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.yl.reservationservice.product.dao.CommentDao;
import kr.or.yl.reservationservice.product.domain.Comment;
import kr.or.yl.reservationservice.product.domain.CommentImage;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;
	private static final int SET_SHOW_ID_LENGTH = 4;
	
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	
	@Override
	public List<Comment> getComments(int displayInfoId) {
		List<Comment> comments = commentDao.selectComment(displayInfoId);
		
		for (Comment comment: comments) {
			int commentId = comment.getCommentId();
			List<CommentImage> commentImages = commentDao.selectCommentImage(commentId);
			comment.setCommentImages(commentImages);
			
			String email = comment.getReservationEmail();
			String userId = email.split("@")[0];
			String maskingUserId = userId;

			int userIdLength = userId.length();
			if (userIdLength > SET_SHOW_ID_LENGTH) {
				
				int maskingLength = userIdLength - SET_SHOW_ID_LENGTH;
				maskingUserId = userId.substring(0, SET_SHOW_ID_LENGTH);
				
				for (int i = 0; i < maskingLength; i++) {
					maskingUserId += "*";
				}
			}
			comment.setMaskingUserId(maskingUserId);
		}
		return comments;
	}

	@Override
	public double getAverageScore(int displayInfoId) {
		List<Comment> comments = commentDao.selectComment(displayInfoId);
		double totalScore = 0;
		int count = 0;
		
		for (Comment comment: comments) {
			double score = comment.getScore();
			totalScore += score;
			count++;
		}
		totalScore = (totalScore / count);
		return Double.parseDouble(String.format("%.2f", totalScore));
	}
}
