<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HUSCZone - My Profile</title>
    <%@ include file="layout/import.jsp" %>
</head>
<body>
    
    <%@ include file="layout/navbar.jsp" %>
    <div class="profile-header text-center">
        <img src="https://via.placeholder.com/120" alt="Avatar" class="rounded-circle mb-3">
        <h2 class="mb-0">Như Ý</h2>
    </div>

    <div class="container my-4">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-4">
                <div class="card no-hover">
                    <div class="card-body">
                        <h5 class="card-title">Thông tin cá nhân</h5>
                        <p class="mt-3"><strong>Email:</strong> nhuy@gmail.com</p>
                        <p><strong>Số điện thoại:</strong> 0123-456-789</p>
                        <p><strong>Ngày sinh:</strong> 15/07/1999</p>
                        <p><strong>Địa chỉ:</strong> TP. Hồ Chí Minh</p>
                        <div class="d-flex">
                            <a href="/update-profile.html" class="btn btn-primary-custom me-2">Chỉnh sửa thông tin</a>
                            <a href="/change-password.html" class="btn btn-primary-custom">Đổi mật khẩu</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <!-- Tabs -->
                <ul class="nav nav-tabs" id="profileTabs" role="tablist">
                    <li class="nav-item">
                        <button class="nav-link active" id="posts-tab" data-bs-toggle="tab" data-bs-target="#posts" type="button">Bài viết của tôi</button>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" id="my-docs-tab" data-bs-toggle="tab" data-bs-target="#my-docs" type="button">Tài liệu của tôi</button>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" id="liked-posts-tab" data-bs-toggle="tab" data-bs-target="#liked-posts" type="button">Bài viết đã thích</button>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" id="saved-docs-tab" data-bs-toggle="tab" data-bs-target="#saved-docs" type="button">Tài liệu đã lưu</button>
                    </li>
                </ul>

                <!-- Tab content -->
                <div class="tab-content mt-3">
                    <!-- Tab: Bài viết của tôi -->
                    <div class="tab-pane fade show active" id="posts" role="tabpanel">
                        <div class="card no-hover" style="margin-bottom: 20px;">
                            <div class="card-body">
                                <div class="d-flex align-items-center mb-3">
                                    <img src="https://via.placeholder.com/50" alt="Avatar" class="rounded-circle me-3" style="width: 50px; height: 50px;">
                                    <input class="form-control rounded-pill" placeholder="Như Ý ơi, bạn muốn chia sẻ gì nào?" style="background-color: #f8f9fa;" data-bs-toggle="modal" data-bs-target="#postModal" readonly>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="postModal" tabindex="-1" aria-labelledby="postModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="postModalLabel">Tạo bài đăng mới</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <textarea class="form-control" rows="5" placeholder="Như Ý ơi, bạn muốn chia sẻ gì nào?"></textarea>
                                        </div>
                                        <div class="card mb-3" style="width: 465px; margin: auto; border: 1px solid #ddd; border-radius: 8px;">
                                            <div class="card-body text-center">
                                                <div class="upload-area" style="position: relative; border: 2px dashed #ddd; border-radius: 8px; padding: 20px; background-color: #f9f9f9;">
                                                    <label for="file-upload" style="cursor: pointer;">
                                                        <div style="margin-bottom: 10px;">
                                                            <i class="bi bi-image-fill"></i>
                                                        </div>
                                                        <strong>Thêm ảnh/video</strong>
                                                        <p style="color: #888;">hoặc kéo và thả</p>
                                                    </label>
                                                    <input type="file" id="file-upload" multiple style="display: none;">
                                                    <button type="button" class="btn-close position-absolute" style="top: 10px; right: 10px;"></button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                        <button type="button" class="btn btn-success">Đăng bài</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="card no-hover">
                            <div class="card-body">
                                <div class="d-flex align-items-center mb-3">
                                    <img src="https://via.placeholder.com/50" alt="Avatar" class="rounded-circle me-3">
                                    <div>
                                        <h6 class="mb-0">Như Ý</h6>
                                        <small class="text-muted">2 giờ trước</small>
                                    </div>
                                </div>
                                <p>Hôm nay mình hoàn thành xong đồ án web cơ bản. Đây là sản phẩm đầu tay!</p>
                                <img src="https://via.placeholder.com/600x300" alt="Post Image" class="img-fluid rounded">
                            </div>
                        </div>
                    </div>

                    <!-- Tab: Tài liệu của tôi -->
                    <div class="tab-pane fade" id="my-docs" role="tabpanel">
                        <div class="card no-hover">
                            <div class="card-body">
                                <h5 class="card-title">Tài liệu của tôi</h5>
                                <ul class="list-unstyled">
                                    <li><i class="bi bi-file-earmark-text me-2"></i> Slide Lập trình Web</li>
                                    <li><i class="bi bi-file-earmark-text me-2"></i> Bài tập Cơ sở dữ liệu</li>
                                    <li><i class="bi bi-file-earmark-text me-2"></i> Đồ án Hệ thống thông tin</li>
                                </ul>
                                <a href="#" class="btn btn-primary-custom btn-sm">Xem tất cả</a>
                            </div>
                        </div>
                    </div> 

                    <!-- Tab: Bài viết đã thích -->
                    <div class="tab-pane fade" id="liked-posts" role="tabpanel">
                        <div class="card no-hover">
                            <div class="card-body">
                                <h5 class="card-title">Bài viết đã thích</h5>
                                <p>Hiển thị danh sách bài viết đã thích ở đây.</p>
                            </div>
                        </div>
                    </div> 

                    <!-- Tab: Tài liệu đã lưu -->
                    <div class="tab-pane fade" id="saved-docs" role="tabpanel">
                        <div class="card no-hover">
                            <div class="card-body">
                                <h5 class="card-title">Tài liệu đã lưu</h5>
                                <ul class="list-unstyled">
                                    <li><i class="bi bi-file-earmark-text me-2"></i> Bài tập Lập trình C++</li>
                                    <li><i class="bi bi-file-earmark-text me-2"></i> Slide Toán rời rạc</li>
                                    <li><i class="bi bi-file-earmark-text me-2"></i> Đồ án Web cơ bản</li>
                                </ul>
                                <a href="#" class="btn btn-primary-custom btn-sm">Xem tất cả</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>
</html>