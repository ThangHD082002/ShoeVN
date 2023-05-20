<%-- 
    Document   : loginAdmin
    Created on : Mar 11, 2023, 10:57:54 PM
    Author     : Thắng đẹp trai
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="icon" href="/assest/img/band/member1.jpg" type="image/x-icon"/>
        <link rel="stylesheet" href="./assest/css/login.css">
        <link rel="stylesheet" href="./assest/fonts/themify-icons-font/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="./assest/css/styles.css">
    </head>
    <body>
    <body>
        <div id = "main">
            <div class = "modal">
                <div class="login-form">
                    <form action="login" method="post">
                        <div class="header-login">
                            <h3 class="header-title">Đăng nhập</h3>
                        </div>

                        <div class="body">
                             <div class="body-account">
                                <input type="text" name="sdt" placeholder="Nhập số điện thoại" class="account-input">
                            </div>
                            <div class="body-account">
                                <input type="text" name="user" placeholder="Nhập tài khoản" class="account-input">
                            </div>
                            <div class="body-account">
                                <input type="text" name="pass" placeholder="Nhập mật khẩu" class="account-input">
                            </div>
                        </div>
                        <input type="submit" value="Đăng nhập" class="submit-form">
                    </form>
                </div>
            </div>
        </div>
    </body>
</body>
</html>

