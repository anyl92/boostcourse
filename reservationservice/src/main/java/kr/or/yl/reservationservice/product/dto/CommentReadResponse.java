package kr.or.yl.reservationservice.product.dto;

import java.util.List;

import kr.or.yl.reservationservice.product.domain.Comment;

public class CommentReadResponse {

	private final double averageScore;
	private final List<Comment> comments;
	
	public CommentReadResponse(double averageScore, List<Comment> comments) {
		this.averageScore = averageScore;
		this.comments = comments;
	}

	public double getAverageScore() {
		return averageScore;
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	@Override
	public String toString() {
		return "CommentReadResponse [averageScore=" + averageScore + ", comments=" + comments + "]";
	}
	
}
