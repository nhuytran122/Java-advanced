<%@page import="khachhangmodal.khachhang"%>
<%@page import="cartmodal.GioHangBo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
    GioHangBo cart = (GioHangBo) session.getAttribute("gh");
    int totalItems = 0;
    int cntPrs = 0;
    if (cart != null) {
    	cntPrs = cart.ds.size();
    }
    khachhang kh = (khachhang)session.getAttribute("kh");
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="sachController">Trang chủ</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">        
        <li class="nav-item">
          <a class="nav-link" href="lichsuController">Xác nhận đặt hàng</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="lichsumuahangController">Lịch sử mua hàng</a>
        </li>
      </ul>
      
      <ul class="navbar-nav ms-auto">
        <% if (kh != null) { %>
        <li class="nav-item d-flex align-items-center">
          <i class="bi bi-cart4" style="color: white; "></i>
          <a class="nav-link" href="giohangController">Giỏ hàng (<%= cntPrs %>)</a>
        </li>
        <li class="nav-item d-flex align-items-center">
          <span class="nav-link">Xin chào, <%= kh.getHoten() %></span>
        </li>
        <li class="nav-item d-flex align-items-center">
          <i class="bi bi-box-arrow-right" style="color: white; margin-left: 5px;"></i>
          <a class="nav-link" href="logoutController">Đăng xuất</a>
        </li>
        <% } else { %>
        	<li class="nav-item d-flex align-items-center">
	          <i class="bi bi-person-add" style="color: white;"></i>
	          <a class="nav-link" href="signupController">Đăng ký</a>
	        </li>
	        <li class="nav-item d-flex align-items-center">
	          <i class="bi bi-person-circle" style="color: white; margin-left: 5px;"></i>
	          <a class="nav-link" href="loginController">Đăng nhập</a>
	        </li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>

<!--  
<% 
  String isInvalid = request.getParameter("isInvalid"); 
%>

<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="loginModalLabel" style="color: #006eb7; font-weight: bold;">LOGIN</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body" style="padding: 50px;">
        <div class="container">
          <div class="row">
            <div class="col-md-7 d-flex flex-column align-items-center">
              <img src="https://student.husc.edu.vn/Themes/Login/images/Logo-ko-nen.png" alt="logo-husc"
                class="img-fluid mb-4" style="width: 112px; height: 112px" />
              <img src="https://student.husc.edu.vn/Themes/Login/images/image1.png" alt="lab-images" class="img-fluid" />
            </div>

            <div class="col-md-5 bg-white p-4 border border-2 border-info">
              <form action="loginController" method="post">
                <div class="mb-3">
                  <label class="form-label">Tên đăng nhập:</label>
                  <input type="text" class="form-control" name="loginId" placeholder="Mã giảng viên/Email">
                </div>
                <div class="mb-3">
                  <label class="form-label">Mật khẩu:</label>
                  <input type="password" class="form-control" name="password" placeholder="Mật khẩu">
                </div>
                <% if ("true".equals(isInvalid)) { %>
                	<span class="text-danger">Thông tin đăng nhập không đúng!</span>
                <% } %>
                <button type="submit" name="btn-login" class="btn btn-primary w-100 py-2 my-2" 
                        style="background-color: #337ab7; border-color: #337ab7;">
                  Đăng nhập
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>-->
