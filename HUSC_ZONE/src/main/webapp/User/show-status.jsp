<%@page import="CommonModal.MethodCommon"%>
<%@page import="V_DetailsPostModal.DetailsPost"%>
<%@page import="StatusPostModal.StatusPost"%>
<%@page import="UserModal.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HUSCZone - Bài đăng</title>
    <%@ include file="layout/import.jsp" %>
    
    <style>
        .btn-like,
        .btn-comment {
            color: #6c757d; 
            border-color: #6c757d;
        }
            
        .btn-comment:hover {
            background-color: #74b9ff; 
        }
            
        .btn-like:hover {
            background-color: #ff4d4d;     
        }
    </style>
    
</head>
<%
    ArrayList<DetailsPost> dsPosts = (ArrayList<DetailsPost>) request.getAttribute("dsPosts");
    ArrayList<User> dsUsers = (ArrayList<User>) request.getAttribute("dsUsers");
    int pageCount = (Integer) request.getAttribute("pageCount");
    int currentPage = (Integer) request.getAttribute("currentPage");
%>
<body class="bg-light">
    <%@ include file="layout/navbar_for_Post.jsp" %>
    
    <div class="container-fluid my-3">
        <div class="row">
            <%@ include file="layout/sidebar.jsp" %>
            
            <main class="col-md-9 my-4">
                <div class="card no-hover mb-3">
                    <div class="card-body">
                        <div class="d-flex align-items-center mb-3">
                            <img src="https://via.placeholder.com/50" alt="Avatar" class="rounded-circle me-3" style="width: 50px; height: 50px;">
                            <!-- Thêm data-bs-toggle và data-bs-target -->
                            <input class="form-control rounded-pill" 
                                   placeholder="Như Ý ơi, bạn muốn chia sẻ gì nào?" 
                                   style="background-color: #f8f9fa;" 
                                   data-bs-toggle="modal" 
                                   data-bs-target="#postModal" 
                                   readonly>
                        </div>
                    </div>
                </div>
                
                <div class="modal fade" id="postModal" tabindex="-1" aria-labelledby="postModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
	                    <form action="../save-post" method="post" enctype="multipart/form-data">
	                        <div class="modal-content">
	                            <div class="modal-header">
	                                <h5 class="modal-title" id="postModalLabel">Tạo bài đăng mới</h5>
	                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                            </div>
	                            <div class="modal-body">
	                                <div class="mb-3">
	                                    <textarea class="form-control" rows="5" placeholder="<%= user.getName() %> ơi, bạn muốn chia sẻ gì nào?"
		                                    name="txtContent" required></textarea>
	                                </div>
	                                <div class="card mb-3" style="width: 465px; margin: auto; border: 1px solid #ddd; border-radius: 8px;">
	                                    <div class="card-body text-center">
	                                        <div class="upload-area" style="position: relative; border: 2px dashed #ddd; border-radius: 8px; padding: 20px; background-color: #f9f9f9;">
	                                            <label style="cursor: pointer;">
	                                            	<strong>Thêm ảnh/video</strong>
	                                                <input type="file" name="fileAnh" accept="image/*">
	                                            </label>
	                                           
	                                            <button type="button" class="btn-close position-absolute" style="top: 10px; right: 10px;"></button>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="modal-footer">
	                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
	                                <button type="submit" 
                                	name=btnPostInPage value="true" class="btn btn-success">Đăng bài</button>
	                            </div>
	                        </div>
	                    </form>
                    </div>
                </div>
                <%
				    int n = 0;
				    if (dsPosts != null && !dsPosts.isEmpty()) {
				        n = dsPosts.size();
				    } else 
				        n = dsUsers.size();
				%>

                <% 
                    if (dsPosts != null && !dsPosts.isEmpty()) {
                        for (DetailsPost stt : dsPosts) {
                %>
                    <div class="card no-hover mb-3">
                        <div class="card-body">
                            <div class="d-flex align-items-center mb-3">
                                 <% if (stt.getAvatar() == null || stt.getAvatar().isBlank()) { 
                                 %>
                                    <a href="../user-profile?userId=<%= stt.getUploadedBy() %>">
                                        <img src="../images/default-avt.jpg" style="width: 60px; height: 60px;" alt="Default" class="rounded-circle me-3">
                                    </a>
                                <% } else { %>
                                    <a href="../user-profile?userId=<%= stt.getUploadedBy() %>">
                                        <img src="<%= request.getContextPath() %><%= stt.getAvatar() %>" style="width: 60px; height: 60px" alt="Avatar" class="rounded-circle me-3">
                                    </a>
                                <% } %>
                            <div>
                                <h6 class="mb-0">
                                    <a href="../user-profile?userId=<%= stt.getUploadedBy() %>" class="text-decoration-none">
                                        <%= stt.getName() %>
                                    </a>
                                </h6>
                                <small class="text-muted">
									<%= stt.getUpdatedAt() == null ? MethodCommon.convertDateToString(stt.getCreatedAt()) 
										: "Đã chỉnh sửa " + MethodCommon.convertDateToString(stt.getUpdatedAt()) %>
								</small>
                            </div>
                        </div>
                        <p>
                            <%= stt.getPostContent() %>
                        </p>
                        <div>
						    <% if (stt.getImagePath() != null && !stt.getImagePath().isBlank()) { %>
						        <a href="../details?postID=<%= stt.getPostID() %>">
						            <img src="<%= request.getContextPath() %><%= stt.getImagePath() %>" 
						                 alt="Post Image" 
						                 class="img-fluid rounded" 
						                 style="width: 300px;">
						        </a>
						    <% } %>
						</div>

                    </div>
                    <div class="card-footer d-flex justify-content-between">
                        <div>
                            <a class="btn btn-outline-primary btn-sm btn-like">
                                <i class="bi bi-heart-fill text-danger"></i> Thích
                            </a>
                            <button class="btn btn-outline-primary btn-sm btn-comment" onclick="toggleComments(this)">
                                <i class="bi bi-chat"></i> Bình luận
                            </button>
                        </div>
                        <div class="ms-auto">
                            <a class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#reportModal<%= stt.getPostID() %>">
							    <i class="bi bi-flag"></i> Báo cáo
							</a>

                        </div>
                    </div>
                    
					<div class="modal fade" id="reportModal<%= stt.getPostID() %>" tabindex="-1" aria-labelledby="reportModalLabel<%= stt.getPostID() %>" aria-hidden="true">
					    <div class="modal-dialog">
					        <div class="modal-content">
					            <div class="modal-header">
					                <h5 class="modal-title" id="reportModalLabel<%= stt.getPostID() %>">Báo cáo bài viết</h5>
					                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					            </div>
					            <form method="post" action="../interact">
					                <input type="hidden" name="postID" value="<%= stt.getPostID() %>">
					                <input type="hidden" name="sttOf" value="<%= stt.getUploadedBy()%>">
					                <div class="modal-body">
					                    <textarea name="txtContentReport" class="form-control" rows="3" placeholder="Mô tả lý do báo cáo bài viết này..." required></textarea>
					                </div>
					                <div class="modal-footer">
					                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
					                    <button type="submit" name="btn-report" value="<%= stt.getPostID() %>" class="btn btn-danger">Gửi</button>
					                </div>
					            </form>
					        </div>
					    </div>
					</div>
					
                     <!-- Show bình luận dưới bài viết -->
                    <div class="comments-section m-3 d-none">
                        <div class="d-flex align-items-center mb-3">
                            <img src="https://via.placeholder.com/40" alt="User Avatar" class="rounded-circle me-2" style="width: 40px; height: 40px;">
                            <input type="text" class="form-control rounded-pill" placeholder="Viết bình luận..." />
                            <button class="btn btn-primary ms-2">Gửi</button>
                        </div>

                        <div class="comments-list">
                            <div class="d-flex align-items-start mb-3">
                                <img src="https://via.placeholder.com/40" alt="User Avatar" class="rounded-circle me-2" style="width: 40px; height: 40px;">
                                <div>
                                    <h6 class="mb-0">Nguyễn Văn A</h6>
                                    <p class="mb-1">Bài viết thú vị quá, cảm ơn bạn đã chia sẻ!</p>
                                    <small class="text-muted">10 phút trước</small>
                                </div>
                            </div>
                            <div class="d-flex align-items-start mb-3">
                                <img src="https://via.placeholder.com/40" alt="User Avatar" class="rounded-circle me-2" style="width: 40px; height: 40px;">
                                <div>
                                    <h6 class="mb-0">Trần Thị B</h6>
                                    <p class="mb-1">Mình cũng thích phim này, diễn biến rất hấp dẫn.</p>
                                    <small class="text-muted">15 phút trước</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <% 
                        }
                    } else if (dsUsers != null && !dsUsers.isEmpty()) {
                        for (User u : dsUsers) {
                %>
                    <div class="card no-hover mb-3">
					    <div class="card-body">
					        <div class="d-flex align-items-center mb-3">
					            <% if (u.getAvatar() == null || u.getAvatar().isBlank()) { 
					             %>
					                <a href="../user-profile?userId=<%= u.getUserID() %>">
					                    <img src="../images/default-avt.jpg" style="width: 60px; height: 60px;" alt="Default" class="rounded-circle me-3">
					                </a>
					            <% } else { %>
					                <a href="../user-profile?userId=<%= u.getUserID() %>">
					                    <img src="<%= request.getContextPath() %><%= u.getAvatar() %>" style="width: 60px; height: 60px" alt="Avatar" class="rounded-circle me-3">
					                </a>
					            <% } %>
					            <div>
					                <a href="../user-profile?userId=<%= u.getUserID() %>" class="text-dark fs-5 text-decoration-none" style="font-weight: bold;">
					                    <%= u.getName() %>
					                </a>
					            </div>
					        </div>
					    </div>
					</div>

                <% 
                        }
                    } else {
                        if (searchKeyword != null && !searchKeyword.isEmpty()) {
                            if (dsPosts == null || dsPosts.isEmpty()) {
                            %>
                                <p class="text-center text-danger">Không tìm thấy bài đăng nào với từ khóa <b>"<%= searchKeyword %>".</b></p>
                                
                            <%}else {
                             %>
                                <p class="text-center text-danger">Không tìm thấy người dùng nào với từ khóa <b>"<%= searchKeyword %>".</b></p>
                            <%
                            }
                        } 
                    }
                %>
                
                <% if (n > 0) { %>
					<nav>
					    <ul class="pagination justify-content-center mt-4">
					        <li class="page-item <%= currentPage > 1 ? "" : "disabled" %>">
					            <a class="page-link" href="<%= currentPage > 1 ? "../status-post?page=" + (currentPage - 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" tabindex="-1" aria-disabled="true">
					                <i class="bi bi-chevron-left"></i>
					            </a>
					        </li>
					        
					        <% for (int p = 1; p <= pageCount; p++) { %>
			                  <li class="page-item <%= p == currentPage ? "active" : "" %>">
			                    <a class="page-link" href="../status-post?page=<%= p %><%= searchKeyword != null ? "&txtSearch=" + searchKeyword : "" %>">
			                      <%= p %>
			                    </a>
			                  </li>
			                <% } %>
					        <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
					            <a class="page-link" href="<%= currentPage < pageCount ? "../status-post?page=" + (currentPage + 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>">
					                 <i class="bi bi-chevron-right"></i>
					            </a>
					        </li>
					    </ul>
					</nav>
				<% } %>
            </main>
        </div>
    </div>

</body>

<script>
        // JS để bật/tắt hiển thị cmt dưới bài viết
        function toggleComments(button) {
            const commentsSection = button.closest('.card').querySelector('.comments-section');
            if (commentsSection.classList.contains('d-none')) {
                commentsSection.classList.remove('d-none');
                button.innerHTML = '<i class="bi bi-chat"></i> Ẩn bình luận';
            } else {
                commentsSection.classList.add('d-none');
                button.innerHTML = '<i class="bi bi-chat"></i> Bình luận';
            }
        }
</script>
</html>
