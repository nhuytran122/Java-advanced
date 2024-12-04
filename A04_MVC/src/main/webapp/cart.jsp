<%@page import="java.text.NumberFormat"%>
<%@page import="cartmodal.GioHangBo"%>
<%@page import="cartmodal.Hang"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sachmodal.sachbo"%>
<%@page import="sachmodal.sach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Giỏ Hàng</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
    </style>
</head>
<%
	NumberFormat nf = NumberFormat.getInstance();
	nf.setGroupingUsed(true);
%>
<body>
    <%@ include file="layout/layout_navbar.jsp" %>
    <div class="container mt-3">
        <div class="row">
            <%@ include file="layout/layout_danhmuc.jsp" %>
            
            <div class="col-sm-8">
                <h5>Giỏ hàng</h5>
                <% 
                    GioHangBo g = (GioHangBo) session.getAttribute("gh");
                    int sh = g.ds.size();

                    if(sh > 0){
                %>

                <!-- Form để cập nhật giỏ hàng -->
                <form method="post" action="updategioController">
                    <table class="table table-hover">
                        <thead class="thead-light">
                            <tr>
                                <th class="col-1">Chọn</th>
                                <th class="col-3">Tên Hàng</th>
                                <th class="col-2">Giá</th>
                                <th class="col-2">Số Lượng</th>
                                <th class="col-2">Thành Tiền</th>
                                <th class="col-2">Thao Tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                for (int i = 0; i < sh; i++) {
                                    Hang h = g.ds.get(i);
                                    String sachId = h.getMasach();
                            %>
                                <tr>
                                    <td>
                                        <input type="checkbox" name="selectedItems" value="<%= sachId %>" />
                                    </td>
                                    <td><%= h.getTensach() %></td>
                                    <td><%= nf.format(h.getGia()) %></td>
                                    <td>
                                        <input type="number" name="<%= sachId %>" value="<%= h.getSoluong() %>" class="form-control" min="1"/>
                                    </td>
                                    <td><%= nf.format(h.getThanhtien()) %></td>
                                    <td>
									    <button type="submit" name="btnSuaSL" value="<%=sachId%>" class="btn btn-warning btn-sm"> 
									        <i class="bi bi-pencil"></i> Sửa 
									    </button>  
									    <a href="updategioController?msxoa=<%=sachId %>" class="btn btn-danger btn-sm">
									        <i class="bi bi-trash"></i> Xóa
									    </a>      
									</td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>

                    <div class="text-end mb-3">
                        <strong>Tổng tiền: <%= nf.format(g.Tongtien()) %></strong>
                    </div>

                    <div class="text-end">
                        <button type="submit" name="deleteSelected" value="Xoa" class="btn btn-danger me-2">Xóa đã chọn</button>
                        <button type="submit" name="deleteAll" value="deleteAll" class="btn btn-danger">Xóa tất cả</button>
                    </div>
                </form>

                <form method="post" action="xacnhanController" class="mt-3">
                    <div class="text-end">
                        <button type="submit" name="confirm" value="confirm" class="btn btn-success">Xác nhận đặt hàng</button>
                    </div>
                </form>

                <% 
                    } else { 
                %>
                    <div class="alert alert-warning" role="alert">
                        Giỏ hàng của bạn đang trống. Hãy thêm sản phẩm để tiếp tục mua sắm!
                    </div>
                <% 
                    } 
                %>
            </div>
            
            <%@ include file="layout/layout_timkiem.jsp" %>
        </div>
    </div>
</body>
</html>
