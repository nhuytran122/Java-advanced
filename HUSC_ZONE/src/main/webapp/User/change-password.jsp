<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đổi mật khẩu - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
    <link rel="stylesheet" href="css/login-style.css">
</head>

<% 
    String message = (String) request.getAttribute("message");
    String messageType = (String) request.getAttribute("messageType");
    String currentPass = (String) request.getAttribute("txtCurrPw");
    String newPass = (String) request.getAttribute("txtNewPw");
    String confirmPass = (String) request.getAttribute("txtConfNewPw");
%>

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
                        	<button type="submit" name="btnUpdateProfile" value="btnUpdateProfile" class="btn btn-primary-custom">Chỉnh sửa thông tin</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <!-- Tab content -->
                <div class="form-bg">
                	<div class="form-container">
                            <h3 class="title text-center">Đổi mật khẩu</h3>
                            <form action="../change-password" method="post" class="form-horizontal">
                                    <div class="mb-3 form-group">
                                        <label>Mật khẩu hiện tại</label> <span class="text-danger">*</span>
                                        <input class="form-control mt-2" type="password" 
							               name="txtCurrPw" 
							               value="<%= currentPass != null ? currentPass : "" %>" 
							               required/>
                                    </div>
        
                                    <div class="mb-3 form-group">
                                        <label class="form-label">Mật khẩu mới</label> <span class="text-danger">*</span>
                                        <input type="password" class="form-control" 
							               name="txtNewPw" 
							               value="<%= newPass != null ? newPass : "" %>" 
							               required>
                                    </div>
        
                                    <div class="mb-3 form-group">
                                        <label class="form-label">Xác nhận mật khẩu mới</label> <span class="text-danger">*</span>
                                        <input type="password" class="form-control" 
							               name="txtConfNewPw" 
							               value="<%= confirmPass != null ? confirmPass : "" %>" 
							               required/>
                                    </div>
                                    <% if (message != null && messageType != null) { %>
									    <div class="alert alert-<%= messageType %> text-center">
									        <%= message %>
									    </div>
									<% } %>
        							 <% if (request.getAttribute("isPasswordChanged") == null) { %>
                                    <div class="text-center">
		                                <a href="../user-profile" class="btn btn-cancel-edit-inf me-2">
		                                    Hủy
		                                </a>
		                                <button type="submit" name="btn-save-pw" class="btn btn-save-edit-inf">
		                                     Đổi mật khẩu
		                                </button>
		                            </div>
		                            <%} %>
                                </form>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    
</body>
</html>
