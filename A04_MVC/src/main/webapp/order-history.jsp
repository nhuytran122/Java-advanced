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
                    <table class="table table-hover table-borderless">
                        <thead class="thead-light">
                            <tr>
                                <th>Mã Hóa Đơn</th>
                                <th>Ngày Mua</th>
                                <th>Mã Sách</th>
                                <th>Số Lượng Mua</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                for (int i = 0; i < dsHoaDon.size(); i++) { 
                                    hoadon hoaDon = dsHoaDon.get(i);
                                    String formattedDate = new java.text.SimpleDateFormat("dd/MM/yyyy").format(hoaDon.getNgayMua());
                                    ArrayList<CTHD> dsCTHD = dsChiTietHoaDon.get(i);
                                    
                                    for (int j = 0; j < dsCTHD.size(); j++) { 
                                        CTHD cthd = dsCTHD.get(j); 
                            %>
                                <tr>
                                    <td><%= j == 0 ? hoaDon.getMaHoaDon() : "" %></td>
                                    <td><%= j == 0 ? formattedDate : "" %></td>
                                    <td><%= cthd.getMaSach() %></td>
                                    <td><%= cthd.getSoLuongMua() %></td>
                                </tr>
                            <% 
                                    } 
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
