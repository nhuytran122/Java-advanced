<%@page import="loaimodal.loai"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>BOOKSTORE - Thêm mới loại</title>
  
  <link rel="stylesheet" href="ADMIN/vendors/feather/feather.css">
  <link rel="stylesheet" href="ADMIN/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="ADMIN/vendors/css/vendor.bundle.base.css">
  <link rel="stylesheet" href="ADMIN/css/vertical-layout-light/style.css">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <link rel="shortcut icon" href="ADMIN/images/favicon.png" />
</head>

<body>

<%
  String maLoai = (String)request.getAttribute("maLoai");
  String tenLoai = (String)request.getAttribute("tenLoai");
    
%>
  <div class="container-scroller">
    <%@ include file="layoutAdmin/navbar.jsp" %>
    
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layoutAdmin/settings-panel.jsp" %>
      <%@ include file="layoutAdmin/left_sidebar.jsp" %>
      
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title mb-4 text-center">Thêm mới loại</h4>
                  <form class="form-horizontal" action="adminSaveLoaiController" method="post">
				    <div class="form-group row">
				        <label class="control-label col-sm-2">Mã loại</label>
				        <div class="col-sm-10">
				            <input type="text" class="form-control" name="txtMaLoai" required
				                value="<%= (maLoai != null) ? maLoai : "" %>">
				        </div>
				    </div>
				
				    <div class="form-group row">
				        <label class="control-label col-sm-2">Tên loại</label>
				        <div class="col-sm-10">
				            <input type="text" class="form-control" name="txtTenLoai" required
				                value="<%= (tenLoai != null) ? tenLoai : "" %>">
				        </div>
				    </div>
				
				    <div class="form-group row">
				        <div class="col-sm-offset-2 col-sm-10 text-center">
				            <!-- Chắc chắn nút này gửi giá trị "add" khi nhấn -->
				            <button type="submit" name="btnAdd" value="add" class="btn btn-primary">Lưu</button>
				            <a href="adminLoaiController" class="btn btn-secondary">Hủy</a>
				        </div>
				    </div>
				</form>

                </div>
              </div>
            </div>
          </div>
        </div>
        
        <%@ include file="layoutAdmin/footer.jsp" %>
      </div>
    </div>   
  </div>

  <script src="ADMIN/vendors/js/vendor.bundle.base.js"></script>
  <script src="ADMIN/js/off-canvas.js"></script>
  <script src="ADMIN/js/hoverable-collapse.js"></script>
  <script src="ADMIN/js/template.js"></script>
  <script src="ADMIN/js/settings.js"></script>
</body>
</html>