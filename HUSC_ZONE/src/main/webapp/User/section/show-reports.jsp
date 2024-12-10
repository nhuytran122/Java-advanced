<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if (dsRpts.size() == 0) { %>
        <p class="text-center text-muted">Hiện bạn chưa báo cáo bài đăng nào.</p>
    <% } else { 
        for (DetailsReport rp : dsRpts) { %>
            <div class="d-flex mb-3 p-3 border rounded-3 bg-white shadow-sm">
                <% if (rp.getImagePath() != null && !rp.getImagePath().isEmpty()) { %>
                    <img src="<%= request.getContextPath() %><%= rp.getImagePath() %>" alt="Img" class="rounded-circle me-3" width="60" height="60">
                <% } else { %>
                    <% if (rp.getPosterAvatar() != null && !rp.getPosterAvatar().isEmpty()) { %>
                        <a href="../user-profile?userId=<%= rp.getPosterID() %>">
                            <img src="<%= request.getContextPath() %><%= rp.getPosterAvatar() %>" width="60" height="60" alt="Avatar" class="rounded-circle me-3">
                        </a>
                    <% } else { %>
                        <a href="../user-profile?userId=<%= rp.getPosterID() %>">
                            <img src="<%= request.getContextPath() %>/default-avatar.jpg" width="60" height="60" alt="Default Avatar" class="rounded-circle me-3">
                        </a>
                    <% } %>
                <% } %>
                <div class="flex-grow-1">
                    <div class="d-flex justify-content-between align-items-center">
                        <a href="../user-profile?userId=<%= rp.getCreatedBy() %>">
                            <h6 class="m-0"><%= user.getName() %></h6>
                        </a>
                        <div class="d-flex align-items-center">
                            <small class="text-muted me-3"><%= MethodCommon.convertDateToString(rp.getCreatedAt()) %></small>
                            <div class="dropdown">
                                <button class="btn btn-link p-0" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="bi bi-three-dots-vertical"></i>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end">
                                    <li>
                                        <form action="../interact" method="POST" class="mb-0">
                                            <input type="hidden" name="postID" value="<%= rp.getPostID() %>">
                                            <input type="hidden" name="deleteInList" value="true">
                                            <button type="submit" name="btnDeleteReport" value="<%= rp.getReportID() %>" class="dropdown-item">
                                                <i class="bi bi-trash3 me-2"></i>Xóa
                                            </button>
                                        </form>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="body my-2">
                        <p>Đã báo cáo bài viết của <b><a href="../user-profile?userId=<%= rp.getPosterID() %>"><%= rp.getPosterName() %></a></b> </p>
                        <p><%= rp.getDescription() %></p>
                        <a href="../details?postID=<%= rp.getPostID() %>" class="text-primary">Xem bài viết...</a>
                    </div>
                </div>
            </div>
        <% } %>
    <% } %>