package kr.or.yl.reservationservice.product.service;

import java.util.List;

import kr.or.yl.reservationservice.product.domain.DisplayInfo;
import kr.or.yl.reservationservice.product.domain.DisplayInfoImage;
import kr.or.yl.reservationservice.product.domain.ProductImage;
import kr.or.yl.reservationservice.product.domain.ProductPrice;

public interface DisplayInfoService {
	DisplayInfo 	   getDisplayInfo(int displayInfoId);
	DisplayInfoImage   getDisplayInfoImage(int displayInfoId);
	List<ProductImage> getProductImages(int displayInfoId);
	List<ProductPrice> getProductPrices(int productId);
}
