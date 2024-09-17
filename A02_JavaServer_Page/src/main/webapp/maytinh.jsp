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
		String kq = "0";
		kq = request.getParameter("kq"); 
		String a = request.getParameter("a"); 
		String b = request.getParameter("b"); 
	%>
	<form action="tinh.jsp" method="post">
	    a = <input type="number" name = "txta" value = "<%=((a==null ? "0" : a)) %>"> <br>
	    b = <input type="number" name = "txtb" value = "<%=((b==null ? "0" : b)) %>"> <br>
	    kq = <input type="number" name = "txtkq" value = "<%=((kq==null ? "0" : kq)) %>"> <br>
	    <input type="submit" name = "btncong" value="+">
	    <input type="submit" name = "btntru" value="-">
	    <input type="submit" name = "btnnhan" value="*">
	    <input type="submit" name = "btnchia" value="/">
	</form>
</body>
</html>