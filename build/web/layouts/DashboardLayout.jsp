<%-- 
    Document   : DashboardLayout
    Created on : Jan 13, 2018, 10:51:16 PM
    Author     : MinhNBHSE61805
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="sample.category.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/custom/custom.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
        <title>Dashboard</title>
    </head>
    <body>
        <div class="row" style="background-color: black; color: white; padding: 11px; margin-top: -8px;">
            <div style="width: 25%">

            </div>
            <div style="margin-left: 75%; width: 25%; text-align: right">
                Xin chào <%= session.getAttribute("USERNAME")%> &nbsp;
                <i class="fa fa-caret-down"></i>&nbsp;|&nbsp;
                <a href="ProcessServlet?btAction=Logout"><i class="fa fa-sign-out"></i></a>
            </div>
        </div>
        <div class="row" style="margin-top: -19px">
            <div id="sidebar" style="width: 16.666667%; background-color: black; color: white">
                <ul class="parentCategory">
                    <li style="padding: 5px"><i class="fa fa-cubes"></i>DANH MỤC<br></li>
                    <li><hr></li>
                    <li><a>Trang chủ</a></li>
                        <%
                            List<CategoryDTO> parentCategories = (List<CategoryDTO>) session.getAttribute("PARENT_CATEGORIES");
                            Map<Integer, List<CategoryDTO>> childCategories = (HashMap<Integer, List<CategoryDTO>>) session.getAttribute("CHILD_CATEGORIES");
                            for (Map.Entry<Integer, List<CategoryDTO>> entry : childCategories.entrySet()) {
                                System.out.println(entry.getValue().size());
                            }
                            if (parentCategories != null && parentCategories.size() > 0) {
                                for (CategoryDTO category : parentCategories) {
                                    List<CategoryDTO> childCategory = childCategories.get(category.getId());
                        %>
                    <li><a href="javascript:void(0)"><%= category.getCategory_name()%></a>
                        <ul class="childCategory">
                            <%
                                if (childCategory != null && childCategory.size() > 0) {
                                    for (CategoryDTO child : childCategory) {
                            %>
                            <li><a href="javascript:void(0)"><%= child.getCategory_name()%></a></li>
                                <%
                                        }
                                    }
                                %>
                        </ul>
                    </li>
                    <%
                            }
                        }
                    %>
                </ul>
            </div>
            <!-- content -->
            <div style="width: 81%; margin-left: 19%; padding-top: 2%; display: inline-flex" id="content"></div>
            <script>
//                    document.getElementById("content").innerHTML='<object style="width: 100%" type="text/html" data="views/product/ShowProduct.jsp"></object>'
                xhr = new XMLHttpRequest();
                xhr.open("GET", <%= session.getAttribute("CONTENT_VIEW") %>, true);
                xhr.setRequestHeader('Content-type', 'text/html');
                xhr.send();
                xhr.onreadystatechange = function () {
                    if (xhr.status == 200 && xhr.readyState == 4) {
                        document.getElementById("content").innerHTML = xhr.responseText;
                    }
                }
            </script>
            <!-- end content -->
        </div>
    </body>
</html>
