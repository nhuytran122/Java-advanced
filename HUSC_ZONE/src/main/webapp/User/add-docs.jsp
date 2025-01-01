<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Tài Liệu - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
</head>

<% 
      String tenDocs = (String) request.getAttribute("tenDocs");
      String moTa = (String) request.getAttribute("moTa");
      Long maNganh = (request.getAttribute("maNganh") != null) 
    	        ? (Long) request.getAttribute("maNganh") : 0L;

      Long maLoai = (request.getAttribute("maLoai") != null) 
    	        ? (Long) request.getAttribute("maLoai") : 0L;
      Boolean isInvalid = (Boolean) request.getAttribute("isInvalid");
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
                    <h4 class="mb-0 p-3 text-center">Tải lên Tài liệu</h4>
                    
                    <div class="card-body">
                        <form action="../edit-docs" method="post" enctype="multipart/form-data">
                            <div class="mb-3">
							    <label class="form-label">Tiêu đề</label>
							    <input type="text" class="form-control" placeholder="Nhập tiêu đề tài liệu" 
							           name="txtTenDocs" 
							           value="<%= tenDocs != null ? tenDocs : "" %>" 
							           required>
							</div>
                            <div class="mb-3">
                                <label class="form-label">Mô tả</label>
                                <textarea class="form-control" rows="3" placeholder="Nhập mô tả ngắn gọn về tài liệu" 
	                                name="txtMoTa" 
	                                value="<%= moTa != null ? moTa : "" %>" ></textarea>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Ngành học</label>
                                <select class="form-select" name = "txtMaNganh" required>
                                    <option value="">-- Chọn ngành học --</option>
			                          <% for (Category cate : listCates) { %>
			                            <option value="<%= cate.getCategoryID() %>" <%= (cate.getCategoryID().equals(maNganh)) ? "selected" : "" %> >
			                              <%= cate.getCategoryName() %>
			                            </option>
			                          <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Loại tài liệu</label>
                                <select class="form-select" name = "txtMaLoai" required>
                                    <option value="">-- Chọn loại tài liệu --</option>
			                          <% for (Material mate : listMates) { %>
			                            <option value="<%= mate.getMaterialID() %>" <%= (mate.getMaterialID().equals(maLoai)) ? "selected" : "" %> >
			                              <%= mate.getMaterialName() %>
			                            </option>
			                          <% } %>
                                </select>
                            </div>
                            <div class="mb-3">
							    <label class="form-label">Tải lên tài liệu</label>
							    <input type="file" class="form-control" name="fileDocs" 
							           accept="image/*,application/pdf,.doc,.docx,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation" 
							           required>
							</div>

                            <div class="text-end">
                                <a href="../home" class="btn btn-danger mt-2 py-2 px-3 pb-2 me-2">
                                    Hủy
                                </a>
                                <button type="submit" 
                                	name="btnAdd" value="add" class="btn btn-success py-2">
                                    <i class="bi bi-upload"></i> Gửi tài liệu
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
