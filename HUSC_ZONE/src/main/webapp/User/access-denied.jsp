<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Từ chối truy cập - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
    <link rel="stylesheet" href="css/detail-post-style.css">
</head>
<body>
    <%@ include file="layout/navbar_for_Post.jsp" %>

    <div class="main-container d-flex flex-column align-items-center justify-content-center" style="height: 100vh; text-align: center;">
        <div class="locked-post-section">
            <i class="bi bi-lock-fill text-danger" style="font-size: 3rem;"></i>
            <h3 class="mt-3">Tài khoản không có quyền truy cập</h3>
            <p class="text-muted">
                Tài khoản của bạn không thể truy cập chức năng này.
            </p>
            <a href="../home" class="btn btn-primary mt-3">Quay lại trang chủ</a>
        </div>
    </div>

</body>
</html>
