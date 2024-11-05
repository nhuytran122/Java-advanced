<%@page import="lichsumodal.lichsu"%>
<%@page import="java.util.ArrayList"%>
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
    ArrayList<lichsu> listLS = (ArrayList<lichsu>) request.getAttribute("listLS");
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
                        Không có lịch sử mua hàng
                    </div>
                <% } else { %>
                    <table class="table table-hover table-borderless">
                        <thead class="thead-light">
                            <tr>
                                <th>Tên sách</th>
                                <th>Số Lượng Mua</th>
                                <th>Giá</th>
                                <th>Thành tiền</th>
                                <th>Ngày mua</th>
                                <th>Đã mua</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                for (lichsu ls : listLS) { 
                                    String formattedDate = new java.text.SimpleDateFormat("dd/MM/yyyy").format(ls.getNgayMua());
                            %>
                            <tr>
                                <td><%= ls.getTensach() %></td>
                                <td><%= ls.getSoLuongMua() %></td>
                                <td><%= ls.getGia() %></td>
                                <td><%= ls.getThanhTien() %></td>
                                <td><%= formattedDate %></td>
                                <td><%= ls.isDamua() ? "Đã mua" : "Chưa mua" %></td>
                            </tr>
                            <% 
                                } 
                            %>
                        </tbody>
                    </table>
                <% } %>
            </div>
            
            <%@ include file="layout/layout_timkiem.jsp" %>
        </div>
    </div>
</body>
</html>
