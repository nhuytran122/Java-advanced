<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String loginId = (String)request.getAttribute("loginId");
	boolean isWrong = request.getAttribute("isWrong")!= null ? (
			boolean)request.getAttribute("isWrong") : false;
	
	boolean isInvalid = request.getAttribute("isInvalid")!= null ? (
			boolean)request.getAttribute("isInvalid") : false;
	
	boolean isInvalidCapcha = request.getAttribute("isInvalidCapcha")!= null ? (
			boolean)request.getAttribute("isInvalidCapcha") : false;
	int d = 0;
	  if (session.getAttribute("dem") != null) {
		   	d = (Integer) session.getAttribute("dem");
		} else {
		   	d = 0;
		}
	boolean isLocked = request.getAttribute("isLocked")!= null ? (
		boolean)request.getAttribute("isLocked") : false;
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
    <link rel="stylesheet" href="css/login-style.css">
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
                    <div class="form-container mt-3">
                        <div class="form-icon"><i class="bi bi-person-circle"></i></div>
                        <h3 class="title">Đăng nhập</h3>
                        <form action="../login" class="form-horizontal" method="post">
                            <div class="form-group">
                                <label>Email</label>
                                <input name="txtLoginId" class="form-control mt-2" type="email" 
                                	placeholder="Email" value="<%= (loginId != null) ? loginId : "" %>"/>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input name ="txtPassword" class="form-control mt-2" type="password" placeholder="Mật khẩu">
                            </div>
                            
                            <% if (d >= 3) { %>
						  <div class="form-group">
							  <label>Captcha</label>
							  <div class="d-flex align-items-center">
							    <img src="../simpleCaptcha.jpg" class="me-3 border border-2" style="height: 50px; width: auto;" alt="Captcha" />
							    <input
							      type="text"
							      class="form-control w-50"
							      name="answerCapcha"
							      placeholder="Nhập mã captcha"
							    />
							  </div>
							</div>
			
						<% } %>
                            <% if (isWrong) { %>
				              <span class="text-danger">Thông tin đăng nhập không đúng!</span>
				            <% } %>
				            
				            <% if (isInvalid) { %>
				              <span class="text-danger">Vui lòng nhập đầy đủ thông tin đăng nhập!</span>
				            <% } %>
				            
				            <% if (isInvalidCapcha) { %>
				              <span class="text-danger">Mã capcha không chính xác!</span>
				            <% } %>
				            
				            <% if (isLocked) { %>
							    <span class="text-danger">Tài khoản của bạn đã bị khóa.</span>
							<% } %>
                            <button name="btn-login" type="submit" class="btn btn-default mt-3">Đăng nhập</button>
                        </form>
                        <p class="mt-4 text-center">
                            Bạn chưa có tài khoản? 
                            <a href="../signup" class="text-primary fw-bold">Đăng ký</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
