<%@page import="V_DetailsBookmarkModal.DetailsBookmark"%>
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
    <title>Tài liệu yêu thích - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
</head>

<%
    ArrayList<DetailsBookmark> ds = (ArrayList<DetailsBookmark>) request.getAttribute("ds");
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
	            	<h4 class="fw-bold">Tài liệu yêu thích</h4>
	            </div>
	            
	            <div class="row">
	            <% 
                          int n = ds.size();
                          if (n == 0) {
                        %>
                            <p class="text-center text-danger">Hiện bạn chưa yêu thích tài liệu nào. Tìm kiếm tài liệu phù hợp với bạn ngay nào!</p>
                        <% 
                          } else { 
                            for (int i = 0; i < n; i++) {
                            	DetailsBookmark docs = ds.get(i);
                        %>
		            <div class="col-md-3 mb-4">
					    <a href="../details?docsID=<%=docs.getDocumentID() %>" class="card-link" style="display: block; text-decoration: none;">
					        <div class="card" title="Click để xem chi tiết">
					            <img src="<%= request.getContextPath() %><%= docs.getCategoryImage() %>" class="card-img-top" alt="Thumbnail" style="height: 150px; object-fit: cover;">
					            <div class="card-body">
					                <h6 class="card-title text-truncate"><%= docs.getTitle() %></h6>
					                <div>
					                    <span class="badge bg-info text-white"><%= docs.getCategoryName() %></span>
					                    <span class="badge bg-success text-white"><%= docs.getMaterialName() %></span>
					                </div>
					            </div>
					            <div class="card-footer d-flex align-items-center">
								    <a href="#" class="btn btn-outline-success btn-sm me-2">
								        <i class="bi bi-download"></i> Download
								    </a>
								    <form action="../interact" method="POST" class="mb-0">
								        <input type="hidden" name="docsID" value="<%= docs.getDocumentID() %>">
								        <input type="hidden" name="unMarkInList" value="unMarkInList">
								        <button type="submit" 
								                name="btn-mark" value="unmark" 
								                class="btn btn-danger btn-sm">
								            <i class="bi bi-heart-fill"></i> Bỏ yêu thích
								        </button>
								    </form>
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
				                <a class="page-link" href="<%= currentPage > 1 ? "../liked-docs?page=" + (currentPage - 1) : "#" %>" tabindex="-1" aria-disabled="true">
				                    <i class="bi bi-chevron-left"></i>
				                </a>
				            </li>
				            
				            <% for (int p = 1; p <= pageCount; p++) { %>
				              <li class="page-item <%= p == currentPage ? "active" : "" %>">
				                <a class="page-link" href="../liked-docs?page=<%= p %>">
				                  <%= p %>
				                </a>
				              </li>
				            <% } %>
				            <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
				                <a class="page-link" href="<%= currentPage < pageCount ? "../liked-docs?page=" + (currentPage + 1) : "#" %>">
				                     <i class="bi bi-chevron-right"></i>
				                </a>
				            </li>
				        </ul>
				    </nav>
				<% } %>

        	</main>
     	</div>

    </div>
</body>
</html>
