package kr.or.yl.reservationservice.product.dto;

public class ProductReadRequest {
	
	private int categoryId;
	private int start;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getstart() {
		return start;
	}

	public void setstart(int start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "ProductReadRequest [categoryId=" + categoryId + ", start=" + start + "]";
	}
	
}
