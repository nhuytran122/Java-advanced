<%@ page import="Tam.CGioHang" %>
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
            }  
            else if (action.startsWith("delete-")) {
                String namePr = action.substring(7);
                if (g != null) {
                    g.Xoa(namePr);
                }
            }
        	
            else if (action.startsWith("update-")) {
                String tenHang = action.substring(7);
                String newQuantityStr = request.getParameter("newQuantity_" + tenHang); 

                if (newQuantityStr != null) {
                    try {
                        int newQuantity = Integer.parseInt(newQuantityStr);
                        g.CapNhatSoLuong(tenHang, newQuantity);
                    } catch (NumberFormatException e) {
                        out.println("Số lượng không hợp lệ!");
                    }
                }
            }
        }

        session.setAttribute("gh", g);

        response.sendRedirect("DatHang.jsp");
    %>
</body>
</html>
