<%@page import="UserModal.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% User user_tmp = (User)session.getAttribute("user");%>
<div class="profile-header text-center">
	    <% if (user_tmp.getAvatar() == null || user_tmp.getAvatar().isBlank()) { %>
	        <img src="../images/default-avt.jpg" style="width: 120px; height: 120px" alt="Default" class="rounded-circle mb-3">
	    <% } else { %>
        <img src="<%= request.getContextPath() %><%= user_tmp.getAvatar() %>" style="width: 120px; height: 120px" alt="Avatar" class="rounded-circle mb-3">
    	<% } %>
	    <h2 class="mb-0"><%= user_tmp.getName() %></h2>
</div>