/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Cart;

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
import model.Cart;
import model.Product;
import model.ProductCart;
import model.ProductGroup;
import model.Size;

/**
 *
 * @author Thắng đẹp trai
 */
@WebServlet(name = "BuyProduct", urlPatterns = {"/buy"})
public class BuyProduct extends HttpServlet {

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
            out.println("<title>Servlet BuyProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyProduct at " + request.getContextPath() + "</h1>");
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
        List<Product> list = msp.getAll();
        List<ProductGroup> listPg = msp.getProductGroup();
        Cookie[] arr = request.getCookies();
        int ok = 0;
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    ok = 1;
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }
        String id = request.getParameter("id");
        String num = request.getParameter("num");
        String size = request.getParameter("size");
        int id_num;
        try {
            id_num = Integer.parseInt(id);
            List<Size> listSize = msp.getSizesByProductId(id_num);
            Product p = msp.getProductById(id_num);
            if (txt.isEmpty()) {
                txt = id + ":" + num + ":" + size;
            } else {
                txt = txt + "a" + id + ":" + num + ":" + size;
            }
//            System.out.println(txt);
            Cookie c = new Cookie("cart", txt);
            c.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(c);
            Cart cart = new Cart(txt, list);
            List<ProductCart> listPC = cart.getProCart();
            int n;
            if (listPC != null) {
                n = listPC.size();
            } else {
                n = 0;
            }
//        System.out.println("txt");
//        System.out.println(n);
            String logout = "";
            String login = "";
            HttpSession session = request.getSession();
            if (session.getAttribute("customer") != null) {
                logout = "Logout";
            } else {
                login = "Đăng nhập";
            }

            request.setAttribute("logout", logout);
            request.setAttribute("login", login);
            request.setAttribute("listSize", listSize);
            request.setAttribute("sizeCart", n);
            request.setAttribute("product", p);
            request.setAttribute("productGroups", listPg);
            request.getRequestDispatcher("inforProduct.jsp").forward(request, response);

        } catch (IOException e) {
            System.out.println(e);
        }

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

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
