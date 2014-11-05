package com.thoughtworks.iamcoach.pos.dao.impl;

import com.thoughtworks.iamcoach.pos.dao.PromotionDao;
import com.thoughtworks.iamcoach.pos.entity.Promotion;
import com.thoughtworks.iamcoach.pos.entity.PromotionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PromotionDaoImpl implements PromotionDao {

    private JdbcTemplate jdbcTemplate;

    public PromotionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Promotion getPromotion(int id) {

        final Promotion[] promotion = new Promotion[1];

        jdbcTemplate.query("SELECT * FROM promotions WHERE p_id = ?",
                new Object[]{id},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        int type = rs.getInt("p_type");
                        promotion[0] = PromotionFactory.getPromotionByType(type);
                        promotion[0].setId(rs.getInt("p_id"));
                        promotion[0].setDescription(rs.getString("p_description"));
                        promotion[0].setType(type);
                    }
                });
        return promotion[0];
    }

    @Override
    public Set<String> getPromotionBarcode() {
        final Set<String> promotionBarcodes = new HashSet<String>();

        jdbcTemplate.query("select * from items i, items_promotions ip where i.i_id = ip.itemid ",
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        promotionBarcodes.add(rs.getString("i_barcode"));
                    }
                });
        return promotionBarcodes;
    }
}
