package kr.or.yl.reservationservice.product.dto;

import javax.validation.constraints.Min;

public class ProductReadRequest {
	@Min(value = 0, message = "카테고리 ID가 유효하지 않습니다.")
	private int categoryId;

	@Min(value = 0, message = "상품정보의 시작 범위가 유효하지 않습니다.")
	private int start = 0;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "ProductReadRequest [categoryId=" + categoryId + ", start=" + start + "]";
	}
	
	

}
