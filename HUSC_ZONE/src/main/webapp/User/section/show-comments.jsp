<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% if (dsCmts.size() == 0) { %>
        <p class="text-center text-muted">Hiện bạn chưa có bình luận nào.</p>
    <% } else { 
        for (DetailsComment cmt : dsCmts) { %>
            <div class="d-flex mb-3 p-3 border rounded-3 bg-white shadow-sm">
                <% if (cmt.getImagePath() != null && !cmt.getImagePath().isEmpty()) { %>
                    <img src="<%= request.getContextPath() %><%= cmt.getImagePath() %>" alt="Img" class="rounded-circle me-3" width="80" height="80">
                <% } else { %>
                    <% if (cmt.getPosterAvatar() != null && !cmt.getPosterAvatar().isEmpty()) { %>
                        <a href="../user-profile?userId=<%= cmt.getPosterID() %>">
                            <img src="<%= request.getContextPath() %><%= cmt.getPosterAvatar() %>" width="80" height="80" alt="Avatar" class="rounded-circle me-3">
                        </a>
                    <% } else { %>
                        <a href="../user-profile?userId=<%= cmt.getPosterID() %>">
                            <img src="<%= request.getContextPath() %>/default-avatar.jpg" width="80" height="80" alt="Default Avatar" class="rounded-circle me-3">
                        </a>
                    <% } %>
                <% } %>
                <div class="flex-grow-1">
                    <div class="d-flex justify-content-between align-items-center">
                        <a href="../user-profile?userId=<%= cmt.getPosterID() %>">
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
                                            <button type="submit" name="btnDeleteCmt" value="<%= cmt.getCommentID() %>" class="dropdown-item">
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
 