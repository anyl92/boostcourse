package kr.or.yl.reservationservice.product.dto;

import java.util.List;

import kr.or.yl.reservationservice.product.domain.Comment;
import kr.or.yl.reservationservice.product.domain.DisplayInfo;
import kr.or.yl.reservationservice.product.domain.DisplayInfoImage;
import kr.or.yl.reservationservice.product.domain.ProductImage;

public class DisplayInfoReadResponse {

	private final double averageScore;
	private final List<Comment> comments;
	private final DisplayInfo displayInfo;
	private final DisplayInfoImage displayInfoImage;
	private final List<ProductImage> productImages;
	
	public DisplayInfoReadResponse(double averageScore, List<Comment> comments, DisplayInfo displayInfo, DisplayInfoImage displayInfoImage, List<ProductImage> productImages) {
		this.averageScore = averageScore;
		this.comments = comments;
		this.displayInfo = displayInfo;
		this.displayInfoImage = displayInfoImage;
		this.productImages = productImages;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public DisplayInfo getDisplay() {
		return displayInfo;
	}

	public DisplayInfoImage getDisplayImage() {
		return displayInfoImage;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	@Override
	public String toString() {
		return "DisplayInfoReadResponse [averageScore=" + averageScore + ", comments=" + comments + ", displayInfo="
				+ displayInfo + ", displayInfoImage=" + displayInfoImage + ", productImages=" + productImages + "]";
	}

}
