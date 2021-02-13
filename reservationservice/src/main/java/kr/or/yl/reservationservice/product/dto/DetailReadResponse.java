package kr.or.yl.reservationservice.product.dto;

import java.util.List;

import kr.or.yl.reservationservice.product.domain.Comment;
import kr.or.yl.reservationservice.product.domain.DisplayInfo;
import kr.or.yl.reservationservice.product.domain.DisplayInfoImage;
import kr.or.yl.reservationservice.product.domain.ProductImage;

public class DetailReadResponse {
	
	double averageScore;
	List<Comment> comments; 
	DisplayInfo displayInfo;
	DisplayInfoImage displayInfoImage; 
	List<ProductImage> productImages;
	
	public DetailReadResponse(double averageScore, List<Comment> comments, DisplayInfo displayInfo, DisplayInfoImage displayInfoImage, List<ProductImage> productImages) {
		this.averageScore = averageScore;
		this.comments = comments; 
		this.displayInfo = displayInfo;
		this.displayInfoImage = displayInfoImage; 
		this.productImages = productImages;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public DisplayInfoImage getDisplayInfoImage() {
		return displayInfoImage;
	}

	public void setDisplayInfoImage(DisplayInfoImage displayInfoImage) {
		this.displayInfoImage = displayInfoImage;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	@Override
	public String toString() {
		return "DetailReadResponse [averageScore=" + averageScore + ", comments=" + comments + ", displayInfo="
				+ displayInfo + ", displayInfoImage=" + displayInfoImage + ", productImages=" + productImages + "]";
	}
	
}
