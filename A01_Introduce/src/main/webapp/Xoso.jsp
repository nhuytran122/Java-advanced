<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    java.util.Date today = new java.util.Date();
    int currentYear = today.getYear() + 1900; // Cần cộng 1900 vì getYear trả về số năm kể từ 1900
    int currentMonth = today.getMonth() + 1;
    int currentDay = today.getDate();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kết quả xổ số</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <div class="container mt-5">
        <div class="mb-4">
            <h3>Hôm nay, ngày <%= currentDay %>, tháng <%= currentMonth %>, năm <%= currentYear %></h3>
        </div>

        <div>
            <form>
                <div class="col-md-6 mb-3">
                    <div class="row py-2 pb-3">
                        <div class="col-md-4">
                            <label for="day" class="form-label">Chọn ngày:</label>
                            <select class="form-select" id="day" name="day">
                                <% 
                                for (int i = 1; i <= 31; i++) { 
                                %>
                                    <option value="<%= i %>" <%= i == currentDay ? "selected" : "" %>><%= i %></option>
                                <% } %>
                            </select>
                        </div>

                        <div class="col-md-4">
                            <label for="month" class="form-label">Chọn tháng:</label>
                            <select class="form-select" id="month" name="month">
                                <% 
                                for (int i = 1; i <= 12; i++) { 
                                %>
                                    <option value="<%= i %>" <%= i == currentMonth ? "selected" : "" %>><%= i %></option>
                                <% } %>
                            </select>
                        </div>

                        <div class="col-md-4">
                            <label for="year" class="form-label">Chọn năm:</label>
                            <select class="form-select" id="year" name="year">
                                <% 
                                for (int i = currentYear; i >= currentYear - 10; i--) { 
                                %>
                                    <option value="<%= i %>" <%= i == currentYear ? "selected" : "" %>><%= i %></option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Xem Kết Quả</button>
                </div>

                
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>