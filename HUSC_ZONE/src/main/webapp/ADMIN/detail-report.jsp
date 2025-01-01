<%@page import="CommonModal.Constants"%>
<%@page import="V_DetailsReportModal.DetailsReport"%>
<%@page import="CommonModal.MethodCommon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Chi tiết báo cáo - HUSCZone</title>
  <%@ include file="layout/import.jsp" %>
</head>

<body>
  <%
    DetailsReport report = (DetailsReport) request.getAttribute("report");
  %>
  <div class="container-scroller">
    <%@ include file="layout/navbar.jsp" %>
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layout/settings-panel.jsp" %>
      <%@ include file="layout/left_sidebar.jsp" %>
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="card-title">Chi tiết báo cáo</h4>
                    <%-- Chỉ khi trạng thái là REPORT_PENDING --%>
                    <%
                      if (report.getStatusID() == Constants.REPORT_PENDING) { // Trạng thái đang chờ xử lý
                    %>
                      <form method="post" action="../admin/edit-report" class="d-flex">
                        <input type="hidden" name="reportID" value="<%= report.getReportID() %>">
                        <button type="submit" name="btnApproveReport" value="<%= report.getPostID() %>" class="btn btn-success btn-sm mx-2" title="Phê duyệt">
                          <i class="bi bi-check-circle"></i> Phê duyệt
                        </button>
                        
                        <button type="submit" name="btnRejectReport" value="reject" class="btn btn-danger btn-sm" title="Từ chối">
                          <i class="bi bi-x-circle"></i> Từ chối
                        </button>
                      </form>
                    <% 
                      } 
                    %>
                  </div>
                  <div class="row">
					<div class="col-md-4 mb-4">
					  <% 
					    if (report.getImagePath() != null && !report.getImagePath().isEmpty()) { 
					  %>
					      <img src="<%= request.getContextPath() %><%= report.getImagePath() %>" class="img-fluid rounded mb-3" alt="Hình ảnh bài viết">
					  <% 
					    } else { 
					      if (report.getPosterAvatar() != null && !report.getPosterAvatar().isEmpty()) { 
					  %>
					        <a href="../user-profile?userId=<%= report.getPosterID() %>">
					          <img src="<%= request.getContextPath() %><%= report.getPosterAvatar() %>" class="img-fluid rounded mb-3" alt="Avatar người đăng bài">
					        </a>
					  <% 
					      } else { 
					  %>
					        <a href="../user-profile?userId=<%= report.getPosterID() %>">
					          <img src="<%= request.getContextPath() %>/default-avatar.jpg" class="img-fluid rounded mb-3" alt="Avatar mặc định">
					        </a>
					  <% 
					      } 
					    } 
					  %>
					</div>

                    <div class="col-md-8">
                      <div class="row mb-3">
                        <div class="col-md-12">
                          <p class="card-text"><strong>Lý do báo cáo:</strong> <%= report.getReason() %></p>
                          <p class="card-text"><strong>Ngày tạo:</strong> <%= MethodCommon.convertDateToString(report.getCreatedAt()) %></p>
                          <p class="card-text"><strong>Ngày xử lý:</strong> <%= report.getSolvedAt() != null ? MethodCommon.convertDateToString(report.getSolvedAt()) : "Chưa xử lý" %></p>
                          <p class="card-text"><strong>Trạng thái:</strong> <%= report.getDescriptionStatus() %></p>
                          <p class="card-text"><strong>Người đăng bài:</strong> <%= report.getPosterName() %></p>
                          <p class="card-text"><strong>Nội dung bài viết:</strong> 
	                          <a href="../details?postID=<%= report.getPostID() %>" class="btn btn-info btn-sm mx-2" title="Xem chi tiết">
						        <i class="bi bi-eye"></i> Xem chi tiết
						      </a>
                        </div>
                      </div>

                      <div class="row">
                        <div class="col-md-12">
                            <input type="hidden" name="reportID" value="<%= report.getReportID() %>">
                            <button type="button" class="btn btn-danger btn-sm ms-2" data-bs-toggle="modal" data-bs-target="#deleteModal<%= report.getReportID() %>" title="Xóa">
                              <i class="bi bi-trash"></i> Xóa
                            </button>
                        </div>
                      </div>

                      <!-- Modal xác nhận xóa -->
                      <div class="modal fade" id="deleteModal<%= report.getReportID() %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa báo cáo</h5>
                            </div>
                            <div class="modal-body">
                              Bạn có chắc chắn muốn xóa báo cáo <b><%= report.getReason() %></b> không?
                            </div>
                            <div class="modal-footer">
                              <form method="post" action="../admin/edit-report">
                                <input type="hidden" name="reportID" value="<%= report.getReportID() %>">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                <button type="submit" name="btnDeleteReport" value="delete" class="btn btn-danger">Xóa</button>
                              </form>
                            </div>
                          </div>
                        </div>
                      </div>

                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>

</html>
