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

}
