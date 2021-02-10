package kr.or.yl.reservationservice.product.dto;

import java.util.List;
import kr.or.yl.reservationservice.product.domain.Product;

public class ProductReadResponse {

	private final List<Product> items;
	private final int count;

	public ProductReadResponse(List<Product> items, int count) {
		this.items = items;
		this.count = count;
	}

	public List<Product> getItems() {
		return items;
	}
	
	public int getCount() {
		return count;
	}

	@Override
	public String toString() {
		return "ProductReadResponse [items=" + items + ", count=" + count + "]";
	}

}
