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
          <span class="nav-link">Xin chào, <%= session.getAttribute("userId") %></span>
        </li>
        <li class="nav-item d-flex align-items-center">
          <i class="bi bi-box-arrow-right" style="color: white; margin-right: 5px; margin-left: 5px;"></i>
          <a class="nav-link" href="logout.jsp">Đăng xuất</a>
        </li>
        <% } else { %>
        <li class="nav-item d-flex align-items-center">
          <i class="bi bi-person-circle" style="color: white; margin-right: 5px;"></i>
          <a class="nav-link" href="login.jsp">Đăng nhập</a>
        </li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>
