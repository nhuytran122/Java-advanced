<%@page pageEncoding="UTF-8" %>
<nav class="sidebar sidebar-offcanvas" id="sidebar">
    <ul class="nav">
      <li class="nav-item">
        <a class="nav-link" href="../admin">
          <i class="icon-grid menu-icon"></i>
          <span class="menu-title">Dashboard</span>
        </a>
      </li>
      
      <li class="nav-item">
		  <a class="nav-link" data-toggle="collapse" data-target="#ui-basic" aria-controls="ui-basic">
		    <i class="bi bi-file-earmark-text menu-icon"></i>
		    <span class="menu-title">Tài liệu</span>
		    <i class="menu-arrow"></i>
		  </a>
		  <div class="collapse" id="ui-basic">
		    <ul class="nav flex-column sub-menu">
		      <li class="nav-item"> <a class="nav-link" href="../admin/docs">Tài liệu</a></li>
		      <li class="nav-item"> <a class="nav-link" href="../admin/categories">Ngành học</a></li>
		      <li class="nav-item"> <a class="nav-link" href="../admin/materials">Loại tài liệu</a></li>
		    </ul>
		  </div>
		</li>
		
      <li class="nav-item">
        <a class="nav-link" href="../admin/posts" aria-expanded="false">
          <i class="bi bi-file-earmark-post menu-icon"></i>
          <span class="menu-title">Bài đăng</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="../admin/users" aria-expanded="false">
          <i class="bi bi-person menu-icon"></i>
          <span class="menu-title">Nguời dùng</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="../admin/reports" aria-expanded="false" >
          <i class="bi bi-flag menu-icon"></i>
          <span class="menu-title">Báo cáo</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link" href="../admin/details?profile=me" aria-expanded="false" aria-controls="auth">
          <i class="bi bi-person-circle menu-icon"></i>
          <span class="menu-title">Tài khoản của tôi</span>
        </a>
      </li>
    </ul>
  </nav>