<%@page contentType="text/html" pageEncoding="UTF-8" %>
<nav class="sidebar sidebar-offcanvas" id="sidebar">
    <ul class="nav">
      <li class="nav-item">
        <a class="nav-link" href="adminController">
          <i class="icon-grid menu-icon"></i>
          <span class="menu-title">Dashboard</span>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="adminSachController" aria-expanded="false" aria-controls="ui-basic">
          <i class="bi bi-book menu-icon"></i>
          <span class="menu-title">Sách</span>
        </a>
        
      </li>
      <li class="nav-item">
        <a class="nav-link" href="adminLoaiController" aria-expanded="false" aria-controls="form-elements">
          <i class="bi bi-bookmarks menu-icon"></i>
          <span class="menu-title">Loại sách</span>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" data-toggle="collapse" href="#charts" aria-expanded="false" aria-controls="charts">
          <i class="bi bi-person menu-icon"></i>
          <span class="menu-title">Khách hàng</span>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" data-toggle="collapse" href="#tables" aria-expanded="false" aria-controls="tables">
          <i class="bi bi-receipt menu-icon"></i>
          <span class="menu-title">Đơn hàng</span>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" data-toggle="collapse" href="#auth" aria-expanded="false" aria-controls="auth">
          <i class="bi bi-person-circle menu-icon"></i>
          <span class="menu-title">Tài khoản của tôi</span>
        </a>
      </li>
    </ul>
  </nav>