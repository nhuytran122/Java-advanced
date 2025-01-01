<%@page import="V_DetailsNotificationModal.DetailsNotification"%>
<%@page import="CommonModal.Constants"%>
<%@page import="UserModal.User"%>
<%@page import="MaterialModal.Material"%>
<%@page import="CategoryModal.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    User user = (User)session.getAttribute("user");
	Long typeSearchID = request.getParameter("typeSearchID") != null ? Long.parseLong(request.getParameter("typeSearchID")) : 0L;
	String searchKeyword = request.getParameter("txtSearch");
	ArrayList<DetailsNotification> listNoti = (ArrayList<DetailsNotification>)request.getAttribute("listNoti");
%>
<nav class="navbar navbar-expand-lg bg-primary-custom py-2">
    <div class="container-fluid mx-5">
        <a class="navbar-brand text-white fw-bold" href="../home">
            <img src="../images/husc-removebg-preview.png" alt="HUSCZone Logo" style="height: 40px; width: 130px;" class="d-inline-block align-top">
        </a>
        <div class="collapse navbar-collapse">
            <form class="d-flex mx-auto align-items-center"
                action="../status-post" method="post">
                <select name="typeSearchID" class="form-select form-select-sm py-2 me-3" style="margin-left: 220px; width: 200px;">
                    <option value="<%= Constants.SEARCH_POST %>" 
                        <%= typeSearchID == Constants.SEARCH_POST ? "selected" : "" %>>Bài đăng</option>
                    <option value="<%= Constants.SEARCH_USER %>" 
                        <%= typeSearchID == Constants.SEARCH_USER ? "selected" : "" %>>Người dùng</option>
                </select>

                <input class="form-control form-control-sm me-2 py-2" 
                    style="width: 300px;"  
                    placeholder="Tìm kiếm bài đăng, người dùng..." 
                    autofocus
                    name="txtSearch"
                    value="<%= searchKeyword != null 
                        ? searchKeyword : "" %>">

                <button type="submit" name="btn-search" value="search" class="btn btn-primary-custom p-2">
                    <i class="bi bi-search"></i>
                </button>
            </form>
<% if (user != null) { %>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link text-white" href="../home">Tài liệu</a></li>
                <li class="nav-item"><a class="nav-link text-white" href="../status-post">Bài đăng</a></li>
                <li class="nav-item dropdown">
				    <a class="nav-link dropdown-toggle text-white" href="#" data-bs-toggle="dropdown" aria-expanded="false">
				        Thông báo
				    </a>
				    <ul class="dropdown-menu" style="max-height: 300px; overflow-y: auto; width: 300px;">
				        <% if (listNoti != null && !listNoti.isEmpty()) { %>
				            <% for (DetailsNotification noti : listNoti) { %>
				                <li class="p-2">
				                    <a class="dropdown-item d-flex align-items-start" 
				                       href="../details?postID=<%= noti.getPostID() %>" 
				                       style="white-space: normal; word-wrap: break-word; word-break: break-word; overflow-wrap: break-word;">
				                        <i class="bi bi-bell-fill me-2 text-primary"></i>
				                        <span style="line-height: 1.5;"><%= noti.getNotificationMessage() %></span>
				                    </a>
				                </li>
				            <% } %>
				        <% } else { %>
				            <li class="text-center p-3">
				                <i class="bi bi-info-circle text-muted" style="font-size: 1.5rem;"></i>
				                <p class="mb-0 text-muted">Bạn không có thông báo nào</p>
				            </li>
				        <% } %>
				    </ul>
				</li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white d-flex align-items-center" href="#" data-bs-toggle="dropdown" aria-expanded="false">
                        <% if (user.getAvatar() != null && !user.getAvatar().isBlank()) { %>
                            <img src="<%= request.getContextPath() %><%= user.getAvatar() %>" alt="Avatar" class="rounded-circle me-2" width="30" height="30">
                        <% } else { %>
                            <img src="../images/default-avt.jpg" width="30" height="30" alt="Default" class="rounded-circle me-2">
                        <% } %>
                        <span>Hi, <%= user.getName() %></span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li class="text-center p-3">
                            <% if (user.getAvatar() != null && !user.getAvatar().isBlank()) { %>
                                <img src="<%= request.getContextPath() %><%= user.getAvatar() %>" alt="Avatar" class="rounded-circle me-2" width="80" height="80">
                            <% } else { %>
                                <img src="../images/default-avt.jpg" width="80" height="80" alt="Default" class="rounded-circle me-2">
                            <% } %>
                            <h6 class="fw-bold mb-0 mt-3"><%= user.getName() %></h6>
                            <p class="text-muted small"><%= user.getEmail() %></p>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="../user-profile">
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
