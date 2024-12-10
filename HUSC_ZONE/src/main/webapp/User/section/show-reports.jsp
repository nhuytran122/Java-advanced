<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if (dsRpts.size() == 0) { %>
    <p class="text-center text-muted">Hiện bạn chưa báo cáo bài đăng nào.</p>
<% } else { 
    for (DetailsReport rp : dsRpts) { %>
        <div class="d-flex mb-3 p-3 border rounded-3 bg-white shadow-sm">
            <% if (rp.getImagePath() != null && !rp.getImagePath().isEmpty()) { %>
                <img src="<%= request.getContextPath() %><%= rp.getImagePath() %>" alt="Img" class="rounded-circle me-3" width="80" height="80">
            <% } else { %>
                <% if (rp.getPosterAvatar() != null && !rp.getPosterAvatar().isEmpty()) { %>
                    <a href="../user-profile?userId=<%= rp.getPosterID() %>">
                        <img src="<%= request.getContextPath() %><%= rp.getPosterAvatar() %>" width="80" height="80" alt="Avatar" class="rounded-circle me-3">
                    </a>
                <% } else { %>
                    <a href="../user-profile?userId=<%= rp.getPosterID() %>">
                        <img src="<%= request.getContextPath() %>/default-avatar.jpg" width="80" height="80" alt="Default Avatar" class="rounded-circle me-3">
                    </a>
                <% } %>
            <% } %>
            <div class="flex-grow-1">
                <div class="d-flex justify-content-between align-items-center">
                    <a href="../user-profile?userId=<%= rp.getCreatedBy() %>">
                        <h6 class="m-0"><%= user.getName() %></h6>
                    </a>
                    <div class="d-flex align-items-center justify-content-end">
                        <% if (rp.getSolvedAt() != null) { %>
                        	<span class="badge bg-success text-white me-3"><%= rp.getDescriptionStatus() %></span>
                            <small class="text-muted me-3">
                                Đã xử lý vào lúc: <%= MethodCommon.convertDateToString(rp.getSolvedAt()) %>
                            </small>
                        <% } else { %>
                        	<span class="badge bg-info text-white me-3"><%= rp.getDescriptionStatus() %></span>
                            <small class="text-muted me-3">
                                Đã báo cáo vào lúc: <%= MethodCommon.convertDateToString(rp.getCreatedAt()) %>
                            </small>
                        <% } %>
                        <div class="dropdown">
                            <button class="btn btn-link p-0" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="bi bi-three-dots-vertical"></i>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li>
                                    <button 
                                    type="button" 
                                    class="dropdown-item"
                                    data-bs-toggle="modal" 
                                    data-bs-target="#deleteModal<%= rp.getReportID() %>" 
                                    title="Xóa">
                                    <i class="bi bi-trash me-2"></i> Xóa báo cáo
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="body my-2">
                    <p>Đã báo cáo bài viết của <b><a href="../user-profile?userId=<%= rp.getPosterID() %>"><%= rp.getPosterName() %></a></b> </p>
                    <p><b>Lý do: </b><%= rp.getReason() %></p>
                    <a href="../details?postID=<%= rp.getPostID() %>" class="text-primary">Xem bài viết...</a>
                </div>
            </div>
            
            <div class="modal fade" id="deleteModal<%= rp.getReportID() %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title text-danger" id="deleteModalLabel<%= rp.getReportID() %>">
                          <i class="bi bi-exclamation-triangle-fill me-2"></i>
                          Xác nhận xóa báo cáo
                      </h5>
                    </div>
                    <div class="modal-body">
                      Bạn có chắc chắn muốn xóa báo cáo này không?
                    </div>
                    <div class="modal-footer">
                      <form method="post" action="../interact">
                        <input type="hidden" name="postID" value="<%= rp.getPostID() %>">
                        <input type="hidden" name="deleteInList" value="true">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <button type="submit" name="btnDeleteReport" value="<%= rp.getReportID() %>" class="btn btn-danger">Xóa</button>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
        </div>
    <% } %>
<% } %>
