<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
    <link rel="stylesheet" href="../User/css/login-style.css">
</head>
<body class="bg-light">
<%@ include file="layout/navbar.jsp" %>
    <div class="container my-4">
        <div class="row w-100">
            <div class="col-md-6 d-flex justify-content-center align-items-center">
                <img src="../images/husc-removebg-preview.png" alt="HUSCZone Logo" class="img-fluid" style="max-width: 500px;">
            </div>

            <div class="col-md-5">
                <div class="form-bg">
                    <div class="form-container">
                        <div class="form-icon"><i class="bi bi-person-circle"></i></div>
                        <h3 class="title">Đăng nhập</h3>
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label>Email</label>
                                <input class="form-control mt-2" type="email" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input class="form-control mt-2" type="password" placeholder="Mật khẩu">
                            </div>
                            <button type="button" class="btn btn-default mt-3">Đăng nhập</button>
                        </form>
                        <p class="mt-4 text-center">
                            Bạn chưa có tài khoản? 
                            <a href="register.jsp" class="text-primary fw-bold">Đăng ký</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
