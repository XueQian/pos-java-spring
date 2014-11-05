package com.thoughtworks.iamcoach.pos.service;

import com.thoughtworks.iamcoach.pos.dao.ItemDao;
import com.thoughtworks.iamcoach.pos.dao.PromotionDao;
import com.thoughtworks.iamcoach.pos.dao.impl.ItemDaoImpl;
import com.thoughtworks.iamcoach.pos.dao.impl.PromotionDaoImpl;
import com.thoughtworks.iamcoach.pos.entity.Promotion;
import com.thoughtworks.iamcoach.pos.entity.PromotionFactory;
import com.thoughtworks.iamcoach.pos.service.impl.PromotionServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PromotionServiceImplTest {

    @Test
    public void should_return_the_promotions_of_an_item(){

        PromotionDao promotionDao = mock(PromotionDaoImpl.class);
        Set<String> promotionBarcodes = new HashSet<String>();
        promotionBarcodes.add("ITEM000001");
        promotionBarcodes.add("ITEM000003");
        when(promotionDao.getPromotionBarcode()).thenReturn(promotionBarcodes);

        ItemDao itemDao = mock(ItemDaoImpl.class);
        List<Promotion> promotions = new ArrayList<Promotion>();
        Promotion promotion = PromotionFactory.getPromotionByType(1);
        promotion.setId(1);
        promotion.setType(1);
        promotion.setDiscount(90);
        promotion.setDescription("打折");
        promotions.add(0, promotion);
        when(itemDao.getItemPromotions("ITEM000001")).thenReturn(promotions);

        PromotionService promotionService = new PromotionServiceImpl(promotionDao,itemDao);

        assertThat(promotionService.getItemPromotions("ITEM000001").get(0).getType()).isEqualTo(1);
        assertThat(promotionService.getItemPromotions("ITEM000002")).isEqualTo(null);


    }

}


