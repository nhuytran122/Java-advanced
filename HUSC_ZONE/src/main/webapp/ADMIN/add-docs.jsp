<%@page import="MaterialModal.Material"%>
<%@page import="CategoryModal.Category"%>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Thêm tài liệu - HUSCZone</title>
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
      ArrayList<Category> listCates = (ArrayList<Category>) request.getAttribute("listCates");
      ArrayList<Material> listMates = (ArrayList<Material>) request.getAttribute("listMates");
%>

<body>
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
                  <h4 class="card-title mb-4 text-center">Thêm tài liệu</h4>
                  <form class="form-horizontal" action="../admin/edit-docs" method="post" enctype="multipart/form-data">
                    
                    <div class="form-group row">
                      <label class="control-label col-sm-2">Tiêu đề</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" name="txtTenDocs" 
							           value="<%= tenDocs != null ? tenDocs : "" %>" 
							           required>
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <label class="control-label col-sm-2">Mô tả</label>
                      <div class="col-sm-10">
                        <textarea class="form-control number-separator" name="txtMoTa" 
	                     ><%= moTa != null ? moTa : "" %></textarea>
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <label class="control-label col-sm-2">Ngành học</label>
                      <div class="col-sm-10">
                        <select class="form-control" name="txtMaNganh" required>
                          <option value="">-- Chọn ngành học --</option>
			              <% for (Category cate : listCates) { %>
			                <option value="<%= cate.getCategoryID() %>" <%= (cate.getCategoryID().equals(maNganh)) ? "selected" : "" %> >
			                  <%= cate.getCategoryName() %>
			                </option>
			              <% } %>
                        </select>
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <label class="control-label col-sm-2">Loại tài liệu</label>
                      <div class="col-sm-10">
                        <select class="form-control" name="txtMaLoai" required>
                          <option value="">-- Chọn loại tài liệu --</option>
			              <% for (Material mate : listMates) { %>
			                <option value="<%= mate.getMaterialID() %>" <%= (mate.getMaterialID().equals(maLoai)) ? "selected" : "" %> >
			                  <%= mate.getMaterialName() %>
			                </option>
			              <% } %>
                        </select>
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <label class="control-label col-sm-2">Tải lên tài liệu</label>
                      <div class="col-sm-10">
                        <input type="file" class="form-control" name="fileDocs" 
                          accept="image/*,application/pdf,.doc,.docx,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation">
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <div class="col-sm-offset-2 col-sm-10 text-center">
                      	<a href="../admin/docs" class="btn btn-secondary">Hủy</a>
                        <button type="submit" name="btnAdd" value="add" class="btn btn-primary">Tải lên tài liệu</button>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <%@ include file="layout/footer.jsp" %>
      </div>
    </div>   
  </div>
</body>
</html>
