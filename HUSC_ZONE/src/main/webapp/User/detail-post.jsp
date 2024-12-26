<%@page import="V_DetailsCommentModal.DetailsComment"%>
<%@page import="CommonModal.MethodCommon"%>
<%@page import="CommentModal.Comment"%>
<%@page import="V_DetailsPostModal.DetailsPost"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
DetailsPost dtlPost = (DetailsPost)request.getAttribute("dtlPost");
ArrayList<DetailsComment> listCmts = (ArrayList<DetailsComment>)request.getAttribute("listCmts");
boolean isLiked = (boolean)request.getAttribute("isLiked");

%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết bài viết - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
    <link rel="stylesheet" href="css/detail-post-style.css">
</head>
<body>
    <%@ include file="layout/navbar_for_Post.jsp" %>

    <div class="main-container">
        <div class="image-section d-flex justify-content-center align-items-center" 
			<% if (dtlPost.getImagePath() == null || dtlPost.getImagePath().isEmpty()) { %>
		         style="display: none; background-color: #ffffff;" 
		     <% } else{ %>
		>
		    		<img src="<%= request.getContextPath() %><%= dtlPost.getImagePath() %>" 
			         alt="Post Image" 
			         class="img-fluid rounded post-image">
		        <%} %>
		</div>

        <div class="content-section">
            <div class="post-header d-flex align-items-center">
                <% if (dtlPost.getAvatar() == null || dtlPost.getAvatar().isBlank()) { 
                %>
                    <a href="../user-profile?userId=<%= dtlPost.getUploadedBy() %>">
                        <img src="../images/default-avt.jpg" style="width: 60px; height: 60px;" alt="Default" class="profile-image me-2">
                    </a>
                <% } else { %>
                    <a href="../user-profile?userId=<%= dtlPost.getUploadedBy() %>">
                        <img src="<%= request.getContextPath() %><%= dtlPost.getAvatar() %>" style="width: 60px; height: 60px" alt="Avatar" class="rounded-circle me-3">
                    </a>
                <% } %>
                <div class="flex-grow-1">
                    <div class="d-flex align-items-center">
                        <h6 class="mb-0">
	                        <a href="../user-profile?userId=<%= dtlPost.getUploadedBy() %>" class="text-decoration-none">
	                         	<%= dtlPost.getName() %>
	                        </a>
	                    </h6>
                        <i class="fas fa-check-circle verified-badge ms-1"></i>
                    </div>
                    <span class="post-time">
                    	<%= dtlPost.getUpdatedAt() == null ? MethodCommon.convertDateToString(dtlPost.getCreatedAt()) 
							: "Đã chỉnh sửa " + MethodCommon.convertDateToString(dtlPost.getUpdatedAt()) %> · 
						<i class="fas fa-globe"></i>
					</span>
                </div>
                <button class="btn text-white"><i class="fas fa-ellipsis-h"></i></button>
            </div>

            <div class="post-content">
                <p><%= dtlPost.getPostContent() %><br><br>
                </p>
            </div>

            <!-- Reaction Bar -->
            <% if(dtlPost.getUploadedBy() == user.getUserID()){ %>
            <div class="px-3 py-2">
                <div class="d-flex align-items-center">
                    <div class="reaction-icons">
                        <i class="bi bi-heart-fill text-danger me-2"></i>
                    </div>
                    <span class="reaction-count ms-2"><%= dtlPost.getCountLikes() %></span>
                    <span class="reaction-count ms-auto"><%= dtlPost.getCountComments() %> comments</span>
                </div>
            </div>
            <%} %>

            <!-- Interaction Bar -->
            <div class="interaction-bar d-flex justify-content-between px-3">
            	<form method="post" action="../interact">
				    <input type="hidden" name="postID" value="<%= dtlPost.getPostID() %>">
				    <% if (isLiked) { %>
				        <button type="submit" name="btn-like" value="unlike" 
				        	class="interaction-button like-button px-5"
				        	style="color: #FE5C5C;">
				            <i class="bi bi-heart-fill text-danger me-2"></i>Liked
				        </button>
				    <% } else { %>
				        <button type="submit" name="btn-like" value="like" 
				        	class="interaction-button like-button px-5">
						    <i class="bi bi-heart me-2"></i>Like
						</button>
				    <% } %>
				</form>
                <button class="interaction-button comment-button" id="commentButton">
                    <i class="bi bi-chat me-2 text-primary"></i>Comment
                </button>
                <button class="interaction-button report-button px-2" data-bs-toggle="modal" data-bs-target="#reportModal">
				    <i class="bi bi-flag me-2 text-danger"></i>Báo cáo
				</button>
            </div>

            <!-- Comments List -->
			<div class="comments-list">
		    <% 
		        if (listCmts == null || listCmts.isEmpty()) { 
		    %>
		        <p class="text-center text-muted">Chưa có bình luận.</p>
		    <% 
		        } else { 
		            for (DetailsComment cmt : listCmts) { 
		    %>
		        <div class="comment d-flex align-items-start">
		            <img src="<%= cmt.getCommentedByAvatar() == null || cmt.getCommentedByAvatar().isBlank() 
		                         ? request.getContextPath() + "/images/default-avt.jpg" 
		                         : request.getContextPath() + cmt.getCommentedByAvatar() %>" 
		                 alt="User" class="profile-image me-2">
		            <div class="comment-content flex-grow-1">
		                <h6 class="mb-0">
		                    <a href="../user-profile?userId=<%= cmt.getCommentedBy() %>" class="text-decoration-none">
		                        <%= cmt.getCommentedByName() %>
		                    </a>
		                </h6>
		                <p class="mt-2"><%= cmt.getCommentContent() %></p>
		                <div class="comment-actions d-flex justify-content-between">
		                    <span class="text-muted">
		                        <%= cmt.getUpdatedAt() == null ? MethodCommon.convertDateToString(cmt.getCommentedAt()) 
		                            : "Đã chỉnh sửa " + MethodCommon.convertDateToString(cmt.getUpdatedAt()) %>
		                    </span>
		                    <% if (cmt.getCommentedBy().equals(user.getUserID())) { %>
		                        <div class="dropdown">
		                            <button class="btn btn-sm" type="button" id="dropdownMenuButton<%= cmt.getCommentID() %>" data-bs-toggle="dropdown" aria-expanded="false">
		                                <i class="bi bi-three-dots"></i>
		                            </button>
		                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton<%= cmt.getCommentID() %>">
		                                <li>
		                                    <button class="dropdown-item" data-bs-toggle="modal" data-bs-target="#editCommentModal<%= cmt.getCommentID() %>">Chỉnh sửa</button>
		                                </li>
		                                <li>
		                                    <form action="../interact" method="post">
		                                    	<input type="hidden" name="postID" value="<%= cmt.getPostID() %>">
		                                        <button type="submit" name="btnDeleteCmt" value="<%= cmt.getCommentID() %>" class="dropdown-item text-danger">Xóa</button>
		                                    </form>
		                                </li>
		                            </ul>
		                        </div>
		                    <% } %>
		                    
		                    <% if (dtlPost.getUploadedBy() == user.getUserID() && !cmt.getCommentedBy().equals(user.getUserID())) { %>
		                        <div class="dropdown">
		                            <button class="btn btn-sm" type="button" id="dropdownMenuButton<%= cmt.getCommentID() %>" data-bs-toggle="dropdown" aria-expanded="false">
		                                <i class="bi bi-three-dots"></i>
		                            </button>
		                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton<%= cmt.getCommentID() %>">
		                                <li>
		                                    <form action="../interact" method="post">
		                                    	<input type="hidden" name="postID" value="<%= cmt.getPostID() %>">
		                                        <button type="submit" name="btnDeleteCmt" value="<%= cmt.getCommentID() %>" class="dropdown-item text-danger">Xóa</button>
		                                    </form>
		                                </li>
		                            </ul>
		                        </div>
		                    <% } %>
		                </div>
		            </div>
		        </div>
		
		        <!-- Modal chỉnh sửa bình luận -->
		        <div class="modal fade" id="editCommentModal<%= cmt.getCommentID() %>" tabindex="-1" aria-labelledby="editCommentModalLabel<%= cmt.getCommentID() %>" aria-hidden="true">
		            <div class="modal-dialog">
		                <div class="modal-content">
		                    <div class="modal-header">
		                        <h5 class="modal-title" id="editCommentModalLabel<%= cmt.getCommentID() %>">Chỉnh sửa bình luận</h5>
		                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		                    </div>
		                    <form action="../interact" method="post">
		                    	<input type="hidden" name="postID" value="<%= dtlPost.getPostID() %>">
		                        <div class="modal-body">
		                            <textarea name="txtEditContentCmt" class="form-control" rows="3" required><%= cmt.getCommentContent() %></textarea>
		                        </div>
		                        <div class="modal-footer">
		                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
		                            <button type="submit" name="btnEditCmt" value="<%= cmt.getCommentID() %>" class="btn btn-primary">Lưu</button>
		                        </div>
		                    </form>
		                </div>
		            </div>
		        </div>
		    <% 
		            } 
		        } 
		    %>
		</div>

            <div class="p-3 border-top">
                <div class="d-flex align-items-center">
                    <% if (user.getAvatar() == null || user.getAvatar().isBlank()) { %>
				        <img src="../images/default-avt.jpg" class="profile-image me-2">
				    <% } else { %>
			        	<img src="<%= request.getContextPath() %><%= user.getAvatar() %>" class="profile-image me-2">
			    	<% } %>
                    <div class="flex-grow-1 d-flex align-items-center">
					    <form action="../interact" method="post" class="d-flex w-100">
					    	<input type="hidden" name="postID" value="<%= dtlPost.getPostID() %>">
					    	<!--<input type="hidden" name="cmtInDetail" value="cmtInDetail">-->
					        <input name="txtContentCmt" type="text" class="form-control comment-input rounded-pill me-2" 
					               placeholder="Viết bình luận..." 
					               autofocus 
					               id="commentInput">
					        <button type="submit" name="btn-cmt" value="btn-cmt" class="btn rounded-circle send-comment-button d-flex align-items-center justify-content-center">
					            <i class="bi bi-send"></i>
					        </button>
					    </form>
					</div>

                </div>
            </div>
        </div>
        
         <div class="modal fade" id="reportModal" tabindex="-1" aria-labelledby="reportModalLabel" aria-hidden="true">
	        <div class="modal-dialog">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title" id="reportModalLabel">Báo cáo bài viết</h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                </div>
	                <form method="post" action="../interact">
	                	<input type="hidden" name="postID" value="<%= dtlPost.getPostID() %>">
	                	<input type="hidden" name="reportInDetail" value="reportInDetail">
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
    </div>
    
    <script>
	    document.getElementById('commentButton').addEventListener('click', function() {
	        // auto focus vào input khi nhấn comment
	        document.getElementById('commentInput').focus();
	    });
	</script>
    
</body>
</html>
