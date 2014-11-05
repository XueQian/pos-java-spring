package com.thoughtworks.iamcoach.pos.entity;

import com.thoughtworks.iamcoach.pos.util.DataTransfer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Calculator {
    public static double getSubtotal(CartItem cartItem) {
        List<Double> promotionMoney = new ArrayList<Double>();

        if (cartItem.getItem().getPromotions().size() != 0) {
            for (Promotion cartPromotion : cartItem.getItem().getPromotions()) {
                double money = PromotionFactory.getPromotionByType(cartPromotion.getType()).calculate(cartItem);
                promotionMoney.add(money);
            }
            return Collections.min(promotionMoney);
        }

        return cartItem.getItem().getPrice() * cartItem.getCount();
    }


    public static double getTotalMoney(List<CartItem> cartItems) {

        double totalMoney = 0.0;
        for (CartItem cartItem : cartItems) {

            totalMoney += cartItem.getItem().getPrice() * cartItem.getCount();
        }
        return totalMoney;
    }


    public static double getTotalMoneyAfterPromoting(List<CartItem> cartItems) {
        double totalMoneyAfterPromoting = 0;

        for (CartItem cartItem : cartItems) {
            totalMoneyAfterPromoting += getSubtotal(cartItem);
        }
        return totalMoneyAfterPromoting;
    }


    public static double getTotalSavedMoney(List<CartItem> cartItems) {

        return DataTransfer.transfer(getTotalMoney(cartItems) - getTotalMoneyAfterPromoting(cartItems));
    }
}
