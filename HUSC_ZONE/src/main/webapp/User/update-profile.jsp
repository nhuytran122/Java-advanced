<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HUSCZone - My Profile</title>
    <%@ include file="layout/import.jsp" %>
    <style>
    </style>
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
                        <a href="/change-password.html" class="btn btn-primary-custom">Đổi mật khẩu</a>
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <!-- Tab content -->
                <div class="tab-content">
                    <main class="col">
                        <div class="card no-hover mb-3">
                            <h4 class="mb-0 p-3 text-center">Chỉnh sửa thông tin cá nhân</h4>
                            <div class="card-body">
                                <form>
                                    <div class="mb-3">
                                        <label class="form-label">Họ và tên</label>
                                        <input type="text" class="form-control" value="Như Ý">
                                    </div>
        
                                    <div class="mb-3">
                                        <label class="form-label">Email</label>
                                        <input type="email" class="form-control" value="nhuy@gmail.com" disabled>
                                    </div>
        
                                    <div class="mb-3">
                                        <label class="form-label">Số điện thoại</label>
                                        <input type="text" class="form-control" value="0123-456-789">
                                    </div>
        
                                    <div class="mb-3">
                                        <label class="form-label">Ngày sinh</label>
                                        <input type="date" class="form-control" value="1999-07-15">
                                    </div>
        
                                    <div class="mb-3">
                                        <label class="form-label">Giới tính</label>
                                        <select class="form-select" >
                                            <option value="" selected>-- Chọn giới tính --</option>
                                            <option value="male">Nam</option>
                                            <option value="female">Nữ</option>
                                        </select>
                                    </div>
        
                                    <div class="text-center">
		                                <a href="/show-docs.html" class="btn btn-danger mt-2 py-2 px-3 pb-2 me-2">
		                                    Hủy
		                                </a>
		                                <button type="submit" class="btn btn-success py-2">
		                                     Lưu thay đổi
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
</body>
</html>
