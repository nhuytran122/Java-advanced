<%@page import="hoadonmodal.hoadon"%>
<%@page import="Controller.Chung"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="lichsumodal.lichsu" %>
<%@ page import="java.text.NumberFormat" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>BOOKSTORE - Chỉnh sửa hóa đơn</title>

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
        // Lấy danh sách chi tiết hóa đơn từ request
        ArrayList<lichsu> ds = (ArrayList<lichsu>) request.getAttribute("lstCTHD");
        Long maHD = Long.parseLong(request.getParameter("idHD"));
        hoadon hd = (hoadon)request.getAttribute("hd");
        
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(true);
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
                                    <h4 class="card-title">Chi tiết hóa đơn</h4>
                                    <form method="post" action="adminUpdateHDController">
	                                    <input type="hidden" name="idHD" value="<%= maHD %>">
	                                    <table class="table">
	                                        <thead>
	                                            <tr>
	                                            	<th>Chọn</th>
	                                                <th>Tên sách</th>
	                                                <th>Giá</th>
	                                                <th>Số lượng mua</th>
	                                                <th>Thành tiền</th>
	                                                <th>Tình trạng</th>
	                                                <th>Thao tác</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                            <% for(lichsu ls : ds) { %>
	                                            <tr>
				                                    <td>
				                                        <input type="checkbox" name="selectedItems" value="<%= ls.getMaCTHD() %>" />
				                                    </td>
	                                                <td><%= ls.getTensach() %></td>
	                                                <td><%= nf.format(ls.getGia()) %></td>
	                                                <td>
	                                                	<div class="input-group input-group-sm">
											                <input type="number" name="<%= ls.getMaCTHD() %>" 
											                    value="<%= ls.getSoLuongMua() %>" 
											                    class="form-control" />
											                <button type="submit" name="btnSuaSL" 
											                    value="<%= ls.getMaCTHD() %>" 
											                    class="btn btn-warning btn-sm mx-2" 
											                    title="Sửa">
											                    <i class="bi bi-pencil"></i>
											                </button>
											            </div>
	                                                </td>
	                                                <td><%= nf.format(ls.getThanhTien()) %></td>
	                                                <td><%= ls.isDamua() ? "Đã thanh toán" : "Đang chờ thanh toán" %></td>
	                                            	<td>
	                                            		<button type="submit" name="btnDeleteCTHD" value="<%= ls.getMaCTHD() %>" class="btn btn-danger btn-sm" title="Xóa">
						                                  <i class="bi bi-trash"></i>
						                                </button>
						                            </td>
	                                            </tr>
	                                            <% } %>
	                                        </tbody>
	                                        <tfoot>
											    <tr>
											        <td colspan="7">
											            <div class="d-flex justify-content-between align-items-center">
											                <div class="text-start">
											                    <button type="submit" name="deleteSelected" value="Xoa" class="btn btn-danger">Xóa đã chọn</button>
											                </div>
											                
											                <div class="text-end d-flex align-items-center">
											                    <h4>Tổng hóa đơn: <%= nf.format(hd.getThanhTien()) %></h4>
											                    <button type="submit" name="btnPayHDInEdit" value="payHD" class="btn btn-success mx-3" title="Xác nhận thanh toán">
											                        XÁC NHẬN THANH TOÁN
											                    </button>
											                </div>
											            </div>
											        </td>
											    </tr>
											</tfoot>
	                                    </table>
	                            	</form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>

<script src="ADMIN/vendors/js/vendor.bundle.base.js"></script>
<script src="ADMIN/js/off-canvas.js"></script>
<script src="ADMIN/js/hoverable-collapse.js"></script>
<script src="ADMIN/js/template.js"></script>
<script src="ADMIN/js/settings.js"></script>

</html>