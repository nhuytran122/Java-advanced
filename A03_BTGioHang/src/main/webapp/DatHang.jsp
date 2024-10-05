<%@ page import="Tam.CGioHang" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Giỏ Hàng</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Giỏ Hàng</h2>
        
        <form method='post' action='DatHang.jsp' class="mb-4">
            <div class="form-group">
                <label for="txtth">Tên hàng:</label>
                <input type='text' id="txtth" name='txtth' class="form-control" required>
            </div>
            <div class="form-group">
                <label for="txtgia">Giá:</label>
                <input type='number' id="txtgia" name='txtgia' class="form-control" required>
            </div>
            <div class="form-group">
                <label for="txtsl">Số lượng:</label>
                <input type='number' id="txtsl" name='txtsl' class="form-control" required>
            </div>
            <button type='submit' name='un1' class='btn btn-primary'>Đặt hàng</button>
        </form>

        <h3>Danh Sách Sản Phẩm</h3>
        <%
            String th = request.getParameter("txtth");
            String gia = request.getParameter("txtgia");
            String sl = request.getParameter("txtsl");
            
            if (th != null && gia != null && sl != null) {
                CGioHang g = new CGioHang();
                
                // Nếu mua hàng lần đầu
                if (session.getAttribute("gh") == null) {
                    session.setAttribute("gh", g); // Tạo giỏ hàng
                }
                
                // Gán session: gh vào biến g
                g = (CGioHang) session.getAttribute("gh");
                
                // Thêm hàng vào biến g
                g.Them(th, Integer.parseInt(gia), Integer.parseInt(sl));
                
                // Lưu biến vào session
                session.setAttribute("gh", g);
            }
            
            // Hiển thị nội dung trong session: gh
            CGioHang g = (CGioHang) session.getAttribute("gh");
            if (g != null) {
                int sh = g.ds.size();
        %>
                <form method="post" action="CapNhat.jsp">
                    <table class="table table-bordered table-hover">
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
                            <% for (int i = 0; i < sh; i++) { %>
                                <tr>
                                    <td>
                                        <input type="checkbox" name="selectedItems" value="<%= g.ds.get(i).getTenhang() %>">
                                    </td>
                                    <td><%= g.ds.get(i).getTenhang() %></td>
                                    <td><%= g.ds.get(i).getGia() %></td>
                                    <td>
                                        <input type="number" name="newQuantity_<%= g.ds.get(i).getTenhang() %>" 
                                        	value="<%= g.ds.get(i).getSoluong() %>" class="form-control" size="5">
                                        <input type="hidden" name="tenHang" value="<%= g.ds.get(i).getTenhang() %>">
                                    </td>
                                    <td><%= g.ds.get(i).getThanhtien() %></td>
                                    <td>
                                        <button type="submit" name="action" value="update-<%= g.ds.get(i).getTenhang() %>" class="btn btn-warning btn-sm">Cập nhật</button>
                                        <button type="submit" name="action" value="delete-<%= g.ds.get(i).getTenhang() %>" class="btn btn-danger btn-sm">Xóa</button>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>

                    <div class="text-right">
                        <strong>Tổng tiền: <%= g.Tongtien() %></strong>
                    </div>
                    <div class="text-right">
                        <button type="submit" name="action" value="deleteSelected" class="btn btn-danger">Xóa đã chọn</button>
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

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
