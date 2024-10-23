<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="loaimodal.loai"%>
<%@page import="loaimodal.loaibo"%>
<div class="col-sm-2">
    <h5>Danh mục</h5>
    <hr>
    <%
	ArrayList<loai> dsLoai = (ArrayList<loai>)request.getAttribute("dsLoai");
	%>
    <div class="list-group">
        <% 
            for(loai l : dsLoai){
        %>
            <a href="sachController?ml=<%=l.getMaloai()%>" class="list-group-item list-group-item-action mb-1">
                <%= l.getTenloai() %>
            </a>
        <% } %>
    </div>
</div>
