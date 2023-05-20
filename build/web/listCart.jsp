<%-- 
    Document   : listCart
    Created on : Mar 12, 2023, 5:55:45 PM
    Author     : Thắng đẹp trai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assest/fonts/themify-icons-font/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="./assest/css/styles.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:set value="${requestScope.totalMoneyCart}" var="totalMoney"/>
        <!--body-->
        <div class="cart-list">
            <div class="grid">
                <div class="grid__row">
                    <div class="grid__column-8-12">
                        <div class="cart-title">
                            <h1 class="cart-title-name">GIỎ HÀNG</h1>
                        </div>
                        <div>
                            <c:forEach items="${requestScope.listProductCart}" var="pc">
                                <form action="deleteCart">
                                    <div class="grid__row grid__row-cart">
                                        <div class ="grid__column-2-8-12">
                                            <img src="${pc.product.img}" alt="alt" class="cart-img"/>
                                        </div>
                                        <div class="grid__column-4-8-12">
                                            <div class="cart-infor-puchare">
                                                <h1 class="cart-infor-name">${pc.product.name}</h1>
                                                <p class="cart-infor-price"><fmt:formatNumber pattern="##.#" value="${pc.price*pc.quantity}"/>đ</p>
                                                <div class="cart-infor-product">
                                                    <div class="cart-size-wrap">
                                                        <p class="cart-size">Size</p>
                                                        <c:set var="i" value="0"/>
                                                        <select name="size" class = "size-product" onchange="">
                                                            <c:forEach items="${pc.product.listSize}" var="pl">

                                                                <c:if test="${i == 0}">
                                                                    <option value="${pc.size}">${pc.size}</option>
                                                                </c:if>
                                                                <c:if test="${i != 0}">
                                                                    <option value="${pl.nameSize}">${pl.nameSize}</option>
                                                                </c:if>
                                                                <c:set var="i" value="${(i+1)}"/>    
                                                            </c:forEach>   
                                                        </select>
                                                    </div>
                                                    <div class="cart-quantity-wrap">
                                                        <p class="cart-quantity">Số lượng</p>
                                                        <a href=""><input type="number" name="quantity" class="cart-quantity-input" value="${pc.quantity}"></a>  
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class ="grid__column-2-8-12 delete-wrap">
                                            <input type="hidden" name="id" value="${pc.product.id}">
                                            <input type="submit" value="Xoá" class="delete-submit">
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="grid__column-4-12">
                        <div class="cart-bill">
                            <div class="cart-bill-all">

                                <h1 class="bill-title">ĐƠN HÀNG</h1>
                                <div class="bill-discount">
                                    <form>
                                        <h2 class="bill-discount-name">NHẬP MÃ KHUYẾN MÃI</h2>
                                        <input type="text" name="discount" class="bill-discount-input">
                                        <input type="submit" value="ÁP DỤNG" class="bill-discount-submit">
                                    </form>
                                </div>
                                <div class="bill-total">
                                    <div class="bill-total-title">
                                        <h3 class="bill-total-name">Đơn hàng</h3>
                                        <h3 class="bill-total-name">Giảm</h3>
                                    </div>
                                    <div class="bill-total-money">
                                        <h3 class="bill-total-money-pay"><fmt:formatNumber pattern="##.#" value="${totalMoney}"/>đ</h3>
                                        <h3 class="bill-total-money-discount">0đ</h3>
                                    </div>
                                </div>
                                <div class="bill-total-final">
                                    <div class ="bill-total-price">
                                        <h2 class="bill-total-price-pay">TẠM TÍNH</h2>
                                        <h2 class="bill-total-price-money"><fmt:formatNumber pattern="##.#" value="${totalMoney}"/>đ</h2>
                                    </div>
                                    <a href="checkin" class="bill-pay-link">THANH TOÁN</a>
                                </div>     
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
