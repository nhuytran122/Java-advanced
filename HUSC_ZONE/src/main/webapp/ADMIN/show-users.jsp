<%@page import="CommonModal.Constants"%>
<%@page import="UserModal.User"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Quản lý nguời dùng - HUSC Zone</title>
  <%@ include file="layout/import.jsp" %>
</head>

<body>
  <%
    ArrayList<User> ds = (ArrayList<User>) request.getAttribute("dsUsers");
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
            <form action="../admin/users" method="get" class="d-flex" style="width: 100%; justify-content: center; align-items: center;">
              <input type="text" class="form-control form-control-sm me-2" id="navbar-search-input" placeholder="Tìm kiếm nguời dùng..." name="txtSearch" aria-label="search"
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
                    <h4 class="card-title">Danh sách Người dùng</h4>
                    <form method="post" action="../admin/edit">
                      <button type="submit" name="btnAddUser" value="add" class="btn btn-primary btn-sm">
                        <i class="bi bi-plus-circle"></i> Thêm mới Người dùng
                      </button>
                    </form>
                  </div>

                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead class="table-light">
                        <tr>
                          <th style="width: 150px;">Avatar</th>
                          <th>Họ tên</th>
                          <th>Email</th>
                          <th>Số điện thoại</th>
                          <th>Trạng thái</th>
                          <th>Vai trò</th>
                          <th>Thao tác</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% 
                          int n = ds.size();
                          if (n == 0) {
                        %>
                            <tr>
                              <td colspan="7" class="text-center text-danger">Không tìm thấy người dùng nào.</td>
                            </tr>
                        <% 
                          } else { 
                            for (int i = 0; i < n; i++) {
                              User user = ds.get(i);
                              Long userID = user.getUserID();
                        %>
	                        <tr>
	                         <td>
	                         <% if (user.getAvatar() != null && !user.getAvatar().isBlank()) { %>
                            	<img src="<%= request.getContextPath() %><%= user.getAvatar() %>" class="img-fluid rounded" style="width: auto; height: 100px; object-fit: cover;">
	                        <% } else { %>
	                            <img src="../images/default-avt.jpg" class="img-fluid rounded" style="width: auto; height: 100px; object-fit: cover;">
	                        <% } %>
	                          </td>
							  <td>
							    <div style="max-width: 200px; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.5;">
							      <%= user.getName() %>
							    </div>
							  </td>
							  <td>
							    <div style="max-width: 200px; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.5;">
							      <%= user.getEmail() %>
							    </div>
							  </td>
							  <td><%= user.getPhone() %></td>
							  <td><%= user.isIsUsing() ? "Đang hoạt động" : "Đã khóa" %></td>
							  <td><%= user.getRoleID() == Constants.ROLE_ADMIN ? "Admin" : "User" %></td>
							  <td>
							    <div class="btn-group" role="group">
							      <form method="post" action="../admin/edit">
							        <input type="hidden" name="userID" value="<%= userID %>">
							        <a href="../admin/details?userID=<%= userID %>" class="btn btn-success btn-sm" title="Xem chi tiết">
									  <i class="bi bi-eye"></i>
									</a>
							        <button type="submit" name="btnUpdateUser" value="update" class="btn btn-warning btn-sm" title="Sửa">
							          <i class="bi bi-pencil"></i>
							        </button>
							        <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal<%= userID %>" title="Xóa">
							          <i class="bi bi-trash"></i>
							        </button>
							      </form>
							    </div>
							  </td>
							</tr>


                        <!-- Modal xác nhận xóa thông thường -->
                        <div class="modal fade" id="deleteModal<%= userID %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title text-danger" id="deleteModalLabel<%= userID %>">
                                <i class="bi bi-exclamation-triangle-fill me-2"></i>
                                Xác nhận xóa Người dùng 
                              </div>
                              <div class="modal-body">
                                Bạn có chắc chắn muốn xóa Người dùng <b class="text-primary"> <%= user.getName() %></b> không?
                                
                                <div class="alert alert-warning d-flex align-items-center mt-4" role="alert">
						          Lưu ý: Xóa Người dùng này sẽ đồng thời xóa các dữ liệu liên quan.
						        </div>
                              </div>
                              <div class="modal-footer">
                                <form method="post" action="../admin/edit-user">
                                  <input type="hidden" name="userID" value="<%= userID %>">
                                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                  <button type="submit" name="btnDeleteUser" value="delete" class="btn btn-danger">Xóa</button>
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
                  <a class="page-link" href="<%= currentPage > 1 ? "../admin/users?page=" + (currentPage - 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>

                <% for (int p = 1; p <= pageCount; p++) { %>
                  <li class="page-item <%= p == currentPage ? "active" : "" %>">
                    <a class="page-link" href="../admin/users?page=<%= p %><%= searchKeyword != null ? "&txtSearch=" + searchKeyword : "" %>">
                      <%= p %>
                    </a>
                  </li>
                <% } %>

                <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
                  <a class="page-link" href="<%= currentPage < pageCount ? "../admin/users?page=" + (currentPage + 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Next">
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
