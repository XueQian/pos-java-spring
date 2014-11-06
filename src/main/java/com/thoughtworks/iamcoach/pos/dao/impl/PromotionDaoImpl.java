package com.thoughtworks.iamcoach.pos.dao.impl;

import com.thoughtworks.iamcoach.pos.dao.PromotionDao;
import com.thoughtworks.iamcoach.pos.entity.Promotion;
import com.thoughtworks.iamcoach.pos.entity.PromotionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PromotionDaoImpl implements PromotionDao {

    private JdbcTemplate jdbcTemplate;

    public PromotionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
// getPromotion　spring　另一种实现方法
//   @Override
//    public Promotion getPromotion(int id) {
//
//        final Promotion[] promotion = new Promotion[1];
//
//        jdbcTemplate.query("SELECT * FROM promotions WHERE p_id = ?",
//                new Object[]{id},
//                new RowCallbackHandler() {
//                    public void processRow(ResultSet rs) throws SQLException {
//                        int type = rs.getInt("p_type");
//                        promotion[0] = PromotionFactory.getPromotionByType(type);
//                        promotion[0].setId(rs.getInt("p_id"));
//                        promotion[0].setDescription(rs.getString("p_description"));
//                        promotion[0].setType(type);
//                    }
//                });
//        return promotion[0];
//    }

    @Override
    public Promotion getPromotion(int id) {
        String sql = "SELECT * FROM promotions WHERE p_id = ?";

        return jdbcTemplate.queryForObject(sql,new Object[]{id},new RowMapper<Promotion>() {
            @Override
            public Promotion mapRow(ResultSet rs, int rowNum) throws SQLException {
                Promotion mapRowPromotion = PromotionFactory.getPromotionByType(rs.getInt("p_type"));
                mapRowPromotion.setId(rs.getInt("p_id"));
                mapRowPromotion.setDescription(rs.getString("p_description"));
                mapRowPromotion.setType(rs.getInt("p_type"));
                return mapRowPromotion;
            }
        });
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
