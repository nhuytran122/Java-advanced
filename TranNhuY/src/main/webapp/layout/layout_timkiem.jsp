<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8"); %>
<div class="col-sm-2">
    <h5>Tìm kiếm</h5>
    <hr>
    <form action="TranNhuY_K45G_tintucController" method="post">
        <input type="text" name="txtSearch" class="form-control" 
               placeholder="Tìm kiếm tin tức..." 
               value="<%= request.getParameter("txtSearch") != null ? request.getParameter("txtSearch") : "" %>">
        <button type="submit" class="btn bg-primary-subtle mt-2">Search</button>
    </form>
</div>