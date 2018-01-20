/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

/**
 *
 * @author MinhNBHSE61805
 */
public class ProductDTO {
    private int id;
    private String product_name;
    private int category_id;
    private String description;
    private String price;
    private String friendly_url;
    private String warranty;
    private String create_date;
    private String update_date;

    public ProductDTO() {
    }

    public ProductDTO(int id, String product_name, int category_id, String description, String price, String friendly_url, String warranty, String create_date, String update_date) {
        this.id = id;
        this.product_name = product_name;
        this.category_id = category_id;
        this.description = description;
        this.price = price;
        this.friendly_url = friendly_url;
        this.warranty = warranty;
        this.create_date = create_date;
        this.update_date = update_date;
    }

    public int getId() {
        return id;
    }
    
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFriendly_url() {
        return friendly_url;
    }

    public void setFriendly_url(String friendly_url) {
        this.friendly_url = friendly_url;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }
    
}
