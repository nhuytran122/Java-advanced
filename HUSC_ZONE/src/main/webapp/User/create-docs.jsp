<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Tài Liệu - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
</head>
<body class="bg-light">
    <!-- Top Navbar -->
    <%@ include file="layout/navbar.jsp" %>

    <div class="container-fluid my-3">
        <div class="row">
            <!-- Sidebar -->
            <%@ include file="layout/sidebar.jsp" %>

            <!-- Main Content -->
            <main class="col-md-9 my-4">
                <div class="card no-hover mb-3">
                    <h4 class="mb-0 p-3 text-center">Tải lên Tài liệu</h4>
                    
                    <div class="card-body">
                        <form>
                            <div class="mb-3">
                                <label class="form-label">Tiêu đề</label>
                                <input type="text" class="form-control" placeholder="Nhập tiêu đề tài liệu" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Mô tả</label>
                                <textarea class="form-control" rows="3" placeholder="Nhập mô tả ngắn gọn về tài liệu" required></textarea>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Ngành học</label>
                                <select class="form-select" required>
                                    <option value="" disabled selected>-- Chọn ngành học --</option>
                                    <option value="cntt">Công nghệ thông tin</option>
                                    <option value="yte">Y tế</option>
                                    <option value="xaydung">Xây dựng</option>
                                    <option value="khac">Khác</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Loại tài liệu</label>
                                <select class="form-select" required>
                                    <option value="" disabled selected>-- Chọn loại tài liệu --</option>
                                    <option value="slide">Slide bài giảng</option>
                                    <option value="baitap">Bài tập</option>
                                    <option value="doan">Đồ án</option>
                                    <option value="khac">Khác</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Tải lên tài liệu</label>
                                <input type="file" class="form-control" required>
                            </div>
                            <div class="text-end">
                                <a href="/show-docs.html" class="btn btn-danger mt-2 py-2 px-3 pb-2 me-2">
                                    Hủy
                                </a>
                                <button type="submit" class="btn btn-success py-2">
                                    <i class="bi bi-upload"></i> Gửi tài liệu
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </main>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-primary-custom text-white text-center p-3">
        <p>© 2024 HUSCZone. All Rights Reserved.</p>
    </footer>
</body>
</html>
