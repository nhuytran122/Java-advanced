<%@page import="cartmodal.CGioHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập Nhật Số Lượng</title>
</head>
<body>
    <%
	    CGioHang g = (CGioHang) session.getAttribute("gh");
	    String action = request.getParameter("action");
	    
	    if (g != null) {
	        if (action.equals("deleteAll")) {
	            g.ds.clear();
	        } else if (action.equals("deleteSelected")) {
	            String[] selectedItems = request.getParameterValues("selectedItems");
	            if (selectedItems != null) {
	                for (String item : selectedItems) {
	                    g.Xoa(item);
	                }
	            }
	        } else if (action.startsWith("delete-")) {
	            String namePr = action.substring(7);
	            g.Xoa(namePr);
	        } else if (action.startsWith("update-")) {
	            for (int i = 0; i < g.ds.size(); i++) {
	                String tenHang = request.getParameter("tenHang_" + i);
	                String newQuantityStr = request.getParameter("newQuantity_" + i);
	                
	                if (tenHang != null && newQuantityStr != null) {
	                    try {
	                        int newQuantity = Integer.parseInt(newQuantityStr);
	                        g.CapNhatSoLuong(tenHang, newQuantity);
	                    } catch (NumberFormatException e) {
	                        out.println("Số lượng không hợp lệ!");
	                    }
	                }
	            }
	        }
	    }
	    
	    session.setAttribute("gh", g);
	    response.sendRedirect("cart.jsp");
	%>

</body>
</html>