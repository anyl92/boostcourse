package kr.or.yl.reservationservice.product.dto;

import java.util.List;
import kr.or.yl.reservationservice.product.domain.Product;

public class ProductReadResponse {

	private List<Product> items;
	private int totalCount;

	public ProductReadResponse(List<Product> items, int totalCount) {
		this.items = items;
		this.totalCount = totalCount;
	}

	public List<Product> getItems() {
		return items;
	}

	public int getTotalCount() {
		return totalCount;
	}

	@Override
	public String toString() {
		return "ProductReadResponse [items=" + items + ", totalCount=" + totalCount + "]";
	}

}
