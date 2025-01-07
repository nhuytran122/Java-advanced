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
    <title>My Profile - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
</head>
<style>
	a {
		text-decoration: none !important;
	}
</style>
<body>

<%
    ArrayList<DetailsPost> dsStt = (ArrayList<DetailsPost>) request.getAttribute("dsStt");
    int pageCount = (Integer) request.getAttribute("pageCount");
    int currentPage = (Integer) request.getAttribute("currentPage");
    
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
	                        <form action="../edit" method="post">
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
                                    <% if (user.getAvatar() != null && !user.getAvatar().isBlank()) { %>
										<img src="<%= request.getContextPath() %><%= user.getAvatar() %>" class="rounded-circle me-3" style="width: 50px; height: 50px;">
								    <% } else { %>
							        	<img src="../images/default-avt.jpg" class="rounded-circle me-3" style="width: 50px; height: 50px;">
							    	<% } %>
                                    <input class="form-control rounded-pill" placeholder="<%= user.getName() %> ơi, bạn muốn chia sẻ gì nào?" style="background-color: #f8f9fa;" data-bs-toggle="modal" data-bs-target="#postModal" readonly>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="postModal" tabindex="-1" aria-labelledby="postModalLabel" aria-hidden="true">
		                    <div class="modal-dialog">
			                    <form action="../edit-post" method="post" enctype="multipart/form-data">
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
		                
						<% if (dsStt.size() == 0) { %>
					    	<div class="alert alert-warning" role="alert">
					             Bạn chưa có bài đăng nào. 
					        </div>
					    <% } else { %>
	                        <% for (DetailsPost stt : dsStt) { %>
							    <div class="card no-hover mb-3">
							        <div class="card-body">
							            <div class="d-flex align-items-center justify-content-between mb-3">
							                <div class="d-flex align-items-center">
							                    <% if (stt.getAvatar() != null && !stt.getAvatar().isBlank()) { %>
							                        <a href="../user-profile?userId=<%= stt.getUploadedBy() %>">
							                            <img src="<%= request.getContextPath() %><%= stt.getAvatar() %>" style="width: 50px; height: 50px;" alt="Avatar" class="rounded-circle me-3">
							                        </a>
							                    <% } else { %>
							                        <a href="../user-profile?userId=<%= stt.getUploadedBy() %>">
							                            <img src="../images/default-avt.jpg" style="width: 50px; height: 50px;" alt="Default" class="rounded-circle me-3">
							                        </a>
							                    <% } %>
							                    <div>
							                        <h6 class="mb-0">
							                            <a href="../user-profile?userId=<%= stt.getUploadedBy() %>" class="text-decoration-none">
							                                <%= stt.getName() %>
							                            </a>
							                        </h6>
							                        <a href="../details?postID=<%= stt.getPostID() %>">
								                        <small class="text-muted">
														  <%= stt.getUpdatedAt() == null ? MethodCommon.convertDateToString(stt.getCreatedAt()) 
																  : "Đã chỉnh sửa " + MethodCommon.convertDateToString(stt.getUpdatedAt()) %>
														</small>
													</a>

							                    </div>
							                </div>
							
							                <!-- Dropdown menu -->
							                <div class="dropdown">
							                    <button class="btn btn-link text-dark p-0" type="button" data-bs-toggle="dropdown" aria-expanded="false">
							                        <i class="bi bi-three-dots"></i>
							                    </button>
							                    <ul class="dropdown-menu dropdown-menu-end">
							                        <li>
							                        	<a class="dropdown-item" href="../edit?editPost=true&postID=<%= stt.getPostID() %>">
							                        		<i class="bi bi-pencil me-2"></i> Chỉnh sửa bài viết
							                        	</a>
							                        </li>
							                        <li>
								                        <button 
									                    type="button" 
									                    class="dropdown-item"
									                    data-bs-toggle="modal" 
									                    data-bs-target="#deleteModal<%= stt.getPostID() %>" 
									                    title="Xóa">
									                    <i class="bi bi-trash me-2"></i> Xóa bài viết
										                </button>
									                </li>
							                    </ul>
							                </div>
							            </div>
							
							            <p><%= stt.getPostContent() %></p>
							            <% if (stt.getImagePath() != null && !stt.getImagePath().isBlank()) { %>
								            <a href="../details?postID=<%= stt.getPostID() %>">
								                <img src="<%= request.getContextPath() %><%= stt.getImagePath() %>" alt="Post Image" class="img-fluid rounded" style="width: 400px; height: 400px;">
								            </a>
							            <% } %>
							        </div>
							        
							        <div class="card-footer d-flex justify-content-between">
									    <div class="d-flex align-items-center">
									        <span class="text-muted me-2">
									            <i class="bi bi-heart-fill text-danger"></i> <%= stt.getCountLikes() %>
									        </span>
									        <span class="text-muted mx-2">
									            <i class="bi bi-chat-dots-fill me-1"></i> <%= stt.getCountComments() %> bình luận
									        </span>
									    </div>
									</div>

							        
							        <div class="modal fade" id="deleteModal<%= stt.getPostID() %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
			                          <div class="modal-dialog">
			                            <div class="modal-content">
			                              <div class="modal-header">
			                                <h5 class="modal-title text-danger" id="deleteModalLabel<%= stt.getPostID() %>">
			                                	<i class="bi bi-exclamation-triangle-fill me-2"></i>
			                                	Xác nhận xóa bài viết
			                                </h5>
			                              </div>
			                              <div class="modal-body">
			                                Bạn có chắc chắn muốn xóa bài viết này không?
			                              </div>
			                              <div class="modal-footer">
			                                <form method="post" action="../edit-post">
			                                  <input type="hidden" name="postID" value="<%= stt.getPostID() %>">
			                                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
			                                  <button type="submit" name="btnDeleteStt" value="<%= stt.getPostID() %>" class="btn btn-danger">Xóa</button>
			                                </form>
			                              </div>
			                            </div>
			                          </div>
			                        </div>
							    </div>
							    
							<% } %>
							
							<% if (pageCount > 1) { %>
							    <nav>
								    <ul class="pagination justify-content-center mt-4">
									    <li class="page-item <%= currentPage > 1 ? "" : "disabled" %>">
									        <a class="page-link" href="<%= currentPage > 1 ? "../user-profile?page=" + (currentPage - 1) : "#" %>" tabindex="-1" aria-disabled="true">
											    <i class="bi bi-chevron-left"></i>
											</a>
									    </li>
									    
									    <% for (int p = 1; p <= pageCount; p++) { %>
									        <li class="page-item <%= p == currentPage ? "active" : "" %>">
									            <a class="page-link" href="../user-profile?page=<%= p %>">
									                <%= p %>
									            </a>
									        </li>
									    <% } %>
									    
									    <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
									        <a class="page-link" href="<%= currentPage < pageCount ? "../user-profile?page=" + (currentPage + 1) : "#" %>">
									            <i class="bi bi-chevron-right"></i>
									        </a>
									    </li>
									</ul>
								</nav>
							<% } %>
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
