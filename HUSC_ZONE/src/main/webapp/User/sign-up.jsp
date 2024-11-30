<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  String password = (String)request.getAttribute("password");
  String hoten = (String)request.getAttribute("hoten");
  String sdt = (String)request.getAttribute("sdt");
  String email = (String)request.getAttribute("loginId");
  String diachi = (String)request.getAttribute("diachi");
  boolean isInvalid = (boolean)request.getAttribute("isInvalid");
  boolean isDuplicate = (boolean)request.getAttribute("isDuplicate");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
    <link rel="stylesheet" href="css/login-style.css">
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
                        <form action="../signup" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label>Tên</label> <span class="text-danger">*</span>
                                <input class="form-control mt-2" type="text" name="txtHoten" placeholder="Nhập họ tên" value="<%= (hoten != null) ? hoten : "" %>" />
                            </div>

                            <div class="form-group">
                                <label>Email</label> <span class="text-danger">*</span>
                                <input class="form-control mt-2" type="email" name="txtLoginId" placeholder="Nhập email" value="<%= (email != null) ? email : "" %>" />
                            </div>

                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label>Số điện thoại</label>
                                    <input class="form-control mt-2" type="text" name="txtSdt" placeholder="Nhập số điện thoại" value="<%= (sdt != null) ? sdt : "" %>" />
                                </div>

                                <div class="col-md-6">
                                    <label class="form-label">Giới tính</label> <span class="text-danger">*</span>
                                    <select class="form-control p-1 px-4" name="txtGioiTinh">
                                        <option value="" <%= (request.getAttribute("gioitinh") == null) ? "selected" : "" %>>-- Chọn giới tính --</option>
                                        <option value="Nam" <%= "Nam".equals(request.getAttribute("gioitinh")) ? "selected" : "" %>>Nam</option>
                                        <option value="Nữ" <%= "Nữ".equals(request.getAttribute("gioitinh")) ? "selected" : "" %>>Nữ</option>
                                        <option value="Khác" <%= "Khác".equals(request.getAttribute("gioitinh")) ? "selected" : "" %>>Khác</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Password</label> <span class="text-danger">*</span>
                                <input class="form-control mt-2" type="password" name="txtPassword" placeholder="Mật khẩu" />
                            </div>

                            <% if (isInvalid) { %>
                                <div class="alert alert-danger mt-2">Vui lòng nhập đầy đủ thông tin bắt buộc!</div>
                            <% } %>

                            <% if (isDuplicate) { %>
                                <div class="alert alert-danger mt-2">Email đã tồn tại trong hệ thống!</div>
                            <% } %>

                            <button type="submit" name="btn-signup" class="btn btn-default mt-3">Đăng ký</button>
                        </form>

                        <p class="mt-4 text-center">
                            Bạn đã có tài khoản? 
                            <a href="../login" class="text-primary fw-bold">Đăng nhập</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
