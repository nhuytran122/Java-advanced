<%@page import="sachmodal.sach"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>BOOKSTORE - Quản lý sách</title>
  
  <link rel="stylesheet" href="ADMIN/vendors/feather/feather.css">
  <link rel="stylesheet" href="ADMIN/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="ADMIN/vendors/css/vendor.bundle.base.css">
  
  <link rel="stylesheet" href="ADMIN/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="ADMIN/css/vertical-layout-light/style.css">

	<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  	<link rel="shortcut icon" href="ADMIN/images/favicon.png" />
</head>
<body>
<%
	ArrayList<sach> ds = (ArrayList<sach>)request.getAttribute("ds"); 
	int pageCount = (Integer)request.getAttribute("pageCount");
	int currentPage = (Integer)request.getAttribute("currentPage");
	String searchKeyword = request.getParameter("txtSearch");
%>
  <div class="container-scroller">
    <%@ include file="layoutAdmin/navbar.jsp" %>
     
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layoutAdmin/settings-panel.jsp" %>
     
      <%@ include file="layoutAdmin/left_sidebar.jsp" %>
      <div class="main-panel">
		<ul class="navbar-nav mr-lg-2 my-4" style="display: flex; justify-content: center; width: 100%;">
		  <li class="nav-item nav-search d-none d-lg-block" style="display: flex; align-items: center;">
		    <form action="adminSachController" method="get" class="d-flex" style="width: 100%; justify-content: center; align-items: center;">
		      <input type="text" class="form-control form-control-sm me-2" id="navbar-search-input" placeholder="Tìm kiếm sách..." name="txtSearch" aria-label="search" 
		             value="<%= request.getParameter("txtSearch") != null ? request.getParameter("txtSearch") : "" %>"
		             style="width: 400px; font-size: 14px; margin-right: 10px;"> 
		      <button type="submit" class="btn btn-primary btn-sm p-2">
		        <i class="bi bi-search"></i>
		      </button>
		    </form>
		  </li>
		</ul>
        <div class="content-wrapper">    
          <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
              <div class="card position-relative">
                <div class="card-body">
				    <div class="d-flex justify-content-between align-items-center mb-4">
				        <h4 class="card-title">Danh sách Sách</h4>
				        <button class="btn btn-primary btn-sm"><i class="bi bi-plus-circle"></i> Thêm mới sách</button>
				    </div>
				   
				    <div class="table-responsive">
				        <table class="table table-hover">
				            <thead class="table-light">
				                <tr>
				                    <th>Ảnh</th>
				                    <th>Tên sách</th>
				                    <th>Số lượng</th>
				                    <th>Giá</th>
				                    <th>Loại sách</th>
				                    <th>Tác giả</th>
				                    <th>Thao tác</th>
				                </tr>
				            </thead>
				            <tbody>
				             <% 
			                        int n = ds.size();
			                        
			                        if (n == 0) {
			                    %>
			                            <p class="text-center text-danger">Không tìm thấy sách nào.</p>
			                    <% 
			                        } else { 
			                            for(int i = 0; i < n; i++){
			                                sach s = ds.get(i);
			                    %>
				                
				                <tr>
				                    <td>
				                        <img src="<%= s.getAnh() %>" alt="" class="img-fluid rounded" style="width: 150px; height: auto;">
				                    </td>
				                    <td><%= s.getTensach() %></td>
				                    <td><%= s.getSoluong() %></td>
				                    <td><%= s.getGia() %></td>
				                    <td><%= s.getMaloai() %></td>
				                    <td><%= s.getTacgia() %></td>
				                    <td>
				                        <div class="btn-group" role="group">
				                            <button class="btn btn-success btn-sm" title="Xem chi tiết">
				                                <i class="bi bi-eye"></i>
				                            </button>
				                            <button class="btn btn-warning btn-sm" title="Sửa">
				                                <i class="bi bi-pencil"></i>
				                            </button>
				                            <button class="btn btn-danger btn-sm" title="Xóa">
				                                <i class="bi bi-trash"></i>
				                            </button>
				                        </div>
				                    </td>
				                </tr>
				                <% 
                            }
                        } 
                    %>
				            </tbody>
				        </table>
				    </div>
				</div>

              </div>
            </div>
          </div>
            <% if (n > 0) { %>
				    <div class="text-center">
				        <nav aria-label="Page navigation example">
				            <ul class="pagination justify-content-center">
				                <li class="page-item <%= currentPage > 1 ? "" : "disabled" %>">
				                    <a class="page-link" href="<%= currentPage > 1 ? "adminSachController?page=" + (currentPage - 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") + (request.getParameter("ml") != null ? "&ml=" + request.getParameter("ml") : "") : "#" %>" aria-label="Previous">
				                        <span aria-hidden="true">&laquo;</span>
				                    </a>
				                </li>
				            
				                <% for (int p = 1; p <= pageCount; p++) { %>
				                    <li class="page-item <%= p == currentPage ? "active" : "" %>">
				                        <a class="page-link" href="adminSachController?page=<%= p %><%= searchKeyword != null ? "&txtSearch=" + searchKeyword : "" %><%= request.getParameter("ml") != null ? "&ml=" + request.getParameter("ml") : "" %>"> <%= p %> </a>
				                    </li>
				                <% } %>
				            
				                <li class="page-item <%= currentPage < pageCount ? "" : "disabled" %>">
				                    <a class="page-link" href="<%= currentPage < pageCount ? "adminSachController?page=" + (currentPage + 1) + (searchKeyword != null ? "&txtSearch=" + searchKeyword : "") + (request.getParameter("ml") != null ? "&ml=" + request.getParameter("ml") : "") : "#" %>" aria-label="Next">
				                        <span aria-hidden="true">&raquo;</span>
				                    </a>
				                </li>
				            </ul>
				         </nav>
				    </div>
				<% } %>
        <%@ include file="layoutAdmin/footer.jsp" %>
      </div>
    </div>   
  </div>
  </div>

  <script src="ADMIN/vendors/js/vendor.bundle.base.js"></script>
  <script src="ADMIN/js/template.js"></script>
  <script src="ADMIN/js/settings.js"></script>
</body>

</html>

