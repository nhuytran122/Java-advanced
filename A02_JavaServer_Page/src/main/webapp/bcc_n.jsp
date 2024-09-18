<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <% String txtNum = request.getParameter("txtNumber"); %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Bảng cửu chương</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  </head>
  <body>
    <div class="container w-50 p-5 mt-5">
        <div class="row">
            <div class="col-md-4">
                <form action="bcc_n.jsp" method="post">
                    <label class="form-label">Nhập n:</label>
                    <input 
                        type="number" 
                        name="txtNumber" 
                        value="<%= txtNum != null ? txtNum : "" %>" 
                        class="form-control"
                    />
                    <button type="submit" class="btn btn-info mt-3">Hiển thị</button>
                </form>
            </div>
            <div class="col-md-8">
                <% if (txtNum != null) { 
                    int number = Integer.parseInt(txtNum); 
                %>
                    <p>Bảng cửu chương <%= number %>:</p>
                    <table class="table table-bordered table-hover text-center w-50">
                        <% for (int i = 1; i <= 10; i++) { %>
                            <tr>
                                <td><%= number %> * <%= i %> = <%= number * i %></td>
                            </tr>
                        <% } %>
                    </table>
                <% } %>
            </div>
      </div>
    </div>
  </body>
</html>
