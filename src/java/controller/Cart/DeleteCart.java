/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Cart;

import HandleWithSql.MySqlProcess;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "DeleteCart", urlPatterns = {"/deleteCart"})
public class DeleteCart extends HttpServlet {

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
            out.println("<title>Servlet DeleteCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteCart at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        String quantity_raw = request.getParameter("quantity");
        String size = request.getParameter("size");
        MySqlProcess msp = new MySqlProcess();
        List<ProductGroup> listPg = msp.getProductGroup();
        List<Product> list = msp.getAll();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        System.out.println(txt);

        int id, quantity;
        try {
            Cart cart = new Cart(txt, list);
            id = Integer.parseInt(id_raw);
            quantity = Integer.parseInt(quantity_raw);
            cart.removeIdSize(id, size);
            List<ProductCart> listPc = cart.getProCart();
            txt = "";
            if (listPc.size() > 0) {
                txt = listPc.get(0).getProduct().getId() + ":" + listPc.get(0).getQuantity() + ":" + listPc.get(0).getSize();
                for (int i = 1; i < listPc.size(); i++) {
                    txt += "a" + listPc.get(i).getProduct().getId() + ":" + listPc.get(i).getQuantity() + ":" + listPc.get(i).getSize();
                }
            }
            System.out.println(txt);
            Cookie c = new Cookie("cart", txt);
            c.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(c);
            int n;
            if (listPc != null) {
                n = listPc.size();
            } else {
                n = 0;
            }
            double totalMoneyCart = cart.getTotalMoneyCart();
            List<Size> listS = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                listS = msp.getSizesByProductId(listPc.get(i).getProduct().getId());
                listPc.get(i).getProduct().setListSize(listS);
//            System.out.println(listPc.get(i).getProduct().getId());
//            for(Size s : listPc.get(i).getProduct().getListSize()){
//                System.out.println(s.getNameSize());
//            }
            }
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
            request.setAttribute("sizeCart", n);
            request.setAttribute("productGroups", listPg);
            request.setAttribute("listProductCart", listPc);
            request.setAttribute("totalMoneyCart", totalMoneyCart);
            request.getRequestDispatcher("listCart.jsp").forward(request, response);
        } catch (NumberFormatException e) {
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
