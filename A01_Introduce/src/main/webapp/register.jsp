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
      <div class="border">
        <div class="p-3 bg-primary text-white">
          <h4>ĐĂNG KÝ</h4>
        </div>

        <div class="row px-4 pt-4">
          <div class="col-md-6">
            <ul class="mb-1">
              <li>Đăng ký nhanh qua Facebook</li>
            </ul>

            <ul class="list-unstyled ps-3">
              <li>
                <button class="btn login-facebook p-2 px-5 my-2">
                  <i class="bi bi-facebook"></i> Đăng nhập bằng Facebook
                </button>
              </li>
              <li>
                <a href="#">Đăng ký mới bằng tài khoản TaiLieu.VN</a>
              </li>
            </ul>
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
          <ul class="list-unstyled ps-4">
            <li>
              Bạn cần kích hoạt tài khoản qua Email sau khi đăng ký để nhận ngay
              50 ePoints thưởng từ TaiLieu.VN
            </li>
          </ul>
        </div>

        <div class="container">
          <div class="row px-4 pt-2">
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
              </div>

              <div class="row">
                <div class="col-md-6 mb-3">
                  <input
                    type="password"
                    class="form-control"
                    id="password"
                    placeholder="Nhập mật khẩu"
                  />
                </div>

                <div class="col-md-6 mb-3">
                  <div class="col-md-4">
                    <select class="form-select" id="gender">
                      <option selected>Giới Tính</option>
                      <option value="male">Nam</option>
                      <option value="female">Nữ</option>
                      <option value="other">Khác</option>
                    </select>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3">
                  <input
                    type="password"
                    class="form-control"
                    id="confirm-password"
                    placeholder="Nhập lại mật khẩu"
                  />
                </div>

                <div class="col-md-6 mb-3">
                  <div class="row">
                    <div class="col-md-4">
                      <select class="form-select" id="day">
                        <option selected>Ngày sinh</option>
                        <% for (int i = 1; i <= 31; i++) { %>
                        <option value="<%= i %>"><%= i %></option>
                        <% } %>
                      </select>
                    </div>

                    <div class="col-md-4">
                      <select class="form-select" id="month">
                        <option selected>Tháng sinh</option>
                        <% for (int i = 1; i <= 12; i++) { %>
                        <option value="<%= i %>"><%= i %></option>
                        <% } %>
                      </select>
                    </div>

                    <div class="col-md-4">
                      <select class="form-select" id="year">
                        <option value="">Năm sinh</option>
                        <% int currentYear =
                        java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                        for (int i = currentYear - 100; i <= currentYear - 10;
                        i++) { %>
                        <option value="<%= i %>"><%= i %></option>
                        <% } %>
                      </select>
                    </div>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3">
                  <input
                    type="email"
                    class="form-control"
                    id="email"
                    placeholder="Nhập địa chỉ email"
                  />
                </div>

                <div class="col-md-6 mb-3">
                  <div class="col-md-4">
                    <select class="form-select" id="province">
                      <option selected>Tỉnh/TP</option>
                      <option value="Hue">Huế</option>
                      <!-- Thêm các tỉnh/thành khác tại đây -->
                    </select>
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3">
                  <input
                    type="email"
                    class="form-control"
                    id="email-confirm"
                    placeholder="Nhập lại địa chỉ email"
                  />
                </div>
              </div>

              <div class="d-flex align-items-center">
                <button type="submit" class="btn btn-primary">Đăng ký</button>
                <input
                  class="form-check-input ms-2"
                  type="checkbox"
                  id="terms"
                  required
                />
                <label class="form-check-label ms-2" for="terms">
                  Tôi đồng ý với các Chính Sách và Thỏa Thuận Sử Dụng của
                  TaiLieu.VN
                </label>
              </div>
              <hr />
            </form>

            <div class="row pt-2">
              <p><a href="#">Bạn đã có tài khoản TaiLieu.VN? Đăng nhập</a></p>
              <div class="col-md-4 border-end">
                <p>Thưởng 50 ePoints khi đăng ký mới</p>
                <p>Tặng 5 ePoints cho mỗi lượt chia sẻ Facebook</p>
              </div>
              <div class="col-md-4 border-end">
                <p>Xem và Tải trên 1 Triệu Tài Liệu miễn phí</p>
                <p>Kết nối với <a href="#">TaiLieu.VN</a></p>
              </div>
              <div class="col-md-4">
                <p>
                  Kết bạn với <a href="#">TaiLieu.VN</a> trên
                  <a href="#">Facebook</a>
                </p>
                <p>
                  Nhận link download miễn phí <strong>100</strong> tài liệu
                  <strong>HOT</strong> mỗi ngày
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
