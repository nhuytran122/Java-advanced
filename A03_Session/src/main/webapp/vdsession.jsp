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
    if(session.getAttribute("tong") == null){
        session.setAttribute("tong", (int) 0);
    }

	String txtN = request.getParameter("txtn");
	if(txtN != null){
		int n = Integer.parseInt(txtN);
		//b1: gan session vao 1 bien
        int s = (int) session.getAttribute("tong");
		//b2: thay doi gia tri cua bien
        s += n;
		//b3: gan bien vao session
        session.setAttribute("tong", s);
        out.print(session.getAttribute("tong"));
	}
	;
%>
    <form method="post" action="vdsession.jsp">
        n = <input type="number" name ="txtn" value="0"> <br>
        <input type="submit" name="but1" value="Tong">
    </form>
</body>
</html>