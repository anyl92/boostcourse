package kr.or.yl.reservationservice.category.dto;

import java.util.List;
import kr.or.yl.reservationservice.category.domain.Category;

public class CategoryDto {

	private List<Category> items;

	public CategoryDto(List<Category> items) {
		this.items = items;
	}

	public List<Category> getItems() {
		return items;
	}

	public void setItems(List<Category> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "CategoryDto [items=" + items + "]";
	}
}
