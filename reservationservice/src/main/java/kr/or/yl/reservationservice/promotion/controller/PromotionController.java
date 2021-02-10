package kr.or.yl.reservationservice.promotion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.yl.reservationservice.promotion.dto.PromotionReadResponse;
import kr.or.yl.reservationservice.promotion.service.PromotionService;

@RestController
@RequestMapping(path = "/api/promotions")
public class PromotionController {

	private final PromotionService promotionService;
	
	public PromotionController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}
	
	@GetMapping
	public ResponseEntity<PromotionReadResponse> getPromotions() {
		return ResponseEntity
				.ok(new PromotionReadResponse(promotionService.getPromotions()));
	}
	
}
