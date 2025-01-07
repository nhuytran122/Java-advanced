<%@page import="DocumentModal.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sửa Tài Liệu - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
</head>

<% 
      Document doc = (Document)request.getAttribute("doc");
%>
    
<body class="bg-light">
    <!-- Top Navbar -->
    <%@ include file="layout/navbar.jsp" %>

    <div class="container-fluid my-3">
        <div class="row">
            <!-- Sidebar -->
            <%@ include file="layout/sidebar.jsp" %>

            <!-- Main Content -->
            <main class="col-md-9 my-4">
                <div class="card no-hover mb-3">
                    <h4 class="mb-0 p-3 text-center">Chỉnh sửa Tài liệu</h4>
                    
                    <div class="card-body">
                        <form action="../edit-docs" method="post" enctype="multipart/form-data">
                        	<input type="hidden" name="txtDocID" value="<%= doc.getDocumentID()%>" >
                            <div class="mb-3">
                                <label class="form-label">Tiêu đề</label> <span class="text-danger">*</span>
                                <input type="text" class="form-control" placeholder="Nhập tiêu đề tài liệu" 
	                                name="txtTenDocs" 
	                                value="<%= doc.getTitle() %>" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Mô tả</label>
                                <textarea class="form-control" rows="3" placeholder="Nhập mô tả ngắn gọn về tài liệu" 
	                                name="txtMoTa" ><%= doc.getDesription() %></textarea>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Ngành học</label> <span class="text-danger">*</span>
                                <select class="form-select" name = "txtMaNganh" required>
                                    <option value="">-- Chọn ngành học --</option>
			                          <% for (Category cate : listCates) { 
			                          		Long currentCate = doc.getCategoryID();
			                          %>
			                            <option value="<%= cate.getCategoryID() %>"
			                              <%= currentCate != null && currentCate == cate.getCategoryID() ? "selected" : "" %>>
			                              <%= cate.getCategoryName() %>
			                            </option>
			                          <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Loại tài liệu</label> <span class="text-danger">*</span>
                                <select class="form-select" name = "txtMaLoai" required>
                                    <option value="">-- Chọn loại tài liệu --</option>
			                         <% for (Material mate : listMates) { 
			                          		Long currentMate = doc.getMaterialID();
			                          %>
			                            <option value="<%= mate.getMaterialID() %>"
			                              <%= currentMate != null && currentMate == mate.getMaterialID() ? "selected" : "" %>>
			                              <%= mate.getMaterialName() %>
			                            </option>
			                          <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
							    <label class="form-label">Tải lên tài liệu</label> <span class="text-danger">*</span></label>
							    <%
		                          String filePath = request.getContextPath() + doc.getFilePath();
		                          if (filePath != null && !filePath.isEmpty()) {
		                            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		                        %>
		                          <div>
		                            <p class="my-2"><strong>File hiện tại:</strong> 
		                              <a href="<%= filePath %>" target="_blank"><%= fileName %></a>
		                            </p>
		                          </div>
		                        <%
		                          }
		                        %>
							    <input type="file" class="form-control" name="fileDocs" 
							           accept="image/*,application/pdf,.doc,.docx,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation" 
							           
							           value="<%= request.getContextPath() %><%= doc.getFilePath()%>">
							</div>

                            <div class="text-end">
                                <a href="../docs-of-user" class="btn btn-danger mt-2 py-2 px-3 pb-2 me-2">
                                    Hủy
                                </a>
                                <button type="submit" 
                                	name="btnUpdate" value="add" class="btn btn-success py-2">
                                    <i class="bi bi-upload"></i> Lưu thay đổi
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </main>
        </div>
    </div>
</body>
</html>
