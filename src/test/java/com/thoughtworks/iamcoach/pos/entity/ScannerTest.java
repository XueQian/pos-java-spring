package com.thoughtworks.iamcoach.pos.entity;

import com.thoughtworks.iamcoach.pos.service.ItemService;
import com.thoughtworks.iamcoach.pos.service.impl.ItemServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScannerTest {

    @Test
    public void should_return_a_cart_list() {
        ItemService itemServiceImpl = mock(ItemServiceImpl.class);
        Item item2 = new Item(2, "ITEM000003", "全家桶", "桶", 50, "食品");
        Item item3 = new Item(2, "ITEM000005", "草莓圣代", "杯", 10, "食品");
        when(itemServiceImpl.getItem("ITEM000003")).thenReturn(item2);
        when(itemServiceImpl.getItem("ITEM000005")).thenReturn(item3);
        String cart3 = "ITEM000003-5.5";
        String cart4 = "ITEM000005";
        String cart5 = "ITEM000005";

        List<String> cartList = new ArrayList<String>();
        cartList.add(cart3);
        cartList.add(cart4);
        cartList.add(cart5);

        Scanner cartItemProcessor = new Scanner(itemServiceImpl);
        assertThat(cartItemProcessor.getCartItems(cartList).get(0).getItem().getName()).isEqualTo("全家桶");
        assertThat(cartItemProcessor.getCartItems(cartList).get(0).getCount()).isEqualTo(5.5);
        assertThat(cartItemProcessor.getCartItems(cartList).size()).isEqualTo(2);
    }

    @Test
    public void should_return_a_cartItems_category_list() {
        Item item1 = new Item(2, "ITEM000002", "全家桶", "桶", 50, "食品");
        Item item2 = new Item(1, "ITEM000001", "麦斯威尔", "盒", 17, "咖啡");

        List<Promotion> noPromotion = new ArrayList<Promotion>();
        item1.setPromotions(noPromotion);

        CartItem cartItem1 = new CartItem(item1, 3);

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

        CartItem cartItem2 = new CartItem(item2, 5);

        List<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        Scanner scanner = new Scanner();
        assertThat(scanner.getCartCategories(cartItems).contains("食品")).isEqualTo(true);
        assertThat(scanner.getCartCategories(cartItems).contains("咖啡")).isEqualTo(true);
        assertThat(scanner.getCartCategories(cartItems).size()).isEqualTo(2);
    }
}
