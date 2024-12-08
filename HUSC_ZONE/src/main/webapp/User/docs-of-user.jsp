<%@page import="V_DetailsDocModal.DetailsDoc"%>
<%@page import="MaterialModal.MaterialBo"%>
<%@page import="CategoryModal.CategoryBo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
</head>

<%
    ArrayList<DetailsDoc> ds = (ArrayList<DetailsDoc>) request.getAttribute("ds");
    int pageCount = (Integer) request.getAttribute("pageCount");
    int currentPage = (Integer) request.getAttribute("currentPage");
    String namePoster = (String) request.getAttribute("namePoster");
    Long IDPoster = (long)request.getAttribute("IDPoster");
%>
  
<body class="bg-light">
<%@ include file="layout/navbar.jsp" %>

    <div class="container-fluid my-3">
    
        <div class="row">
            <%@ include file="layout/sidebar.jsp" %>
	        <main class="col-md-9 my-4">
		        <div class="d-flex justify-content-between align-items-center mb-3">
	            	<h4 class="fw-bold">Danh sách Tài liệu của <%= namePoster %></h4>
	            	<form method="post" action="../edit-docs">
		                 <button type="submit" name="btnAddDoc" value="btnAddDoc" class="btn btn-success">
		                 	<i class="bi bi-plus-circle"></i> Tải lên tài liệu
	                 	</button>
	                 </form>
	            </div>
	            
	            <div class="row">
	            <% 
                          int n = ds.size();
                          if (n == 0) {
                        %>
                            <div class="alert alert-warning">
							  Không tìm thấy tài liệu nào.
							</div>
                        <% 
                          } else { 
                            for (int i = 0; i < n; i++) {
                            	DetailsDoc docs = ds.get(i);
                        %>
		            <div class="col-md-3 mb-4">
					    <a href="../details?docsID=<%=docs.getDocumentID() %>" class="card-link" style="display: block; text-decoration: none;">
					        <div class="card" title="Click để xem chi tiết">
					            <img src="https://via.placeholder.com/150" class="card-img-top" alt="Thumbnail" style="height: 150px; object-fit: cover;">
					            <div class="card-body">
					                <h6 class="card-title text-truncate"><%= docs.getTitle() %></h6>
					                <div>
					                    <span class="badge bg-info text-white"><%= docs.getCategoryName() %></span>
					                    <span class="badge bg-success text-white"><%= docs.getMaterialName() %></span>
					                </div>
					            </div>
					            <div class="card-footer d-flex justify-content-between align-items-center">
								    <a href="#" class="btn btn-outline-success btn-sm">
								        <i class="bi bi-download"></i> Download
								    </a>
								
								    <% if (user != null && IDPoster == (user.getUserID())) { %>
								        <div class="d-flex">
								            <form method="post" action="../edit-docs" class="d-inline-flex">
								                <input type="hidden" name="docID" value="<%= docs.getDocumentID() %>">
								                <button type="submit" name="btnUpdateDoc" value="btnUpdateDoc" class="btn btn-outline-warning btn-sm mx-2">
								                    <i class="bi bi-pencil"></i> Sửa
								                </button>
								
								                <button 
								                    type="button" 
								                    class="btn btn-outline-danger btn-sm" 
								                    data-bs-toggle="modal" 
								                    data-bs-target="#deleteModal<%= docs.getDocumentID() %>" 
								                    title="Xóa">
								                    <i class="bi bi-trash"></i> Xóa
								                </button>
								            </form>
								        </div>
								    <% } else { %>
								        <button class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#reportModal">
								            <i class="bi bi-flag"></i> Báo cáo
								        </button>
								    <% } %>
								</div>

					        </div>
					    </a>
					</div>
					
					<div class="modal fade" id="deleteModal<%= docs.getDocumentID() %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title text-danger" id="deleteModalLabel<%= docs.getDocumentID() %>">
                                	<i class="bi bi-exclamation-triangle-fill me-2"></i>
                                	Xác nhận xóa Tài liệu
                                </h5>
                              </div>
                              <div class="modal-body">
                                Bạn có chắc chắn muốn xóa Tài liệu <b class="text-primary"><%= docs.getTitle() %></b> không?
                              </div>
                              <div class="modal-footer">
                                <form method="post" action="../edit-docs">
                                  <input type="hidden" name="docID" value="<%= docs.getDocumentID() %>">
                                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                  <button type="submit" name="btnDeleteDoc" value="<%= docs.getDocumentID() %>" class="btn btn-danger">Xóa</button>
                                </form>
                              </div>
                            </div>
                          </div>
                        </div>
					<% } 
				}%>
				</div>
				<% if (n > 0) { %>
				    <nav>
				        <ul class="pagination justify-content-center mt-4">
				            <li class="page-item <%= currentPage > 1 ? "" : "disabled" %>">
				                <a class="page-link" href="<%= currentPage > 1 ? "../docs-of-user?posterID=" + user.getUserID() + "&page=" + (currentPage - 1) : "#" %>" tabindex="-1" aria-disabled="true">
				                    <i class="bi bi-chevron-left"></i>
				                </a>
				            </li>
				            
				            <% for (int p = 1; p <= pageCount; p++) { %>
				                <li class="page-item <%= p == currentPage ? "active" : "" %>">
				                    <a class="page-link" href="../docs-of-user?posterID=<%= user.getUserID() %>&page=<%= p %>">
				                        <%= p %>
				                    </a>
				                </li>
				            <% } %>
				            
				            <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
				                <a class="page-link" href="<%= currentPage < pageCount ? "../docs-of-user?posterID=" + user.getUserID() + "&page=" + (currentPage + 1) : "#" %>">
				                    <i class="bi bi-chevron-right"></i>
				                </a>
				            </li>
				        </ul>
				    </nav>
				<% } %>

        	</main>
     	</div>

    </div>

    <!-- Report Modal -->
    <div class="modal fade" id="reportModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Báo cáo tài liệu</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <textarea class="form-control" rows="3" placeholder="Mô tả lý do báo cáo..."></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    <button type="button" class="btn btn-danger">Gửi Báo cáo</button>
                </div>
            </div>
        </div>
    </div>    
</body>
</html>
