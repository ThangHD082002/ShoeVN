<%-- 
    Document   : header.jsp
    Created on : Mar 9, 2023, 11:40:50 AM
    Author     : Thắng đẹp trai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set value="${sessionScope.customer}" var="c"/>
        <c:set value="${requestScope.logout}" var="t"/>
        <c:set value="${requestScope.login}" var="n"/>
        <div class="header">
            <div class="grid">
                <div class="header-menu">
                    <ul class = "header__menu-list">
                        <c:forEach items="${requestScope.productGroups}" var="pr">
                            <li class="menu-item"><a href="shoesgroup?id=${pr.id}" class="menu-item-link">${pr.name}</a></li>
                            </c:forEach>
                    </ul>
                    
                    <a href=""class ="header-login-link-user" >
                        <c:if test="${c.img != null}">
                            <img src="${c.img}" class="header__login-img"/>
                        </c:if>
                            <p>${c.name}</p>
                    </a>
                    <a href="logout"class ="header-logout-link" >${t}</a>
                    <a href="login.jsp"class ="header-login-link" >${n}</a>
                </div>
                <div class="header__search">
                    <form action="search" class="header__search-form">

                        <div class="header__search-wrap">
                            <input type="text" name="ten" id="" class="header__search-input" placeholder="Tìm kiếm">
                        </div>
                        <input type="submit" value="search" class = "header__search-submit">
                        <div class="header__cart">
                            <a href="listCart" class="header-cart-link" >
                                <div class="header__cart-wrap">
                                    <i class="header__cart-icon ti-shopping-cart-full"></i>
                                    <c:set value="${requestScope.sizeCart}" var="s"/>
                                    <span class="header__cart-notice">${s}</span>
                                </div>
                            </a>
                        </div>
                    </form>



                </div>


            </div>
        </div>
    </body>
</html>
