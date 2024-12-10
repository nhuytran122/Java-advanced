<%@page import="CommonModal.MethodCommon"%>
<%@page import="V_DetailsLikedModal.DetailsLiked"%>
<%@page import="V_DetailsCommentModal.DetailsComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bài viết đã thích - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
</head>
<style>
a {
	text-decoration: none; 
	color: #000;
}
</style>
<% 
	Long filterID = request.getParameter("filterID") != null 
			? Long.parseLong(request.getParameter("filterID")) : 0L;
	ArrayList<DetailsComment> dsCmts = (ArrayList<DetailsComment>) request.getAttribute("dsCmts");
	ArrayList<DetailsLiked> dsLikes = (ArrayList<DetailsLiked>) request.getAttribute("dsLikes");
	int pageCount = (Integer) request.getAttribute("pageCount");
    int currentPage = (Integer) request.getAttribute("currentPage");

%>

<body class="bg-light">
    <%@ include file="layout/navbar_for_Post.jsp" %>

    <div class="container-fluid my-3">
        <div class="row">
            <%@ include file="layout/sidebar.jsp" %>
            
            <main class="col-md-9 my-3">
                <div class="d-flex justify-content-between align-items-center mb-2">
			        <h4 class="pb-2">Nhật ký hoạt động</h4>
			        <form class="d-flex align-items-center" action="../activity-history" method="post">
			            <select name="filterID" class="form-select form-select-sm py-2 me-3" style="width: 200px;">
			                <option value="<%= Constants.FILTER_LIKED %>" 
			                    <%= filterID == Constants.FILTER_LIKED ? "selected" : "" %>>Lượt thích</option>
			                <option value="<%= Constants.FILTER_COMMENTED %>" 
			                    <%= filterID == Constants.FILTER_COMMENTED ? "selected" : "" %>>Bình luận</option>
			                    <option value="<%= Constants.FILTER_REPORT %>" 
			                    <%= filterID == Constants.FILTER_REPORT ? "selected" : "" %>>Báo cáo</option>
			            </select>
			            <button type="submit" name="btn-filter-ah" value="true" class="btn btn-primary-custom p-2">
			                <i class="bi bi-search"></i>
			            </button>
			        </form>
			    </div>
                <%
				    int n = 0;
				    if (dsLikes != null && !dsLikes.isEmpty()) {
				        n = dsLikes.size();
				    } else if (dsCmts != null && !dsCmts.isEmpty())
				        n = dsCmts.size();
				%>
				 <%
				    if (dsLikes != null) {
				%>
				    <% if (dsLikes.size() == 0) { %>
				        <p class="text-center text-muted">Hiện bạn chưa thích bài viết nào.</p>
				    <% } else { 
				        for (DetailsLiked like : dsLikes) { %>
				            <div class="d-flex mb-3 p-3 border rounded-3 bg-white shadow-sm">
				                <% if (like.getImagePath() != null || like.getImagePath().isBlank()) { %>
					            <img src="<%= request.getContextPath() %><%= like.getImagePath() %>" alt="Img" class="rounded-circle me-3" width="60" height="60">
						        <% } else { %>
						            <% if (like.getPosterAvatar() == null || like.getPosterAvatar().isBlank()) { %>
						                <a href="../user-profile?userId=<%= like.getPosterID() %>">
						                    <img src="../images/default-avt.jpg" style="width: 60px; height: 60px;" alt="Default" class="rounded-circle me-3">
						                </a>
						            <% } else { %>
						                <a href="../user-profile?userId=<%= like.getPosterID() %>">
						                    <img src="<%= request.getContextPath() %><%= like.getPosterAvatar() %>" style="width: 60px; height: 60px" alt="Avatar" class="rounded-circle me-3">
						                </a>
						            <% } %>
						        <% } %>
				                <div class="flex-grow-1">
				                    <div class="d-flex justify-content-between align-items-center">
				                    	<a href="../user-profile?userId=<%= user.getUserID() %>">
				                        	<h6 class="m-0"><%= user.getName() %></h6>
				                        </a>
				                        <div class="d-flex align-items-center">
				                            <small class="text-muted me-3"><%= MethodCommon.convertDateToString(like.getLikedAt()) %></small>
				                            <div class="dropdown">
			                                    <button class="btn btn-link p-0" data-bs-toggle="dropdown" aria-expanded="false">
			                                        <i class="bi bi-three-dots-vertical"></i>
			                                    </button>
			                                    <ul class="dropdown-menu dropdown-menu-end">
			                                        <li>
			                                        	<form action="../interact" method="POST" class="mb-0">
													        <input type="hidden" name="postID" value="<%= like.getPostID() %>">
													        <input type="hidden" name="unLikeInList" value="true">
													        <button type="submit" 
													                name="btn-like" value="true" 
													                class="dropdown-item">
													            <i class="bi bi-heartbreak me-2"></i>Bỏ thích
													        </button>
													    </form>
			                                        </li>
			                                    </ul>
			                                </div>
				                        </div>
				                    </div>
				                    <div class="body my-2">
				                        <p>Đã thích bài viết của <b><a href="../user-profile?userId=<%= like.getPosterID() %>"><%= like.getPosterName() %></a></b> </p>
				                        <p><%= like.getPostContent() %></p>
				                        <a href="../details?postID=<%= like.getPostID() %>" class="text-primary">Xem thêm...</a>
				                    </div>
				                </div>
				            </div>
				        <% } %>
				    <% } %>
				<%
				    } else {
				%>
				    <!-- Hiển thị danh sách bình luận -->
				    <% if (dsCmts == null || dsCmts.size() == 0) { %>
				        <p class="text-center text-muted">Hiện bạn chưa có bình luận nào.</p>
				    <% } else { 
				        for (DetailsComment cmt : dsCmts) { %>
				            <div class="d-flex mb-3 p-3 border rounded-3 bg-white shadow-sm">
				                <% if (cmt.getImagePath() != null || cmt.getImagePath().isBlank()) { %>
					            <img src="<%= request.getContextPath() %><%= cmt.getImagePath() %>" alt="Img" class="rounded-circle me-3" width="60" height="60">
						        <% } else { %>
						            <% if (cmt.getPosterAvatar() == null || cmt.getPosterAvatar().isBlank()) { %>
						                <a href="../user-profile?userId=<%= cmt.getPosterID() %>">
						                    <img src="../images/default-avt.jpg" width="60" height="60" alt="Default" class="rounded-circle me-3">
						                </a>
						            <% } else { %>
						                <a href="../user-profile?userId=<%= cmt.getPosterID() %>">
						                    <img src="<%= request.getContextPath() %><%= cmt.getPosterAvatar() %>" width="60" height="60" alt="Avatar" class="rounded-circle me-3">
						                </a>
						            <% } %>
						        <% } %>
				                <div class="flex-grow-1">
				                    <div class="d-flex justify-content-between align-items-center">
				                    <a href="../user-profile?userId=<%= user.getUserID() %>">
				                        <h6 class="m-0"><%= cmt.getCommentedByName() %></h6>
				                    </a>
				                        <div class="d-flex align-items-center">
				                            <small class="text-muted me-3"><%= MethodCommon.convertDateToString(cmt.getCommentedAt()) %></small>
				                            <div class="dropdown">
			                                    <button class="btn btn-link p-0" data-bs-toggle="dropdown" aria-expanded="false">
			                                        <i class="bi bi-three-dots-vertical"></i>
			                                    </button>
			                                    <ul class="dropdown-menu dropdown-menu-end">
			                                        <li>
			                                        	<form action="../interact" method="POST" class="mb-0">
													        <input type="hidden" name="postID" value="<%= cmt.getPostID() %>">
													        <input type="hidden" name="deleteInList" value="true">
													        <button type="submit" 
													                name="btnDeleteCmt" value="<%= cmt.getCommentID() %>" 
													                class="dropdown-item">
													            <i class="bi bi-trash3 me-2"></i>Xóa
													        </button>
													    </form>
			                                        </li>
			                                    </ul>
			                                </div>
				                        </div>
				                    </div>
				                    <div class="body my-2">
				                        <p>Đã bình luận về bài viết của <b><a href="../user-profile?userId=<%= cmt.getPosterID() %>"><%= cmt.getPosterName() %></a></b> </p>
				                        <p><%= cmt.getCommentContent() %></p>
				                        <a href="../details?postID=<%= cmt.getPostID() %>" class="text-primary">Xem thêm...</a>
				                    </div>
				                </div>
				            </div>
				        <% } %>
				    <% } %>
				<%
				    }
				%>

		    
		    <% if (n > 0) { %>
					<nav>
					    <ul class="pagination justify-content-center mt-4">
					        <li class="page-item <%= currentPage > 1 ? "" : "disabled" %>">
					            <a class="page-link" 
								   href="<%= currentPage > 1 ? "../activity-history?page=" + (currentPage - 1) : "#" %>" 
								   tabindex="-1" 
								   aria-disabled="true">
								    <i class="bi bi-chevron-left"></i>
								</a>

					        </li>
					        
					        <% for (int p = 1; p <= pageCount; p++) { %>
			                  <li class="page-item <%= p == currentPage ? "active" : "" %>">
			                    <a class="page-link" href="../activity-history?page=<%= p %>">
			                      <%= p %>
			                    </a>
			                  </li>
			                <% } %>
					        <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
					            <a class="page-link" 
					            	href="<%= currentPage < pageCount ? "../activity-history?page=" + (currentPage + 1) : "#" %>">
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
</html>
