package kr.or.yl.reservationservice.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.yl.reservationservice.product.dao.ProductDao;
import kr.or.yl.reservationservice.product.domain.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductDao productDao;

	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<Product> getAllProduct(int start) {
		return productDao.selectAllProduct(start);
	}

	@Override
	public int getAllProductCount() {
		return productDao.selectAllProductCount();
	}
	
	@Override
	public List<Product> getCategoryProducts(int categoryId, int start) {
		return productDao.selectCategoryProducts(categoryId, start);
	}
	
	@Override
	public int getCategoryProductsCount(int categoryId) {
		return productDao.selectCategoryProductsCount(categoryId);
	}
}
