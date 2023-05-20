/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Pay;

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
import model.Customer;
import model.Product;
import model.ProductCart;
import model.ProductGroup;
import model.Size;

/**
 *
 * @author Thắng đẹp trai
 */
@WebServlet(name = "PayBillHaveAccount", urlPatterns = {"/payBillHaveAccount"})
public class PayBillHaveAccount extends HttpServlet {

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
            out.println("<title>Servlet PayBillHaveAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PayBillHaveAccount at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
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
        Cart cart = new Cart(txt, list);
        List<ProductCart> listPc = cart.getProCart();
        int n;
        if (listPc != null) {
            n = listPc.size();
        } else {
            n = 0;
        }
        double totalMoneyCart = cart.getTotalMoneyCart();
//        for(ProductCart pc : listPc){
//            List<Size> listS = msp.getSizesByProductId(pc.getProduct().getId());
//            pc.getProduct().setListSize(listS);
//            
//        }
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
        request.getRequestDispatcher("confirmBill.jsp").forward(request, response);
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
