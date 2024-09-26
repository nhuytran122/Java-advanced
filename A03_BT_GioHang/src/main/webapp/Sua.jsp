<%@page import="Tam.CGioHang"%> <%@ page language="java" contentType="text/html;
charset=ISO- 8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta
      http-equiv="Content-Type"
      content="text/html;
charset=ISO-8859-1"
    />
    <title>Insert title here</title>
  </head>
  <body>
    <% 
        CGioHang g = (CGioHang)session.getAttribute("gh"); 
        String th = request.getParameter("th"); 
        String sl = request.getParameter("txtsua");
        g.Them(th, 111,Integer.parseInt(sl)); 
        session.setAttribute("gh", g);
        response.sendRedirect("DatHang.jsp"); 
    %>
  </body>
</html>