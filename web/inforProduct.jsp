<%-- 
    Document   : inforProduct
    Created on : Mar 11, 2023, 3:46:16 PM
    Author     : Thắng đẹp trai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./assest/css/styles.css">
        <link rel="stylesheet" href="./assest/fonts/themify-icons-font/themify-icons/themify-icons.css">
        <title>JSP Page</title>

    </head>
    <body>
        <c:set value="${requestScope.product}" var="p"/>
        <%@include file="header.jsp" %>

        <div class ="infor-Product">
            <div class ="grid">
                <div class ="grid__row infor-wrap">
                    <div class="grid__colum-half">
                        <div class="infor-wrap-img">
                            <img class="infor-img" src="${p.img}">
                        </div> 
                    </div>
                    <div class="grid__colum-half">
                        <div class ="infor-purchare">
                            <h1 class ="infor-name">${p.name}</h1>
                            <h2 class ="infor-price"><fmt:formatNumber pattern="##.#" value="${p.price}"/>đ</h2>
                            <form action="buy">
                                <div class="infor-form">
                                    <div class="id-wrap">
                                        <p class = "infor-quantity-name">Mã sản phẩm</p>
                                        <input  type="number" name="id" class = "infor-quantity-input" value="${p.id}">
                                    </div>
                                    <div class ="quantity-wrap">
                                        <p class = "infor-quantity-name">Số lượng</p>
                                        <input  type="number" name="num" class = "infor-quantity-input" value="1">
                                    </div>
                                    <div class ="size-wrap">
                                        <p class = "infor-quantity-name">Size</p>
                                        <select name = "size" class="select">
                                            <c:forEach  items="${requestScope.listSize}" var="ls">
                                                <option value="${ls.nameSize}">${ls.nameSize}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <input type="submit" value="Thêm vào giỏ hàng" class="infor-add-cart" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
