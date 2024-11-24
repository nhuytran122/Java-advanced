<%@page import="sachmodal.sach"%>
<%@page import="loaimodal.loai"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>BOOKSTORE - Sửa sách</title>
  
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
  sach s = (sach) request.getAttribute("book");
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
                  <h4 class="card-title mb-4 text-center">Sửa sách</h4>
                  <form class="form-horizontal" action="adminSaveSachController" method="post" enctype="multipart/form-data">
                    <div class="form-group row">
                      <label class="control-label col-sm-2">Mã sách</label>
                      <div class="col-sm-10">
                        <input disabled type="text" class="form-control" name="txtMasach" required value="<%= s.getMasach() %>">
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <label class="control-label col-sm-2">Tên sách</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" name="txtTensach" required value="<%= s.getTensach() %>">
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <label class="control-label col-sm-2" for="txtSoluong">Số lượng</label>
                      <div class="col-sm-10">
                        <input type="number" class="form-control" name="txtSoluong" min="1" required value="<%= s.getSoluong() %>">
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <label class="control-label col-sm-2">Giá</label>
                      <div class="col-sm-10">
                        <input type="number" class="form-control" name="txtGia" min="0" required value="<%= s.getGia() %>">
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <label class="control-label col-sm-2">Loại sách</label>
                      <div class="col-sm-10">
                        <select class="form-control" name="txtMaloai" required>
                          <option value="">-- Chọn loại sách --</option>
                          <%
                            ArrayList<loai> loaiSach = (ArrayList<loai>) request.getAttribute("dsLoai");
                            String currentLoai = s.getMaloai(); // Lấy mã loại sách hiện tại của sách
                            for (loai loai : loaiSach) {
                          %>
                            <option value="<%= loai.getMaloai() %>"
                              <%= currentLoai != null && currentLoai.equals(loai.getMaloai()) ? "selected" : "" %>>
                              <%= loai.getTenloai() %>
                            </option>
                          <%
                            }
                          %>
                        </select>
                      </div>
                    </div>
                    
                    <div class="form-group row">
                      <label class="control-label col-sm-2">Tác giả</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" name="txtTacgia" required value="<%= s.getTacgia() %>">
                      </div>
                    </div>

                    <div class="form-group row">
                      <label class="control-label col-sm-2">Số tập</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" name="txtSoTap" required value="<%= s.getSoTap() %>">
                      </div>
                    </div>

                    <div class="form-group row">
                      <label class="control-label col-sm-2">Ảnh</label>
                      <div class="col-sm-10">
                        <input type="file" class="form-control" name="fileAnh" accept="image/*">
                      </div>
                    </div>

                    <div class="form-group row">
					  <div class="col-sm-offset-2 col-sm-10 text-center">
					    <button type="submit" class="btn btn-primary">Lưu</button>
					    <a href="adminSachController" class="btn btn-secondary">Hủy</a>
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
