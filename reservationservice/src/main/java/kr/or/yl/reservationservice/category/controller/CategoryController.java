package kr.or.yl.reservationservice.category.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.yl.reservationservice.category.dto.CategoryReadRequest;
import kr.or.yl.reservationservice.category.service.CategoryService;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
	
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<CategoryReadRequest> getCategories() {
		return ResponseEntity
				.ok(new CategoryReadRequest(categoryService.getCategories()));
	}

}
