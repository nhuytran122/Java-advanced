<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bài viết đã thích - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
</head>

<body class="bg-light">
    <%@ include file="layout/navbar.jsp" %>

    <div class="container-fluid my-3">
        <div class="row">
            <%@ include file="layout/sidebar.jsp" %>
            
            <main class="col-md-9 my-3">
                <h4 class="pb-2">Bài viết đã thích</h4>

                <!-- Post Item -->
                <div class="d-flex mb-3 p-3 border rounded-3 bg-white shadow-sm">
                    <img src="https://via.placeholder.com/50" alt="Avatar" class="rounded-circle me-3" width="60" height="60">
                    <div class="flex-grow-1">
                        <div class="d-flex justify-content-between align-items-center">
                            <h6 class="m-0">Như Ý Trần</h6>
                            <div class="d-flex align-items-center">
                                <small class="text-muted me-3">26 Tháng 11, 2024 - 20:05</small>
                                <div class="dropdown">
                                    <button class="btn btn-link p-0" data-bs-toggle="dropdown" aria-expanded="false">
                                        <i class="bi bi-three-dots-vertical"></i>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-end">
                                        <li><a class="dropdown-item" href="#">Bỏ thích</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="body my-2">
                            <p>Đã thích ảnh của Mun Đi Bảo. Tiểu thuyết "When The Phone Rings"...</p>
                            <a href="#" class="text-primary">Xem thêm...</a>
                        </div>
                    </div>
                </div>
                
                <!-- Repeat the above post-item for other posts -->
                <div class="d-flex mb-3 p-3 border rounded-3 bg-white shadow-sm">
                    <img src="https://via.placeholder.com/50" alt="Avatar" class="rounded-circle me-3" width="60" height="60">
                    <div class="flex-grow-1">
                        <div class="d-flex justify-content-between align-items-center">
                            <h6 class="m-0">Như Ý Trần</h6>
                            <div class="d-flex align-items-center">
                                <small class="text-muted me-3">26 Tháng 11, 2024 - 20:05</small>
                                <div class="dropdown">
                                    <button class="btn btn-link p-0" data-bs-toggle="dropdown" aria-expanded="false">
                                        <i class="bi bi-three-dots-vertical"></i>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-end">
                                        <li><a class="dropdown-item" href="#">Bỏ thích</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="body my-2">
                            <p>Đã thích ảnh của Mun Đi Bảo. Tiểu thuyết "When The Phone Rings"...</p>
                            <a href="#" class="text-primary">Xem thêm...</a>
                        </div>
                    </div>
                </div>

            </main>
        </div>
    </div>
</body>
</html>
