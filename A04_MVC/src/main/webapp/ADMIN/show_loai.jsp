<%@page import="loaimodal.loai"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>BOOKSTORE - Quản lý loại sách</title>

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
    ArrayList<loai> ds = (ArrayList<loai>) request.getAttribute("ds");
    int pageCount = (Integer) request.getAttribute("pageCount");
    int currentPage = (Integer) request.getAttribute("currentPage");
    String searchKeyword = request.getParameter("txtSearch");
  %>
  <div class="container-scroller">
    <%@ include file="layoutAdmin/navbar.jsp" %>
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layoutAdmin/settings-panel.jsp" %>
      <%@ include file="layoutAdmin/left_sidebar.jsp" %>
      <div class="main-panel">
        <ul class="navbar-nav mr-lg-2 my-4" style="display: flex; justify-content: center; width: 100%;">
          <li class="nav-item nav-search d-none d-lg-block" style="display: flex; align-items: center;">
            <form action="adminLoaiController" method="get" class="d-flex" style="width: 100%; justify-content: center; align-items: center;">
              <input type="text" class="form-control form-control-sm me-2" id="navbar-search-input" placeholder="Tìm kiếm loại sách..." name="txtSearch" aria-label="search"
                     value="<%= request.getParameter("txtSearch") != null ? request.getParameter("txtSearch") : "" %>"
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
                    <h4 class="card-title">Danh sách Loại Sách</h4>
                    <form method="post" action="adminUpdateLoaiController">
                      <button type="submit" name="btnAddLoai" value="btnAddLoai" class="btn btn-primary btn-sm">
                        <i class="bi bi-plus-circle"></i> Thêm mới loại sách
                      </button>
                    </form>
                  </div>

                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead class="table-light">
                        <tr>
                          <th>Mã loại sách</th>
                          <th>Tên loại sách</th>
                          <th>Thao tác</th>
                        </tr>
                      </thead>
                      <tbody>
					  <% 
					    int n = ds.size();
					    if (n == 0) {
					  %>
					      <tr>
					        <td colspan="3" class="text-center text-danger">Không tìm thấy loại sách nào.</td>
					      </tr>
					  <% 
					    } else { 
					      for (int i = 0; i < n; i++) {
					        loai l = ds.get(i);
					        String safeId = l.getMaloai().replace(" ", "-"); // Thay khoảng trắng bằng dấu gạch ngang
					  %>
					  <tr>
					    <td><%= l.getMaloai() %></td>
					    <td><%= l.getTenloai() %></td>
					    <td>
					      <div class="btn-group" role="group">
					        <form method="post" action="adminUpdateLoaiController">
					          <input type="hidden" name="idLoai" value="<%= l.getMaloai() %>">
					          <button type="submit" name="btnDetailLoai" class="btn btn-success btn-sm" title="Xem chi tiết">
					            <i class="bi bi-eye"></i>
					          </button>
					          <button type="submit" name="btnUpdateLoai" value="<%= l.getMaloai() %>" class="btn btn-warning btn-sm" title="Sửa">
					            <i class="bi bi-pencil"></i>
					          </button>
					          <button type="button" 
					                  class="btn btn-danger btn-sm" 
					                  data-bs-toggle="modal" 
					                  data-bs-target="#deleteModal<%= safeId %>" 
					                  title="Xóa">
					            <i class="bi bi-trash"></i>
					          </button>
					        </form>
					      </div>
					    </td>
					  </tr>
					
					  <!-- Modal xác nhận xóa -->
					  <div class="modal fade" id="deleteModal<%= safeId %>" tabindex="-1" aria-labelledby="deleteModalLabel<%= safeId %>" aria-hidden="true">
					    <div class="modal-dialog">
					      <div class="modal-content">
					        <div class="modal-header">
					          <h5 class="modal-title" id="deleteModalLabel<%= safeId %>">Xác nhận xóa loại sách</h5>
					          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					        </div>
					        <div class="modal-body">
					          Bạn có chắc chắn muốn xóa loại sách <b><%= l.getTenloai() %></b> không?
					        </div>
					        <div class="modal-footer">
					          <form method="post" action="adminUpdateLoaiController">
					            <input type="hidden" name="idLoai" value="<%= l.getMaloai() %>">
					            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
					            <button type="submit" name="btnDeleteLoai" value="<%= l.getMaloai() %>" class="btn btn-danger">Xóa</button>
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
                  <a class="page-link" href="<%= currentPage > 1 ? "adminLoaiController?page=" + (currentPage - 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>

                <% for (int p = 1; p <= pageCount; p++) { %>
                  <li class="page-item <%= p == currentPage ? "active" : "" %>">
                    <a class="page-link" href="adminLoaiController?page=<%= p %><%= searchKeyword != null ? "&txtSearch=" + searchKeyword : "" %>">
                      <%= p %>
                    </a>
                  </li>
                <% } %>

                <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
                  <a class="page-link" href="<%= currentPage < pageCount ? "adminLoaiController?page=" + (currentPage + 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Next">
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

</html>
