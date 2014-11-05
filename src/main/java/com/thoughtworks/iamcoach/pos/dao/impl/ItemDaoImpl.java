package com.thoughtworks.iamcoach.pos.dao.impl;

import com.thoughtworks.iamcoach.pos.dao.ItemDao;
import com.thoughtworks.iamcoach.pos.dao.PromotionDao;
import com.thoughtworks.iamcoach.pos.entity.Item;
import com.thoughtworks.iamcoach.pos.entity.Promotion;
import com.thoughtworks.iamcoach.pos.entity.PromotionFactory;
import com.thoughtworks.iamcoach.pos.util.JdbcUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    private JdbcUtil jdbcUtil = new JdbcUtil();
    private JdbcTemplate jdbcTemplate;
    private  ResultSet rs;
    private  PreparedStatement pre;

    public ItemDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item getItem(final String barcode) {

        final Item item = new Item();
        jdbcTemplate.query("select * from items i,categories c where  i.i_categoryid = c.c_id and i_barcode = ?",
                new Object[] {barcode},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        item.setId(rs.getInt("i_id"));
                        item.setBarcode(barcode);
                        item.setName(rs.getString("i_name"));
                        item.setUnit(rs.getString("i_unit"));
                        item.setPrice(rs.getDouble("i_price"));
                        item.setCategory(rs.getString("c_name"));
                    }
                });
        return item;
    }

    @Override
    public List<Promotion> getItemPromotions(String barcode) {
        Connection conn = jdbcUtil.getConnection();
        List<Promotion> itemPromotions = new ArrayList<Promotion>();

        try {
            pre = conn.prepareStatement("select * from items i,items_promotions ip where i.i_id=ip.itemid  and i_barcode = ?");
            pre.setString(1, barcode);
            rs = pre.executeQuery();

            while (rs.next()) {

                PromotionDao promotionDaoImpl = new PromotionDaoImpl();
                int promotionId = rs.getInt("promotionid");
                Promotion promotionForType = promotionDaoImpl.getPromotion(promotionId);

                Promotion promotion = PromotionFactory.getPromotionByType(promotionForType.getType());
                promotion.setId(promotionForType.getId());
                promotion.setType(promotionForType.getType());
                promotion.setDescription(promotionForType.getDescription());
                promotion.setDiscount(rs.getDouble("discount"));

                itemPromotions.add(promotion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pre.close();
                jdbcUtil.closeConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return itemPromotions;
    }
}



