<%@page import="CommonModal.Constants"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Sửa thông tin tài khoản - HUSCZone</title>
  <%@ include file="layout/import.jsp" %>
</head>
<% User user = (User)session.getAttribute("user"); %>
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
                  <form class="form-horizontal" action="../save-profile" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="userID" value="<%= user.getUserID()%>" />
                    <input type="hidden" name="editInAdminPage" value="true" />
                    <div class="form-group mb-3">
                      <label class="form-label">Tên người dùng <span class="text-danger">*</span></label>
                      <input type="text" class="form-control" name="txtHoten" value="<%= user.getName() %>" required>
                    </div>
                    
                    <div class="form-group mb-3">
                      <label class="form-label">Email</label>
                      <input type="email" class="form-control" value="<%= user.getEmail() %>" disabled>
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Số điện thoại</label>
                      <input type="text" class="form-control" name="txtSdt" value="<%= user.getPhone()!= null ? user.getPhone() : "" %>">
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Giới tính <span class="text-danger">*</span></label>
                      <select class="form-control" name="txtGioiTinh" required>
                        <option value="" <%= (user.getGender() == null) ? "selected" : "" %>>-- Chọn giới tính --</option>
                        <option value="Nam" <%= "Nam".equals(user.getGender()) ? "selected" : "" %>>Nam</option>
                        <option value="Nữ" <%= "Nữ".equals(user.getGender()) ? "selected" : "" %>>Nữ</option>
                        <option value="Khác" <%= "Khác".equals(user.getGender()) ? "selected" : "" %>>Khác</option>
                      </select>
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Ảnh đại diện</label>
                      <input type="file" class="form-control" name="fileAnh" accept="image/*" id="fileInput">
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Preview Ảnh</label>
                      <img id="imagePreview" src="<%= request.getContextPath() %><%= user.getAvatar() != null ? user.getAvatar() : "" %>" 
                           alt="Image Preview" style="max-height: 250px; display: <%= user.getAvatar() != null ? "block" : "none" %>;">
                    </div>
                      
                    <div class="form-group text-center">
                      <a href="../admin/details?profile=me" class="btn btn-secondary me-2">Hủy</a>
                      <button type="submit" class="btn btn-primary">Lưu</button>
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

<%@ include file="layout/script_preview_image.jsp" %>
</body>

</html>
