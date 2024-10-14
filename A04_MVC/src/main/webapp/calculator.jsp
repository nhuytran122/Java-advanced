<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Máy tính</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<%
		float a = 0, b = 0, kq = 0;
		String tmpa = request.getParameter("txta");
        String tmpb = request.getParameter("txtb");
        
        String btncong = request.getParameter("btncong");
        String btntru = request.getParameter("btntru");
        String btnnhan = request.getParameter("btnnhan");
        String btnchia = request.getParameter("btnchia");

        boolean valuesPresent = (tmpa != null && !tmpa.isEmpty()) && (tmpb != null && !tmpb.isEmpty());
        if(valuesPresent){
            a = Float.parseFloat(tmpa);
            b = Float.parseFloat(tmpb);
            
            if(btncong != null)
            	kq = a + b;
            else if(btntru != null)
            	kq = a - b;
            else if(btnnhan != null)
            	kq = a*b;
            else {
            	if(b == 0){
            		out.println("b phải khác 0");
            		kq = 0;
            	}
            	else 
            		kq = a/b;
            }
        }
        else{
			if(btncong != null || btntru != null || btnnhan != null || btnchia != null)
				out.println("Chưa nhập a, b");
		}	
	%>
	<%@ include file="layout/layout_navbar.jsp" %>
  
	<form action="calculator.jsp" method="post">
	    a = <input type="number" name = "txta" value = "<%= tmpa %>"> <br>
	    b = <input type="number" name = "txtb" value = "<%= tmpb %>"> <br>
	    kq = <input type="number" name = "txtkq" value = "<%= kq %>"> <br>
	    <input type="submit" name = "btncong" value="+">
	    <input type="submit" name = "btntru" value="-">
	    <input type="submit" name = "btnnhan" value="*">
	    <input type="submit" name = "btnchia" value="/">
	</form>
</body>
</html>