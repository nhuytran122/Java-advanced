<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="CommonModal.Constants"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Đổi mật khẩu - HUSCZone</title>
  <%@ include file="layout/import.jsp" %>
</head>
<%
  String message = (String) request.getAttribute("message");
  String messageType = (String) request.getAttribute("messageType");
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
                  <h4 class="card-title mb-4 text-center">Đổi mật khẩu</h4>
                  <form class="form-horizontal" action="../change-password" method="post">
                    <input type="hidden" name="changePwWithAd" value="true" />
                    <div class="form-group mb-3">
                      <label class="form-label">Mật khẩu hiện tại <span class="text-danger">*</span></label>
                      <input type="password" class="form-control" name="txtCurrPw" required>
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Mật khẩu mới <span class="text-danger">*</span></label>
                      <input type="password" class="form-control" name="txtNewPw" required>
                    </div>

                    <div class="form-group mb-3">
                      <label class="form-label">Xác nhận mật khẩu mới <span class="text-danger">*</span></label>
                      <input type="password" class="form-control" name="txtConfNewPw" required>
                    </div>

                    <% if (message != null && messageType != null) { %>
                      <div class="alert alert-<%= messageType %> text-center">
                        <%= message %>
                      </div>
                    <% } %>
					<% if (request.getAttribute("isPasswordChanged") == null) { %>
	                    <div class="form-group text-center">
	                      <a href="../admin/details?profile=me" class="btn btn-secondary me-2">Hủy</a>
	                      <button type="submit" class="btn btn-primary">Đổi mật khẩu</button>
	                    </div>
                    <%} %>
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
