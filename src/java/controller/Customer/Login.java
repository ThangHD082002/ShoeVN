/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Customer;

import HandleWithSql.MySqlProcess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Brand;
import model.Cart;
import model.Customer;
import model.Product;
import model.ProductCart;
import model.ProductGroup;

/**
 *
 * @author Thắng đẹp trai
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.sendRedirect("login.jsp");
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
        MySqlProcess msp = new MySqlProcess();
        List<ProductGroup> listPg = msp.getProductGroup();
        List<Product> list = msp.getAll();
        List<Brand> listBr = msp.getBrand();
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String sdt = request.getParameter("sdt");
        Customer c = msp.checkAccount(sdt,user, pass);
        HttpSession session = request.getSession();
        if (c != null) {
            if (session.getAttribute("customer") == null) {
                Cookie[] arr = request.getCookies();
                String txt = "";
                if (arr != null) {
                    for (Cookie o : arr) {
                        if (o.getName().equals("cart")) {
                            txt += o.getValue();
                        }
                    }
                }
                Cart cart = new Cart(txt, list);
                List<ProductCart> listPC = cart.getProCart();
                int n;
                if (listPC != null) {
                    n = listPC.size();
                } else {
                    n = 0;
                }
                request.setAttribute("listBrand", listBr);
                session.setAttribute("customer", c);
                request.setAttribute("productGroups", listPg);
                request.setAttribute("sizeCart", n);
                request.setAttribute("logout", "Logout");
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
            else{
                Cookie[] arr = request.getCookies();
                String txt = "";
                if (arr != null) {
                    for (Cookie o : arr) {
                        if (o.getName().equals("cart")) {
                            txt += o.getValue();
                        }
                    }
                }
                Cart cart = new Cart(txt, list);
                List<ProductCart> listPC = cart.getProCart();
                int n;
                if (listPC != null) {
                    n = listPC.size();
                } else {
                    n = 0;
                }
                request.setAttribute("listBrand", listBr);
                request.setAttribute("productGroups", listPg);
                request.setAttribute("sizeCart", n);
                request.setAttribute("logout", "Logout");
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
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
