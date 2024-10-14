<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="tc.jsp">Trang chủ</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="calculator.jsp">Máy tính</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Trang 1
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li><a class="dropdown-item" href="#">Trang 1-1</a></li>
            <li><a class="dropdown-item" href="#">Trang 1-2</a></li>
            <li><a class="dropdown-item" href="#">Trang 1-3</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Trang 2</a>
        </li>
      </ul>
      
      <ul class="navbar-nav ms-auto">
        <% if (session.getAttribute("userId") != null) { %>
        <li class="nav-item d-flex align-items-center">
          <i class="bi bi-cart4" style="color: white; "></i>
          <a class="nav-link" href="cart.jsp">Giỏ hàng</a>
        </li>
        <li class="nav-item d-flex align-items-center">
          <span class="nav-link">Xin chào, <%= session.getAttribute("userId") %></span>
        </li>
        <li class="nav-item d-flex align-items-center">
          <i class="bi bi-box-arrow-right" style="color: white; margin-left: 5px;"></i>
          <a class="nav-link" href="logout.jsp">Đăng xuất</a>
        </li>
        <% } else { %>
	        <li class="nav-item d-flex align-items-center">
	          <i class="bi bi-person-circle" style="color: white;"></i>
	          <a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#loginModal">Đăng nhập</a>
	        </li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>

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
              <form action="login.jsp" method="post">
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
</div>

