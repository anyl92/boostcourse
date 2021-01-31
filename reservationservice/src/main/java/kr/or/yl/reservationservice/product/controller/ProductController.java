package kr.or.yl.reservationservice.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.yl.reservationservice.product.domain.Product;
import kr.or.yl.reservationservice.product.dto.ProductReadRequest;
import kr.or.yl.reservationservice.product.dto.ProductReadResponse;
import kr.or.yl.reservationservice.product.service.ProductServiceImpl;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {
	private final ProductServiceImpl productService;

	public ProductController(ProductServiceImpl productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<ProductReadResponse> getProducts(@Valid ProductReadRequest productReadRequest) {
		List<Product> products = productService.getProducts(productReadRequest);
		return ResponseEntity
			.ok(new ProductReadResponse(products, products.size()));
	}

}
