/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.category.CategoryDAO;
import sample.category.CategoryDTO;
import sample.page.Page;
import sample.product.ProductDAO;
import sample.product.ProductDTO;

/**
 *
 * @author MinhNBHSE61805
 */
public class DashboardServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String sCategoryId = request.getParameter("categoryId");

            HttpSession session = request.getSession();
            ProductDAO productDAO = new ProductDAO();
            CategoryDAO categoryDAO = new CategoryDAO();

            // Get products
            if (sCategoryId != null) {
                productDAO.getProductByCategory(sCategoryId);
            } else {
                productDAO.getAllProduct();
            }

            List<ProductDTO> productList = productDAO.getProductList();
            session.setAttribute("PRODUCT_LIST", productList);

            // Get parent categories
            List<CategoryDTO> parentCategories = new ArrayList<>();
            if (session.getAttribute("PARENT_CATEGORIES") == null) {
                categoryDAO.getParentCategory();
                parentCategories = categoryDAO.getCategoryList();
                session.setAttribute("PARENT_CATEGORIES", parentCategories);
            }

            // Get child categories
            if (session.getAttribute("CHILD_CATEGORIES") == null) {
                Map<Integer, List<CategoryDTO>> categoryMap = new HashMap<Integer, List<CategoryDTO>>();

                for (CategoryDTO category : parentCategories) {
                    categoryDAO.getChildCategory(category.getId());
                    List<CategoryDTO> childCategory = categoryDAO.getCategoryList();
                    if (childCategory.size() > 0) {
                        categoryMap.put(category.getId(), childCategory);
                        session.setAttribute("CHILD_CATEGORIES", categoryMap);
                    }
                }
            }
            session.setAttribute("CONTENT_VIEW", "views/product/ShowProduct.jsp");
            String url = Page.layoutDashboard;
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (NamingException ex) {
            log("DashboardServlet: NamingException");
        } catch (SQLException ex) {
            log("DashboardServlet: SQLException");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}