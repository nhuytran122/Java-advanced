<%@page import="CategoryModal.Category"%>
<%@page import="MaterialModal.Material"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="V_DetailsDocModal.DetailsDoc"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>
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
    ArrayList<DetailsDoc> ds = (ArrayList<DetailsDoc>) request.getAttribute("ds");
    int pageCount = (Integer) request.getAttribute("pageCount");
    int currentPage = (Integer) request.getAttribute("currentPage");
    String searchKeyword = request.getParameter("txtSearch");
    Long cateID = request.getParameter("cateID") != null ? Long.parseLong(request.getParameter("cateID")) : 0L;
    Long mateID = request.getParameter("mateID") != null ? Long.parseLong(request.getParameter("mateID")) : 0L;
    ArrayList<Category> listCates = (ArrayList<Category>) request.getAttribute("listCates");
    ArrayList<Material> listMates = (ArrayList<Material>) request.getAttribute("listMates");
  %>
  <div class="container-scroller">
    <%@ include file="layout/navbar.jsp" %>
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layout/settings-panel.jsp" %>
      <%@ include file="layout/left_sidebar.jsp" %>
      <div class="main-panel">
        <ul class="navbar-nav mr-lg-2 my-4 d-flex justify-content-center" style="width: 100%;">
		  <li class="nav-item nav-search">
		    <form action="../admin/docs" method="post" class="d-flex align-items-center justify-content-center" style="gap: 10px;">
		      <select name="cateID" class="form-control form-control-sm" style="width: 200px;">
		        <option value="0" <%= (cateID == 0L ? "selected" : "") %>>-- Chọn ngành học --</option>
		        <% for(Category cate : listCates) { %>
		          <option value="<%= cate.getCategoryID() %>" <%= (cate.getCategoryID().equals(cateID) ? "selected" : "") %>>
		            <%= cate.getCategoryName() %>
		          </option>
		        <% } %>
		      </select>
		
		      <select name="mateID" class="form-control form-control-sm" style="width: 200px;">
		        <option value="0" <%= (mateID == 0L ? "selected" : "") %>>-- Chọn dạng tài liệu --</option>
		        <% for(Material mate : listMates) { %>
		          <option value="<%= mate.getMaterialID() %>" <%= (mate.getMaterialID().equals(mateID) ? "selected" : "") %>>
		            <%= mate.getMaterialName() %>
		          </option>
		        <% } %>
		      </select>
		
		      <input type="text" class="form-control form-control-sm" id="navbar-search-input" 
		             placeholder="Tìm kiếm tài liệu..." name="txtSearch" 
		             value="<%= searchKeyword != null ? searchKeyword : "" %>" 
		             style="width: 300px;">
		
		      <button type="submit" name="btn-search" value="search" class="btn btn-primary btn-sm">
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
                    <h4 class="card-title">Danh sách Tài liệu</h4>
                    <form method="post" action="../admin/edit">
                      <button type="submit" name="btnAddDoc" value="btnAddDoc" class="btn btn-primary btn-sm">
                        <i class="bi bi-plus-circle"></i> Thêm mới tài liệu
                      </button>
                    </form>
                  </div>

                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead class="table-light">
                        <tr>
                          <th style="max-width: 200px;">Tên tài liệu</th>
                          <th>Ngành học</th>
                          <th>Loại tài liệu</th>
                          <th>Người đăng</th>
                          <th>Thao tác</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% 
                          int n = ds.size();
                          if (n == 0) {
                        %>
                            <tr>
                              <td colspan="7" class="text-center text-danger">Không tìm thấy tài liệu nào.</td>
                            </tr>
                        <% 
                          } else { 
                            for (int i = 0; i < n; i++) {
                              DetailsDoc doc = ds.get(i);
                              Long docID = doc.getDocumentID();
                        %>
                        <tr>
                          <td>
                            <div style="max-width: 200px; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.5;">
                              <%= doc.getTitle() %>
                            </div>
                          </td>
                          <td><%= doc.getCategoryName() %></td>
                          <td><%= doc.getMaterialName() %></td>
                          <td>
	                          <div style="max-width: 200px; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.5;">
	                              <%= doc.getName() %>
	                           </div>
                          </td>
                          <td>
                            <div class="btn-group" role="group">
                              <form method="post" action="../admin/edit">
                                <a href="../admin/details?docID=<%= docID %>" class="btn btn-success btn-sm" title="Xem chi tiết">
									  <i class="bi bi-eye"></i>
								</a>
                                <input type="hidden" name="docID" value="<%= docID %>">
                                <button type="submit" name="btnUpdateDoc" value="update" class="btn btn-warning btn-sm" title="Sửa">
                                  <i class="bi bi-pencil"></i>
                                </button>
                                <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal<%= docID %>" title="Xóa">
                                  <i class="bi bi-trash"></i>
                                </button>
                              </form>
                            </div>
                          </td>
                        </tr>

                        <!-- Modal xác nhận xóa thông thường -->
                        <div class="modal fade" id="deleteModal<%= docID %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title text-danger" id="deleteModalLabel<%= docID %>">
                                	<i class="bi bi-exclamation-triangle-fill me-2"></i>
                                	Xác nhận xóa Tài liệu
                                </h5>
                              </div>
                              <div class="modal-body">
                                Bạn có chắc chắn muốn xóa tài liệu <b class="text-primary"><%= doc.getTitle() %></b> không?
                                 <div class="alert alert-warning d-flex align-items-center mt-4" role="alert">
						          Lưu ý: Xóa tài liệu này sẽ đồng thời xóa các thông tin liên quan.
						        </div>
                              </div>
                              <div class="modal-footer">
                                <form method="post" action="../admin/edit-docs">
                                  <input type="hidden" name="docID" value="<%= docID %>">
                                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                  <button type="submit" name="btnDeleteDoc" value="<%= docID %>" class="btn btn-danger">Xóa</button>
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
                  <a class="page-link" href="<%= currentPage > 1 ? "../admin/docs?page=" + (currentPage - 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>

                <% for (int p = 1; p <= pageCount; p++) { %>
                  <li class="page-item <%= p == currentPage ? "active" : "" %>">
                    <a class="page-link" href="../admin/docs?page=<%= p %><%= searchKeyword != null ? "&txtSearch=" + searchKeyword : "" %>">
                      <%= p %>
                    </a>
                  </li>
                <% } %>

                <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
                  <a class="page-link" href="<%= currentPage < pageCount ? "../admin/docs?page=" + (currentPage + 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Next">
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
