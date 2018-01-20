<%-- 
    Document   : ShowProduct
    Created on : Jan 18, 2018, 7:43:54 AM
    Author     : MinhNBHSE61805
--%>

<%@page import="sample.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="assets/product/ProductStyle.css">
<%
    List<ProductDTO> productList = (List<ProductDTO>) session.getAttribute("PRODUCT_LIST");
%>

<div class="row product_row">
    <%
        int count = 0;
        if (productList != null && productList.size() > 0) {
            for (ProductDTO product : productList) {
    %>
    <div class="product">
        <h3><%= product.getProduct_name()%></h3>
        <%= product.getPrice()%>
    </div>
    <%
        count++;
        if (count % 4 == 0) {
    %>
</div>
<div class="row product_row">
    <%
                }
            }
        }
    %>
</div>

