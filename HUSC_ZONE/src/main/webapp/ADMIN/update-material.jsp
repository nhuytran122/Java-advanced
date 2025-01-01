<%@page import="MaterialModal.Material"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Sửa loại tài liệu - HUSCZone</title>
  <%@ include file="layout/import.jsp" %>
</head>
<body>
<%
  	Material material = (Material)request.getAttribute("material");
%>
  <div class="container-scroller">
    <%@ include file="layout/navbar.jsp" %>
    
    <div class="container-fluid page-body-wrapper">
      <%@ include file="layout/settings-panel.jsp" %>
      <%@ include file="layout/left_sidebar.jsp" %>
      
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title mb-4 text-center">Sửa loại</h4>
                  <form class="form-horizontal" action="../admin/edit-material" method="post">
                    <input type="hidden" name="mateID" value="<%= material.getMaterialID() %>">
                    <div class="form-group row">
                        <label class="control-label col-sm-2">Tên loại</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="txtTenLoai" required
                                value="<%= material.getMaterialName() %>">
                        </div>
                    </div>
                    
                    <div class="form-group row">
                        <label class="control-label col-sm-2">Mô tả</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="txtMoTa"
                                value="<%= material.getDescription()!= null ? material.getDescription() : "" %>">
                        </div>
                    </div>
                    
                            <div class="form-group row">
                              <div class="col-sm-offset-2 col-sm-10 text-center">
                                <a href="../admin/materials" class="btn btn-secondary">Hủy</a>
                                <button type="submit" name="btnUpdate" value="add" class="btn btn-primary">Lưu</button>
                              </div>
                            </div>
                  </form>

                </div>
              </div>
            </div>
          </div>
        </div>
        
        <%@ include file="layout/footer.jsp" %>
      </div>
    </div>   
  </div>
</body>
</html>