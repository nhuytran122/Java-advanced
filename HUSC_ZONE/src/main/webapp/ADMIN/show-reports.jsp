<%@page import="V_DetailsReportModal.DetailsReport"%>
<%@page import="CommonModal.MethodCommon"%>
<%@page import="CommonModal.Constants"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Quản lý báo cáo - HUSC Zone</title>
  <%@ include file="layout/import.jsp" %>
</head>

<body>
  <%
    ArrayList<DetailsReport> ds = (ArrayList<DetailsReport>) request.getAttribute("dsReports");
    int pageCount = (Integer) request.getAttribute("pageCount");
    int currentPage = (Integer) request.getAttribute("currentPage");
    Long statusID = request.getParameter("statusID") != null 
    		? Long.parseLong(request.getParameter("statusID")) : 0L;
    String searchKeyword = request.getParameter("txtSearch");
  %>
  <div class="container-scroller">
    <%@ include file="layout/navbar.jsp" %>
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layout/settings-panel.jsp" %>
      <%@ include file="layout/left_sidebar.jsp" %>
      <div class="main-panel">
        <ul class="navbar-nav mr-lg-2 my-4" style="display: flex; justify-content: center; width: 100%;">
          <li class="nav-item nav-search d-none d-lg-block" style="display: flex; align-items: center;">
            <form action="../admin/reports" method="get" class="d-flex align-items-center justify-content-center" style="gap: 10px;">
		      <select name="statusID" class="form-control form-control-sm" style="width: 200px;">
		        <option value="0" <%= (statusID == 0L ? "selected" : "") %>>-- Chọn trạng thái --</option>
		        <option value="<%= Constants.REPORT_ACCEPTED %>" <%= (statusID == Constants.REPORT_ACCEPTED ? "selected" : "") %>>
		          Đã phê duyệt 
		        </option>
		        <option value="<%= Constants.REPORT_PENDING %>" <%= (statusID == Constants.REPORT_PENDING ? "selected" : "") %>>
		          Đang chờ 
		        </option>
		        <option value="<%= Constants.REPORT_REJECTED %>" <%= (statusID == Constants.REPORT_REJECTED ? "selected" : "") %>>
		          Đã từ chối 
		        </option>
		      </select>
              <input type="text" class="form-control form-control-sm me-2" id="navbar-search-input" placeholder="Tìm kiếm báo cáo..." name="txtSearch" aria-label="search"
                     value="<%= searchKeyword != null ? searchKeyword : "" %>"
                     style="width: 400px; font-size: 14px; margin-right: 10px;">
              <button type="submit" class="btn btn-primary btn-sm p-2">
                <i class="bi bi-search"></i>
              </button>
            </form>
          </li>
        </ul>

        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
              <div class="card position-relative">
                <div class="card-body">
                  <div class="d-flex justify-content-between align-items-center mb-4">
                    <h4 class="card-title">Danh sách Báo cáo</h4>
                  </div>

                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead class="table-light">
                        <tr>
                          <th style="width: 150px;"></th>
                          <th>Lí do</th>
                          <th>Ngày tạo</th>
                          <th>Tình trạng</th>
                          <th>Thao tác</th>
                        </tr>
                      </thead>
                      <tbody>
                        <% 
                          int n = ds.size();
                          if (n == 0) {
                        %>
                            <tr>
                              <td colspan="7" class="text-center text-danger">Không tìm thấy Báo cáo nào.</td>
                            </tr>
                        <% 
                          } else { 
                            for (int i = 0; i < n; i++) {
                              DetailsReport report = ds.get(i);
                              Long reportID = report.getReportID();
                        %>
	                        <tr>
	                         <td>
	                        <% if (report.getImagePath() != null && !report.getImagePath().isEmpty()) { %>
				                <img src="<%= request.getContextPath() %><%= report.getImagePath() %>" class="img-fluid rounded" style="width: auto; height: 100px; object-fit: cover;">
				            <% } else { %>
				                <% if (report.getPosterAvatar() != null && !report.getPosterAvatar().isEmpty()) { %>
				                        <img src="<%= request.getContextPath() %><%= report.getPosterAvatar() %>" class="img-fluid rounded" style="width: auto; height: 100px; object-fit: cover;">
				                <% } else { %>
				                        <img src="<%= request.getContextPath() %>/default-avatar.jpg" class="img-fluid rounded" style="width: auto; height: 100px; object-fit: cover;">
				                <% } %>
				            <% } %>
	                          </td>
							  <td>
							    <div style="max-width: 200px; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis; white-space: normal; line-height: 1.5;">
							      <%= report.getReason() %>
							    </div>
							  </td>
							  <td>
							      <%= MethodCommon.convertDateToString(report.getCreatedAt()) %>
							  </td>
							  <td><%= report.getDescriptionStatus() %></td>
							  <td>
							    <div class="btn-group" role="group">
							        <a href="../admin/details?reportID=<%= reportID %>" class="btn btn-success btn-sm" title="Xem chi tiết">
									  <i class="bi bi-eye"></i>
									</a>

							        <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal<%= reportID %>" title="Xóa">
							          <i class="bi bi-trash"></i>
							        </button>
							    </div>
							  </td>
							</tr>


                        <!-- Modal xác nhận xóa thông thường -->
                        <div class="modal fade" id="deleteModal<%= reportID %>" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                              <div class="modal-header">
                                <h5 class="modal-title text-danger" id="deleteModalLabel<%= reportID %>">
                                <i class="bi bi-exclamation-triangle-fill me-2"></i>
                                Xác nhận xóa Báo cáo 
                              </div>
                              <div class="modal-body">
                                Bạn có chắc chắn muốn xóa Báo cáo này không?
                                
                                <div class="alert alert-warning d-flex align-items-center mt-4" role="alert">
						          Lưu ý: Xóa Báo cáo này sẽ đồng thời xóa các dữ liệu liên quan.
						        </div>
                              </div>
                              <div class="modal-footer">
                                <form method="post" action="../admin/edit-report">
                                  <input type="hidden" name="reportID" value="<%= reportID %>">
                                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                  <button type="submit" name="btnDeletereport" value="delete" class="btn btn-danger">Xóa</button>
                                </form>
                              </div>
                            </div>
                          </div>
                        </div>
                        
                        <% 
                            }
                          }
                        %>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            </div>

          <% if (n > 0) { %>
          <div class="text-center">
            <nav aria-label="Page navigation example">
              <ul class="pagination justify-content-center">
                <li class="page-item <%= currentPage > 1 ? "" : "disabled" %>">
                  <a class="page-link" href="<%= currentPage > 1 ? "../admin/reports?page=" + (currentPage - 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>

                <% for (int p = 1; p <= pageCount; p++) { %>
                  <li class="page-item <%= p == currentPage ? "active" : "" %>">
                    <a class="page-link" href="../admin/reports?page=<%= p %><%= searchKeyword != null ? "&txtSearch=" + searchKeyword : "" %>">
                      <%= p %>
                    </a>
                  </li>
                <% } %>

                <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
                  <a class="page-link" href="<%= currentPage < pageCount ? "../admin/reports?page=" + (currentPage + 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") : "#" %>" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                  </a>
                </li>
              </ul>
            </nav>
          </div>
          <% } %>
        </div>
      </div>
    </div>
  </div>
</body>

</html>
