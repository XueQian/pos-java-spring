package com.thoughtworks.iamcoach.pos.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class PromotionDiscountTest {
    private List<Promotion> promotions;
    private PromotionDiscount promotionDiscount;
    private Promotion promotion;
    private Item item;

    @Before
    public void before() {
        promotionDiscount = new PromotionDiscount();
        promotions = new ArrayList<Promotion>();
        promotion = PromotionFactory.getPromotionByType(1);
        promotion.setId(1);
        promotion.setType(1);
        item = new Item(1, "ITEM000001", "测试１", "你猜", 100, "不告诉你");
        promotion.setDescription("打折");
    }

    @Test
    public void should_return_money_with_a_discount_promotion() {
        promotion.setDiscount(90);
        promotions.add(0, promotion);
        item.setPromotions(promotions);
        CartItem cartItem = new CartItem(item, 2);
        assertThat(promotionDiscount.calculate(cartItem)).isEqualTo(180);
    }

    @Test
    public void should_return_money_without_a_discount_promotion() {
        promotion.setDiscount(0);
        promotions.add(0, promotion);
        item.setPromotions(promotions);
        CartItem cartItem = new CartItem(item, 2);
        assertThat(promotionDiscount.calculate(cartItem)).isEqualTo(200);
    }
}
