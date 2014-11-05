package com.thoughtworks.iamcoach.pos.entity;

import java.util.List;

public class Item {

    private int id;
    private String barcode;
    private String name;
    private String unit;
    private double price;
    private String category;
    private List<Promotion> promotions;

    public Item() {
    }

    public Item(int id, String barcode, String name, String unit, double price, String category) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

}
