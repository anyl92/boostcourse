package kr.or.yl.reservationservice.promotion.dto;

import java.util.List;

import kr.or.yl.reservationservice.promotion.domain.Promotion;

public class PromotionReadResponse {

	private final List<Promotion> items;
	
	public PromotionReadResponse(List<Promotion> items) {
		this.items = items;
	}

	public List<Promotion> getItems() {
		return items;
	}
	
	@Override
	public String toString() {
		return "PromotionReadResponse [items=" + items + "";
	}
}
