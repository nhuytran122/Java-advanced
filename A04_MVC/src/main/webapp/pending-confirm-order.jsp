<%@page import="hoadonmodal.hoadon"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="lichsumodal.lichsu"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Xác nhận đặt hàng</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<%
    ArrayList<lichsu> listLS = (ArrayList<lichsu>) request.getAttribute("listLS");
    hoadon hd = (hoadon)request.getAttribute("hd");
    
    NumberFormat nf = NumberFormat.getInstance();
    nf.setGroupingUsed(true);

    // Tính tổng tiền
    long totalPrice = 0;
    if (listLS != null) {
        for (lichsu ls : listLS) {
        	totalPrice += ls.getThanhTien();
        }
    }
%>
<body>
    <%@ include file="layout/layout_navbar.jsp" %>
    <div class="container mt-3">
        <div class="row">
            <%@ include file="layout/layout_danhmuc.jsp" %>
            
            <div class="col-sm-8">
                <h5>Lịch sử đặt hàng</h5>
                
                <% if (listLS == null || listLS.isEmpty()) { %>
                    <div class="alert alert-warning text-center" role="alert">
                        Không có lịch sử đặt hàng đang chờ xác nhận
                    </div>
                <% } else { %>
                    
                    <table class="table table-hover table-borderless">
                        <thead class="thead-light">
                            <tr>
                                <th class="col-4">Tên sách</th>
                                <th class="col-1">Giá</th>
                                <th class="col-1">SL</th>
                                <th class="col-2">Thành tiền</th>
                                <th class="col-2">Ngày đặt</th>
                                <th class="col-2">Trạng thái </th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                for (lichsu ls : listLS) { 
                                    String formattedDate = new java.text.SimpleDateFormat("dd/MM/yyyy").format(ls.getNgayMua());
                            %>
                            <tr>
                                <td><%= ls.getTensach() %></td>
                                <td><%= nf.format(ls.getGia()) %>đ</td>
                                <td><%= ls.getSoLuongMua() %></td>
                                <td><%= nf.format(ls.getThanhTien()) %>đ</td>
                                <td><%= formattedDate %></td>
                                <td><%= ls.isDaThanhToan() ? "Đã hoàn thành" : "Đang chờ" %></td>
                            </tr>
                            <% 
                                } 
                            %>
                        </tbody>
                    </table>
                    
                    <div class="text-end mt-3 mx-5">
                        <strong>Tổng tiền: <%= nf.format(totalPrice) %>đ</strong>
                    </div>
                <% } %>
            </div>
            
            <%@ include file="layout/layout_timkiem.jsp" %>
        </div>
    </div>
</body>
</html>
