<%@page import="CommonModal.Constants"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Thêm nguời dùng - HUSCZone</title>
  <%@ include file="layout/import.jsp" %>
</head>
<%
    String hoten = request.getParameter("txtHoten");
    String sdt = request.getParameter("txtSdt");
    String email = request.getParameter("txtLoginId");
    String gioitinh = request.getParameter("txtGioiTinh");
    String txtVaiTro = request.getParameter("txtVaiTro");
    Long vaiTro = (txtVaiTro != null && !txtVaiTro.isEmpty()) ? Long.parseLong(txtVaiTro) : 0L;
    boolean isInvalid = request.getAttribute("isInvalid") != null ? 
                        (boolean) request.getAttribute("isInvalid") : false;
    boolean isDuplicate = request.getAttribute("isDuplicate") != null ? 
                          (boolean) request.getAttribute("isDuplicate") : false;
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
                  <h4 class="card-title mb-4 text-center">Thêm mới người dùng</h4>
                  <form class="form-horizontal" action="../admin/edit-user" method="post">

                    <div class="form-group mb-3">
                      <label class="form-label">Tên người dùng <span class="text-danger">*</span></label>
                      <input type="text" class="form-control" name="txtHoten" value="<%= (hoten != null) ? hoten : "" %>">
                    </div>
                    
                    <div class="form-group mb-3">
                      <label class="form-label">Vai trò <span class="text-danger">*</span></label>
                      <select class="form-control" name="txtVaiTro">
                        <option value="" <%= (vaiTro == 0L) ? "selected" : "" %>>-- Chọn vai trò --</option>
                        <option value="<%=Constants.ROLE_ADMIN %>" <%= vaiTro == Constants.ROLE_ADMIN  ? "selected" : "" %>>ADMIN</option>
                        <option value="<%=Constants.ROLE_USER %>" <%= vaiTro == Constants.ROLE_USER  ? "selected" : "" %>>USER</option>
                      </select>
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Email <span class="text-danger">*</span></label>
                      <input type="email" class="form-control" name="txtLoginId" value="<%= (email != null) ? email : "" %>">
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Số điện thoại</label>
                      <input type="text" class="form-control" name="txtSdt" value="<%= (sdt != null) ? sdt : "" %>">
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Giới tính <span class="text-danger">*</span></label>
                      <select class="form-control" name="txtGioiTinh">
                        <option value="" <%= (gioitinh == null) ? "selected" : "" %>>-- Chọn giới tính --</option>
                        <option value="Nam" <%= "Nam".equals(gioitinh) ? "selected" : "" %>>Nam</option>
                        <option value="Nữ" <%= "Nữ".equals(gioitinh) ? "selected" : "" %>>Nữ</option>
                        <option value="Khác" <%= "Khác".equals(gioitinh) ? "selected" : "" %>>Khác</option>
                      </select>
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                      <input type="password" class="form-control" name="txtPassword">
                    </div>
                    
					<% if (isInvalid) { %>
                          <span class="text-danger">Vui lòng nhập đầy đủ thông tin bắt buộc!</span><br>
                      <% } %>

                      <% if (isDuplicate) { %>
                          <span class="text-danger">Email đã tồn tại trong hệ thống!</span><br>
                      <% } %>
                      
                    <div class="form-group text-center">
                      <a href="../admin/users" class="btn btn-secondary me-2">Hủy</a>
                      <button type="submit" name="btnAdd" value="add" class="btn btn-primary">Lưu</button>
                      
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
