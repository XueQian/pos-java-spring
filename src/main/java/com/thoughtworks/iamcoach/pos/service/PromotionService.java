package com.thoughtworks.iamcoach.pos.service;

import com.thoughtworks.iamcoach.pos.entity.Promotion;

import java.util.List;

public interface PromotionService {
    List<Promotion> getItemPromotions(String barcode);
}
