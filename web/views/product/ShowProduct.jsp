<%-- 
    Document   : ShowProduct
    Created on : Jan 18, 2018, 7:43:54 AM
    Author     : MinhNBHSE61805
--%>

<%@page import="sample.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<ProductDTO> productList = (List<ProductDTO>) session.getAttribute("PRODUCT_LIST");
%>
<!--<div style="width: 100%">-->
    <%
        if (productList != null && productList.size() > 0) {
            for (ProductDTO product : productList) {
    %>
    <div style="width: 25%; float: left; clear: both;">
        <h3><%= product.getProduct_name() %></h3>
        <%= product.getPrice() %>
    </div>
    <%
            }
        }
    %>
<!--</div>-->
