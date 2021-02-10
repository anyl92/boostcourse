package kr.or.yl.reservationservice.product.service;

import java.util.List;

import kr.or.yl.reservationservice.product.domain.Product;

public interface ProductService {
	List<Product> getAllProduct(int start);
	int 		  getAllProductCount();
	List<Product> getCategoryProducts(int categoryId, int start);
	int 		  getCategoryProductsCount(int categoryId);
}
