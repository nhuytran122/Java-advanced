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
%>
  
<body class="bg-light">
<%@ include file="layout/navbar.jsp" %>

    <div class="container-fluid my-3">
    
        <div class="row">
            <%@ include file="layout/sidebar.jsp" %>
	        <main class="col-md-9 my-4">
		        <div class="d-flex justify-content-between align-items-center mb-3">
	            	<h4 class="fw-bold">Danh sách Tài liệu</h4>
	            	<form method="post" action="../edit">
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
                            <p class="text-center text-danger">Không tìm thấy tài liệu nào phù hợp</p>
                        <% 
                          } else { 
                            for (int i = 0; i < n; i++) {
                            	DetailsDoc docs = ds.get(i);
                        %>
		            <div class="col-md-3 mb-4 d-flex">
					    <a href="../details?docsID=<%=docs.getDocumentID() %>" class="card-link" style="display: block; text-decoration: none;">
					        <div class="card h-100" title="Click để xem chi tiết">
					            <% if (docs.getCategoryImage() != null && !docs.getCategoryImage().isBlank()) { %>
					                <img src="<%= request.getContextPath() %><%= docs.getCategoryImage() %>" class="card-img-top" alt="Thumbnail" style="height: 150px; object-fit: cover;">
					            <% } else { %>
					                <img src="../images/default-category.jpg" class="card-img-top" alt="Thumbnail" style="height: 150px; object-fit: cover;">
					            <% } %>
					
					            <span class="badge bg-warning text-white position-absolute top-0 end-0 m-2 p-2" style="font-size: 15px">
					                <i class="bi bi-bookmark-fill"></i> <%= docs.getCountBookmarks() %>
					            </span>
					            
					            <div class="card-body d-flex flex-column">
					                <h6 class="card-title text-truncate"><%= docs.getTitle() %></h6>
					                <div>
					                    <a href="../home?cateID=<%= docs.getCategoryID()%>">
					                        <span class="badge bg-info text-white"><%= docs.getCategoryName() %></span>
					                    </a>
					                    <a href="../home?mateID=<%= docs.getMaterialID()%>">
					                        <span class="badge bg-success text-white"><%= docs.getMaterialName() %></span>
					                    </a>
					                </div>
					            </div>
					            
					            <div class="card-footer text-center">
					                <a href="<%= request.getContextPath() %><%= docs.getFilePath() %>" class="btn btn-outline-success btn-sm">
					                    <i class="bi bi-download"></i> Download
					                </a>
					            </div>
					        </div>
					    </a>
					</div>

					<% } 
						}%>
				</div>
				<% if (pageCount > 1) { %>
					<nav>
					    <ul class="pagination justify-content-center mt-4">
						    <li class="page-item <%= currentPage > 1 ? "" : "disabled" %>">
						        <a class="page-link" href="<%= currentPage > 1 ? "../home?page=" + (currentPage - 1) + "&cateID=" + cateID + "&mateID=" + mateID + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" tabindex="-1" aria-disabled="true">
						            <i class="bi bi-chevron-left"></i>
						        </a>
						    </li>
						    
						    <% for (int p = 1; p <= pageCount; p++) { %>
						        <li class="page-item <%= p == currentPage ? "active" : "" %>">
						            <a class="page-link" href="../home?page=<%= p %>&cateID=<%= cateID %>&mateID=<%= mateID %><%= searchKeyword != null ? "&txtSearch=" + searchKeyword : "" %>">
						                <%= p %>
						            </a>
						        </li>
						    <% } %>
						    
						    <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
						        <a class="page-link" href="<%= currentPage < pageCount ? "../home?page=" + (currentPage + 1) + "&cateID=" + cateID + "&mateID=" + mateID + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>">
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
