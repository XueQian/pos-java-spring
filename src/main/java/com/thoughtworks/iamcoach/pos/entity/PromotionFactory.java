package com.thoughtworks.iamcoach.pos.entity;

public class PromotionFactory {

    public static final int PROMOTION_ONE_FREE = 1;
    public static final int PROMOTION_SECOND_HALF = 2;
    public static final int PROMOTION_DISCOUNT = 3;

    public static Promotion getPromotionByType(int type) {
        Promotion promotion = null;
        switch (type) {
            case PROMOTION_SECOND_HALF:
                promotion = new PromotionSecondHalf();
                break;
            case PROMOTION_ONE_FREE:
                promotion = new PromotionOneFree();
                break;
            case PROMOTION_DISCOUNT:
                promotion = new PromotionDiscount();
                break;
            default:
                break;
        }
        return promotion;
    }
}
