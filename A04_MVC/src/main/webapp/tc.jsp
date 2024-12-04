<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sachmodal.sachbo"%>
<%@page import="sachmodal.sach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<%
ArrayList<sach> ds = (ArrayList<sach>)request.getAttribute("ds"); 
int pageCount = (Integer)request.getAttribute("pageCount");
int currentPage = (Integer)request.getAttribute("currentPage");
String searchKeyword = request.getParameter("txtSearch");
NumberFormat nf = NumberFormat.getInstance();
nf.setGroupingUsed(true);
%>

    <%@ include file="layout/layout_navbar.jsp" %>
    <div class="container mt-3">
        <div class="row">
            <%@ include file="layout/layout_danhmuc.jsp" %>
            
            <div class="col-sm-8">
                <h5 class="mb-3">Sách</h5>
                <div class="row g-3">
                    <% 
                        int n = ds.size();
                        
                        if (n == 0) {
                    %>
                            <p class="text-center text-danger">Không tìm thấy sản phẩm nào.</p>
                    <% 
                        } else { 
                            for(int i = 0; i < n; i++){
                                sach s = ds.get(i);
                    %>
                        <div class="col-sm-4 mb-3">
					        <div class="card h-100">
					            <img src="<%= s.getAnh() %>" class="card-img-top img-fluid" style="height: 220px; object-fit: cover;" alt="<%= s.getTensach() %>">
					            <div class="card-body d-flex flex-column">
					                <h7 class="card-title text-center mb-3"><b><%= s.getTensach() %></b></h7>
					                <p class="card-text text-center mt-auto">Giá bán: <%= nf.format(s.getGia()) %> đ</p>
					                <div class="mt-3">
					                    <form action="giohangController" method="post">
					                        <input type="hidden" name="bookId" value="<%= s.getMasach() %>">
					                        <input type="hidden" name="ts" value="<%= s.getTensach() %>">
					                        <input type="hidden" name="gia" value="<%= s.getGia() %>">
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
					    </div>
                    <% 
                            }
                        } 
                    %>
                </div>

				
				<% if (n > 0) { %>
				    <div class="text-center">
				        <nav aria-label="Page navigation example">
				            <ul class="pagination justify-content-center">
				                <li class="page-item <%= currentPage > 1 ? "" : "disabled" %>">
				                    <a class="page-link" href="<%= currentPage > 1 ? "sachController?page=" + (currentPage - 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") + (request.getParameter("ml") != null ? "&ml=" + request.getParameter("ml") : "") : "#" %>" aria-label="Previous">
				                        <span aria-hidden="true">&laquo;</span>
				                    </a>
				                </li>
				            
				                <% for (int p = 1; p <= pageCount; p++) { %>
				                    <li class="page-item <%= p == currentPage ? "active" : "" %>">
				                        <a class="page-link" href="sachController?page=<%= p %><%= searchKeyword != null ? "&txtSearch=" + searchKeyword : "" %><%= request.getParameter("ml") != null ? "&ml=" + request.getParameter("ml") : "" %>"> <%= p %> </a>
				                    </li>
				                <% } %>
				            
				                <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
				                    <a class="page-link" href="<%= currentPage < pageCount ? "sachController?page=" + (currentPage + 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") + (request.getParameter("ml") != null ? "&ml=" + request.getParameter("ml") : "") : "#" %>" aria-label="Next">
				                        <span aria-hidden="true">&raquo;</span>
				                    </a>
				                </li>
				            </ul>
				         </nav>
				    </div>
				<% } %>
            </div>

            <%@ include file="layout/layout_timkiem.jsp" %>
        </div>
    </div>
</body>
</html>
