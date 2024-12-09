<%@ page import="java.util.ArrayList" %>
<%@ page import="loaimodal.loai" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>BOOKSTORE - Thêm mới sách</title>
  
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
  <div class="container-scroller">
    <% 
      // Lấy giá trị từ request
      String maSach = (String) request.getAttribute("maSach");
      String tenSach = (String) request.getAttribute("tenSach");
      String soLuong = (String) request.getAttribute("soLuong");
      String gia = (String) request.getAttribute("gia");
      String tacGia = (String) request.getAttribute("tacGia");
      String soTap = (String) request.getAttribute("soTap");
      String maloai = (String) request.getAttribute("maloai");
      ArrayList<loai> loaiSach = (ArrayList<loai>) request.getAttribute("dsLoai");
      Boolean isInvalid = (Boolean) request.getAttribute("isInvalid");
    %>
    
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layoutAdmin/navbar.jsp" %>
      <%@ include file="layoutAdmin/settings-panel.jsp" %>
      <%@ include file="layoutAdmin/left_sidebar.jsp" %>
      
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title mb-4 text-center">Thêm mới sách</h4>
                  <form class="form-horizontal" action="adminSaveSachController" method="post" enctype="multipart/form-data">
                    <div class="form-group row">
                      <label class="control-label col-sm-2">Mã sách</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" name="txtMaSach" value="<%= maSach != null ? maSach : "" %>" required>
                        
			          <% if (isInvalid != null && isInvalid) { %>
		              	<span class="text-danger">Mã sách đã tồn tại!</span>
		              <% } %>
                      </div>
                      
                    </div>

                    <div class="form-group row">
                      <label class="control-label col-sm-2">Tên sách</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" name="txtTenSach" value="<%= tenSach != null ? tenSach : "" %>" required>	                      
                      </div>
                       
                    </div>

                    <div class="form-group row">
                      <label class="control-label col-sm-2">Số lượng</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control number-separator" name="txtSoLuong" value="<%= soLuong != null ? soLuong : "" %>" min="1" required>
                      </div>
                    </div>

                    <div class="form-group row">
                      <label class="control-label col-sm-2">Giá</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control number-separator" name="txtGia" value="<%= gia != null ? gia : "" %>" min="0" required>
                      </div>
                       
                    </div>

                    <div class="form-group row">
                      <label class="control-label col-sm-2">Loại sách</label>
                      <div class="col-sm-10">
                        <select class="form-control" name="txtMaloai" required>
                          <option value="">-- Chọn loại sách --</option>
                          <% for (loai loai : loaiSach) { %>
                            <option value="<%= loai.getMaloai() %>" <%= (loai.getMaloai().equals(maloai)) ? "selected" : "" %> >
                              <%= loai.getTenloai() %>
                            </option>
                          <% } %>
                        </select>
                      </div>
                    </div>

                    <div class="form-group row">
                      <label class="control-label col-sm-2">Tác giả</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" name="txtTacGia" value="<%= tacGia != null ? tacGia : "" %>" required>
                      </div>
                    </div>

                    <div class="form-group row">
                      <label class="control-label col-sm-2">Số tập</label>
                      <div class="col-sm-10">
                        <input type="text" class="form-control" name="txtSoTap" value="<%= soTap != null ? soTap : "" %>" required>
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
						  <!-- Hiển thị ảnh nếu có -->
						  <img id="imagePreview" alt="Image Preview" style="max-height: 250px; display: none "/>
						</div>
                    </div>

                    <div class="form-group row">
                      <div class="col-sm-offset-2 col-sm-10 text-center">
                        <button type="submit" name="btnAdd" value="add" class="btn btn-primary">Lưu</button>
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
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/5.0.9/jquery.inputmask.min.js"></script>
    <script>
    $(document).ready(function () {
        $('.number-separator').inputmask({
            alias: "numeric",
            groupSeparator: ",",       // Dấu phẩy phân cách phần nghìn
            autoGroup: true,           // Tự động thêm dấu phân cách
            digits: 0,                 // Không có chữ số thập phân
            rightAlign: false,
            removeMaskOnSubmit: true   // Loại bỏ ký tự phân cách phần nghìn khi submit form
        });

        // Hiển thị ảnh preview khi người dùng chọn ảnh mới
        $('#fileInput').change(function (e) {
            var file = e.target.files[0];
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#imagePreview').attr('src', e.target.result);
                $('#imagePreview').show();
            };

            reader.readAsDataURL(file);
        });
    });
  </script>
</body>
</html>
