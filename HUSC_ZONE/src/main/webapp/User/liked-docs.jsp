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
<body class="bg-light">
    <!-- Navbar -->
    <%@ include file="layout/navbar.jsp" %>

    <!-- Nội dung chính -->
    <div class="container-fluid my-3">
        <div class="row">
            <%@ include file="layout/sidebar.jsp" %>
            
            <main class="col-md-9 my-3">
                <h4 class="pb-2">Tài liệu yêu thích</h4>
                <div class="row">
                    <div class="col-md-3 mb-4">
					    <a href="document-detail.jsp" class="card-link" style="display: block; text-decoration: none;">
					        <div class="card" title="Click để xem chi tiết">
					            <img src="https://via.placeholder.com/150" class="card-img-top" alt="Thumbnail" style="height: 150px; object-fit: cover;">
					            <div class="card-body">
					                <h6 class="card-title text-truncate">Tiêu đề tài liệu</h6>
					                <div>
					                    <span class="badge bg-info text-white">CNTT</span>
					                    <span class="badge bg-success text-white">Slide</span>
					                </div>
					                <p class="text-muted small mt-2">Mô tả tài liệu ngắn gọn...</p>
					            </div>
					            <div class="card-footer d-flex justify-content-between">
								    <a href="#" class="btn btn-outline-success btn-sm">
								        <i class="bi bi-download"></i> Download
								    </a>
								    <a class="btn btn-warning btn-sm">
								        <i class="bi bi-heart-fill"></i> Bỏ yêu thích
								    </a>
								</div>
					        </div>
					    </a>
					</div>
                    
                    <div class="col-md-3 mb-4">
					    <a href="document-detail.jsp" class="card-link" style="display: block; text-decoration: none;">
					        <div class="card" title="Click để xem chi tiết">
					            <img src="https://via.placeholder.com/150" class="card-img-top" alt="Thumbnail" style="height: 150px; object-fit: cover;">
					            <div class="card-body">
					                <h6 class="card-title text-truncate">Tiêu đề tài liệu</h6>
					                <div>
					                    <span class="badge bg-info text-white">CNTT</span>
					                    <span class="badge bg-success text-white">Slide</span>
					                </div>
					                <p class="text-muted small mt-2">Mô tả tài liệu ngắn gọn...</p>
					            </div>
					            <div class="card-footer d-flex justify-content-between">
								    <a href="#" class="btn btn-outline-success btn-sm">
								        <i class="bi bi-download"></i> Download
								    </a>
								    <a class="btn btn-warning btn-sm">
								        <i class="bi bi-heart-fill"></i> Bỏ yêu thích
								    </a>
								</div>
					        </div>
					    </a>
					</div>
                    
                    <div class="col-md-3 mb-4">
					    <a href="document-detail.jsp" class="card-link" style="display: block; text-decoration: none;">
					        <div class="card" title="Click để xem chi tiết">
					            <img src="https://via.placeholder.com/150" class="card-img-top" alt="Thumbnail" style="height: 150px; object-fit: cover;">
					            <div class="card-body">
					                <h6 class="card-title text-truncate">Tiêu đề tài liệu</h6>
					                <div>
					                    <span class="badge bg-info text-white">CNTT</span>
					                    <span class="badge bg-success text-white">Slide</span>
					                </div>
					                <p class="text-muted small mt-2">Mô tả tài liệu ngắn gọn...</p>
					            </div>
					            <div class="card-footer d-flex justify-content-between">
								    <a href="#" class="btn btn-outline-success btn-sm">
								        <i class="bi bi-download"></i> Download
								    </a>
								    <a class="btn btn-warning btn-sm">
								        <i class="bi bi-heart-fill"></i> Bỏ yêu thích
								    </a>
								</div>
					        </div>
					    </a>
					</div>
					
					<div class="col-md-3 mb-4">
					    <a href="document-detail.jsp" class="card-link" style="display: block; text-decoration: none;">
					        <div class="card" title="Click để xem chi tiết">
					            <img src="https://via.placeholder.com/150" class="card-img-top" alt="Thumbnail" style="height: 150px; object-fit: cover;">
					            <div class="card-body">
					                <h6 class="card-title text-truncate">Tiêu đề tài liệu</h6>
					                <div>
					                    <span class="badge bg-info text-white">CNTT</span>
					                    <span class="badge bg-success text-white">Slide</span>
					                </div>
					                <p class="text-muted small mt-2">Mô tả tài liệu ngắn gọn...</p>
					            </div>
					            <div class="card-footer d-flex justify-content-between">
								    <a href="#" class="btn btn-outline-success btn-sm">
								        <i class="bi bi-download"></i> Download
								    </a>
								    <button class="btn btn-warning btn-sm">
								        <i class="bi bi-heart-fill"></i> Bỏ yêu thích
								    </button>
								</div>

					        </div>
					    </a>
					</div>
                    
                </div>
                <nav>
				    <ul class="pagination justify-content-center mt-4">
				        <li class="page-item disabled">
				            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">
				                <i class="bi bi-chevron-left"></i>
				            </a>
				        </li>
				        <li class="page-item active" aria-current="page">
				            <a class="page-link" href="#">1</a>
				        </li>
				        <li class="page-item">
				            <a class="page-link" href="#">2</a>
				        </li>
				        <li class="page-item">
				            <a class="page-link" href="#">3</a>
				        </li>
				        <li class="page-item">
				            <a class="page-link" href="#">
				                 <i class="bi bi-chevron-right"></i>
				            </a>
				        </li>
				    </ul>
				</nav>

            </main>
        </div>
    </div>
</body>
</html>
