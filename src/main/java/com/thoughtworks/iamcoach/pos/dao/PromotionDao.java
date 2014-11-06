package com.thoughtworks.iamcoach.pos.dao;

import com.thoughtworks.iamcoach.pos.entity.Promotion;

import java.util.Set;

public interface PromotionDao {
    Promotion getPromotion(int id);

    Set<String> getPromotionBarcodes();
}
