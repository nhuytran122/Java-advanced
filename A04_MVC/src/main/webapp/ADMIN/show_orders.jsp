<%@page import="Controller.Chung"%>
<%@page import="hoadonmodal.hoadon"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="sachmodal.sach"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>BOOKSTORE - Quản lý đơn hàng</title>

  <link rel="stylesheet" href="ADMIN/vendors/feather/feather.css">
  <link rel="stylesheet" href="ADMIN/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="ADMIN/vendors/css/vendor.bundle.base.css">
  <link rel="stylesheet" href="ADMIN/css/vertical-layout-light/style.css">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <link rel="shortcut icon" href="ADMIN/images/favicon.png" />
</head>

<body>
  <%
  	ArrayList<hoadon> ds = (ArrayList<hoadon>) request.getAttribute("ds");
    int pageCount = (Integer) request.getAttribute("pageCount");
    int currentPage = (Integer) request.getAttribute("currentPage");
    String searchKeyword = request.getParameter("txtSearch");
    
    NumberFormat nf = NumberFormat.getInstance();
    nf.setGroupingUsed(true);
  %>
  <div class="container-scroller">
    <%@ include file="layoutAdmin/navbar.jsp" %>
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layoutAdmin/settings-panel.jsp" %>
      <%@ include file="layoutAdmin/left_sidebar.jsp" %>
      <div class="main-panel">
        <ul class="navbar-nav mr-lg-2 my-4" style="display: flex; justify-content: center; width: 100%;">
          <li class="nav-item nav-search d-none d-lg-block" style="display: flex; align-items: center;">
            <form action="adminOrderController" method="get" class="d-flex" style="width: 100%; justify-content: center; align-items: center;">
              <input type="text" class="form-control form-control-sm me-2" id="navbar-search-input" placeholder="Tìm kiếm đơn hàng theo tên khách hàng" name="txtSearch" aria-label="search"
                     value="<%= searchKeyword != null ? searchKeyword : "" %>"
                     style="width: 400px; font-size: 14px; margin-right: 10px;">
              <button type="submit" class="btn btn-primary btn-sm p-2">
                <i class="bi bi-search"></i>
              </button>
            </form>
          </li>
        </ul>

        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
              <div class="card position-relative">
                <div class="card-body">
                  <div class="d-flex justify-content-between align-items-center mb-4">
                    <h4 class="card-title">Danh sách đơn hàng</h4>
                  </div>

                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead class="table-light">
                        <tr>
                          <th>Khách hàng</th>
                          <th>Tổng số lượng</th>
                          <th>Tổng thành tiền</th>
                          <th>Ngày mua</th>
                          <th>Tình trạng</th>
                          <th>Thao tác</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% 
                          int n = ds.size();
                          if (n == 0) {
                        %>
                            <tr>
                              <td colspan="7" class="text-center text-danger">Không tìm thấy đơn hàng nào.</td>
                            </tr>
                        <% 
                          } else { 
                            for (int i = 0; i < n; i++) {
                              hoadon s = ds.get(i);
                        %>
                        <tr>
                          <td>
                            <div style="max-width: 200px; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.5;">
                              <%= s.getHoten() %>
                            </div>
                          </td>
                          <td>
                              <%= s.getTongSoLuong() %>
                          </td>
                          <td><%= nf.format(s.getThanhTien()) %>đ</td>
                          <td><%= Chung.convertDateToString(s.getNgayMua()) %></td>
                          <td><%= s.isDamua() ? "Đã thanh toán" : "Đang chờ" %></td>
                          <td>
                            <div class="btn-group" role="group">
                              <form method="post" action="adminUpdateHDController">
                                <input type="hidden" name="idHD" value="<%= s.getMaHoaDon() %>">
                                <button type="submit" name="btnDetailHD" class="btn btn-success btn-sm" title="Xem chi tiết">
                                  <i class="bi bi-eye"></i>
                                </button>
                                <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal<%= s.getMaHoaDon() %>" title="Xóa">
                                  <i class="bi bi-trash"></i>
                                </button>
                              </form>
                            </div>
                          </td>
                        </tr>

                        <!-- Modal xác nhận xóa thông thường -->
                        <div class="modal fade" id="deleteModal<%= s.getMaHoaDon() %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa hóa đơn</h5>
                              </div>
                              <div class="modal-body">
                                Bạn có chắc chắn muốn xóa hóa đơn này không?
                              </div>
                              <div class="modal-footer">
                                <form method="post" action="adminUpdateHDController">
                                  <input type="hidden" name="idHD" value="<%= s.getMaHoaDon() %>">
                                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                  <button type="submit" name="btnDeleteHD" value="<%= s.getMaHoaDon() %>" class="btn btn-danger">Xóa</button>
                                </form>
                              </div>
                            </div>
                          </div>
                        </div>
                        <% 
                            }
                          }
                        %>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <% if (n > 0) { %>
          <div class="text-center">
            <nav aria-label="Page navigation example">
              <ul class="pagination justify-content-center">
                <li class="page-item <%= currentPage > 1 ? "" : "disabled" %>">
                  <a class="page-link" href="<%= currentPage > 1 ? "adminOrderController?page=" + (currentPage - 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>

                <% for (int p = 1; p <= pageCount; p++) { %>
                  <li class="page-item <%= p == currentPage ? "active" : "" %>">
                    <a class="page-link" href="adminOrderController?page=<%= p %><%= searchKeyword != null ? "&txtSearch=" + searchKeyword : "" %>">
                      <%= p %>
                    </a>
                  </li>
                <% } %>

                <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
                  <a class="page-link" href="<%= currentPage < pageCount ? "adminOrderController?page=" + (currentPage + 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                  </a>
                </li>
              </ul>
            </nav>
          </div>
          <% } %>
        </div>
      </div>
    </div>
  </div>
</body>

  <script src="ADMIN/vendors/js/vendor.bundle.base.js"></script>
  <script src="ADMIN/js/off-canvas.js"></script>
  <script src="ADMIN/js/hoverable-collapse.js"></script>
  <script src="ADMIN/js/template.js"></script>
  <script src="ADMIN/js/settings.js"></script>
</html>
