<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Đăng Ký</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
      rel="stylesheet"
    />
    <style>
      strong {
        color: red;
      }

      a {
        text-decoration: none;
      }

      /* .register-box {
        width: 70%;
      } */

      .login-facebook {
        background-color: #4267b2;
        color: white;
      }

      .border-dashed {
        border: 1px dashed #ccc;
        padding: 10px;
      }
    </style>
  </head>
  <body>
    <div class="container mt-5 w-75">
      <div class="register-box border">
        <div class="p-3 bg-primary text-white">
          <h4 class>ĐĂNG KÝ</h4>
        </div>

        <div class="row p-4">
          <div class="col-md-6">
            <ul>
              <li>Đăng ký nhanh qua Facebook</li>
              <li style="list-style-type: none">
                <button class="btn login-facebook p-2 px-5 my-2">
                  <i class="bi bi-facebook"></i> Đăng nhập bằng Facebook
                </button>
              </li>
            </ul>

            <p>
              <a href="#">Đăng ký mới bằng tài khoản TaiLieu.VN</a>
            </p>
          </div>
          <div class="col-md-6">
            <div class="border-dashed">
              <ul>
                <li>
                  Thưởng ngay <strong>50</strong> ePoints khi đăng ký mới &
                  <strong>5</strong> ePoints cho mỗi lượt chia sẻ Facebook
                </li>
                <li>
                  Tích lũy nhiều ePoints khi Upload tài liệu <a href="/">>></a>
                </li>
              </ul>
            </div>
          </div>

          <form>
            <div class="row">
              <div class="col-md-6 mb-3">
                <input
                  type="text"
                  class="form-control"
                  id="username"
                  placeholder="Nhập Username"
                />
              </div>
              <div class="col-md-6 mb-3">
                <input
                  type="text"
                  class="form-control"
                  id="name"
                  placeholder="Nhập Họ và Tên"
                />
              </div>
              <div class="col-md-6 mb-3">
                <input
                  type="password"
                  class="form-control"
                  id="password"
                  placeholder="Nhập mật khẩu"
                />
              </div>
              <div class="col-md-6 mb-3">
                <input
                  type="password"
                  class="form-control"
                  id="confirm-password"
                  placeholder="Nhập lại mật khẩu"
                />
              </div>
              <div class="col-md-6 mb-3">
                <input
                  type="email"
                  class="form-control"
                  id="email"
                  placeholder="Nhập địa chỉ Email"
                />
              </div>
              <div class="col-md-6 mb-3">
                <select class="form-select" id="gender">
                  <option selected>Giới Tính</option>
                  <option value="male">Nam</option>
                  <option value="female">Nữ</option>
                  <option value="other">Khác</option>
                </select>
              </div>
              <div class="col-md-4 mb-3">
                <select class="form-select" id="day">
                  <option selected>Ngày sinh</option>
                  <% for (int i = 1; i <= 31; i++) { %>
                  <option value="<%= i %>"><%= i %></option>
                  <% } %>
                  <!-- Thêm các tùy chọn cho ngày -->
                </select>
              </div>
              <div class="col-md-4 mb-3">
                <select class="form-select" id="month">
                  <option selected>Tháng sinh</option>
                  <% for (int i = 1; i <= 12; i++) { %>
                  <option value="<%= i %>"><%= i %></option>
                  <% } %>
                  <!-- Thêm các tùy chọn cho ngày -->
                </select>
              </div>
              <div class="col-md-4 mb-3">
                <select class="form-select" id="year">
                  <option value="">Năm sinh</option>
                  <% int currentYear =
                  java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                  for (int i = currentYear - 100; i <= currentYear-10; i++) { %>
                  <option value="<%= i %>"><%= i %></option>
                  <% } %>
                </select>
              </div>
              <div class="col-md-6 mb-3">
                <select class="form-select" id="city">
                  <option selected>Tỉnh/TP</option>
                  <!-- Thêm các tùy chọn cho tỉnh/thành phố -->
                </select>
              </div>
            </div>
            <button type="submit" class="btn btn-primary">Đăng ký</button>
          </form>

          <hr />

          <div class="row">
            <div class="col-md-4">
              <p>Thưởng 50 ePoints khi đăng ký mới</p>
              <p>Tặng 5 ePoints cho mỗi lượt chia sẻ Facebook</p>
            </div>
            <div class="col-md-4">
              <p>Xem và Tải trên 1 Triệu Tài Liệu miễn phí</p>
              <p>Kết nối với <a href="#">TaiLieu.VN</a></p>
            </div>
            <div class="col-md-4">
              <p>
                Kết bạn với <a href="#">TaiLieu.VN</a> trên
                <a href="#">Facebook</a>
              </p>
              <p>Nhận link download miễn phí 100 tài liệu HOT mỗi ngày</p>
            </div>
          </div>
        </div>

        <!-- Form đăng ký -->
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
