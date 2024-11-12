<%@page import="TranNhuYthongkeModal.TranNhuY_K45G_thongke"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Thống kê tin tức</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<%
ArrayList<TranNhuY_K45G_thongke> dsThongKe = (ArrayList<TranNhuY_K45G_thongke>)request.getAttribute("dsThongKe");
%>

<div class="container mt-3">
    <div class="row">
        <%@ include file="layout/layout_timkiem.jsp" %>
        
        <div class="col-sm-8">
            <h5 class="mb-3">Thống kê</h5>
            <div class="row">
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th scope="col">STT</th>
                            <th scope="col">Tên loại tin</th>
                            <th scope="col">Số lượng tin tức</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                        for (int i = 0; i < dsThongKe.size(); i++) {
                            TranNhuY_K45G_thongke tk = dsThongKe.get(i);
                        %>
                        <tr>
                            <th scope="row"><%= i + 1 %></th>
                            <td><%= tk.getTenLoai() %></td>
                            <td><%= tk.getSoLuongTin() %></td>
                        </tr>
                        <% } %> 
                    </tbody>
                </table>
            </div>
        </div>

        <div class="col-sm-2">
            <h5>Thống kê loại tin</h5>
            <div class="list-group">
                <form action="TranNhuY_K45G_thongKeController" method="post">
                    <button type="submit" class="btn bg-primary-subtle mt-2">Thống kê</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
