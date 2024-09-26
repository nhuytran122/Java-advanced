<%@page import="Tam.Hang"%>
<%@page import="Tam.CGioHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xóa hàng</title>
</head>
<body>
	<%
		CGioHang g = (CGioHang)session.getAttribute("gh");
		if (g != null) {
			if(request.getParameter("btn-delete-only") != null){
				int index = Integer.parseInt(request.getParameter("index"));
				(g.ds).remove(index);
			}
			else if(request.getParameter("btn-delete-all") != null){
				g.ds.clear();
					
			}
			else{
				String[] selectedItems = request.getParameterValues("checkboxItem");
				if (selectedItems != null) {
					for (String item : selectedItems) {
						int index = Integer.parseInt(item);
						g.ds.remove(index);
					}
				}
			}
			session.setAttribute("gh", g);
				
		}
		response.sendRedirect("DatHang.jsp");
	%>
</body>
</html>