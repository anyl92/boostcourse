package kr.or.yl.reservationservice.reservation.dto;

import java.time.LocalDateTime;
import java.util.List;

import kr.or.yl.reservationservice.product.domain.CommentImage;

public class ReservationCommentReadRequest {

	private String comment;
	private int commentId;
	private List<CommentImage> commentImage;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private int productId;
	private int reservationInfoId;
	private int score;
	
	public ReservationCommentReadRequest(String comment, int commentId, List<CommentImage> commentImage,
			LocalDateTime createDate, LocalDateTime modifyDate, int productId, int reservationInfoId, int score) {
		super();
		this.comment = comment;
		this.commentId = commentId;
		this.commentImage = commentImage;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.productId = productId;
		this.reservationInfoId = reservationInfoId;
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public List<CommentImage> getCommentImage() {
		return commentImage;
	}

	public void setCommentImage(List<CommentImage> commentImage) {
		this.commentImage = commentImage;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ReservationCommentReadRequest [comment=" + comment + ", commentId=" + commentId + ", commentImage="
				+ commentImage + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", productId="
				+ productId + ", reservationInfoId=" + reservationInfoId + ", score=" + score + "]";
	}

}
