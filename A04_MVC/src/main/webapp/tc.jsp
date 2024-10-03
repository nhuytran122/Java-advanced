<%@page import="java.util.ArrayList"%>
<%@page import="sachmodal.sachbo"%>
<%@page import="sachmodal.sach"%>
<%@page import="loaimodal.loai"%>
<%@page import="loaimodal.loaibo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Trang chủ</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  </head>
  <body>
    <%@ include file="layout_navbar.jsp" %>
    <div class="container">
    	<div class="row">
    		<div class="col-sm-2">
    			<% 
	    			loaibo lbo = new loaibo();
	    			for(loai l : lbo.getLoai()){
    			%>
    				<a href="tc.jsp?ml=<%=l.getMaloai()%>">
    					<%= l.getTenloai() %> <hr>
    				</a>
    				<%} %>
    		</div>
    		
    		<div class="col-sm-8">
    			<% 
	    			sachbo sbo = new sachbo();
    				ArrayList<sach> ds = sbo.getSach();
    			  	String ml = request.getParameter("ml");
    			  	if(ml != null){
    			  		ds = sbo.TimMa(ml);
    			  	}
    				int n = ds.size();
	    			for(int i = 0; i<ds.size(); i++){
	    				sach s = ds.get(i);
    			%>
    			
    				<div class="row">
		                <div class="col-sm-4">
		                    <img src="<%= s.getAnh() %>" style="height: 264px"> <br>
		                    <%=s.getTensach() %> <br>
		                    <%=s.getGia() %>
		                    <img src ="https://minhkhai.com.vn/store/images/buynow.jpg">
		                </div>
		                <%i++;
		                	if(i<n){ s = ds.get(i); %>
				                <div class="col-sm-4">
				                    <img src="<%= s.getAnh() %>" style="height: 264px"> <br>
				                    <%=s.getTensach() %> <br>
				                    <%=s.getGia() %>
				                    <img src ="https://minhkhai.com.vn/store/images/buynow.jpg">
				                </div>
				                <%} %>
				        <%i++;
		                	if(i<n){ s = ds.get(i); %>
				                <div class="col-sm-4">
				                    <img src="<%= s.getAnh() %>" style="height: 264px"> <br>
				                    <%=s.getTensach() %> <br>
				                    <%=s.getGia() %>
				                    <img src ="https://minhkhai.com.vn/store/images/buynow.jpg">
				                </div>
				                <%} %>
					</div>

    				
    				<%} %>

			</div>
    		
    		<div class="col-sm-2">
    			Hien thi Tim kiem
    		</div>
		</div>	
	</div>
  </body>
</html>