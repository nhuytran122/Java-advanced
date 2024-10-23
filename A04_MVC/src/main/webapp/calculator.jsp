<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Máy tính</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet" />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
  <% float a=0, b=0, kq=0;
  									
  %>
	<%@ include file="layout/layout_navbar.jsp" %>

	<form action="tinhController" method="post">
	    a = <input type="number" name = "txta" value = "<%=request.getAttribute("tmpa") %>"> <br>
	    b = <input type="number" name = "txtb" value = "<%= request.getAttribute("tmpb") %>"> <br>
	    kq = <input type="number" name = "txtkq" value = "<%= request.getAttribute("kq") %>"> <br>
	    <input type="submit" name = "btncong" value="+">
	    <input type="submit" name = "btntru" value="-">
	    <input type="submit" name = "btnnhan" value="*">
	    <input type="submit" name = "btnchia" value="/">
	</form>
</body>
</html>