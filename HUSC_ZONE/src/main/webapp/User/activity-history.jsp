<%@page import="V_DetailsReportModal.DetailsReport"%>
<%@page import="CommonModal.MethodCommon"%>
<%@page import="V_DetailsLikedModal.DetailsLiked"%>
<%@page import="V_DetailsCommentModal.DetailsComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nhật ký hoạt động - HUSCZone</title>
    <%@ include file="layout/import.jsp" %>
</head>
<style>
a {
	text-decoration: none; 
	color: #000;
}
</style>
<% 
	Long filterID = request.getAttribute("filterID") != null 
			? (Long)(request.getAttribute("filterID")) : 0L;
	ArrayList<DetailsComment> dsCmts = request.getAttribute("dsCmts") != null 
			? (ArrayList<DetailsComment>)request.getAttribute("dsCmts") : null;
	ArrayList<DetailsLiked> dsLikes = request.getAttribute("dsLikes") != null 
			?(ArrayList<DetailsLiked>) request.getAttribute("dsLikes") : null;
	ArrayList<DetailsReport> dsRpts = (ArrayList<DetailsReport>) request.getAttribute("dsRpts") != null
			? (ArrayList) request.getAttribute("dsRpts") : null;
	int pageCount = (Integer) request.getAttribute("pageCount");
    int currentPage = (Integer) request.getAttribute("currentPage");
%>

<body class="bg-light">
    <%@ include file="layout/navbar_for_Post.jsp" %>

    <div class="container-fluid my-3">
        <div class="row">
            <%@ include file="layout/sidebar.jsp" %>
            
            <main class="col-md-9 my-3">
                <div class="d-flex justify-content-between align-items-center mb-2">
			        <h4 class="pb-2">Nhật ký hoạt động</h4>
			        <form class="d-flex align-items-center" action="../activity-history" method="post">
			            <select name="filterID" class="form-select form-select-sm py-2 me-3" style="width: 200px;">
			                <option value="<%= Constants.FILTER_LIKED %>" 
			                    <%= filterID == Constants.FILTER_LIKED ? "selected" : "" %>>Lượt thích</option>
			                <option value="<%= Constants.FILTER_COMMENTED %>" 
			                    <%= filterID == Constants.FILTER_COMMENTED ? "selected" : "" %>>Bình luận</option>
			                    <option value="<%= Constants.FILTER_REPORT %>" 
			                    <%= filterID == Constants.FILTER_REPORT ? "selected" : "" %>>Báo cáo</option>
			            </select>
			            <button type="submit" name="btn-filter-ah" value="true" class="btn btn-primary-custom p-2">
			                <i class="bi bi-search"></i>
			            </button>
			        </form>
			    </div>
                <%
				    int n = 0;
				    if (dsLikes != null) 
				        n = dsLikes.size();
				    else if (dsCmts != null)
				        n = dsCmts.size();
				    else if(dsRpts != null)
				    	n = dsRpts.size();
				    
				%>

				<% if (dsCmts != null) { %>
				    <%@ include file="section/show-comments.jsp" %>
				<% } %>
				
				<% if (dsLikes != null) { %>
				    <%@ include file="section/show-likes.jsp" %>
				<% } %>

				<% if (dsRpts != null) { %>
				    <%@ include file="section/show-reports.jsp" %>
				<% } %>


		    
				<% if (pageCount > 1) { %>
					<nav>
					    <ul class="pagination justify-content-center mt-4">
					        <li class="page-item <%= currentPage > 1 ? "" : "disabled" %>">
					            <a class="page-link" 
								   href="<%= currentPage > 1 ? "../activity-history?page=" + (currentPage - 1) : "#" %>&filterID=<%=filterID%>" 
								   tabindex="-1" 
								   aria-disabled="true">
								    <i class="bi bi-chevron-left"></i>
								</a>

					        </li>
					        
					        <% for (int p = 1; p <= pageCount; p++) { %>
			                  <li class="page-item <%= p == currentPage ? "active" : "" %>">
			                    <a class="page-link" href="../activity-history?page=<%= p %>&filterID=<%=filterID%>">
			                      <%= p %>
			                    </a>
			                  </li>
			                <% } %>
					        <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
					            <a class="page-link" 
					            	href="<%= currentPage < pageCount ? "../activity-history?page=" + (currentPage + 1) : "#" %>&filterID=<%=filterID%>">
					                 <i class="bi bi-chevron-right"></i>
					            </a>
					        </li>
					    </ul>
					</nav>
			<% } %>
		</main>
        </div>
    </div>
</body>
</html>
