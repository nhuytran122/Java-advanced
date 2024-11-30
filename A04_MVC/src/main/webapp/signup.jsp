<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
  String loginId = (String)request.getAttribute("loginId");
  String password = (String)request.getAttribute("password");
  String hoten = (String)request.getAttribute("hoten");
  String sdt = (String)request.getAttribute("sdt");
  String email = (String)request.getAttribute("email");
  String diachi = (String)request.getAttribute("diachi");
  boolean isInvalid = (boolean)request.getAttribute("isInvalid");
  boolean isDuplicate = (boolean)request.getAttribute("isDuplicate");
	
    
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Đăng ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<style>
    .fg-color {
        color: #006eb7;
    }
    .bg-color {
        background-color: #337ab7;
    }
    .text-danger {
        color: red;
    }
</style>

<body style="background-color: #63b0e3">
    <div class="container mt-5 w-75 p-5">
        <div class="row">
            <div class="col-md-7 d-flex flex-column align-items-center">
                <img src="https://student.husc.edu.vn/Themes/Login/images/Logo-ko-nen.png" alt="logo-husc" class="img-fluid mb-4" style="width: 112px; height: 112px" />
                <img src="https://student.husc.edu.vn/Themes/Login/images/image1.png" alt="lab-images" class="img-fluid" />
            </div>

            <div class="col-md-5 bg-white p-4 border border-2 border-info">
                <h3 class="mb-4 fg-color">ĐĂNG KÝ</h3>
                <form action="signupController" method="post">
                    <div class="mb-3">
                        <label class="form-label">Họ tên: <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" name="txtHoten" placeholder="Nhập họ tên" value="<%= (hoten != null) ? hoten : "" %>" />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Tên đăng nhập: <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" name="txtLoginId" placeholder="Nhập tên đăng nhập" value="<%= (loginId != null) ? loginId : "" %>" />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Số điện thoại:</label>
                        <input type="text" class="form-control" name="txtSdt" placeholder="Nhập số điện thoại" value="<%= (sdt != null) ? sdt : "" %>" />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Email:</label>
                        <input type="text" class="form-control" name="txtEmail" placeholder="Nhập email" value="<%= (email != null) ? email : "" %>" />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Địa chỉ:</label>
                        <input type="text" class="form-control" name="txtDiachi" placeholder="Nhập địa chỉ" value="<%= (diachi != null) ? diachi : "" %>" />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Mật khẩu: <span class="text-danger">*</span></label>
                        <input type="password" class="form-control" name="txtPassword" placeholder="Mật khẩu" value="<%= (password != null) ? password : "" %>" />
                    </div>

                    <% if (isInvalid) { %>
                        <span class="text-danger">Vui lòng nhập đầy đủ thông tin bắt buộc!</span>
                    <% } %>

                    <% if (isDuplicate) { %>
                        <span class="text-danger">Tên đăng nhập đã tồn tại!</span>
                    <% } %>

                    <button type="submit" name="btn-signup" class="btn btn-primary w-100 bg-color py-2 my-2">Đăng ký</button>
                    <p class="text-center text-muted mt-5 mb-0">Đã có tài khoản? <a href="loginController" class="fw-bold text-body">Đăng nhập</a></p>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
