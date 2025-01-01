<%@page import="CategoryModal.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Sửa ngành học - HUSCZone</title>
  <%@ include file="layout/import.jsp" %>
</head>
<body>
<%
  	Category category = (Category)request.getAttribute("category");
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
                  <h4 class="card-title mb-4 text-center">Thêm mới ngành học</h4>
                  <form class="form-horizontal" action="../admin/edit-category" method="post" enctype="multipart/form-data">
				    <input type="hidden" name="cateID" value="<%= category.getCategoryID() %>">
				    <div class="form-group row">
				        <label class="control-label col-sm-2">Tên loại</label>
				        <div class="col-sm-10">
				            <input type="text" class="form-control" name="txtTenNganh" required
				                value="<%= category.getCategoryName() %>">
				        </div>
				    </div>
				    
				    <div class="form-group row">
					  <label class="control-label col-sm-2">Ảnh</label>
					  <div class="col-sm-10">
					    <input type="file" class="form-control" name="fileAnh" accept="image/*" required id="fileInput">
					  </div>
					</div>
					
					<div class="form-group row">
					  <label class="control-label col-sm-2">Preview Ảnh</label>
					  <div class="col-sm-10">
					  	<img id="imagePreview" src="<%= category.getImage() != null ? request.getContextPath() + category.getImage() : "" %>" alt="Image Preview" style="max-height: 250px; display: <%= category.getImage() != null ? "block" : "none" %>;"/>
					  </div>
					</div>

                    <div class="form-group row">
                      <div class="col-sm-offset-2 col-sm-10 text-center">
                      	<a href="../admin/categories" class="btn btn-secondary">Hủy</a>
                        <button type="submit" name="btnUpdate" value="add" class="btn btn-primary">Lưu</button>
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

<%@ include file="layout/script_preview_image.jsp" %>

</body>
</html>