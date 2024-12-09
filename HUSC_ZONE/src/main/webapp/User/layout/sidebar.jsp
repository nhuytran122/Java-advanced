<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="col-md-2" style="margin-left: 50px;">
    <div class="list-group custom-list-group">
        <% if (session.getAttribute("user") != null) { %>
            <a href="../user-profile" class="list-group-item bg-light text-dark fw-bold py-3 d-flex align-items-center">
                <i class="bi bi-person-circle me-2 text-primary"></i> Quản lý tài khoản
            </a>
            <a href="../liked-docs" class="list-group-item bg-light text-dark fw-bold py-3 d-flex align-items-center">
                <i class="bi bi-bookmark-heart-fill me-2 text-warning"></i> Tài liệu yêu thích
            </a>
            <a href="../activity-history" class="list-group-item bg-light text-dark fw-bold py-3 d-flex align-items-center">
                <i class="bi bi-heart-fill me-2 text-danger"></i> Nhật ký hoạt động
            </a>
        <% } else { %>
            <div class="list-group-item bg-light text-center py-4">
                <h5 class="mb-3">Để truy cập đầy đủ các tính năng, bạn có thể...</h5>
                <div class="d-flex justify-content-center mb-3">
                    <a href="../login" class="btn btn-primary btn-lg d-inline-flex align-items-center me-3">
                        <i class="bi bi-box-arrow-in-right me-2"></i>Đăng nhập
                    </a>
                </div>
                <p class="text-muted">Bạn chưa có tài khoản? <a href="../signup" class="text-primary">Đăng ký ngay</a></p>
            </div>
        <% } %>
    </div>
</div>
