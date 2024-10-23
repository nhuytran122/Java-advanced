<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
  Gia tri cua session: <%=session.getAttribute("mySession") %>
  Gia tri cua bien request: <% String st = (String)request.getAttribute("b1");
  									out.print(st);
  							%>
  Gia tri cua bien request []: <% String[] ds =  (String[])request.getAttribute("ds");
  								for(String tmp : ds){
  									out.print(tmp);
  								}
  %>
  
  							<hr>
    <%for (int i=1; i<=100; i++){%>
    <b>Xin chao <%= i%></b>
    <hr />
    <%} %>
  </body>
</html>
