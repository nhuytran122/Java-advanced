<%@page import="cartmodal.GioHangBo"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập Nhật Số Lượng</title>
</head>
<body>
    <%
    	GioHangBo g = (GioHangBo) session.getAttribute("gh");
        String action = request.getParameter("action");

        if (g != null) {
            if (action.equals("deleteAll")) {
                g.ds.clear();
            } 
            else if (action.equals("deleteSelected")) {
                String[] selectedItems = request.getParameterValues("selectedItems");
                if (selectedItems != null) {
                    for (String masach : selectedItems) {
                        g.Xoa(masach);
                    }
                }
            } 
            else if (action.startsWith("delete-")) {
                String masach = action.substring(7);
                g.Xoa(masach);
            } 
            else if (action.startsWith("update-")) {
                String masach = action.substring(7);
                String newQuantityStr = request.getParameter("newQuantity-" + masach); 

                if (newQuantityStr != null) {
	                int newQuantity = Integer.parseInt(newQuantityStr);
	                g.CapNhatSoLuong(masach, newQuantity);
                }
            }
        }

        session.setAttribute("gh", g);
        response.sendRedirect("cart.jsp");
    %>
</body>
</html>
