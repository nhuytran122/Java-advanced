<%@page import="CommonModal.MethodCommon"%>
<%@page import="V_DetailsPostModal.DetailsPost"%>
<%@page import="CommonModal.Constants"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Quản lý bài đăng - HUSC Zone</title>
  <%@ include file="layout/import.jsp" %>
</head>

<body>
  <%
    ArrayList<DetailsPost> ds = (ArrayList<DetailsPost>) request.getAttribute("dsPosts");
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
            <form action="../admin/posts" method="get" class="d-flex" style="width: 100%; justify-content: center; align-items: center;">
              <input type="text" class="form-control form-control-sm me-2" id="navbar-search-input" placeholder="Tìm kiếm bài đăng..." name="txtSearch" aria-label="search"
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
                    <h4 class="card-title">Danh sách Bài đăng</h4>
                      <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" 
                                   data-bs-target="#postModal" >
                        <i class="bi bi-plus-circle"></i> Thêm mới Bài đăng
                      </button>
                  </div>

                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead class="table-light">
                        <tr>
                          <th style="width: 150px;">Hình ảnh</th>
                          <th>Nội dung</th>
                          <th>Người đăng</th>
                          <th>Ngày tạo</th>
                          <th>Thao tác</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% 
                          int n = ds.size();
                          if (n == 0) {
                        %>
                            <tr>
                              <td colspan="7" class="text-center text-danger">Không tìm thấy Bài đăng nào.</td>
                            </tr>
                        <% 
                          } else { 
                            for (int i = 0; i < n; i++) {
                              DetailsPost post = ds.get(i);
                              Long postID = post.getPostID();
                        %>
	                        <tr>
	                         <td>
	                         <% if (post.getImagePath() != null && !post.getImagePath().isBlank()) { %>
                            	<img src="<%= request.getContextPath() %><%= post.getImagePath() %>" class="img-fluid rounded" style="width: auto; height: 100px; object-fit: cover;">
	                        <% } else { %>
	                            <img src="../images/default-avt.jpg" class="img-fluid rounded" style="width: auto; height: 100px; object-fit: cover;">
	                        <% } %>
	                          </td>
							  <td>
							    <div style="max-width: 200px; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.5;">
							      <%= post.getPostContent() %>
							    </div>
							  </td>
							  <td>
							    <div style="max-width: 200px; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.5;">
							      <%= post.getName() %>
							    </div>
							  </td>
							  <td><%= MethodCommon.convertDateToString(post.getCreatedAt()) %></td>
							  <td>
							    <div class="btn-group" role="group">
							        <a href="../details?postID=<%= postID%>" class="btn btn-success btn-sm" title="Xem chi tiết">
							          <i class="bi bi-eye"></i>
							        </a>
							        <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal<%= postID %>" title="Xóa">
							          <i class="bi bi-trash"></i>
							        </button>
							    </div>
							  </td>
							</tr>


                        <!-- Modal xác nhận xóa thông thường -->
                        <div class="modal fade" id="deleteModal<%= postID %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title text-danger" id="deleteModalLabel<%= postID %>">
                                <i class="bi bi-exclamation-triangle-fill me-2"></i>
                                Xác nhận xóa Bài đăng 
                              </div>
                              <div class="modal-body">
                                Bạn có chắc chắn muốn xóa Bài đăng này của <b class="text-primary"> <%= post.getName() %></b> không?
                                
                                <div class="alert alert-warning d-flex align-items-center mt-4" role="alert">
						          Lưu ý: Xóa Bài đăng này sẽ đồng thời xóa các dữ liệu liên quan.
						        </div>
                              </div>
                              <div class="modal-footer">
                                <form method="post" action="../admin/edit-post">
                                  <input type="hidden" name="postID" value="<%= postID %>">
                                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                  <button type="submit" name="btnDeletePost" value="delete" class="btn btn-danger">Xóa</button>
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
            
            <div class="modal fade" id="postModal" tabindex="-1" aria-labelledby="postModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
	                    <form action="../admin/edit-post" method="post" enctype="multipart/form-data">
	                        <div class="modal-content">
	                            <div class="modal-header">
	                                <h5 class="modal-title" id="postModalLabel">Tạo bài đăng mới</h5>
	                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                            </div>
	                            <div class="modal-body">
	                                <div class="mb-3">
	                                    <textarea class="form-control" rows="5" placeholder="Nhu Y ơi, bạn muốn chia sẻ gì nào?"
		                                    name="txtContent" required></textarea>
	                                </div>
	                                <div class="card mb-3" style="width: 465px; margin: auto; border: 1px solid #ddd; border-radius: 8px;">
	                                    <div class="card-body text-center">
	                                        <div class="upload-area" style="position: relative; border: 2px dashed #ddd; border-radius: 8px; padding: 20px; background-color: #f9f9f9;">
	                                            <label style="cursor: pointer;">
	                                            	<strong>Thêm ảnh/video</strong>
	                                                <input type="file" name="fileAnh" accept="image/*">
	                                            </label>
	                                           
	                                            <button type="button" class="btn-close position-absolute" style="top: 10px; right: 10px;"></button>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="modal-footer">
	                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
	                                <button type="submit" 
                                	name="btnAddPost" value="add" class="btn btn-success">Đăng bài</button>
	                            </div>
	                        </div>
	                    </form>
                    </div>
                </div>
          </div>

          <% if (n > 0) { %>
          <div class="text-center">
            <nav aria-label="Page navigation example">
              <ul class="pagination justify-content-center">
                <li class="page-item <%= currentPage > 1 ? "" : "disabled" %>">
                  <a class="page-link" href="<%= currentPage > 1 ? "../admin/posts?page=" + (currentPage - 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>

                <% for (int p = 1; p <= pageCount; p++) { %>
                  <li class="page-item <%= p == currentPage ? "active" : "" %>">
                    <a class="page-link" href="../admin/posts?page=<%= p %><%= searchKeyword != null ? "&txtSearch=" + searchKeyword : "" %>">
                      <%= p %>
                    </a>
                  </li>
                <% } %>

                <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
                  <a class="page-link" href="<%= currentPage < pageCount ? "../admin/posts?page=" + (currentPage + 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Next">
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
