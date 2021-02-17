package kr.or.yl.reservationservice.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.yl.reservationservice.product.dao.DisplayInfoDao;
import kr.or.yl.reservationservice.product.domain.DisplayInfo;
import kr.or.yl.reservationservice.product.domain.DisplayInfoImage;
import kr.or.yl.reservationservice.product.domain.ProductImage;
import kr.or.yl.reservationservice.product.domain.ProductPrice;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	private final DisplayInfoDao displayInfoDao;
	
	public DisplayInfoServiceImpl(DisplayInfoDao displayInfoDao) {
		this.displayInfoDao = displayInfoDao;
	}
	
	@Override
	public DisplayInfo getDisplayInfo(int displayInfoId) {
		return displayInfoDao.selectDisplayInfo(displayInfoId);
	}

	@Override
	public DisplayInfoImage getDisplayInfoImage(int displayInfoId) {
		return displayInfoDao.selectDisplayInfoImage(displayInfoId);
	}
	
	@Override
	public List<ProductImage> getProductImages(int displayInfoId) {
		return displayInfoDao.selectDisplayProductImages(displayInfoId);
	}
	
	@Override
	public List<ProductPrice> getProductPrices(int displayInfoId) {
		return displayInfoDao.selectProductPrices(displayInfoId);
	}
	
}
