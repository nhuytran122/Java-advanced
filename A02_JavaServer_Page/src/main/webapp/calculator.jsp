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
		int a = 0, b = 0, kq = 0;
		String tmpa = request.getParameter("txta");
        String tmpb = request.getParameter("txtb");
        
        String btncong = request.getParameter("btncong");
        String btntru = request.getParameter("btntru");
        String btnnhan = request.getParameter("btnnhan");
        String btnchia = request.getParameter("btnchia");
        
        if(tmpa != null && tmpb != null){
            a = Integer.parseInt(tmpa);
            b = Integer.parseInt(tmpb);
            
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
        else
        	out.println("Chưa nhập a, b");
	%>
	<form action="mt.jsp" method="post">
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