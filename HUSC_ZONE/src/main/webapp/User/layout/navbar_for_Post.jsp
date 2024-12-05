<%@page import="UserModal.User"%>
<%@page import="MaterialModal.Material"%>
<%@page import="CategoryModal.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    User user = (User)session.getAttribute("user");
%>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg bg-primary-custom py-2">
    <div class="container-fluid mx-5">
        <a class="navbar-brand text-white fw-bold" href="../home">
            <img src="../images/husc-removebg-preview.png" alt="HUSCZone Logo" style="height: 40px; width: 130px;" class="d-inline-block align-top">
        </a>
        <div class="collapse navbar-collapse">
            <form class="d-flex mx-auto align-items-center"
				action="../status-post" method="post">
                <input class="form-control form-control-sm me-2 py-2" 
                    style="width: 400px; margin-left: 300px;"  
                    placeholder="Tìm kiếm bài đăng, người dùng..." 
                    autofocus
					name="txtSearch"
	                value="<%= request.getParameter("txtSearch") != null 
	                	? request.getParameter("txtSearch") : "" %>">

                <button type="submit" class="btn btn-primary-custom p-2">
                    <i class="bi bi-search"></i>
                </button>
            </form>
<% if (user != null) { %>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link text-white" href="../home">Tài liệu</a></li>
                <li class="nav-item"><a class="nav-link text-white" href="../status-post">Bài đăng</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" data-bs-toggle="dropdown" aria-expanded="false">
                        Thông báo <span class="badge bg-danger">3</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><h6 class="dropdown-header">Thông báo mới</h6></li>
                        <li><a class="dropdown-item" href="#">Bài chia sẻ đã được phê duyệt</a></li>
                        <li><a class="dropdown-item" href="#">Bình luận mới trên tài liệu</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><h6 class="dropdown-header">Thông báo cũ</h6></li>
                        <li><a class="dropdown-item" href="#">Tài liệu được xếp hạng cao</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white d-flex align-items-center" href="#" data-bs-toggle="dropdown" aria-expanded="false">
                        <% if (user.getAvatar() == null || user.getAvatar().isBlank()) { %>
						        <img src="../images/default-avt.jpg" alt="Default" class="rounded-circle me-2" style="width: 30px; height: 30px">
						<% } else { %>
						        <img src="<%= request.getContextPath() %><%= user.getAvatar() %>" alt="Avatar" class="rounded-circle me-2" style="width: 30px; height: 30px">
						<% } %>
                        <span>Hi, Nhu Y</span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li class="text-center p-3">
                            <% if (user.getAvatar() == null || user.getAvatar().isBlank()) { %>
						        <img src="../images/default-avt.jpg" style="width: 80px; height: 80px" alt="Default" class="rounded-circle mb-3">
						    <% } else { %>
						        <img src="<%= request.getContextPath() %><%= user.getAvatar() %>" alt="Avatar" class="rounded-circle mb-3" style="width: 80px; height: 80px">
						    <% } %>
                            <h6 class="fw-bold mb-0"><%= user.getName() %></h6>
                            <p class="text-muted small"> <%= user.getEmail() %></p>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="../my-profile">
                            <i class="bi bi-person me-2"></i>Trang cá nhân
                        </a></li>
                        <li><a class="dropdown-item text-danger" href="../logout">
                            <i class="bi bi-box-arrow-right me-2"></i>Đăng xuất
                        </a></li>
                    </ul>
                </li>
            </ul>
            <% } else { %>
			    <ul class="navbar-nav ms-auto d-flex align-items-center">
			        <li class="nav-item">
			            <a class="btn btn-primary btn-sm d-flex align-items-center px-3" href="../signup">
			                <i class="bi bi-person-plus-fill me-2"></i>Đăng ký
			            </a>
			        </li>
			        
			        <li class="nav-item me-2">
			            <a class="btn btn-outline-light btn-sm d-flex align-items-center px-3 mx-3" href="../login">
			                <i class="bi bi-box-arrow-in-right me-2"></i>Đăng nhập
			            </a>
			        </li>			        
			    </ul>
			<% } %>
        </div>
    </div>
</nav>
