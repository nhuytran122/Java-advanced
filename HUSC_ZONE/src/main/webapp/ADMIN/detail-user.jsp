<%@page import="CommonModal.MethodCommon"%>
<%@page import="CommonModal.Constants"%>
<%@page import="UserModal.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Chi tiết nguời dùng - HUSCZone</title>
  <%@ include file="layout/import.jsp" %>
</head>

<body>
  <%
    User user = (User) request.getAttribute("user");
  %>
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
                  <h4 class="card-title">Chi tiết người dùng</h4>
                  <div class="row">
                    <div class="col-md-4 mb-4">
                    	<% if (user.getAvatar() != null && !user.getAvatar().isBlank()) { %>
                         <img src="<%= request.getContextPath() %><%= user.getAvatar() %>" class="img-fluid rounded" alt="Avatar">
	                    <% } else { %>
	                     <img src="../images/default-avt.jpg" class="img-fluid rounded" alt="Avatar">
	                    <% } %>
                    </div>

                    <div class="col-md-8">
                      <div class="row mb-3">
                        <div class="col-md-12">
                          <h5 class="card-title mb-3"><%= user.getName() %></h5>
                          <p class="card-text"><strong>ID:</strong> <%= user.getUserID() %></p>
                          <p class="card-text"><strong>Email:</strong> <%= user.getEmail() %></p>
                          <p class="card-text"><strong>Số điện thoại:</strong> <%= user.getPhone()%></p>
                          <p class="card-text"><strong>Giới tính:</strong> <%= user.getGender() %></p>
                          <p class="card-text"><strong>Trạng thái:</strong> <%= user.isIsUsing() ? "Đang hoạt động" : "Bị khóa" %></p>
                          <p class="card-text"><strong>Vai trò:</strong> <%= user.getRoleID() == Constants.ROLE_ADMIN ? "ADMIN" : "USER" %></p>
                          <p class="card-text"><strong>Ngày tạo:</strong> <%= MethodCommon.convertDateToString(user.getCreatedAt()) %></p>
                          <p class="card-text"><strong>Ngày cập nhật:</strong> <%= user.getUpdatedAt() != null ? MethodCommon.convertDateToString(user.getUpdatedAt()) : "" %></p>
                        </div>
                      </div>

                      <div class="row">
                        <div class="col-md-12">
                          <form method="post" action="../admin/edit">
                            <input type="hidden" name="userID" value="<%= user.getUserID() %>">
                            <button type="submit" name="btnUpdateUser" value="update" class="btn btn-warning btn-sm ms-2" title="Sửa">
                              <i class="bi bi-pencil"></i> Sửa
                            </button>
                            <button type="button" class="btn btn-danger btn-sm ms-2" data-bs-toggle="modal" data-bs-target="#deleteModal<%= user.getUserID() %>" title="Xóa">
                              <i class="bi bi-trash"></i> Xóa
                            </button>
                          </form>
                        </div>
                      </div>

                      <!-- Modal xác nhận xóa -->
                      <div class="modal fade" id="deleteModal<%= user.getUserID() %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa người dùng</h5>
                            </div>
                            <div class="modal-body">
                              Bạn có chắc chắn muốn xóa người dùng <b><%= user.getName() %></b> không?
                            </div>
                            <div class="modal-footer">
                              <form method="post" action="../admin/edit-user">
                                <input type="hidden" name="userID" value="<%= user.getUserID() %>">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                <button type="submit" name="btnDeleteUser" value="delete" class="btn btn-danger">Xóa</button>
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
        </div>
      </div>
    </div>
  </div>
</body>

</html>
