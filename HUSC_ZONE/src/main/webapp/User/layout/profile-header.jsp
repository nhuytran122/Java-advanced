<%@page import="UserModal.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% User user_tmp = (User)session.getAttribute("user");%>
<div class="profile-header text-center">
	    <% if (user_tmp.getAvatar() != null && !user_tmp.getAvatar().isBlank()) { %>
	    	<img src="<%= request.getContextPath() %><%= user_tmp.getAvatar() %>" width="120" height="120" alt="Avatar" class="rounded-circle mb-3">
	        
	    <% } else { %>
        	<img src="../images/default-avt.jpg" width="120" height="120" alt="Default" class="rounded-circle mb-3">
    	<% } %>
	    <h2 class="mb-0"><%= user_tmp.getName() %></h2>
</div>