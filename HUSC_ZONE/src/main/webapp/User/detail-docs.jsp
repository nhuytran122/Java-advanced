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
	Document docs = (Document)request.getAttribute("docs");
	
	Category cateOfDocs = (Category)request.getAttribute("cateOfDocs");
	Material mateOfDocs = (Material)request.getAttribute("mateOfDocs");
	User uploadedBy = (User)request.getAttribute("uploadedBy");
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = docs.getCreatedAt() != null ? sdf.format(docs.getCreatedAt()) : "";

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
                            <h4 class="card-title mb-3"><%=docs.getTitle() %></h4>
                            <p class="card-text"><%=docs.getDesription() %></p>
                            <p><strong>Ngành học:</strong> <%= cateOfDocs.getCategoryName() %></p>
                            <p><strong>Loại tài liệu:</strong> <%= mateOfDocs.getMaterialName() %></p>
                            <p><strong>Tải lên bởi:</strong> <%= uploadedBy.getName() %></p>
                            <p><strong>Ngày tải lên:</strong> <%= formattedDate %></p>

                            <div class="d-flex align-items-center">
                                <a class="btn btn-outline-warning me-2 p-2">
                                    <i class="bi bi-bookmark-heart"></i> Yêu thích
                                </a>
                                <a href="#" class="btn btn-outline-success p-2">
                                    <i class="bi bi-download"></i> Download
                                </a>
                            </div>

                            <div class="mt-3">
                                <a href="#" class="badge bg-info text-white text-decoration-none"><%= cateOfDocs.getCategoryName() %></a>
                                <a href="#" class="badge bg-success text-white text-decoration-none"><%= mateOfDocs.getMaterialName() %></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Suggested Documents Section -->
            <div class="mt-5">
                <h5 class="fw-bold mb-3">Tài liệu tương tự</h5>
                <div class="row">
                    <!-- Suggested documents -->
                    <div class="col-md-3">
                        <div class="card h-100">
                            <img src="https://via.placeholder.com/150x150" class="card-img-top" alt="Thumbnail">
                            <div class="card-body">
                                <h6 class="card-title">Lập trình C cơ bản</h6>
                                <div class="pb-2">
                                    <span class="badge bg-info text-white">CNTT</span>
                                    <span class="badge bg-success text-white">Slide</span>
                                </div>
                                <p class="text-muted">Tài liệu về C.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card h-100">
                            <img src="https://via.placeholder.com/150x150" class="card-img-top" alt="Thumbnail">
                            <div class="card-body">
                                <h6 class="card-title">Lập trình Python nâng cao</h6>
                                <div class="pb-2">
                                    <span class="badge bg-info text-white">CNTT</span>
                                    <span class="badge bg-success text-white">Slide</span>
                                </div>
                                <p class="text-muted">Khóa học Python cho người có kinh nghiệm.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card h-100">
                            <img src="https://via.placeholder.com/150x150" class="card-img-top" alt="Thumbnail">
                            <div class="card-body">
                                <h6 class="card-title">Học lập trình JavaScript</h6>
                                <div class="pb-2">
                                    <span class="badge bg-info text-white">CNTT</span>
                                    <span class="badge bg-success text-white">Slide</span>
                                </div>
                                <p class="text-muted">Giới thiệu về JavaScript cho lập trình viên web.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card h-100">
                            <img src="https://via.placeholder.com/150x150" class="card-img-top" alt="Thumbnail">
                            <div class="card-body">
                                <h6 class="card-title">Giới thiệu về C++</h6>
                                <div class="pb-2">
                                    <span class="badge bg-info text-white">CNTT</span>
                                    <span class="badge bg-success text-white">Slide</span>
                                </div>
                                <p class="text-muted">Khóa học cơ bản về lập trình C++.</p>
                            </div>
                        </div>
                    </div>
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
