/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utilities.DBUtils;

/**
 *
 * @author MinhNBHSE61805
 */
public class ProductDAO {

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void getAllProduct() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM Product";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String product_name = rs.getString("product_name");
                    int category_id = rs.getInt("category_id");
                    String description = rs.getString("description");
                    String price = (String) rs.getString("price");
                    String friendly_url = rs.getString("friendly_url");
                    String warranty = rs.getString("warranty");
                    String create_date = rs.getString("create_date");
                    String update_date = rs.getString("update_date");
                    ProductDTO product = new ProductDTO(id, product_name, category_id, description, price, friendly_url, warranty, create_date, update_date);
                    if (productList == null) {
                        productList = new ArrayList<ProductDTO>();
                    }
                    productList.add(product);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void getProductByCategory(String categoryId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM Product WHERE category_id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, categoryId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String product_name = rs.getString("product_name");
                    int category_id = rs.getInt("category_id");
                    String description = rs.getString("description");
                    String price = (String) rs.getString("price");
                    String friendly_url = rs.getString("friendly_url");
                    String warranty = rs.getString("warranty");
                    String create_date = rs.getString("create_date");
                    String update_date = rs.getString("update_date");
                    ProductDTO product = new ProductDTO(id, product_name, category_id, description, price, friendly_url, warranty, create_date, update_date);
                    if (productList == null) {
                        productList = new ArrayList<ProductDTO>();
                    }
                    productList.add(product);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
