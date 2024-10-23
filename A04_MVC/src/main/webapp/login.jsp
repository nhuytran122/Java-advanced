<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
  String txtLoginId = (String)request.getAttribute("loginId");
  String txtPassword = (String)request.getAttribute("password");
  String btnLogin = (String)request.getAttribute("btn-login");
  boolean isInvalid = (boolean)request.getAttribute("isInvalid");

  // lay ve un va pass
  // kiem tra chay lan dau
  // neu un = abc va pass=123 thi:
    // tao ra 1 session de luu un 
    // mo tc.jsp
    
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  </head>

  <style>
    .fg-color {
      color: #006eb7;
    }
    .bg-color {
      background-color: #337ab7;
    }
  </style>

  <body style="background-color: #63b0e3">
    <div class="container mt-5 w-75 p-5">
      <div class="row">
        <div class="col-md-7 d-flex flex-column align-items-center">
          <img
            src="https://student.husc.edu.vn/Themes/Login/images/Logo-ko-nen.png"
            alt="logo-husc"
            class="img-fluid mb-4"
            style="width: 112px; height: 112px"
          />
          <img
            src="https://student.husc.edu.vn/Themes/Login/images/image1.png"
            alt="lab-images"
            class="img-fluid"
          />
        </div>

        <div class="col-md-5 bg-white p-4 border border-2 border-info">
          <h3 class="mb-4 fg-color">GIẢNG VIÊN</h3>
          <form action="loginController" method="post">
            <div class="mb-3">
              <label class="form-label">Tên đăng nhập:</label>
              <input
                type="text"
                class="form-control"
                name="loginId"
                placeholder="Mã giảng viên/Email"
                value="<%= (txtLoginId != null) ? txtLoginId : "" %>"
              />
            </div>
            <div class="mb-3">
              <label class="form-label">Mật khẩu:</label>
              <input
                type="password"
                class="form-control"
                name="password"
                placeholder="Mật khẩu"
                value="<%= (txtPassword != null) ? txtPassword : "" %>"
              />
            </div>
            <% if (isInvalid) { %>
              <span class="text-danger">Thông tin đăng nhập không đúng!</span>
            <% } %>
            <button
              type="submit"
              name="btn-login"
              class="btn btn-primary w-100 bg-color py-2 my-2"
            >
              Đăng nhập
            </button>
          </form>
        </div>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>