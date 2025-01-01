<%@page import="V_DetailsDocModal.DetailsDoc"%>
<%@page import="CommonModal.MethodCommon"%>
<%@page import="V_DetailsPostModal.DetailsPost"%>
<%@page import="DocumentModal.Document"%>
<%@page import="StatusPostModal.StatusPost"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% User targetUser = (User)request.getAttribute("targetUser"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= targetUser.getName() %> - HUSCZone</title>
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
    
    <div class="profile-header text-center">
	    <% if (targetUser.getAvatar() == null || targetUser.getAvatar().isBlank()) { %>
	        <img src="../images/default-avt.jpg" style="width: 120px; height: 120px" alt="Default" class="rounded-circle mb-3">
	    <% } else { %>
        <img src="<%= request.getContextPath() %><%= targetUser.getAvatar() %>" style="width: 120px; height: 120px" alt="Avatar" class="rounded-circle mb-3">
    	<% } %>
	    <h2 class="mb-0"><%= targetUser.getName() %></h2>
	</div>


    <div class="container my-4">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-4">
                <div class="card no-hover">
                    <div class="card-body">
                        <h5 class="card-title">Thông tin cá nhân</h5>
                        <p class="mt-3"><strong>Email:</strong> <%= targetUser.getEmail() %></p>
                        <p><strong>Giới tính:</strong> <%= targetUser.getGender() %></p>
	                    
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <!-- Tabs -->
                <ul class="nav nav-tabs" id="profileTabs" role="tablist">
                    <li class="nav-item">
                        <button class="nav-link active" id="posts-tab" data-bs-toggle="tab" data-bs-target="#posts" type="button">Bài viết</button>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" id="my-docs-tab" data-bs-toggle="tab" data-bs-target="#my-docs" type="button">Tài liệu</button>
                    </li>
                </ul>

                <!-- Tab content -->
                <div class="tab-content mt-3">
                    <!-- Tab: Bài viết của tôi -->
                    <div class="tab-pane fade show active" id="posts" role="tabpanel">

						<% if (dsStt.size() == 0) { %>
					    	<div class="alert alert-warning" role="alert">
					             <%= targetUser.getName() %> chưa có bài đăng nào. 
					        </div>
					    <% } else { %>
	                        <% for (DetailsPost stt : dsStt) { %>
							    <div class="card no-hover mb-3">
							        <div class="card-body">
							            <div class="d-flex align-items-center mb-3">
							                <% if (stt.getAvatar() == null || stt.getAvatar().isBlank()) { %>
							                    <a href="../user-profile?userId=<%= stt.getUploadedBy() %>">
							                        <img src="../images/default-avt.jpg" style="width: 50px; height: 50px;" alt="Default" class="rounded-circle me-3">
							                    </a>
							                <% } else { %>
							                    <a href="../user-profile?userId=<%= stt.getUploadedBy() %>">
							                        <img src="<%= request.getContextPath() %><%= stt.getAvatar() %>" style="width: 50px; height: 50px" alt="Avatar" class="rounded-circle me-3">
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
							                
							                <div class="ms-auto"> 
								                <a class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#reportModal<%= stt.getPostID() %>">
								                    <i class="bi bi-flag"></i> Báo cáo
								                </a>
								            </div>
							            </div>
							            <p><%= stt.getPostContent() %></p>
							            <% if (stt.getImagePath() != null && !stt.getImagePath().isBlank()) { %>
							            	<a href="../details?postID=<%= stt.getPostID() %>">
							                	<img src="<%= request.getContextPath() %><%= stt.getImagePath() %>" alt="Post Image" class="img-fluid rounded" style="width: 400px; height: 400px;">
							            	</a>
							            <% } %>
							        </div>
							    </div>
							
							    <!-- Modal Báo cáo -->
							    <div class="modal fade" id="reportModal<%= stt.getPostID() %>" tabindex="-1" aria-labelledby="reportModalLabel<%= stt.getPostID() %>" aria-hidden="true">
							        <div class="modal-dialog">
							            <div class="modal-content">
							                <div class="modal-header">
							                    <h5 class="modal-title" id="reportModalLabel<%= stt.getPostID() %>">Báo cáo bài viết</h5>
							                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							                </div>
							                <form method="post" action="../interact">
							                	<input type="hidden" name="postID" value="<%= stt.getPostID() %>">
							                	<input type="hidden" name="reportInUserProfile" value="true">
							                	<input type="hidden" name="sttOf" value="<%= stt.getUploadedBy()%>">
							                    <div class="modal-body">
							                        <textarea name="txtContentReport" class="form-control" rows="3" placeholder="Mô tả lý do báo cáo bài viết này..." required></textarea>
							                    </div>
							                    <div class="modal-footer">
							                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
							                        <button type="submit" name="btn-report" value="btn-report" class="btn btn-danger">Gửi</button>
							                    </div>
							                </form>
							            </div>
							        </div>
							    </div>
							<% } %>
							
							<% if (pageCount > 1) { %>
							    <nav>
								    <ul class="pagination justify-content-center mt-4">
								        <li class="page-item <%= currentPage > 1 ? "" : "disabled" %>">
								            <a class="page-link" href="<%= currentPage > 1 ? "../user-profile?userID=" + targetUser.getUserID() + "&page=" + (currentPage - 1) : "#" %>" tabindex="-1" aria-disabled="true">
								                <i class="bi bi-chevron-left"></i>
								            </a>
								        </li>
								
								        <% for (int p = 1; p <= pageCount; p++) { %>
								            <li class="page-item <%= p == currentPage ? "active" : "" %>">
								                <a class="page-link" href="../user-profile?userID=<%= targetUser.getUserID() %>&page=<%= p %>">
								                    <%= p %>
								                </a>
								            </li>
								        <% } %>
								
								        <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
								            <a class="page-link" href="<%= currentPage < pageCount ? "../user-profile?userID=" + targetUser.getUserID() + "&page=" + (currentPage + 1) : "#" %>">
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
					            <h5 class="card-title">Tài liệu của <%= targetUser.getName() %></h5>
					            <% if (dsDocs.size() == 0) { %>
					                <div class="alert alert-warning" role="alert">
					                    <%= targetUser.getName() %> chưa có tài liệu nào.
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
					                <a href="../docs-of-user?posterID=<%= targetUser.getUserID() %>" class="btn btn-primary-custom btn-sm">Xem tất cả</a>
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
