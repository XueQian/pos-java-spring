package com.thoughtworks.iamcoach.pos.service.impl;

import com.thoughtworks.iamcoach.pos.dao.ItemDao;
import com.thoughtworks.iamcoach.pos.dao.PromotionDao;
import com.thoughtworks.iamcoach.pos.entity.Promotion;
import com.thoughtworks.iamcoach.pos.service.PromotionService;

import java.util.List;

public class PromotionServiceImpl implements PromotionService {

    private PromotionDao promotionDaoImpl;
    private ItemDao itemDaoImpl;

    public PromotionServiceImpl(PromotionDao promotionDaoImpl, ItemDao itemDaoImpl) {
        this.promotionDaoImpl = promotionDaoImpl;
        this.itemDaoImpl = itemDaoImpl;
    }

    @Override
    public List<Promotion> getItemPromotions(String barcode) {

        if (promotionDaoImpl.getPromotionBarcode().contains(barcode)) {
            return itemDaoImpl.getItemPromotions(barcode);
        } else {
            return null;
        }
    }
}
