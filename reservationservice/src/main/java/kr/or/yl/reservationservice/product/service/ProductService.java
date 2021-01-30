package kr.or.yl.reservationservice.product.service;

import java.util.List;

import kr.or.yl.reservationservice.product.domain.Product;
import kr.or.yl.reservationservice.product.dto.ProductReadRequest;

interface ProductService {
	List<Product> getProducts(ProductReadRequest productReadRequest);
}
