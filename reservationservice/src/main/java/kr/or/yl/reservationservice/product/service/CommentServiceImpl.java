package kr.or.yl.reservationservice.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.yl.reservationservice.product.dao.CommentDao;
import kr.or.yl.reservationservice.product.domain.Comment;
import kr.or.yl.reservationservice.product.domain.CommentImage;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private final CommentDao commentDao;
	
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
			int userIdLength = email.split("@")[0].length();
			String userId = email.split("@")[0].substring(0, 4);

			if (userIdLength > 4) {
				for (int i = 0; i<userIdLength-4; i++) {
					userId += "*";				
				}
			}
			comment.setStarOfUserId(userId);
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
