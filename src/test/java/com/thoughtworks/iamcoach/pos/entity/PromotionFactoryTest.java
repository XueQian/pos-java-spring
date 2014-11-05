package com.thoughtworks.iamcoach.pos.entity;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class PromotionFactoryTest {
    @Test
    public void should_return_promoion_by_type() {
        PromotionFactory promotionFactory = new PromotionFactory();
        assertThat(promotionFactory.getPromotionByType(2).getType()).isEqualTo(0);
        assertThat(promotionFactory.getPromotionByType(1).getType()).isEqualTo(0);
        assertThat(promotionFactory.getPromotionByType(3).getType()).isEqualTo(0);
    }
}
