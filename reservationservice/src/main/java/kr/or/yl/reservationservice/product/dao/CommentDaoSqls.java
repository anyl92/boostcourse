package kr.or.yl.reservationservice.product.dao;

public class CommentDaoSqls {
	
	public static final String SELECT_COMMENT
	= "SELECT reservation_user_comment.comment, "
	+ "	      reservation_user_comment.id AS comment_id, "
	+ "       reservation_user_comment.create_date, "
	+ "       reservation_user_comment.modify_date, "
	+ "       reservation_user_comment.product_id, "
	+ "       reservation_info.reservation_date, "
	+ "       reservation_info.reservation_email, "
	+ "       reservation_info.id AS reservation_info_id, "
	+ "       reservation_info.reservation_name, "
	+ "       reservation_info.reservation_tel AS reservation_telephone, "
	+ "       reservation_user_comment.score "
	+ "FROM   reservation_user_comment "
	+ "JOIN   reservation_info ON "
	+ "	      reservation_info.id = reservation_user_comment.reservation_info_id "
	+ "WHERE  reservation_info.product_id = :displayInfoId ";

	public static final String SELECT_COMMENT_IMAGE
	= "SELECT file_info.content_type, "
	+ "		  file_info.create_date, "
	+ "    	  file_info.delete_flag, "
	+ "       file_info.id AS file_id, "
	+ "       file_info.file_name, "
	+ "       reservation_user_comment_image.id AS image_id, "
	+ "       file_info.modify_date, "
	+ "       reservation_user_comment_image.reservation_info_id, "
	+ "       reservation_user_comment_image.reservation_user_comment_id, "
	+ "       file_info.save_file_name "
	+ "FROM   file_info "
	+ "JOIN   reservation_user_comment_image ON "
	+ "	      reservation_user_comment_image.file_id = file_info.id "
	+ "WHERE  reservation_user_comment_image.reservation_user_comment_id = :commentId;";
	
	public static final String AVERAGE_SCORE
	= "SELECT avg(score) AS average_score "
	+ "FROM   reservation_user_comment "
	+ "JOIN   display_info ON display_info.product_id = reservation_user_comment.product_id "
	+ "WHERE  display_info.id = 1;";
	
}
