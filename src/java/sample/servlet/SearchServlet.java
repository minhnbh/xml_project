/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sample.page.Page;
import sample.student.StudentDTO;
import sample.utils.XMLUtilities;

/**
 *
 * @author MinhNBHSE61805
 */
public class SearchServlet extends HttpServlet {

    public String xmlFile = "WEB-INF/studentAccounts.xml";
    
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
        PrintWriter out = response.getWriter();
        String searchValue = request.getParameter("txt:searchValue");
        try {
            String realPath = this.getServletContext().getRealPath("/");
            String xmlFilePath = realPath + xmlFile;
            Document doc = XMLUtilities.parseFileToDOM(xmlFilePath);
            if (doc != null) {
                XPath xpath = XMLUtilities.getXPath();
                String exp = "//student[contains(address, '" + searchValue + "')]";
                NodeList students = (NodeList)xpath.evaluate(exp, doc, XPathConstants.NODESET);
                
                for (int i = 0; i < students.getLength(); i++) {
                    Node tmp = students.item(i);
                    exp = "@id";
                    String id = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
                    
                    exp = "@class";
                    String sClass = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
                    
                    exp = "lastname";
                    String lastname = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
                    
                    exp = "middlename";
                    String middlename = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
                    
                    exp = "firstname";
                    String firstname = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
                    
                    exp = "sex";
                    String sex = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
                    
                    exp = "address";
                    String address = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
                    
                    exp = "status";
                    String status = (String) xpath.evaluate(exp, doc, XPathConstants.STRING);
                    
                    StudentDTO dto = new StudentDTO(id, sClass, lastname, middlename, firstname, sex, address, status);
                }//end for studens
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
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
