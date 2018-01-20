/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.category;

/**
 *
 * @author MinhNBHSE61805
 */
public class CategoryDTO {
    
    private int id;
    private String category_name;
    private int parent_category;

    public CategoryDTO() {
    }

    public CategoryDTO(int id, String category_name, int parent_category) {
        this.id = id;
        this.category_name = category_name;
        this.parent_category = parent_category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getParent_category() {
        return parent_category;
    }

    public void setParent_category(int parent_category) {
        this.parent_category = parent_category;
    }
    
    
}
