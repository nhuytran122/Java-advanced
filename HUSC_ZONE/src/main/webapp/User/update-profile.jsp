<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HUSCZone - Chỉnh sửa thông tin</title>
    <%@ include file="layout/import.jsp" %>
    <link rel="stylesheet" href="css/login-style.css">
</head>

<style>
.form-container .form-horizontal .btn-save-edit-inf, 
.form-container .form-horizontal .btn-cancel-edit-inf {
    border-radius: 50px;
    padding: 12px;
    width: 45%;
    color: #fff;
}

.form-container .form-horizontal .btn-save-edit-inf {
    background-color: #28a745;
}

.form-container .form-horizontal .btn-save-edit-inf:hover {
    background-color: #218838;
}

.form-container .form-horizontal .btn-cancel-edit-inf {
    background-color: #dc3545;
}

.form-container .form-horizontal .btn-cancel-edit-inf:hover {
    background-color: #c82333;
}


</style>
<body>
    
    <%@ include file="layout/navbar_for_Post.jsp" %>
    <%@ include file="layout/profile-header.jsp" %>

    <div class="container my-4">
        <div class="row">
            <div class="col-md-4">
                <div class="card no-hover">
                    <div class="card-body">
                        <h5 class="card-title">Thông tin cá nhân</h5>
                        <p class="mt-3"><strong>Email:</strong> <%= user.getEmail() %></p>
                        <p><strong>Số điện thoại:</strong> <%= user.getPhone() %></p>
                        <p><strong>Giới tính:</strong> <%= user.getGender() %></p>
                        <form action="../edit" method="post">
                        	<button type="submit" name="btnChangePW" value="btnChangePW" class="btn btn-primary-custom">Đổi mật khẩu</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <!-- Tab content -->
                <div class="form-bg">
                	<div class="form-container">
                            <h3 class="title text-center">Chỉnh sửa thông tin cá nhân</h3>
                            <form action="../save-profile" method="post" class="form-horizontal" enctype="multipart/form-data">
                                    <div class="mb-3 form-group">
                                        <label>Tên</label> <span class="text-danger">*</span>
                                        <input class="form-control mt-2" type="text" 
                                        	name="txtHoten" value="<%= user.getName() %>" required/>
                                    </div>
        
                                    <div class="mb-3 form-group">
                                        <label class="form-label">Email</label>
                                        <input type="email" class="form-control" value="<%= user.getEmail() %>" disabled>
                                    </div>
        
                                    <div class="mb-3 form-group">
                                        <label class="form-label">Số điện thoại</label>
                                        <input type="text" name="txtSdt" class="form-control" 
                                        	value="<%= user.getPhone() %>" />
                                    </div>
        
                                    <div class="mb-3 form-group">
                                        <label class="form-label">Giới tính</label>
                                        <select class="form-control p-1 px-4" name="txtGioiTinh" required>
                                            <option value="" <%= (user.getGender() == null) ? "selected" : "" %>>-- Chọn giới tính --</option>
	                                        <option value="Nam" <%= "Nam".equals(user.getGender()) ? "selected" : "" %>>Nam</option>
	                                        <option value="Nữ" <%= "Nữ".equals(user.getGender()) ? "selected" : "" %>>Nữ</option>
	                                        <option value="Khác" <%= "Khác".equals(user.getGender()) ? "selected" : "" %>>Khác</option>
                                        </select>
                                    </div>
                                    
                                    <div class="mb-3 form-group">
                                         <label class="form-label">Ảnh</label>
					                     <input type="file" class="form-control px-4" name="fileAnh" accept="image/*" id="fileInput">
                                    </div>
                                    
                                    <div class="mb-3 form-group">
                                        <label class="form-label">Preview Ảnh</label>
										<div class="col-sm-10">
										  <!-- Hiển thị ảnh nếu có -->
										  <img id="imagePreview" src="<%= request.getContextPath() %><%= user.getAvatar() != null ?  user.getAvatar() : "" %>" alt="Image Preview" style="max-height: 250px; display: <%= user.getAvatar() != null ? "block" : "none" %>;"/>
										</div>
                                    </div>
        
                                    <div class="text-center">
		                                <a href="../user-profile" class="btn btn-cancel-edit-inf me-2">
		                                    Hủy
		                                </a>
		                                <button type="submit" name="btn-save-edit-inf" class="btn btn-save-edit-inf">
		                                     Lưu thay đổi
		                                </button>
		                            </div>
                                </form>
                            </div>
                        </div>
                    
                </div>
            </div>
        </div>
<%@ include file="layout/script_preview_image.jsp" %>
</body>
</html>
