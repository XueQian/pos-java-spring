package com.thoughtworks.iamcoach.pos.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class PromotionDiscountTest {
    @Test
    public void should_return_money_with_a_discount_promotion() {
        PromotionDiscount promotionDiscount = new PromotionDiscount();
        List<Promotion> promotions = new ArrayList<Promotion>();
        Promotion promotion = PromotionFactory.getPromotionByType(1);
        promotion.setId(1);
        promotion.setType(1);
        promotion.setDiscount(90);
        promotion.setDescription("打折");
        promotions.add(0, promotion);
        Item item = new Item(1, "ITEM000001", "测试１", "你猜", 100, "不告诉你");
        item.setPromotions(promotions);
        CartItem cartItem = new CartItem(item, 2);
        assertThat(promotionDiscount.calculate(cartItem)).isEqualTo(180);
    }
}
