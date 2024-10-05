<%@page import="java.util.ArrayList"%>
<%@page import="sachmodal.sachbo"%>
<%@page import="sachmodal.sach"%>
<%@page import="loaimodal.loai"%>
<%@page import="loaimodal.loaibo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
    <%@ include file="layout_navbar.jsp" %>
    <div class="container mt-3">
        <div class="row">
            <div class="col-sm-2">
                <h5>Danh mục</h5>
                <hr>
                <div class="list-group">
                    <% 
                        loaibo lbo = new loaibo();
                        for(loai l : lbo.getLoai()){
                    %>
                        <a href="tc.jsp?ml=<%=l.getMaloai()%>" class="list-group-item list-group-item-action mb-1">
                            <%= l.getTenloai() %>
                        </a>
                    <% } %>
                </div>
            </div>

            <div class="col-sm-8">
			    <h5 class="mb-3">Sách</h5>
			    <div class="row">
			        <% 
			            sachbo sbo = new sachbo();
			            ArrayList<sach> ds = sbo.getSach();
			            String ml = request.getParameter("ml");
			            String keySearch = request.getParameter("txtSearch");
			
			            if(ml != null){
			                ds = sbo.TimMa(ml);
			            }
			            if(keySearch != null){
			                ds = sbo.Tim(keySearch);
			            }
			
			            int n = ds.size();
			            
			            if (n == 0) {
			        %>
			                <p class="text-center text-danger">Không tìm thấy sản phẩm nào.</p>
			        <% 
			            } else { 
			                for(int i = 0; i < n; i++){
			                    sach s = ds.get(i);
			        %>
			            <div class="col-sm-4 mb-4">
			                <div class="card">
			                    <img src="<%= s.getAnh() %>" class="card-img-top" style="height: 264px;" alt="<%= s.getTensach() %>">
			                    <div class="card-body">
			                        <h5 class="card-title text-center"><%= s.getTensach() %></h5>
			                        <p class="card-text text-center">Giá bán: <%= s.getGia() %> đ</p>
			
			                        <form action="<%= session.getAttribute("userId") != null ? "cart.jsp" : "login.jsp" %>" method="post">
			                            <input type="hidden" name="bookId" value="<%= s.getMasach() %>">
			                            <div class="d-flex justify-content-center">
			                                <button type="submit" class="btn bg-info">
			                                    <i class="bi bi-cart-plus me-2"></i>
			                                    Thêm vào giỏ
			                                </button>
			                            </div>
			                        </form>
			                    </div>
			                </div>
			            </div>
			        <% 
			                }
			            } 
			        %>
			    </div>
			</div>

            <div class="col-sm-2">
                <h5>Tìm kiếm</h5>
                <hr>
                <form action="tc.jsp" method="post">
                	<input type="text" name = "txtSearch" class="form-control" 
                		placeholder="Tìm kiếm sách..." 
                		value="<%= keySearch != null ? keySearch : "" %>"> 
                	<button type="submit" class="btn bg-primary-subtle mt-2">Tìm kiếm</button>
				</form>
                
            </div>
        </div>
    </div>
</body>
</html>
