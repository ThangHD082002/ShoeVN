/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Product;

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
import model.Product;
import model.ProductCart;
import model.ProductGroup;

/**
 *
 * @author Thắng đẹp trai
 */
@WebServlet(name = "ShowProductOfBrand", urlPatterns = {"/shoesbrand"})
public class ShowProductOfBrand extends HttpServlet {

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
            out.println("<title>Servlet ShowProductOfBrand</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowProductOfBrand at " + request.getContextPath() + "</h1>");
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
        MySqlProcess msp = new MySqlProcess();
        String brid_raw = request.getParameter("brid");
        List<ProductGroup> listPg = msp.getProductGroup();
        List<Product> list = msp.getAll();
        List<Brand> listBr = msp.getBrand();
        int brid;
        try {
            brid = Integer.parseInt(brid_raw);
            if (brid != 0) {
                List<Product> list_Bid = msp.getProductByBrand_Id(brid);
                request.setAttribute("products", list_Bid);
            } else {
                request.setAttribute("products", list);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
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
        String logout = "";
        String login="";
        HttpSession session = request.getSession();
        if(session.getAttribute("customer") != null){
            logout = "Logout";
        }
        else{
            login = "Đăng nhập";
        }
//        System.out.println("txt");
//        System.out.println(n);
        request.setAttribute("sizeCart", n);
        request.setAttribute("logout", logout);
        request.setAttribute("productGroups", listPg);
        request.setAttribute("listBrand", listBr);
        request.setAttribute("login", login);

        request.getRequestDispatcher("home.jsp").forward(request, response);
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
