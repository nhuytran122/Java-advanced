<%@page import="CommonModal.Constants"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Sửa nguời dùng - HUSCZone</title>
  <%@ include file="layout/import.jsp" %>
</head>
<%
  User user = (User) request.getAttribute("user");
  boolean isInvalid = request.getAttribute("isInvalid") != null ? 
        (boolean) request.getAttribute("isInvalid") : false;
%>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layout/navbar.jsp" %>
      <%@ include file="layout/settings-panel.jsp" %>
      <%@ include file="layout/left_sidebar.jsp" %>
      
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row justify-content-center">
            <div class="col-md-10 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title mb-4 text-center">Chỉnh sửa người dùng</h4>
                  <form class="form-horizontal" action="../admin/edit-user" method="post">
					<input type="hidden" name="userID" value="<%= user.getUserID()%>" />
                    <div class="form-group mb-3">
                      <label class="form-label">Tên người dùng</span></label>
                      <input disabled type="text" class="form-control" value="<%= user.getName()  %>">
                    </div>
                    
                    <div class="form-group mb-3">
                      <label class="form-label">Vai trò <span class="text-danger">*</span></label>
                      <select class="form-control" name="txtVaiTro">
                        <option value="" <%= (user.getRoleID() == 0L) ? "selected" : "" %>>-- Chọn vai trò --</option>
                        <option value="<%=Constants.ROLE_ADMIN %>" <%= user.getRoleID() == Constants.ROLE_ADMIN  ? "selected" : "" %>>ADMIN</option>
                        <option value="<%=Constants.ROLE_USER %>" <%= user.getRoleID() == Constants.ROLE_USER  ? "selected" : "" %>>USER</option>
                      </select>
                      <% if (isInvalid) { %>
                          <span class="text-danger">Vui lòng lựa chọn vai trò!</span><br>
                      <% } %>
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Email</label>
                      <input disabled type="email" class="form-control" value="<%= user.getEmail() %>">
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Số điện thoại</label>
                      <input disabled type="text" class="form-control" value="<%= user.getPhone()!= null ? user.getPhone() : "" %>">
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Giới tính</label>
                      <input disabled type="text" class="form-control" value="<%= user.getGender() %>">
                    </div>
                    
                    <div class="form-group mb-3">
						<input type="checkbox" name="isUsing" value="true" <%= user.isIsUsing() ? "checked" : "" %>>
						<label class="form-check-label" for="isUsing">Tài khoản đang sử dụng?</label>
					  </div>
                      
                    <div class="form-group text-center">
                      <a href="../admin/users" class="btn btn-secondary me-2">Hủy</a>
                      <button type="submit" name="btnUpdate" value="update" class="btn btn-primary">Lưu</button>
                      
                    </div>

                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <%@ include file="layout/footer.jsp" %>
      </div>
    </div>   
  </div>
</body>

</html>
