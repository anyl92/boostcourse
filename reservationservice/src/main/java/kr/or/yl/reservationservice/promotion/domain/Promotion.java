package kr.or.yl.reservationservice.promotion.domain;

public class Promotion {

	private int id;
	private String productImageUrl;

	public Promotion() {
		
	}
	
	public Promotion(int id, String productImageUrl) {
		this.id = id;
		this.productImageUrl = productImageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productImageUrl=" + productImageUrl + "]";
	}
	
}
