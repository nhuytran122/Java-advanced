<%@page import="V_DetailsDocModal.DetailsDoc"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="MaterialModal.MaterialBo"%>
<%@page import="CategoryModal.CategoryBo"%>
<%@page import="DocumentModal.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết Tài liệu</title>
    <%@ include file="layout/import.jsp" %>
</head>
<%


	DetailsDoc dtlDocs = (DetailsDoc)request.getAttribute("dtlDocs");
	User uploadedBy = (User)request.getAttribute("uploadedBy");
	ArrayList<DetailsDoc> lstDocsSuggest = (ArrayList<DetailsDoc>)request.getAttribute("lstDocsSuggest");
	Boolean isMarked = (Boolean)request.getAttribute("isMarked");
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = dtlDocs.getCreatedAt() != null ? sdf.format(dtlDocs.getCreatedAt()) : "";

%>
<body class="bg-light">
<%@ include file="layout/navbar.jsp" %>

<div class="container-fluid my-3">
    <div class="row">
        <%@ include file="layout/sidebar.jsp" %>

        <!-- Column for document details -->
        <main class="col-md-9 my-4">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <h4 class="fw-bold">Chi tiết Tài liệu</h4>
                <a class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#reportModal">
                    <i class="bi bi-flag"></i> Báo cáo
                </a>
            </div>

            <div class="row">
                <div class="row g-0">
                    <div class="col-md-4">
                        <!-- Thumbnail -->
                        <img src="https://via.placeholder.com/350x350" class="document-thumbnail" alt="Document Thumbnail">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h4 class="card-title mb-3"><%=dtlDocs.getTitle() %></h4>
                            <p class="card-text"><%=dtlDocs.getDesription() %></p>
                            <p><strong>Ngành học:</strong> <%= dtlDocs.getCategoryName() %></p>
                            <p><strong>Loại tài liệu:</strong> <%= dtlDocs.getMaterialName() %></p>
                            <p><strong>Tải lên bởi:</strong> <%= dtlDocs.getName() %></p>
                            <p><strong>Ngày tải lên:</strong> <%= formattedDate %></p>

                            <div class="d-flex align-items-center">
							    <form action="../interact" method="POST" class="me-2">
							        <input type="hidden" name="docsID" value="<%= dtlDocs.getDocumentID()%>">
							        <% if (isMarked != null && isMarked) { %>
							            <button type="submit" name="btn-mark" value="unmark" class="btn btn-warning p-2 mt-0">
							                <i class="bi bi-bookmark-heart-fill"></i> Đã Yêu thích
							            </button>
							        <% } else { %>
							            <button type="submit" name="btn-mark" value="mark" class="btn btn-outline-warning p-2 mt-0">
							                <i class="bi bi-bookmark-heart"></i> Yêu thích
							            </button>
							        <% } %>
							    </form>
							    <a href="#" class="btn btn-outline-success p-2">
							        <i class="bi bi-download"></i> Download
							    </a>
							</div>



                            <div class="mt-3">
                                <a href="#" class="badge bg-info text-white text-decoration-none"><%= dtlDocs.getCategoryName() %></a>
                                <a href="#" class="badge bg-success text-white text-decoration-none"><%= dtlDocs.getMaterialName() %></a>
                            </div>
                        </div>
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
				                <img src="https://via.placeholder.com/150x150" class="card-img-top" alt="Thumbnail">
				                <div class="card-body">
				                    <h6 class="card-title"><%= doc.getTitle() %></h6>
				                    <div class="pb-2">
				                        <span class="badge bg-info text-white"><%= doc.getCategoryName() %></span>
				                        <span class="badge bg-success text-white"><%= doc.getMaterialName() %></span>
				                    </div>
				                    <p class="text-muted"><%= doc.getDesription() %></p>
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
