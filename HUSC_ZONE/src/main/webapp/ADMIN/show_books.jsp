<%@page import="java.text.NumberFormat"%>
<%@page import="sachmodal.sach"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>BOOKSTORE - Quản lý sách</title>

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
    ArrayList<sach> ds = (ArrayList<sach>) request.getAttribute("ds");
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
            <form action="adminSachController" method="get" class="d-flex" style="width: 100%; justify-content: center; align-items: center;">
              <input type="text" class="form-control form-control-sm me-2" id="navbar-search-input" placeholder="Tìm kiếm sách..." name="txtSearch" aria-label="search"
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
                    <h4 class="card-title">Danh sách Sách</h4>
                    <form method="post" action="adminUpdateSachController">
                      <button type="submit" name="btnAddSach" value="btnAddSach" class="btn btn-primary btn-sm">
                        <i class="bi bi-plus-circle"></i> Thêm mới sách
                      </button>
                    </form>
                  </div>

                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead class="table-light">
                        <tr>
                          <th style="width: 150px;">Ảnh</th>
                          <th style="max-width: 200px;">Tên sách</th>
                          <th>Số lượng</th>
                          <th>Giá</th>
                          <!--  <th>Loại sách</th>-->
                          <th>Tác giả</th>
                          <th>Thao tác</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% 
                          int n = ds.size();
                          if (n == 0) {
                        %>
                            <tr>
                              <td colspan="7" class="text-center text-danger">Không tìm thấy sách nào.</td>
                            </tr>
                        <% 
                          } else { 
                            for (int i = 0; i < n; i++) {
                              sach s = ds.get(i);
                        %>
                        <tr>
                          <td>
                            <img src="<%= s.getAnh() %>" alt="Ảnh sách" class="img-fluid rounded" style="width: 150px; height: 150px; object-fit: cover;">
                          </td>
                          <td>
                            <div style="max-width: 200px; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.5;">
                              <%= s.getTensach() %>
                            </div>
                          </td>
                          <td><%= s.getSoluong() %></td>
                          <td><%= nf.format(s.getGia()) %></td>
                          <td>
	                          <div style="max-width: 200px; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.5;">
	                              <%= s.getTacgia() %>
	                           </div>
                          <td>
                            <div class="btn-group" role="group">
                              <form method="post" action="adminUpdateSachController">
                                <input type="hidden" name="idSach" value="<%= s.getMasach() %>">
                                <button type="submit" name="btnDetailSach" class="btn btn-success btn-sm" title="Xem chi tiết">
                                  <i class="bi bi-eye"></i>
                                </button>
                                <button type="submit" name="btnUpdateSach" value="<%= s.getMasach() %>" class="btn btn-warning btn-sm" title="Sửa">
                                  <i class="bi bi-pencil"></i>
                                </button>
                                <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal<%= s.getMasach() %>" title="Xóa">
                                  <i class="bi bi-trash"></i>
                                </button>
                              </form>
                            </div>
                          </td>
                        </tr>

                        <!-- Modal xác nhận xóa thông thường -->
                        <div class="modal fade" id="deleteModal<%= s.getMasach() %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title text-danger" id="deleteModalLabel<%= s.getMasach() %>">
                                	<i class="bi bi-exclamation-triangle-fill me-2"></i>
                                	Xác nhận xóa Sách
                                </h5>
                              </div>
                              <div class="modal-body">
                                Bạn có chắc chắn muốn xóa sách <b class="text-primary"><%= s.getTensach() %></b> không?
                                
                                <div class="alert alert-warning d-flex align-items-center mt-4" role="alert">
						          Lưu ý: Xóa sách này sẽ đồng thời xóa các chi tiết hóa đơn liên quan.
						        </div>
                              </div>
                              <div class="modal-footer">
                                <form method="post" action="adminUpdateSachController">
                                  <input type="hidden" name="idSach" value="<%= s.getMasach() %>">
                                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                  <button type="submit" name="btnDeleteSach" value="<%= s.getMasach() %>" class="btn btn-danger">Xóa</button>
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
                  <a class="page-link" href="<%= currentPage > 1 ? "adminSachController?page=" + (currentPage - 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>

                <% for (int p = 1; p <= pageCount; p++) { %>
                  <li class="page-item <%= p == currentPage ? "active" : "" %>">
                    <a class="page-link" href="adminSachController?page=<%= p %><%= searchKeyword != null ? "&txtSearch=" + searchKeyword : "" %>">
                      <%= p %>
                    </a>
                  </li>
                <% } %>

                <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
                  <a class="page-link" href="<%= currentPage < pageCount ? "adminSachController?page=" + (currentPage + 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Next">
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
