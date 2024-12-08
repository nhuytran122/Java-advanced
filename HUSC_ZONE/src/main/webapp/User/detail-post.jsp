<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Facebook Post - Fullscreen</title>
    <%@ include file="layout/import.jsp" %>
    <link rel="stylesheet" href="css/detail-post-style.css">
</head>
<body>
    <!-- Top Navigation -->
    <%@ include file="layout/navbar_for_Post.jsp" %>

    <!-- Main Content -->
    <div class="main-container">
        <!-- Left Section - Image -->
        <div class="image-section">
        	<button class="btn btn-link position-absolute top-0 start-0 m-3 exit-button">
                <i class="bi bi-arrow-left text-white fs-4"></i>
            </button>
            <img src="/placeholder.svg" alt="Concert Seating Map" class="post-image">
        </div>

        <!-- Right Section - Content -->
        <div class="content-section">
            <!-- Post Header -->
            <div class="post-header d-flex align-items-center">
                <img src="/placeholder.svg" alt="Profile" class="profile-image me-2">
                <div class="flex-grow-1">
                    <div class="d-flex align-items-center">
                        <h6 class="mb-0">Anh Trai Vuot Ngan Chong Gai</h6>
                        <i class="fas fa-check-circle verified-badge ms-1"></i>
                    </div>
                    <span class="post-time">12 tháng 11 lúc 10:54 · <i class="fas fa-globe"></i></span>
                </div>
                <button class="btn text-white"><i class="fas fa-ellipsis-h"></i></button>
            </div>

            <!-- Post Content -->
            <div class="post-content">
                <p>
                    ĐÃ SOLD OUT TOÀN BỘ VÉ!<br><br>
                    Chân thành cảm ơn sự ủng hộ của quý khán giả!!<br>
                    Vì sự yêu thương quá lớn nên lượng chờ mua hơn 150k làm trải nghiệm mua vé bị gián đoạn bởi tình trạng không đăng nhập vào được web<br><br>
                    Ban tổ chức gửi lời xin lỗi vì sự bất tiện này đồng thời gửi lời cảm ơn đến các bạn khán giả đã cùng nhau làm nên kì tích tuyệt vời của chúng ta ❤️❤️❤️<br><br>
                </p>
            </div>

            <!-- Reaction Bar -->
            <div class="px-3 py-2">
                <div class="d-flex align-items-center">
                    <div class="reaction-icons">
                        <img src="/placeholder.svg" alt="Like" width="18">
                        <img src="/placeholder.svg" alt="Love" width="18">
                    </div>
                    <span class="reaction-count ms-2">45K</span>
                    <span class="reaction-count ms-auto">19K comments</span>
                </div>
            </div>

            <!-- Interaction Bar -->
            <div class="interaction-bar d-flex justify-content-between px-3">
                <a href="#" class="interaction-button like-button">
                    <i class="bi bi-heart-fill text-danger me-2"></i>Like
                </a>
                <a href="#" class="interaction-button comment-button">
                    <i class="bi bi-chat me-2 text-primary"></i>Comment
                </a>
                <a href="#" class="interaction-button report-button">
                    <i class="bi bi-flag me-2 text-danger"></i>Report
                </a>
            </div>

            <!-- Comments List -->
            <div class="comments-list">
                <!-- Sample Comment -->
                <div class="comment">
                    <img src="/placeholder.svg" alt="User" class="profile-image me-2">
                    <div class="comment-content">
                        <h6 class="mb-0">User Name</h6>
                        <p>This is a sample comment. Great post!</p>
                        <div class="comment-actions">
                            <a href="#" class="me-2">Like</a>
                            <a href="#" class="me-2">Reply</a>
                            <span class="text-muted">2h</span>
                        </div>
                    </div>
                </div>
                <!-- Add more comments as needed -->
            </div>

            <!-- Comment Input Section -->
            <div class="p-3 border-top">
                <div class="d-flex align-items-center">
                    <img src="/placeholder.svg" alt="User" class="profile-image me-2">
                    <div class="flex-grow-1 d-flex">
                        <input type="text" class="form-control comment-input rounded-pill me-2" placeholder="Write a comment...">
                        <button class="btn rounded-circle send-comment-button">
                            <i class="bi bi-send"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
