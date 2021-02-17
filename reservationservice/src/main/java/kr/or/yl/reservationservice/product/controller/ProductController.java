package kr.or.yl.reservationservice.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.yl.reservationservice.product.domain.Comment;
import kr.or.yl.reservationservice.product.domain.Product;
import kr.or.yl.reservationservice.product.dto.CommentReadResponse;
import kr.or.yl.reservationservice.product.dto.DisplayInfoReadResponse;
import kr.or.yl.reservationservice.product.dto.ProductReadResponse;
import kr.or.yl.reservationservice.product.dto.ReserveReadResponse;
import kr.or.yl.reservationservice.product.service.CommentService;
import kr.or.yl.reservationservice.product.service.DetailService;
import kr.or.yl.reservationservice.product.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	private final ProductService productService;
	private final DetailService detailService;
	private final CommentService commentService;

	public ProductController(ProductService productService, DetailService detailService, CommentService commentService) {
		this.productService = productService;
		this.detailService = detailService;
		this.commentService = commentService;
	}
	
	@GetMapping
	public ResponseEntity<ProductReadResponse> getAllProduct(int start) {
		List<Product> products = productService.getAllProduct(start);
		int count = productService.getAllProductCount();
		
		return ResponseEntity
				.ok(new ProductReadResponse(products, count));
	}
	
	@GetMapping(params = {"categoryId", "start"})
	public ResponseEntity<ProductReadResponse> getCategoryProducts(int categoryId, int start) {
		List<Product> products = productService.getCategoryProducts(categoryId, start);
		int count = productService.getCategoryProductsCount(categoryId);
		
		return ResponseEntity
				.ok(new ProductReadResponse(products, count));
	}
	
	@GetMapping("/{displayInfoId}")
	public ResponseEntity<DisplayInfoReadResponse> getDisplayInfo(@PathVariable(name="displayInfoId") int displayInfoId) {
		DisplayInfoReadResponse displayInfoReadResponse = detailService.getDetail(displayInfoId);
		
		return ResponseEntity
				.ok(displayInfoReadResponse);
	}
	
	@GetMapping("/{displayInfoId}/reviews")
	public ResponseEntity<CommentReadResponse> getDisplayComments(@PathVariable(name="displayInfoId") int displayInfoId) {
		double averageScore = commentService.getAverageScore(displayInfoId);
		List<Comment> comment = commentService.getComments(displayInfoId);
		
		return ResponseEntity
				.ok(new CommentReadResponse(averageScore, comment));
	}

	@GetMapping("/{displayInfoId}/reserve")
	public ResponseEntity<ReserveReadResponse> getDisplayInfoByReserve(@PathVariable(name="displayInfoId") int displayInfoId) {
		ReserveReadResponse reserveReadResponse = detailService.getDetailByReserve(displayInfoId);
		
		return ResponseEntity
				.ok(reserveReadResponse);
	}
	
}
