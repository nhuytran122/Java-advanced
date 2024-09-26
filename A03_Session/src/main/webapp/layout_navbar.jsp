<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
      <li class="active"><a href="tc.jsp">Trang chủ</a></li>
      <li><a href="calculator.jsp">Máy tính</a></li>
      <li><a href="#">Page 2</a></li>
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"
          >Page 1 <span class="caret"></span
        ></a>
        <ul class="dropdown-menu">
          <li><a href="#">Page 1-1</a></li>
          <li><a href="#">Page 1-2</a></li>
          <li><a href="#">Page 1-3</a></li>
        </ul>
      </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <% if (session.getAttribute("userId")!= null) { %>
      <li>
        <p class="navbar-text">
          Xin chào, <%=session.getAttribute("userId") %>
        </p>
      </li>
      <li>
        <a href="logout.jsp"
          ><span class="glyphicon glyphicon-log-in"></span> Logout</a
        >
      </li>

      <% } else { %>
      <li>
        <a href="login.jsp"
          ><span class="glyphicon glyphicon-user"></span> Login</a
        >
      </li>
      <% } %>
    </ul>
  </div>
</nav>
