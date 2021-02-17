package kr.or.yl.reservationservice.reservation.domain;

import java.time.LocalDateTime;

public class CommentImage {

	private LocalDateTime createDate;
	private boolean deleteFlag;
	private int fileId;
	private String fileName;
	private LocalDateTime modifyDate;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private String saveFileName;
	
	public CommentImage(LocalDateTime createDate, boolean deleteFlag, int fileId, String fileName,
			LocalDateTime modifyDate, int reservationInfoId, int reservationUserCommentId, String saveFileName) {
		this.createDate = createDate;
		this.deleteFlag = deleteFlag;
		this.fileId = fileId;
		this.fileName = fileName;
		this.modifyDate = modifyDate;
		this.reservationInfoId = reservationInfoId;
		this.reservationUserCommentId = reservationUserCommentId;
		this.saveFileName = saveFileName;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(int reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "CommentImage [createDate=" + createDate + ", deleteFlag=" + deleteFlag + ", fileId=" + fileId
				+ ", fileName=" + fileName + ", modifyDate=" + modifyDate + ", reservationInfoId=" + reservationInfoId
				+ ", reservationUserCommentId=" + reservationUserCommentId + ", saveFileName=" + saveFileName + "]";
	}
	
}
