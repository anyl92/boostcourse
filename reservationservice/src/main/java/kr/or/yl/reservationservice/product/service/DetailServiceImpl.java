package kr.or.yl.reservationservice.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.yl.reservationservice.product.domain.Comment;
import kr.or.yl.reservationservice.product.domain.DisplayInfo;
import kr.or.yl.reservationservice.product.domain.DisplayInfoImage;
import kr.or.yl.reservationservice.product.domain.ProductImage;
import kr.or.yl.reservationservice.product.domain.ProductPrice;
import kr.or.yl.reservationservice.product.dto.DisplayInfoReadResponse;
import kr.or.yl.reservationservice.product.dto.ReserveReadResponse;

@Service
public class DetailServiceImpl implements DetailService {

	private final DisplayInfoService displayInfoService;
	private final CommentService commentService;
	
	public DetailServiceImpl(DisplayInfoService displayInfoService, CommentService commentService) {
		this.displayInfoService = displayInfoService;
		this.commentService = commentService;
	}
	
	@Override
	public DisplayInfoReadResponse getDetail(int displayInfoId) {
		double averageScore = commentService.getAverageScore(displayInfoId);
		List<Comment> comment = commentService.getComments(displayInfoId);
		DisplayInfo displayInfo = displayInfoService.getDisplayInfo(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoService.getDisplayInfoImage(displayInfoId);
		List<ProductImage> productImages = displayInfoService.getProductImages(displayInfoId);

		return new DisplayInfoReadResponse(averageScore, comment, displayInfo, displayInfoImage, productImages);
	}
	
	@Override
	public ReserveReadResponse getDetailByReserve(int displayInfoId) {
		DisplayInfo displayInfo = displayInfoService.getDisplayInfo(displayInfoId);
		List<ProductImage> productImages = displayInfoService.getProductImages(displayInfoId);
		List<ProductPrice> productPrices = displayInfoService.getProductPrices(displayInfoId);

		return new ReserveReadResponse(displayInfo, productImages, productPrices);
	}
}
