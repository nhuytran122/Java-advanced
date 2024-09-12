<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List, java.util.Random" %>
<%! 
    String genNum(int n) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = random.nextInt(10);
            result.append(num);
        }
        return result.toString();
    }

    List<String> genListNum(int cnt, int digits) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            list.add(genNum(digits));
        }
        return list;
    }
%> 

<%
    java.time.LocalDate today = java.time.LocalDate.now(); 
    int currentYear = today.getYear(); 
    int currentMonth = today.getMonthValue();
    int currentDay = today.getDayOfMonth(); 

    String giaiDacBiet = genNum(6);
    String giaiNhat = genNum(5);
    String giaiNhi = genNum(5);
    List<String> giaiBa = genListNum(2, 5);
    List<String> giaiTu = genListNum(7, 5);
    String giaiNam = genNum(4);
    List<String> giaiSau = genListNum(3, 4);
    String giaiBay = genNum(3);
    String giaiTam = genNum(2);
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Kết quả xổ số</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <style>
        td{
            line-height:40px;
        }
    </style>
    <body>
        <div class="container mt-3 w-50">
            <h3>Hôm nay, ngày <%= currentDay %>, tháng <%= currentMonth %>, năm <%= currentYear %></h3>
            <div class="py-3">
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
            
            <h3>Kết quả Xổ số Thừa Thiên Huế <%= currentDay %>/<%= currentMonth %>/<%= currentYear %></h3>
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
                        <td class="text-danger fw-bold fs-3"><%= giaiDacBiet %></td>
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
                                    <div class="px-5 <%= i == 3 ? "" : "border-end" %>"><%= giaiTu.get(i) %></div>
                                <% } %>
                            </div>

                            <div class="d-flex justify-content-center mt-2">
                                <% for (int i = 4; i < giaiTu.size(); i++) { %>
                                    <div class="px-5 <%= i < giaiTu.size() - 1 ? "border-end" : "" %>"><%= giaiTu.get(i) %></div>
                                <% } %>
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
                                <% for (int i = 0; i < giaiSau.size(); i++) { %>
                                    <div class="px-5 <%= i < giaiSau.size() - 1 ? "border-end" : "" %>"><%= giaiSau.get(i) %></div>
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
                        <td><%= giaiTam %></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
