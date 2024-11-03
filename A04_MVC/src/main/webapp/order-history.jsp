<%@page import="cartmodal.Hang"%> 
<%@page import="hoadonmodal.hoadon"%>
<%@page import="java.util.ArrayList"%>
<%@page import="chitiethoadonmodal.CTHD"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lịch sử mua hàng</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<% 
    ArrayList<hoadon> dsHoaDon = (ArrayList<hoadon>) request.getAttribute("dsHoaDon");
    ArrayList<ArrayList<CTHD>> dsChiTietHoaDon = (ArrayList<ArrayList<CTHD>>) request.getAttribute("dsChiTietHoaDon");
 %>
<body>
    <%@ include file="layout/layout_navbar.jsp" %>
    <div class="container mt-3">
        <div class="row">
            <%@ include file="layout/layout_danhmuc.jsp" %>
            
            <div class="col-sm-8">
                <h5>Lịch sử mua hàng</h5>
                
                <% if (dsHoaDon == null || dsHoaDon.isEmpty()) { %>
                    <div class="alert alert-warning text-center" role="alert">
                        Không có lịch sử mua hàng
                    </div>
                <% } else { %>
                    <table class="table table-hover">
                        <thead class="thead-light">
                            <tr>
                                <th>Mã Hóa Đơn</th>
                                <th>Ngày Mua</th>
                                <th>Chi Tiết Sản Phẩm</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < dsHoaDon.size(); i++) { %>
                                <tr>
                                    <td><%= dsHoaDon.get(i).getMaHoaDon() %></td>
                                    <td><%= dsHoaDon.get(i).getNgayMua() %></td>
                                    <td>
                                        <table class="table table-borderless">
                                            <thead>
                                                <tr>
                                                    <th>Tên Sách</th>
                                                    <th>Số Lượng</th>
                                                    <th>Giá</th>
                                                    <th>Thành Tiền</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% 
                                                    ArrayList<CTHD> dsCTHD = dsChiTietHoaDon.get(i);
                                                    for (CTHD cthd : dsCTHD) { 
                                                        Hang hang = new Hang(cthd.getTenSach(cthd.getMaSach()), cthd.getSoLuongMua());
                                                        hang.setSach(cthd.getTenSach(cthd.getMaSach()));
                                                %>
                                                    <tr>
                                                        <td><%= hang.getSach().getTensach() %></td>
                                                        <td><%= cthd.getSoLuongMua() %></td>
                                                        <td><%= hang.getSach().getGia() %> đ</td>
                                                        <td><%= hang.getThanhtien() %> đ</td>
                                                    </tr>
                                                <% } %>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } %>
            </div>
            
            <%@ include file="layout/layout_timkiem.jsp" %>
        </div>
    </div>
</body>
</html>