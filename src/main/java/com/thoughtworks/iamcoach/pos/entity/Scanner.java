package com.thoughtworks.iamcoach.pos.entity;

import com.thoughtworks.iamcoach.pos.service.ItemService;

import java.util.*;

public class Scanner {
    private ItemService itemServiceImpl;

    public Scanner(ItemService itemServiceImpl) {
        this.itemServiceImpl = itemServiceImpl;
    }

    public Scanner() {

    }

    public List<CartItem> getCartItems(List<String> cartList) {
        List<CartItem> cartItems = new ArrayList<CartItem>();

        for (String aCartList : cartList) {
            if (aCartList.length() > 10) {
                String stringBarcode = aCartList.split("-")[0];
                String stringCount = aCartList.substring(11);
                CartItem cartItem = new CartItem(itemServiceImpl.getItem(stringBarcode), Double.parseDouble(stringCount));
                cartItems.add(cartItem);
            }
        }

        Set<String> uniqueBarcode = new HashSet<String>(cartList);
        for (String barcode : uniqueBarcode) {
            if (barcode.length() <= 10) {
                CartItem cartItem = new CartItem(itemServiceImpl.getItem(barcode), Collections.frequency(cartList, barcode));
                cartItems.add(cartItem);
            }
        }
        return cartItems;
    }

    public Set<String> getCartCategories(List<CartItem> cartItems) {
        Set<String> cartCategories = new HashSet<String>();
        for (CartItem cartItem : cartItems) {
            cartCategories.add(cartItem.getItem().getCategory());
        }

        return cartCategories;
    }
}
