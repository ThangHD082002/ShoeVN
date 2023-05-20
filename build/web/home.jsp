<%-- 
    Document   : home.jsp
    Created on : Mar 9, 2023, 11:41:01 AM
    Author     : Thắng đẹp trai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
        <!--header-->

        <%@include file="header.jsp" %>

        <!--body-->
        <div class="home-product">
            <div class="grid">
                <div class="grid__row app_content">
                    <div class="grid__colum-2-12">
                        <nav class="category">
                            <h3 class="category__heading">
                                <i class="category__heading-icon fa-solid fa-list"></i>
                                <p class ="category__heading-name">Danh mục</p>
                            </h3>
                            <ul class="category-list">
                                <li class="category-item"><a href="shoesgroup?id=${0}" class="category-item__link">Sản phẩm</a></li>
                                    <c:forEach items="${requestScope.productGroups}" var="pr">
                                    <!--                                    category-item--active-->
                                    <li class="category-item ">
                                        <a href="shoesgroup?id=${pr.id}" class="category-item__link">${pr.name}</a>
                                    </li>
                                </c:forEach>
                                <c:forEach items="${requestScope.listBrand}" var="br">
                                    <!--                                    category-item--active-->
                                    <li class="category-item ">
                                        <a href="shoesbrand?brid=${br.id}" class="category-item__link">${br.name}</a>
                                    </li>
                                </c:forEach>

                            </ul>
                        </nav>
                    </div>
                    <div class="grid__colum-10-12">
                        <div class="filter">
                            <img src="assest/img/home/filter.jpg" alt="alt" class="filter-img"/>
                        </div>
                        <div class ="home-product-body">
                            <div class="grid__row">
                                <c:forEach items="${requestScope.products}" var="p">
                                    <div class="grid__colum-2-10">
                                        <a class="home-product-item" href="inforProduct?id=${p.id}">
                                            <div class="home-product-item__img"
                                                 style="background-image: url(${p.img});">
                                            </div>
                                            <h4 class="home-product-item__name">${p.name}</h4>
                                            <div class="home-product-item__price">
                                                <span class="home-product-item__price-old">22.990.999đ</span>
                                                <span class="home-product-item__price-curent"><fmt:formatNumber pattern="##.#" value="${p.price}"/>đ</span>
                                            </div>
                                            <div class="home-product-item__action ">
                                                <span class="home-product-item__heart home-product-item__like--liked">
                                                    <i class="home-product-item__like-empty fa-regular fa-heart"></i>
                                                    <i class="home-product-item__like-fill fa-solid fa-heart"></i>
                                                </span>

                                                <div class="home-product-item__rating">
                                                    <i class="ti-star"></i>
                                                    <i class="ti-star"></i>
                                                    <i class="ti-star"></i>
                                                    <i class="ti-star"></i>
                                                    <i class="ti-star"></i>
                                                </div>
                                                <span class="home-product-item__sold">Đã bán 1000</span>
                                            </div>
                                            <div class="home-product-item__origin">
                                                <span class="home-product-item__brand">${p.id}</span>
                                                <span class="home-product-item__origin-name">${p.productGroup.name}</span>
                                            </div>
                                            <div class="home-product-item__favourite">
                                                <i class="fa-solid fa-check"></i>
                                                <span>Yêu thích</span>
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <span class="home-product-item__sale-off-percent">43%</span>
                                                <span class="home-product-item__sale-off-label">GIẢM</span>
                                            </div>
                                        </a>
                                    </div>

                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <!--footer-->
        <%@include file="footer.jsp" %>
    </body>
</html>
