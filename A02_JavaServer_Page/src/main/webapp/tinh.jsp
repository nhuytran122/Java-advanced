<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculator</title>
</head>
<body>
    <%
        String tmpa = request.getParameter("txta");
        String tmpb = request.getParameter("txtb");
        String btncong = request.getParameter("btncong");
        String btntru = request.getParameter("btntru");
        String btnnhan = request.getParameter("btnnhan");
        String btnchia = request.getParameter("btnchia");
        
        if(tmpa != null && tmpb != null){
            int a = Integer.parseInt(tmpa);
            int b = Integer.parseInt(tmpb);
            int kq = 0;
            
            if(btncong != null)
            	kq = a + b;
            else if(btntru != null)
            	kq = a - b;
            else if(btnnhan != null)
            	kq = a*b;
            else {
            	if( b == 0)
            		kq = 0;
            	else 
            		kq = a/b;
            }
            String urltmp = "maytinh.jsp?a=" + a + "&b=" + b + "&kq=" + kq; 
            response.sendRedirect(urltmp);
        }
        else
        	out.println("Chua nhap a, b");
    %>
</body>
</html>