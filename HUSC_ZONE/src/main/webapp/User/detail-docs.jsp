<%@page import="CommonModal.MethodCommon"%>
<%@page import="V_DetailsDocModal.DetailsDoc"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="MaterialModal.MaterialBo"%>
<%@page import="CategoryModal.CategoryBo"%>
<%@page import="DocumentModal.Document"%>

<% DetailsDoc dtlDocs = (DetailsDoc)request.getAttribute("dtlDocs"); 
	User uploadedBy = (User)request.getAttribute("uploadedBy");
	ArrayList<DetailsDoc> lstDocsSuggest = (ArrayList<DetailsDoc>)request.getAttribute("lstDocsSuggest");
	boolean isMarked = (boolean)request.getAttribute("isMarked");
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= dtlDocs.getTitle() %> - Chi tiết Tài liệu</title>
    <%@ include file="layout/import.jsp" %>
</head>

<body class="bg-light">
<%@ include file="layout/navbar.jsp" %>

<div class="container-fluid my-3">
    <div class="row">
        <%@ include file="layout/sidebar.jsp" %>

        <!-- Column for document details -->
        <main class="col-md-9 my-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h4 class="fw-bold">Chi tiết Tài liệu</h4>
            </div>

            <div class="row">
                <div class="row g-0">
                    <div class="col-md-4">
                        <!-- Thumbnail -->
                        <% if (dtlDocs.getCategoryImage() != null && !dtlDocs.getCategoryImage().isBlank()) { %>
                            	<img src="<%= request.getContextPath() %><%= dtlDocs.getCategoryImage() %>" class="document-thumbnail" alt="Document Thumbnail" height="350" width="350">
	                    <% } else { %>
	                        <img src="../images/default-category.jpg" class="document-thumbnail" alt="Document Thumbnail" height="350" width="350">
	                    <% } %>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h4 class="card-title mb-3"><%=dtlDocs.getTitle() %></h4>
                            <p class="card-text"><%=dtlDocs.getDescription() %></p>
                            <p><strong>Ngành học: </strong> 
                            	<a href="../home?cateID=<%= dtlDocs.getCategoryID()%>" class="badge bg-info text-white text-decoration-none" style="font-size: 16px;">
                            		<%= dtlDocs.getCategoryName() %>
                            	</a>
                           	</p>
                            <p><strong>Loại tài liệu:</strong>
                            	<a href="../home?mateID=<%= dtlDocs.getMaterialID()%>" class="badge bg-success text-white text-decoration-none" style="font-size: 16px;">
                            		<%= dtlDocs.getMaterialName() %>
                            	</a>
                           	</p>
                            <p><strong>Tải lên bởi:</strong> 
                            	<a href="../user-profile?userId=<%= dtlDocs.getUploadedBy() %>" class="text-decoration-none">
									<%= dtlDocs.getName() %>
								</a>
							</p>
                            <p><strong>Ngày tải lên:</strong> 
                            	<%= dtlDocs.getUpdatedAt() == null ? MethodCommon.convertDateToString(dtlDocs.getCreatedAt()) 
															  : "Đã chỉnh sửa " + MethodCommon.convertDateToString(dtlDocs.getUpdatedAt()) %></p>
							<p><strong>Lượt yêu thích: </strong> 
                            	<%= dtlDocs.getCountBookmarks() %>
                           	</p>
                            <div class="d-flex align-items-center">
							    <form action="../interact" method="POST" class="me-2">
							        <input type="hidden" name="docsID" value="<%= dtlDocs.getDocumentID()%>">
							        <% if (isMarked) { %>
							            <button type="submit" name="btn-mark" value="unmark" class="btn btn-warning p-2 mt-0">
							                <i class="bi bi-bookmark-heart-fill"></i> Đã Yêu thích
							            </button>
							        <% } else { %>
							            <button type="submit" name="btn-mark" value="mark" class="btn btn-outline-warning p-2 mt-0">
							                <i class="bi bi-bookmark-heart"></i> Yêu thích
							            </button>
							        <% } %>
							    </form>
							    <a href="<%= request.getContextPath() %><%= dtlDocs.getFilePath() %>" class="btn btn-outline-success p-2">
							        <i class="bi bi-download"></i> Download
							    </a>
							</div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="reportModalLabel" aria-hidden="true">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title" id="reportModalLabel">Báo cáo tài liệu</h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                </div>
	                <form method="post" action="../interact">
	                	<input type="hidden" name="docsID" value="<%= dtlDocs.getDocumentID() %>">
	                	<input type="hidden" name="reportInDetail" value="reportInDetail">
		                <div class="modal-body">
		                    <textarea name="txtContentReport" class="form-control" rows="3" placeholder="Mô tả lý do báo cáo bài viết này..." required></textarea>
		                </div>
		                <div class="modal-footer">
		                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
		                    <button type="submit" name="btn-report" value="btn-report" class="btn btn-danger">Gửi</button>
		                </div>
	                </form>
	        </div>
        </div>
    </div>

            <!-- Suggested Documents -->
			<div class="mt-5">
			    <h5 class="fw-bold mb-3">Tài liệu tương tự</h5>
			    <div class="row">
			        <%
			            if (lstDocsSuggest == null || lstDocsSuggest.size() == 0) {
			        %>
			            <p class="text-muted">Hiện không có tài liệu tương tự.</p>
			        <%
			            } else {
			                for (DetailsDoc doc : lstDocsSuggest) {
			        %>
			        <div class="col-md-3">
			        	<a href="../details?docsID=<%=doc.getDocumentID() %>" class="card-link" style="display: block; text-decoration: none;">
				            <div class="card h-100">
				                <% if (doc.getCategoryImage() != null && !doc.getCategoryImage().isBlank()) { %>
                            	<img src="<%= request.getContextPath() %><%= doc.getCategoryImage() %>" class="card-img-top" alt="Thumbnail" style="height: 170px; object-fit: cover;">
	                        <% } else { %>
	                            <img src="../images/default-category.jpg" class="card-img-top" alt="Thumbnail" style="height: 170px; object-fit: cover;">
	                        <% } %>
				                <span class="badge bg-warning text-white position-absolute top-0 end-0 m-2 p-2" style="font-size: 15px">
				                    <i class="bi bi-bookmark-fill"></i> <%= doc.getCountBookmarks() %>
				                </span>
				                <div class="card-body">
				                    <h6 class="card-title"><%= doc.getTitle() %></h6>
				                    <div class="pb-2">
					                    <a href="../home?cateID=<%= doc.getCategoryID()%>" class="badge bg-info text-white text-decoration-none">
					                    	<%= doc.getCategoryName() %>
					                    </a>
					                    <a href="../home?mateID=<%= doc.getMaterialID()%>" class="badge bg-success text-white text-decoration-none">
					                    	<%= doc.getMaterialName() %>
					                    </a>
				                    </div>
				                </div>
				            </div>
				        </a>
			        </div>
			        <%
			                }
			            }
			        %>
			    </div>
			</div>

        </main>
    </div>
</div>

<!-- Report Modal -->
<div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="reportModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="reportModalLabel">Báo cáo tài liệu</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
