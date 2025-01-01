<%@page import="MaterialModal.Material"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Quản lý tài liệu - HUSCZone</title>
  <%@ include file="layout/import.jsp" %>
</head>

<body>
  <%
    ArrayList<Material> ds = (ArrayList<Material>) request.getAttribute("dsMates");
    int pageCount = (Integer) request.getAttribute("pageCount");
    int currentPage = (Integer) request.getAttribute("currentPage");
    String searchKeyword = request.getParameter("txtSearch");
  %>
  <div class="container-scroller">
    <%@ include file="layout/navbar.jsp" %>
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layout/settings-panel.jsp" %>
      <%@ include file="layout/left_sidebar.jsp" %>
      <div class="main-panel">
        <ul class="navbar-nav mr-lg-2 my-4" style="display: flex; justify-content: center; width: 100%;">
          <li class="nav-item nav-search d-none d-lg-block" style="display: flex; align-items: center;">
            <form action="../admin/materials" method="get" class="d-flex" style="width: 100%; justify-content: center; align-items: center;">
              <input type="text" class="form-control form-control-sm me-2" id="navbar-search-input" placeholder="Tìm kiếm loại tài liệu..." name="txtSearch" aria-label="search"
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
                    <h4 class="card-title">Danh sách Loại tài liệu</h4>
                    <form method="post" action="../admin/edit">
                      <button type="submit" name="btnAddMate" value="btnAddMate" class="btn btn-primary btn-sm">
                        <i class="bi bi-plus-circle"></i> Thêm mới loại tài liệu
                      </button>
                    </form>
                  </div>

                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead class="table-light">
                        <tr>
					      <th class="col-3">Tên loại</th> 
					      <th class="col-6">Mô tả</th> 
					      <th class="col-3">Thao tác</th>   
					    </tr>
                      </thead>
                      <tbody>
					  <% 
					    int n = ds.size();
					    if (n == 0) {
					  %>
					      <tr>
					        <td colspan="3" class="text-center text-danger">Không tìm thấy loại tài liệu nào.</td>
					      </tr>
					  <% 
					    } else { 
					      for (int i = 0; i < n; i++) {
					        Material mate = ds.get(i);
					  %>
					  <tr>
					    <td><%= mate.getMaterialName() %></td>
					    <td><%= mate.getDescription() == null ? "" : mate.getDescription()%></td>
					    <td>
					      <div class="btn-group" role="group">
					        <form method="post" action="../admin/edit">
					          <input type="hidden" name="mateID" value="<%= mate.getMaterialID() %>">
					          <button type="submit" name="btnUpdateMate" value="update" class="btn btn-warning btn-sm" title="Sửa">
					            <i class="bi bi-pencil"></i>
					          </button>
					          <button type="button" 
					                  class="btn btn-danger btn-sm" 
					                  data-bs-toggle="modal" 
					                  data-bs-target="#deleteModal<%= mate.getMaterialID() %>" 
					                  title="Xóa">
					            <i class="bi bi-trash"></i>
					          </button>
					        </form>
					      </div>
					    </td>
					  </tr>
					
					  <div class="modal fade" id="deleteModal<%= mate.getMaterialID() %>" tabindex="-1" aria-labelledby="deleteModalLabel<%= mate.getMaterialID() %>" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title text-danger" id="deleteModalLabel<%= mate.getMaterialID() %>">
						          <i class="bi bi-exclamation-triangle-fill me-2"></i>
						          Xác nhận xóa loại tài liệu
						        </h5>
						      </div>
						      <div class="modal-body">
						        Bạn có chắc chắn muốn xóa loại tài liệu <b class="text-primary"><%= mate.getMaterialName() %></b> không?
						        <div class="alert alert-warning d-flex align-items-center mt-4" role="alert">
						          Lưu ý: Xóa loại tài liệu này sẽ đồng thời xóa dữ liệu liên quan.
						        </div>
						      </div>
						      <div class="modal-footer">
						        <form method="post" action="../admin/edit-material">
						          <input type="hidden" name="mateID" value="<%= mate.getMaterialID() %>">
						          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
						          <button type="submit" name="btnDeleteMate" value="delete" class="btn btn-danger">
						            <i class="bi bi-trash"></i> Xóa
						          </button>
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
                  <a class="page-link" href="<%= currentPage > 1 ? "../admin/materials?page=" + (currentPage - 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>

                <% for (int p = 1; p <= pageCount; p++) { %>
                  <li class="page-item <%= p == currentPage ? "active" : "" %>">
                    <a class="page-link" href="../admin/materials?page=<%= p %><%= searchKeyword != null ? "&txtSearch=" + searchKeyword : "" %>">
                      <%= p %>
                    </a>
                  </li>
                <% } %>

                <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
                  <a class="page-link" href="<%= currentPage < pageCount ? "../admin/materials?page=" + (currentPage + 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Next">
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
