<%-- 
    Document   : ShowProduct
    Created on : Jan 18, 2018, 7:43:54 AM
    Author     : MinhNBHSE61805
--%>

<%@page import="sample.product.Product"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="assets/product/ProductStyle.css">
<%
    List<Product> productList = (List<Product>) session.getAttribute("PRODUCT_LIST");
%>

<section>
    <div class="row product_row_heading">
        <div style="width: 100%">
            Sản phẩm mới
        </div>
    </div>
    <div class="row product_row">
        <%
            int count = 0;
            if (productList != null && productList.size() > 0) {
                for (Product product : productList) {
        %>
        <div class="product">
            <div class="product_image"><img src="assets/images/pg27vq.png" /></div>
            <h3 class="product_title"><%= product.getProductName()%></h3>
            <h4 class="product_price"><%= product.getPrice()%></h4>
        </div>
        <%
            count++;
            if (count % 5 == 0) {
        %>
    </div>
    <div class="row product_row">
        <%
                    }
                }
                while (count % 5 != 0) {
                    out.println("<div class='product'></div>");
                    count++;
                }
            }
        %>
    </div>
</section>