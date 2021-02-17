package kr.or.yl.reservationservice.promotion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.yl.reservationservice.promotion.dao.PromotionDao;
import kr.or.yl.reservationservice.promotion.domain.Promotion;

@Service
public class PromotionServiceImpl implements PromotionService {

	private final PromotionDao promotionDao;
	
	public PromotionServiceImpl(PromotionDao promotionDao) {
		this.promotionDao = promotionDao;
	}
	
	public List<Promotion> getPromotions() {
		return promotionDao.selectAllPromotion();
	}
	
}
