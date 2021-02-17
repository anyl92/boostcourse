package kr.or.yl.reservationservice.product.dto;

import java.util.List;

import kr.or.yl.reservationservice.product.domain.DisplayInfo;
import kr.or.yl.reservationservice.product.domain.ProductImage;
import kr.or.yl.reservationservice.product.domain.ProductPrice;

public class ReserveReadResponse {
	
	private final DisplayInfo displayInfo;
	private final List<ProductImage> productImages;
	private final List<ProductPrice> productPrices;
	
	public ReserveReadResponse(DisplayInfo displayInfo, List<ProductImage> productImages, List<ProductPrice> productPrices) {
		this.displayInfo = displayInfo;
		this.productImages = productImages;
		this.productPrices = productPrices;
	}
	
	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	@Override
	public String toString() {
		return "ReserveReadResponse [displayInfo=" + displayInfo + ", productImages=" + productImages
				+ ", productPrices=" + productPrices + "]";
	}

}
