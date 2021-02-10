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
import kr.or.yl.reservationservice.product.domain.DisplayInfo;
import kr.or.yl.reservationservice.product.domain.DisplayInfoImage;
import kr.or.yl.reservationservice.product.domain.Product;
import kr.or.yl.reservationservice.product.domain.ProductImage;
import kr.or.yl.reservationservice.product.dto.CommentReadResponse;
import kr.or.yl.reservationservice.product.dto.DisplayInfoReadResponse;
import kr.or.yl.reservationservice.product.dto.ProductReadResponse;
import kr.or.yl.reservationservice.product.service.CommentService;
import kr.or.yl.reservationservice.product.service.DisplayInfoService;
import kr.or.yl.reservationservice.product.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	private final ProductService productService;
	private final DisplayInfoService displayInfoService;
	private final CommentService commentService;

	public ProductController(ProductService productService, DisplayInfoService displayInfoService, CommentService commentService) {
		this.productService = productService;
		this.displayInfoService = displayInfoService;
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
		
		double averageScore = commentService.getAverageScore(displayInfoId);
		List<Comment> comment = commentService.getComments(displayInfoId);
		DisplayInfo displayInfo = displayInfoService.getDisplayInfo(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoService.getDisplayInfoImage(displayInfoId);
		List<ProductImage> productImages = displayInfoService.getDisplayProductImages(displayInfoId);
		
		DisplayInfoReadResponse displayInfoReadResponse = 
				new DisplayInfoReadResponse(averageScore, comment, displayInfo, displayInfoImage, productImages);
		
		return ResponseEntity
				.ok(displayInfoReadResponse);
	}
	
	@GetMapping("/{displayInfoId}/reviews")
	public ResponseEntity<CommentReadResponse> getDisplayComments(@PathVariable(name="displayInfoId") int displayInfoId) {
		
		double averageScore = commentService.getAverageScore(displayInfoId);
		List<Comment> comment = commentService.getComments(displayInfoId);
		
		CommentReadResponse commentReadResponse = 
				new CommentReadResponse(averageScore, comment);
		
		return ResponseEntity
				.ok(commentReadResponse);
	}
	
}
