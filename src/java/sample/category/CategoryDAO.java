/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.category;

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
public class CategoryDAO {
    
    private List<CategoryDTO> categoryList;
    
    public List<CategoryDTO> getCategoryList() {
        return categoryList;
    }
    
    public void getParentCategory() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM Category WHERE parent_category = 0";
//                stm.setString(0, "0");
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String category_name = rs.getString("category_name");
                    int parent_category = rs.getInt("parent_category");
                    CategoryDTO dto = new CategoryDTO(id, category_name, parent_category);
                    if (categoryList == null) {
                        categoryList = new ArrayList<>();
                    }
                    categoryList.add(dto);
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
    
    public void getChildCategory(int parentId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        categoryList = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM Category WHERE parent_category = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, Integer.toString(parentId));
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String category_name = rs.getString("category_name");
                    int parent_category = rs.getInt("parent_category");
                    CategoryDTO dto = new CategoryDTO(id, category_name, parent_category);
                    categoryList.add(dto);
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