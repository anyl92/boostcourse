package kr.or.yl.reservationservice.category.service;

import java.util.List;

import org.springframework.stereotype.Service;
import kr.or.yl.reservationservice.category.dao.CategoryDao;
import kr.or.yl.reservationservice.category.domain.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryDao categoryDao;

	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> getCategories() {
		return categoryDao.selectAll();
	}

}
