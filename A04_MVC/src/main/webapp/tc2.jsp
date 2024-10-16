<%@page import="java.util.ArrayList"%>
<%@page import="sachmodal.sachbo"%>
<%@page import="sachmodal.sach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8"); %>
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
    <%@ include file="layout/layout_navbar.jsp" %>
    <div class="container mt-3">
        <div class="row">
            <%@ include file="layout/layout_danhmuc.jsp" %>
            
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
			
			                        <form action="<%= session.getAttribute("userId") != null ? "htgio2.jsp" : "login.jsp" %>" method="post">
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

            <%@ include file="layout/layout_timkiem.jsp" %> <!-- Right side: Tìm kiếm -->
        </div>
    </div>
</body>
</html>