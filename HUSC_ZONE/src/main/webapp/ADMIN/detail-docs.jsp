<%@page import="V_DetailsDocModal.DetailsDoc"%>
<%@page import="DocumentModal.Document"%>
<%@page import="CommonModal.MethodCommon"%>
<%@page import="CommonModal.Constants"%>
<%@page import="UserModal.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Chi tiết tài liệu - HUSCZone</title>
  <%@ include file="layout/import.jsp" %>
</head>

<body>
  <%
    DetailsDoc docs = (DetailsDoc) request.getAttribute("docs");
  %>
  <div class="container-scroller">
    <%@ include file="layout/navbar.jsp" %>
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layout/settings-panel.jsp" %>
      <%@ include file="layout/left_sidebar.jsp" %>
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Chi tiết tài liệu</h4>
                  <div class="row">
                    <div class="col-md-4 mb-4">
                    	<% if (docs.getCategoryImage() != null && !docs.getCategoryImage().isBlank()) { %>
                         <img src="<%= request.getContextPath() %><%= docs.getCategoryImage() %>" class="img-fluid rounded" alt="Hình ảnh danh mục">
	                    <% } else { %>
	                     <img src="../images/default-category.jpg" class="img-fluid rounded" alt="Hình ảnh mặc định">
	                    <% } %>
                    </div>

                    <div class="col-md-8">
                      <div class="row mb-3">
                        <div class="col-md-12">
                          <h5 class="card-title mb-3"><%= docs.getTitle() %></h5>
                          <p class="card-text"><strong>Mô tả:</strong> <%= !docs.getDescription().isBlank() ? docs.getDescription() : "Chưa cập nhật" %></p>
                          <p class="card-text"><strong>Ngày tạo:</strong> <%= MethodCommon.convertDateToString(docs.getCreatedAt()) %></p>
                          <p class="card-text"><strong>Ngày cập nhật:</strong> <%= docs.getUpdatedAt() != null ? MethodCommon.convertDateToString(docs.getUpdatedAt()) : "Chưa cập nhật" %></p>
                          <p class="card-text"><strong>Ngành học:</strong> <%= docs.getCategoryName() %></p>
                          <p class="card-text"><strong>Loại tài liệu:</strong> <%= docs.getMaterialName() %></p>
                          <p class="card-text"><strong>Người tải lên:</strong> <%= docs.getName() %></p>
                          <p class="card-text"><strong>Lượt đánh dấu:</strong> <%= docs.getCountBookmarks() %></p>
                        </div>
                      </div>

                      <div class="row">
                        <div class="col-md-12">
                          <form method="post" action="../admin/edit">
                            <input type="hidden" name="docID" value="<%= docs.getDocumentID() %>">
                            <a href="<%= request.getContextPath() %><%= docs.getFilePath() %>" class="btn btn-success btn-sm ms-2" >
                              <i class="bi bi-download"></i> Tải xuống
                            </a>
                            
                            <button type="submit" name="btnUpdateDoc" value="update" class="btn btn-warning btn-sm ms-2" title="Sửa">
                              <i class="bi bi-pencil"></i> Sửa
                            </button>
                            <button type="button" class="btn btn-danger btn-sm ms-2" data-bs-toggle="modal" data-bs-target="#deleteModal<%= docs.getDocumentID() %>" title="Xóa">
                              <i class="bi bi-trash"></i> Xóa
                            </button>
                          </form>
                        </div>
                      </div>

                      <!-- Modal xác nhận xóa -->
                      <div class="modal fade" id="deleteModal<%= docs.getDocumentID() %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa tài liệu</h5>
                            </div>
                            <div class="modal-body">
                              Bạn có chắc chắn muốn xóa tài liệu <b><%= docs.getTitle() %></b> không?
                            </div>
                            <div class="modal-footer">
                              <form method="post" action="../admin/edit-docs">
                                <input type="hidden" name="docID" value="<%= docs.getDocumentID() %>">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                <button type="submit" name="btnDeleteDoc" value="delete" class="btn btn-danger">Xóa</button>
                              </form>
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
        </div>
      </div>
    </div>
  </div>
</body>

</html>
