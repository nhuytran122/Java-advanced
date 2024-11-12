<%@page import="TranNhuYtintucModal.TranNhuY_K45G_tintuc"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Tin tức</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<%
ArrayList<TranNhuY_K45G_tintuc> ds = (ArrayList<TranNhuY_K45G_tintuc>)request.getAttribute("ds"); 
String searchKeyword = request.getParameter("txtSearch");
%>

    <div class="container mt-3">
        <div class="row">
            <%@ include file="layout/layout_timkiem.jsp" %>
            
            <div class="col-sm-8">
                <h5 class="mb-3">Tin tức</h5>
                <div class="row">
                    <% 
                        int n = ds.size();
                        
                        if (n == 0) {
                    %>
                            <p class="text-center text-danger">Không tìm thấy tin tức nào.</p>
                    <% 
                        } else { 
                            for(int i = 0; i < n; i++){
                            	TranNhuY_K45G_tintuc tt = ds.get(i);
                    %>
                        <div class="col-sm-4 mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title text-center"><%= tt.getTieuDe() %></h5>
                                    <p class="card-text text-center"><%= tt.getTomTat() %> </p>

                                    

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
