package com.thoughtworks.iamcoach.pos.entity;

import java.util.List;

public class PromotionDiscount extends Promotion {

    @Override
    public double calculate(CartItem cartItem) {
        double basicMoney = cartItem.getCount() * cartItem.getItem().getPrice();
        return basicMoney * getDiscount(cartItem);
    }

    private double getDiscount(CartItem cartItem) {

        List<Promotion> promotions = cartItem.getItem().getPromotions();
        for (Promotion promotion : promotions) {
            if (promotion.getDiscount() != 0) {
                return promotion.getDiscount() / 100;
            }
        }
        return 1;
    }
}
