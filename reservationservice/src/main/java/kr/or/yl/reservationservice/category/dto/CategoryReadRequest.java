package kr.or.yl.reservationservice.category.dto;

import java.util.List;

import kr.or.yl.reservationservice.category.domain.Category;

public class CategoryReadRequest {

	private final List<Category> items;

	public CategoryReadRequest(List<Category> items) {
		this.items = items;
	}

	public List<Category> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "CategoryReadRequest [items=" + items + "]";
	}
	
}
