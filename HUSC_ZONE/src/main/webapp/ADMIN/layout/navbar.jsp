<%@page import="UserModal.User"%>
<%    
    User currentUser = (User)session.getAttribute("user");
%>
<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
        <a class="navbar-brand brand-logo mr-5" href="../admin"><img src="../images/husc-removebg-preview.png" class="mr-2" alt="logo"/></a>
        <a class="navbar-brand brand-logo-mini" href="../admin"><img src="../images/husc-removebg-preview.png" alt="logo"/></a>
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
        <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
          <span class="icon-menu"></span>
        </button>
        
        <ul class="navbar-nav navbar-nav-right">
          
          <li class="nav-item nav-profile dropdown">
            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
              <% if (currentUser.getAvatar() != null && !currentUser.getAvatar().isBlank()) { %>
                  <img src="<%= request.getContextPath() %><%= currentUser.getAvatar() %>" alt="Avatar" class="rounded-circle me-2" width="30" height="30">
              <% } else { %>
                  <img src="../images/default-avt.jpg" width="30" height="30" alt="Default" class="rounded-circle me-2">
              <% } %>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
              <a href="../logout" class="dropdown-item">
                <i class="ti-power-off text-primary"></i>
                Logout
              </a>
            </div>
          </li>
        </ul>
      </div>
    </nav>