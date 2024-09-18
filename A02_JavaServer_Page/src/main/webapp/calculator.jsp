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