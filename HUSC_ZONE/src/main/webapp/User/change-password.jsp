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
            <div class="col-md-4">
                <div class="card no-hover">
                    <div class="card-body">
                        <h5 class="card-title">Thông tin cá nhân</h5>
                        <p class="mt-3"><strong>Email:</strong> nhuy@gmail.com</p>
                        <p><strong>Số điện thoại:</strong> 0123-456-789</p>
                        <p><strong>Ngày sinh:</strong> 15/07/1999</p>
                        <p><strong>Địa chỉ:</strong> TP. Hồ Chí Minh</p>
                        <a href="/update-profile.html" class="btn btn-primary-custom me-2">Chỉnh sửa thông tin</a>
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <div class="tab-content">
                    <main class="col">
                        <div class="card no-hover mb-3">
                            <h4 class="mb-0 p-3 text-center">Đổi mật khẩu</h4>
                            
                            <div class="card-body">
                                <form>
                                    <div class="mb-3">
                                        <label class="form-label">Mật khẩu hiện tại</label>
                                        <input type="password" class="form-control" value="">
                                    </div>
        
                                    <div class="mb-3">
                                        <label class="form-label">Mật khẩu mới</label>
                                        <input type="password" class="form-control" value="">
                                    </div>
        
                                    <div class="mb-3">
                                        <label class="form-label">Xác nhận mật khẩu mới</label>
                                        <input type="password" class="form-control" value="">
                                    </div>
        
                                    <div class="text-center">
		                                <a href="/show-docs.html" class="btn btn-danger mt-2 py-2 px-3 pb-2 me-2">
		                                    Hủy
		                                </a>
		                                <button type="submit" class="btn btn-success py-2">
		                                    Đổi mật khẩu
		                                </button>
		                            </div>
                                </form>
                            </div>
                        </div>
                    </main>
                </div>
            </div>
        </div>
    </div>

    <footer class="bg-primary-custom text-white text-center p-3">
        <p>© 2024 HUSCZone. All Rights Reserved.</p>
    </footer>
</body>
</html>
