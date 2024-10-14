<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-sm-2">
    <h5>Tìm kiếm</h5>
    <hr>
    <form action="tc.jsp" method="post">
        <input type="text" name="txtSearch" class="form-control" 
               placeholder="Tìm kiếm sách..." 
               value="<%= request.getParameter("txtSearch") != null ? request.getParameter("txtSearch") : "" %>">
        <button type="submit" class="btn bg-primary-subtle mt-2">Tìm kiếm</button>
    </form>
</div>
