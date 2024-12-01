<%@page import="java.text.NumberFormat"%>
<%@page import="sachmodal.sach"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>BOOKSTORE - Chi tiết sách</title>

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
    sach s = (sach) request.getAttribute("book");
    NumberFormat nf = NumberFormat.getInstance();
    nf.setGroupingUsed(true);
  %>
  <div class="container-scroller">
    <%@ include file="layoutAdmin/navbar.jsp" %>
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layoutAdmin/settings-panel.jsp" %>
      <%@ include file="layoutAdmin/left_sidebar.jsp" %>
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <!-- Card chi tiết sách -->
            <div class="col-md-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Chi tiết sách</h4>
                  <div class="row">
                    <!-- Ảnh sách -->
                    <div class="col-md-4 mb-4">
                      <img src="<%= s.getAnh() %>" class="img-fluid rounded" alt="Ảnh sách">
                    </div>

                    <!-- Thông tin sách -->
                    <div class="col-md-8">
                      <div class="row mb-3">
                        <div class="col-md-12">
                          <h5 class="card-title mb-3"><%= s.getTensach() %></h5>
                          <p class="card-text"><strong>Mã sách:</strong> <%= s.getMasach() %></p>
                          <p class="card-text"><strong>Giá:</strong> <%= nf.format(s.getGia()) %>
                          <p class="card-text"><strong>Số lượng:</strong> <%= s.getSoluong() %></p> 
                          <p class="card-text"><strong>Loại sách:</strong> <%= s.getMaloai() %></p>
                          <p class="card-text"><strong>Số Tập:</strong> <%= s.getSoTap() %></p>
                          <p class="card-text"><strong>Tác giả:</strong> <%= s.getTacgia() %></p>
                        </div>
                      </div>

                      <div class="row">
						  <div class="col-md-12">
						    <form method="post" action="adminUpdateSachController">
						      <input type="hidden" name="idSach" value="<%= s.getMasach() %>">
						      
						      <button type="submit" name="btnUpdateSach" value="<%= s.getMasach() %>" class="btn btn-warning btn-sm ms-2" title="Sửa">
						        <i class="bi bi-pencil"></i>
						      </button>
						      <button type="button" class="btn btn-danger btn-sm ms-2" data-bs-toggle="modal" data-bs-target="#deleteModal<%= s.getMasach() %>" title="Xóa">
						        <i class="bi bi-trash"></i>
						      </button>
						    </form>
						  </div>
						</div>

                      <!-- Modal xác nhận xóa thông thường -->
                      <div class="modal fade" id="deleteModal<%= s.getMasach() %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa sách</h5>
                            </div>
                            <div class="modal-body">
                              Bạn có chắc chắn muốn xóa sách <b><%= s.getTensach() %></b> không?
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
                      
                      <!-- Modal không thể xóa sách -->
                      <% if (request.getAttribute("inUsed") != null) { 
                        if((Boolean) request.getAttribute("inUsed")){%>
                        <div class="modal fade" id="cannotDeleteModal" tabindex="-1" aria-labelledby="cannotDeleteModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title" id="cannotDeleteModalLabel">Không thể xóa sách</h5>
                              </div>
                              <div class="modal-body text-danger">
                                Không thể xóa sách vì sách này đang được sử dụng.
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                              </div>
                            </div>
                          </div>
                        </div>
                      <% } 
                      }%>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
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
