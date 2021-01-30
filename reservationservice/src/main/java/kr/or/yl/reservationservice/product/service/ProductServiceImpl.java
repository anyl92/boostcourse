package kr.or.yl.reservationservice.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.yl.reservationservice.product.dao.ProductDao;
import kr.or.yl.reservationservice.product.domain.Product;
import kr.or.yl.reservationservice.product.dto.ProductReadRequest;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;

	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> getProducts(ProductReadRequest productReadRequest) {
		System.out.println(productDao+"다오다오다오다오");
		return productDao.selectAll(productReadRequest);
	}
}
