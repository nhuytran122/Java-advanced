<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="lichsumodal.lichsu" %>
<%@ page import="java.text.NumberFormat" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>BOOKSTORE - Chi tiết hóa đơn</title>

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
                        <!-- Card chi tiết hóa đơn -->
                        <div class="col-md-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Chi tiết hóa đơn</h4>
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Tên sách</th>
                                                <th>Số lượng mua</th>
                                                <th>Giá</th>
                                                <th>Thành tiền</th>
                                                <th>Ngày mua</th>
                                                <th>Tình trạng</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% int count = 1; %>
                                            <% for(lichsu ls : ds) { %>
                                            <tr>
                                                <td><%= count++ %></td>
                                                <td><%= ls.getTensach() %></td>
                                                <td><%= ls.getSoLuongMua() %></td>
                                                <td><%= nf.format(ls.getGia()) %></td>
                                                <td><%= nf.format(ls.getThanhTien()) %></td>
                                                <td><%= ls.getNgayMua() %></td>
                                                <td><%= ls.isDamua() ? "Đã thanh toán" : "Đang chờ thanh toán" %></td>
                                            </tr>
                                            <% } %>
                                        </tbody>
                                    </table>
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
s