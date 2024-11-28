<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
    <link rel="stylesheet" href="../User/css/login-style.css">
</head>
<body class="bg-light">
<%@ include file="layout/navbar.jsp" %>
    <div class="container my-3">
        <div class="row w-100">
            <div class="col-md-6 d-flex justify-content-center align-items-center">
                <img src="../images/husc-removebg-preview.png" alt="HUSCZone Logo" class="img-fluid" style="max-width: 500px;">
            </div>

            <div class="col-md-6">
                <div class="form-bg">
                    <div class="form-container">
                        <h3 class="title">Đăng ký</h3>
                        <form class="form-horizontal">
                        	<div class="form-group">
                                <label>Tên</label>
                                <input class="form-control mt-2" type="text" placeholder="Tên">
                            </div>
                            
                            <div class="row mb-3">
                            	<div class="col-md-6">
                                	<label>Email</label>
                                 <input class="form-control mt-2" type="email" placeholder="Email">
                            	</div>
                            	
                            	<div class="col-md-6">
                                	<label>Số điện thoại</label>
                               		<input class="form-control mt-2" type="text" placeholder="Số điện thoại">
                            	</div>
                            </div>
                            
                            <div class="row mb-3">
                            	<div class="col-md-6">
                                	<label>Ngày sinh</label>
                                	<input class="form-control mt-2" type="date" placeholder="Ngày sinh">
                            	</div>
                            	
                            	<div class="col-md-6">
                                	<label class="form-label">Giới tính</label>
	                            	<select class="form-control p-1 px-4" data-style="btn-default">
	                            	    <option value="" selected>-- Chọn giới tính --</option>
	                            	    <option value="male">Nam</option>
	                             	   <option value="female">Nữ</option>
	                            	</select>
                            	</div>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input class="form-control mt-2" type="password" placeholder="Mật khẩu">
                            </div>
                            <button type="button" class="btn btn-default mt-3">Đăng ký</button>
                        </form>
                        <p class="mt-4 text-center">
                            Bạn đã có tài khoản? 
                            <a href="register.jsp" class="text-primary fw-bold">Đăng nhập</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
