package com.thoughtworks.iamcoach.pos.service;

import com.thoughtworks.iamcoach.pos.dao.ItemDao;
import com.thoughtworks.iamcoach.pos.dao.impl.ItemDaoImpl;
import com.thoughtworks.iamcoach.pos.entity.Item;
import com.thoughtworks.iamcoach.pos.entity.Promotion;
import com.thoughtworks.iamcoach.pos.entity.PromotionFactory;
import com.thoughtworks.iamcoach.pos.service.impl.ItemServiceImpl;
import com.thoughtworks.iamcoach.pos.service.impl.PromotionServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemServiceImplTest {

    @Test
    public void should_return_Item_with_promotions() throws SQLException {


        PromotionService promotionService = mock(PromotionServiceImpl.class);
        List<Promotion> promotions = new ArrayList<Promotion>();
        Promotion promotion = PromotionFactory.getPromotionByType(1);
        promotion.setType(1);
        promotion.setDiscount(90);
        promotion.setDescription("打折");
        promotions.add(0, promotion);


        ItemDao itemDao = mock(ItemDaoImpl.class);
        Item item = new Item(1,"ITEM000001","测试","菜菜",100,"hahaha");
        item.setPromotions(promotions);

        when(promotionService.getItemPromotions("ITEM000003")).thenReturn(promotions);
        when(itemDao.getItem("ITEM000003")).thenReturn(item);

        ItemService itemServiceImpl = new ItemServiceImpl(promotionService,itemDao);
        assertThat(itemServiceImpl.getItem("ITEM000003").getPromotions().size()).isEqualTo(1);
    }
}


