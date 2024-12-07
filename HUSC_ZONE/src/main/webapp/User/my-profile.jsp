<%@page import="V_DetailsDocModal.DetailsDoc"%>
<%@page import="CommonModal.MethodCommon"%>
<%@page import="V_DetailsPostModal.DetailsPost"%>
<%@page import="DocumentModal.Document"%>
<%@page import="StatusPostModal.StatusPost"%>
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

<%
    ArrayList<DetailsPost> dsStt = (ArrayList<DetailsPost>) request.getAttribute("dsStt");
    int pageCountPosts = (Integer) request.getAttribute("pageCountPosts");
    int currentPagePosts = (Integer) request.getAttribute("currentPagePosts");
    
    ArrayList<DetailsDoc> dsDocs = (ArrayList<DetailsDoc>) request.getAttribute("dsDocs");
%>
    
    <%@ include file="layout/navbar_for_Post.jsp" %>
    <%@ include file="layout/profile-header.jsp" %>


    <div class="container my-4">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-4">
                <div class="card no-hover">
                    <div class="card-body">
                        <h5 class="card-title">Thông tin cá nhân</h5>
                        <p class="mt-3"><strong>Email:</strong> <%= user.getEmail() %></p>
                        <p><strong>Số điện thoại:</strong> <%= user.getPhone() %></p>
                        <p><strong>Giới tính:</strong> <%= user.getGender() %></p>
	                        <div class="d-flex">
	                        <form action="../edit-profile" method="post">
	                            <button type="submit" name="btnUpdateProfile" value="btnUpdateProfile" class="btn btn-primary-custom me-2">Chỉnh sửa thông tin</button>
	                            <button type="submit" name="btnChangePW" value="btnUpdateProfile" class="btn btn-primary-custom">Đổi mật khẩu</button>
	                        </form>
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
                </ul>

                <!-- Tab content -->
                <div class="tab-content mt-3">
                    <!-- Tab: Bài viết của tôi -->
                    <div class="tab-pane fade show active" id="posts" role="tabpanel">
                        <div class="card no-hover" style="margin-bottom: 20px;">
                            <div class="card-body">
                                <div class="d-flex align-items-center mb-3">
                                    <% if (user.getAvatar() == null || user.getAvatar().isBlank()) { %>
								        <img src="../images/default-avt.jpg" class="rounded-circle me-3" style="width: 50px; height: 50px;">
								    <% } else { %>
							        	<img src="<%= request.getContextPath() %><%= user.getAvatar() %>" class="rounded-circle me-3" style="width: 50px; height: 50px;">
							    	<% } %>
                                    <input class="form-control rounded-pill" placeholder="<%= user.getName() %> ơi, bạn muốn chia sẻ gì nào?" style="background-color: #f8f9fa;" data-bs-toggle="modal" data-bs-target="#postModal" readonly>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="postModal" tabindex="-1" aria-labelledby="postModalLabel" aria-hidden="true">
		                    <div class="modal-dialog">
			                    <form action="../edit-status" method="post" enctype="multipart/form-data">
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
		                                	name="btnAdd" value="add" class="btn btn-success">Đăng bài</button>
			                            </div>
			                        </div>
			                    </form>
		                    </div>
		                </div>

                        <% for (DetailsPost stt : dsStt) { %>
						    <div class="card no-hover mb-3">
						        <div class="card-body">
						            <div class="d-flex align-items-center mb-3">
						                <% if (user.getAvatar() == null || user.getAvatar().isBlank()) { %>
									        <img src="../images/default-avt.jpg" class="rounded-circle me-3" style="width: 50px; height: 50px;">
									    <% } else { %>
								        	<img src="<%= request.getContextPath() %><%= user.getAvatar() %>" class="rounded-circle me-3" style="width: 50px; height: 50px;">
								    	<% } %>
						                <div>
						                    <h6 class="mb-0"><%= user.getName() %></h6>
						                    <small class="text-muted"><%= MethodCommon.convertDateToString(stt.getCreatedAt()) %></small>
						                </div>
						            </div>
						            <p><%= stt.getPostContent() %></p>
						            <% if (stt.getImagePath() != null) { %>
						                <img src="<%= request.getContextPath() %><%= stt.getImagePath() %>" alt="Post Image" class="img-fluid rounded" style="width: 400px; height: 400px;">
						            <% } %>
						        </div>
						        <div class="card-footer d-flex justify-content-between">
			                        <div>
			                            <a class="btn btn-outline-primary btn-sm btn-like">
			                                <i class="bi bi-heart-fill text-danger"></i> Thích
			                            </a>
			                            <span class="ms-2"><%= stt.getCountLikes() %> lượt thích</span>
			                        </div>
			                    </div>
			                    
						    </div>
						<% } %>
                    </div>

                    <!-- Tab: Tài liệu của tôi -->
                    <div class="tab-pane fade" id="my-docs" role="tabpanel">
					    <div class="card no-hover">
					        <div class="card-body">
					            <h5 class="card-title">Tài liệu của tôi</h5>
					            <% if (dsDocs.size() == 0) { %>
					                <div class="alert alert-warning" role="alert">
					                    Bạn chưa có tài liệu nào.
					                </div>
					            <% } else { %>
					                <ul class="list-unstyled">
					                    <% for (DetailsDoc doc : dsDocs) { %>
					                        <li>
					                            <a href="../details?docsID=<%= doc.getDocumentID() %>" class="text-dark text-decoration-none fw-medium">
					                                <i class="bi bi-file-earmark-text me-2"></i> <%= doc.getTitle() %>
					                            </a>
					                        </li>
					                    <% } %>
					                </ul>
					                <a href="../docs-of-user?posterID=<%= user.getUserID() %>" class="btn btn-primary-custom btn-sm">Xem tất cả</a>
					            <% } %>
					        </div>
					    </div>
					</div>
                    
                </div>
            </div>
        </div>

    </div>
</body>
</html>
