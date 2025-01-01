<%@page import="CommonModal.MethodCommon"%>
<%@page import="CommonModal.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>My Profile - HUSCZone</title>
  <%@ include file="layout/import.jsp" %>
</head>
<% User user = (User)session.getAttribute("user"); %>

<body>
  <div class="container-scroller">
    <%@ include file="layout/navbar.jsp" %>
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layout/settings-panel.jsp" %>
      <%@ include file="layout/left_sidebar.jsp" %>
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="card-title">My Profile</h4>
                  </div>

                  <div class="row">
                    <div class="col-md-4 mb-4 text-center">
                      <% if (user.getAvatar() != null && !user.getAvatar().isBlank()) { %>
                            <img src="<%= request.getContextPath() %><%= user.getAvatar() %>" alt="Avatar" class="img-fluid rounded-circle mb-3" alt="User Avatar" style="width: 150px; height: 150px;">
                        <% } else { %>
                            <img src="../images/default-avt.jpg" class="img-fluid rounded-circle mb-3" alt="User Avatar" style="width: 150px; height: 150px;">
                        <% } %>
                      <h5><%= user.getName() %></h5>
                    </div>

                    <div class="col-md-8">
                      <div class="row mb-3">
                        <div class="col-md-12">
                          	<p class="card-text"><strong>Email:</strong> <%= user.getEmail() %></p>
                          	<p class="card-text"><strong>Số điện thoại:</strong> <%= user.getPhone()%></p>
                          	<p class="card-text"><strong>Giới tính:</strong> <%= user.getGender() %></p>
                        	<p class="card-text"><strong>Vai trò:</strong> <%= user.getRoleID() == Constants.ROLE_ADMIN ? "Admin" : "User" %></p>
                        	<p class="card-text"><strong>Ngày tham gia:</strong> <%= MethodCommon.convertDateToString(user.getCreatedAt()) %></p>
                        </div>
                      </div>
					<form action="../admin/edit" method="post">
					  <div class="row">
					    <div class="col-md-6 mb-3">
					      <button type="submit"
					              name="btnUpdateProfile" 
					              value="btnUpdateProfile" 
					              class="btn btn-primary btn-sm w-100">
					        <i class="bi bi-pencil"></i> Chỉnh sửa thông tin
					      </button>
					    </div>
					    <div class="col-md-6">
					      <button type="submit" 
					              name="btnChangePW" 
					              value="btnChangePW" 
					              class="btn btn-warning btn-sm w-100">
					        <i class="bi bi-lock"></i> Đổi mật khẩu
					      </button>
					    </div>
					  </div>
					</form>

                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>

</html>
