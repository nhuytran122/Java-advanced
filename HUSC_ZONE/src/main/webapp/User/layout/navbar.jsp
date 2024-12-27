<%@page import="V_DetailsNotificationModal.DetailsNotification"%>
<%@page import="NotificationModal.Notification"%>
<%@page import="UserModal.User"%>
<%@page import="MaterialModal.Material"%>
<%@page import="CategoryModal.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    ArrayList<Category> listCates = (ArrayList<Category>)request.getAttribute("listCates");
    ArrayList<Material> listMates = (ArrayList<Material>)request.getAttribute("listMates");
    
    Long cateID = request.getParameter("cateID") != null ? 
    				Long.parseLong(request.getParameter("cateID")) : 0L;
    Long mateID = request.getParameter("mateID") != null ? 
    				Long.parseLong(request.getParameter("mateID")) : 0L;
    
    User user = (User)session.getAttribute("user");
    String searchKeyword = request.getParameter("txtSearch");
    
    ArrayList<DetailsNotification> listNoti = (ArrayList<DetailsNotification>)request.getAttribute("listNoti");
%>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg bg-primary-custom py-2">
    <div class="container-fluid mx-5">
        <a class="navbar-brand text-white fw-bold" href="../home">
            <img src="../images/husc-removebg-preview.png" alt="HUSCZone Logo" style="height: 40px; width: 130px;" class="d-inline-block align-top">
        </a>
        <div class="collapse navbar-collapse">
            <form class="d-flex mx-auto align-items-center"
            	action="../home" method="post">
                <select name="cateID" class="form-select form-select-sm me-2 py-2" style="margin-left: 150px;">
				    <option value="0" <%= (cateID == 0L ? "selected" : "") %>>-- Chọn ngành học --</option>
				    <% 
				        for(Category cate : listCates) {
				    %>
				    <option value="<%= cate.getCategoryID() %>" 
				    	<%= (cate.getCategoryID().equals(cateID) ? "selected" : "") %>
				    >
				        <%= cate.getCategoryName() %>
				    </option>
				    <% 
				        } 
				    %>
				</select>
				
				<select name="mateID" class="form-select form-select-sm me-2 py-2">
				    <option value="0" <%= (mateID == 0L ? "selected" : "") %>>-- Chọn dạng tài liệu --</option>
				    <% 
				        for(Material mate : listMates) {
				    %>
				    <option value="<%= mate.getMaterialID() %>" 
				    	<%= (mate.getMaterialID().equals(mateID) ? "selected" : "") %>
				    >
				        <%= mate.getMaterialName() %>
				    </option>
				    <% 
				        } 
				    %>
				</select>


                <input class="form-control form-control-sm me-2 py-2" 
                    style="width: 200px;"  
                    placeholder="Tìm kiếm tài liệu..." 
                    autofocus
                    name="txtSearch"
	                value="<%= searchKeyword != null 
	                	? searchKeyword : "" %>">

                <button type="submit" name="btn-search" value = "search" class="btn btn-primary-custom btn-sm py-2">
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
                            <p class="text-muted small"> <%= user.getEmail() %></p>
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
