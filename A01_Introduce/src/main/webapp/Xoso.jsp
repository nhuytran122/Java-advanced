<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList, java.util.List, java.util.Random" %>
  <%! 
    Random random = new Random();

    int genNum(int len) {
        return random.nextInt((int)Math.pow(10, len)) + (int)Math.pow(10, len - 1);
    }
%> 

<%
java.time.LocalDate today = java.time.LocalDate.now(); 
    int currentYear = today.getYear(); 
    int currentMonth = today.getMonthValue();
    int currentDay = today.getDayOfMonth(); 
    Random random = new Random();
    int giaiDacBiet = genNum(7);
    int giaiNhat = genNum(5);
    int giaiNhi = genNum(5);
    List<Integer> giaiBa = new ArrayList<>();
    for (int i = 0; i < 2; i++) giaiBa.add(genNum(5));

    ArrayList<Integer> giaiTu = new ArrayList<>();
    for (int i = 0; i < 7; i++) giaiTu.add(genNum(5));

    int giaiNam = genNum(4);

    List<Integer> giaiSau = new ArrayList<>();
    for (int i = 0; i < 3; i++) giaiSau.add(genNum(4));

    int giaiBay = genNum(3);

    int giaiTam = genNum(2);
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kết quả xổ số</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

    <div class="container mt-5 w-50">
        <div class="mb-4">
            <h3>Hôm nay, ngày <%= currentDay %>, tháng <%= currentMonth %>, năm <%= currentYear %></h3>
        </div>
        <div class="py-4">
            <form>
                <div class="row mb-3">
                    <div class="col-md-4">
                        <label for="day" class="form-label">Chọn ngày:</label>
                        <select class="form-select" id="day" name="day">
                            <% for (int i = 1; i <= 31; i++) { %>
                                <option value="<%= i %>" <%= i == currentDay ? "selected" : "" %>><%= i %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="col-md-4">
                        <label for="month" class="form-label">Chọn tháng:</label>
                        <select class="form-select" id="month" name="month">
                            <% for (int i = 1; i <= 12; i++) { %>
                                <option value="<%= i %>" <%= i == currentMonth ? "selected" : "" %>><%= i %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="col-md-4">
                        <label for="year" class="form-label">Chọn năm:</label>
                        <select class="form-select" id="year" name="year">
                            <% for (int i = currentYear; i >= currentYear - 10; i--) { %>
                                <option value="<%= i %>" <%= i == currentYear ? "selected" : "" %>><%= i %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Xem Kết Quả</button>
            </form>
        </div>
        
        <h3>Kết quả Xổ số Đà Nẵng <%= currentDay %>/<%= currentMonth %>/<%= currentYear %></h3>
        <table class="table table-bordered text-center mt-3">
            <thead>
                <tr class="table-dark">
                    <th scope="col">Giải</th>
                    <th scope="col">Kết quả</th>
                </tr>
            </thead>
            <tbody>
                <tr class="table-warning">
                    <td>Đặc biệt</td>
                    <td class="text-danger fw-bold fs-3"><%= giaiDacBiet%></td>
                </tr>
                <tr>
                    <td>Giải nhất</td>
                    <td><%= giaiNhat %></td>
                </tr>
                <tr>
                    <td>Giải nhì</td>
                    <td><%= giaiNhi %></td>
                </tr>
                <tr>
    <td>Giải ba</td>
    <td>
        <div class="d-flex justify-content-center">
            <% for (int i = 0; i < giaiBa.size(); i++) { %>
                <div class="px-5 <%= i < giaiBa.size() - 1 ? "border-end" : "" %>"><%= giaiBa.get(i) %></div>
            <% } %>
        </div>
    </td>
</tr>
                <tr>
    <td>Giải tư</td>
    <td>
        <div class="d-flex justify-content-center">
            <% for (int i = 0; i < 4; i++) { %>
                <div class="px-5 border-end"><%= giaiTu.get(i) %></div>
            <% } %>
        </div>
        <div class="d-flex justify-content-center mt-2">
            <% for (int i = 4; i < giaiTu.size(); i++) { %>
                <div class="px-5 <%= i < giaiTu.size() - 1 ? "border-end" : "" %>"><%= giaiTu.get(i) %></div>
            <% } %>
        </div>
    </td>
</tr>


                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Giải năm</td>
                    <td><%= giaiNam %></td>
                </tr>
                <tr>
                    <td>Giải sáu</td>
                    <td>
                        <div class="d-flex justify-content-center">
                            <% for (Integer giai : giaiSau) { %>
                                <div class="px-5 border-end"><%= giai %></div>
                            <% } %>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Giải bảy</td>
                    <td><%= giaiBay %></td>
                </tr>
                <tr>
                    <td>Giải tám</td>
                    <td class="text-danger fw-bold fs-3"><%= giaiTam %></td>
                </tr>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
