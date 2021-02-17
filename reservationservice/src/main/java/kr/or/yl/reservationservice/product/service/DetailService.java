package kr.or.yl.reservationservice.product.service;

import kr.or.yl.reservationservice.product.dto.DisplayInfoReadResponse;
import kr.or.yl.reservationservice.product.dto.ReserveReadResponse;

public interface DetailService {
	
	DisplayInfoReadResponse getDetail(int displayInfoId);
	ReserveReadResponse     getDetailByReserve(int displayInfoId);
	
}
