<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if (dsLikes.size() == 0) { %>
    <p class="text-center text-muted">Hiện bạn chưa thích bài viết nào.</p>
    <% } else { 
        for (DetailsLiked like : dsLikes) { %>
            <div class="d-flex mb-3 p-3 border rounded-3 bg-white shadow-sm">
                <% if (like.getImagePath() != null && !like.getImagePath().isEmpty()) { %>
                    <img src="<%= request.getContextPath() %><%= like.getImagePath() %>" alt="Img" class="rounded-circle me-3" width="80" height="80">
                <% } else { %>
                    <% if (like.getPosterAvatar() != null && !like.getPosterAvatar().isEmpty()) { %>
                        <a href="../user-profile?userId=<%= like.getPosterID() %>">
                            <img src="<%= request.getContextPath() %><%= like.getPosterAvatar() %>" width="80" height="80" alt="Avatar" class="rounded-circle me-3">
                        </a>
                    <% } else { %>
                        <a href="../user-profile?userId=<%= like.getPosterID() %>">
                            <img src="<%= request.getContextPath() %>/default-avatar.jpg" width="80" height="80" alt="Default Avatar" class="rounded-circle me-3">
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
                                            <button type="submit" name="btn-like" value="true" class="dropdown-item">
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