package kr.or.yl.reservationservice.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.yl.reservationservice.category.dao.CategoryDao;
import kr.or.yl.reservationservice.category.domain.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private final CategoryDao categoryDao;

	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> getCategories() {
		return categoryDao.selectAllCategory();
	}

}
