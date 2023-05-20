<%-- 
    Document   : listProduct
    Created on : Mar 11, 2023, 10:39:00 PM
    Author     : Thắng đẹp trai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("are you sure delete categor with id = " + id)) {
                    window.location = "check?action=deleteProduct?id=" + id;
                }
            }
        </script>
    </head>
    <body>
        <h3><a href="check?action=add">Add new product</a></h3>
        <table border="1px" width="40%">
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>PRICE</th>
                <th>QUANTITY</th>
                <th>IMAGE</th>
                <th>PGID</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${requestScope.products}" var="p">
                <c:set var="id" value="${p.id}"/>
                <tr>
                    <td>${id}</td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>${p.quantity}</td>
                    <td>${p.img}</td>
                    <td>${p.productGroup.id}</td>
                    <td>
                        <a href="check?action=update&id=${id}">Update</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="#" onclick="doDelete('${id}')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
