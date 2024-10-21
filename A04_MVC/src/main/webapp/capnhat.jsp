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
		if(request.getParameter("deleteSelected") != null){
            String[] selectedItems = request.getParameterValues("selectedItems");
            if (selectedItems != null) {
                for (String masach : selectedItems) {
                    g.Xoa(masach);
                }
            }
		}
		
		if(request.getParameter("btnSuaSL") != null){
			String mssua = request.getParameter("btnSuaSL");
			String slsua = request.getParameter(mssua);
			g.CapNhatSoLuong(mssua, Integer.parseInt(slsua));
		}
		
		if(request.getParameter("msxoa") != null){
			String msxoa = request.getParameter("msxoa");
			g.Xoa(msxoa);
		}
		
		if(request.getParameter("deleteAll") != null){
			g.ds.clear();
		}

        session.setAttribute("gh", g);
        response.sendRedirect("cart.jsp");
    %>
</body>
</html>
