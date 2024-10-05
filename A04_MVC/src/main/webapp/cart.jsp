<%@page import="cartmodal.CGioHang"%>
<%@page import="cartmodal.Hang"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sachmodal.sachbo"%>
<%@page import="sachmodal.sach"%>
<%@page import="loaimodal.loai"%>
<%@page import="loaimodal.loaibo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Giỏ Hàng</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
    <%@ include file="layout_navbar.jsp" %>
    <div class="container mt-5">
        <h3>Giỏ hàng</h3>
		<%
			if(session.getAttribute("userId") == null){
				response.sendRedirect("tc.jsp");
			}
			CGioHang g = (CGioHang) session.getAttribute("gh");
		
		    String masach = request.getParameter("bookId");
		    
		 	// Nếu mua hàng lần đầu
            if (g == null) {
                g = new CGioHang();
                session.setAttribute("gh", g); 
            }
	        
	        // Thêm hàng vào biến g
	       	// Nếu có mã sách, thêm sách vào giỏ hàng
		    if (masach != null) {
		        g.Them(masach, 1);
		    }
	        
	        // Lưu biến vào session
	        session.setAttribute("gh", g);
	
			int sh = g.ds.size();
		
		    if(sh > 0){
		%>

                <form method="post" action="capnhat.jsp">
                    <table class="table table-hover">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col"></th>
                                <th scope="col">Tên Hàng</th>
                                <th scope="col">Giá</th>
                                <th scope="col">Số Lượng</th>
                                <th scope="col">Thành Tiền</th>
                                <th scope="col">Thao Tác</th>
                            </tr>
                        </thead>
                        <tbody>
						    <% for (int i = 0; i < sh; i++) { 
						        sach s = g.ds.get(i).getSach();
						    %>
						        <tr>
						            <td>
						                <input type="checkbox" name="selectedItems" value="<%= s.getTensach() %>">
						            </td>
						            <td><%= s.getTensach() %></td>
						            <td><%= s.getGia() %></td>
						            <td>
						                <input type="number" name="newQuantity_<%= i %>" value="<%= g.ds.get(i).getSoluong() %>" class="form-control" size="5">
						                <input type="hidden" name="tenHang_<%= i %>" value="<%= s.getTensach() %>">
						            </td>
						            <td><%= g.ds.get(i).getThanhtien() %></td>
						            <td>
						                <button type="submit" name="action" value="update-<%= s.getTensach() %>" class="btn btn-warning btn-sm">Cập nhật</button>
						                <button type="submit" name="action" value="delete-<%= s.getTensach() %>" class="btn btn-danger btn-sm">Xóa</button>
						            </td>
						        </tr>
						    <% } %>
						</tbody>

                    </table>

					<div class="text-end mb-3">
					    <strong>Tổng tiền: <%= g.Tongtien() %></strong>
					</div>
					<div class="text-end mb-3">
					    <button type="submit" name="action" value="deleteSelected" class="btn btn-danger me-2">Xóa đã chọn</button>
					    <button type="submit" name="action" value="deleteAll" class="btn btn-danger">Xóa tất cả</button>
					</div>

                </form>
        <% 
            } else { 
        %>
                <p>Giỏ hàng trống.</p>
        <% 
            } 
        %>
    </div>
</body>
</html>
