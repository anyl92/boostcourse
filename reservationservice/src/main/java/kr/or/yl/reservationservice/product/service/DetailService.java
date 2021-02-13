package kr.or.yl.reservationservice.product.service;

import kr.or.yl.reservationservice.product.dto.DetailReadResponse;

public interface DetailService {
	
	DetailReadResponse getDetail(int displayInfoId);
	
}
