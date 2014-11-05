package com.thoughtworks.iamcoach.pos.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class CalculatorTest {

    private CartItem cartItem1;
    private CartItem cartItem2;
    private List<CartItem> cartItems;

    @Before
    public void before(){
        Item item1 = new Item(2, "ITEM000002", "全家桶", "桶", 50, "食品");
        Item item2 = new Item(1, "ITEM000001", "麦斯威尔", "盒", 17, "咖啡");

        List<Promotion> noPromotion = new ArrayList<Promotion>();
        item1.setPromotions(noPromotion);

        cartItem1 = new CartItem(item1, 3);

        Promotion oneFreePromotion = PromotionFactory.getPromotionByType(1);
        oneFreePromotion.setType(1);
        Promotion secondHalfPromotion = PromotionFactory.getPromotionByType(2);
        secondHalfPromotion.setType(2);
        Promotion dicountPromotion = PromotionFactory.getPromotionByType(3);
        dicountPromotion.setType(3);
        dicountPromotion.setDiscount(50);

        List<Promotion> itemPromotions = new ArrayList<Promotion>();
        itemPromotions.add(0, oneFreePromotion);
        itemPromotions.add(1, secondHalfPromotion);
        itemPromotions.add(2, dicountPromotion);

        item2.setPromotions(itemPromotions);

        cartItem2 = new CartItem(item2, 5);

        cartItems = new ArrayList<CartItem>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
    }

    @Test
    public void should_getSubtotal_function_return_a_lowest_money() {
        assertThat(Calculator.getSubtotal(cartItem2)).isEqualTo(42.5);
    }

    @Test
    public void should_return_basic_money_when_item_has_no_promotions() {
        assertThat(Calculator.getSubtotal(cartItem1)).isEqualTo(150);
    }


    @Test
    public void should_return_total_money_for_all_cart_items(){
        assertThat(Calculator.getTotalMoney(cartItems)).isEqualTo(235);
    }

    @Test
    public void should_return_a_total_money_with_promotions_for_all_cart_items() {
        assertThat(Calculator.getTotalMoneyAfterPromoting(cartItems)).isEqualTo(192.5);
    }

    @Test
    public void should_return_total_saved_money() {
        assertThat(Calculator.getTotalSavedMoney(cartItems)).isEqualTo(42.5);
    }
}
