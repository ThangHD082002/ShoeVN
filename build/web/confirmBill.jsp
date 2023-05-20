<%-- 
    Document   : confirmBill
    Created on : Mar 15, 2023, 3:09:04 AM
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
        <c:set value="${sessionScope.customer}" var="c"/>
        <c:set value="${requestScope.totalMoneyCart}" var="totalMoney"/>
        <div class="confirm">
            <div class="grid-confirm">
                <div class="confirm-bill">
                    <h1 class="confirm-title">Thông tin đơn hàng</h1>
                    <div class="confirm-infor">
                        <div class="confirm-customer-title">
                            <p class="confirm-customer-infor">Tên: </p>
                            <p class="confirm-customer-infor">Số điện thoại </p>
                            <p class="confirm-customer-infor">Địa chỉ: </p>
                        </div>
                        <div class="confirm-customer-detail">
                            <p class="confirm-customer-infor">${c.name}</p>
                            <p class="confirm-customer-infor">${c.sdt} </p>
                            <p class="confirm-customer-infor">${c.diaChi}</p>
                        </div>
                    </div>
                    <div class="confirm-list">
                        <c:forEach items="${requestScope.listProductCart}" var="pc">
                            <div class="confirm-item">
                                <div class="confirm-item-wrap">
                                    <img src ="${pc.product.img}" class="confirm-item-img"/>
                                </div>
                                <div class="confirm-item-infor-product">
                                    <h1 class="confirm-item-name">${pc.product.name}</h1>
                                    <p class="confirm-price"><fmt:formatNumber pattern="##.#" value="${pc.price*pc.quantity}"/>đ</p>
                                    <p class="confirm-size">Size :${pc.size}</p>
                                    <p class="confirm-quantity">Số lượng: ${pc.quantity}</p>
                                </div>
                            </div>
                        </c:forEach>
                        <h2 class="confirm-total-money">Tổng: <fmt:formatNumber pattern="##.#" value="${totalMoney}"/>đ</h2>
                    </div>
                        <div class="confirm-action">
                            <a href="listCart" class="confirm-back">Trở lại</a>
                            <a class="confirm-submit">Xác nhận</a>
                        </div>

                </div>
            </div>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
