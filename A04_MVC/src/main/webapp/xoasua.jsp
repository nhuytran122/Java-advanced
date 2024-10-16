<%@page import="cartmodal.GioHangBo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		GioHangBo g = (GioHangBo) session.getAttribute("gh");
		if(request.getParameter("btnxoachon") != null){
			String[] gtck=request.getParameterValues("ck");
			for(String ms: gtck)
				g.Xoa(ms);
		}
		
		if(request.getParameter("butsuasl") != null){
			String mssua=request.getParameter("butsuasl");
			String slsua=request.getParameter(mssua);
			g.CapNhatSoLuong(mssua, Integer.parseInt(slsua));
		}
		
		if(request.getParameter("msxoa") != null){
			String msxoa = request.getParameter("msxoa");
			g.Xoa(msxoa);
		}
		
		if(request.getParameter("btnXoaAll") != null){
			g.ds.clear();
		}
		
		 session.setAttribute("gh", g);
		 response.sendRedirect("htgio2.jsp");
	%>
</body>
</html>