package com.thoughtworks.iamcoach.pos.dao.impl;

import com.thoughtworks.iamcoach.pos.dao.PromotionDao;
import com.thoughtworks.iamcoach.pos.entity.Promotion;
import com.thoughtworks.iamcoach.pos.entity.PromotionFactory;
import com.thoughtworks.iamcoach.pos.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PromotionDaoImpl implements PromotionDao {
    ResultSet rs;
    PreparedStatement pre;
    private JdbcUtil jdbcUtil = new JdbcUtil();

    @Override
    public Promotion getPromotion(int id) {
        Connection conn = jdbcUtil.getConnection();
        Promotion promotion = null;
        try {
            pre = conn.prepareStatement("SELECT * FROM promotions WHERE p_id = ?");
            pre.setInt(1, id);
            rs = pre.executeQuery();
            rs.next();
            int type = rs.getInt("p_type");
            promotion = PromotionFactory.getPromotionByType(type);
            promotion.setId(rs.getInt("p_id"));
            promotion.setDescription(rs.getString("p_description"));
            promotion.setType(rs.getInt("p_type"));

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
        return promotion;
    }

    @Override
    public Set<String> getPromotionBarcode() {
        Connection conn = jdbcUtil.getConnection();
        Set<String> promotionBarcodes = new HashSet<String>();
        try {
            pre = conn.prepareStatement("select * from items i, items_promotions ip where i.i_id = ip.itemid ");
            rs = pre.executeQuery();
            while (rs.next()) {
                promotionBarcodes.add(rs.getString("i_barcode"));
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
        return promotionBarcodes;
    }

}
