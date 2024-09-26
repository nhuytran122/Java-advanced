<%@page import="Tam.CGioHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Mua hàng</title>
</head>
<body>
    <form method='post' action='DatHang.jsp'>
        Ten hang: <input type='text' name='txtth'><br>
        Gia: <input type='text' name='txtgia'><br>
        So luong: <input type='text' name='txtsl'><br>
        <input type='submit' name='un1' value='Dat Hang'>
    </form>

    <h2>Gio hang</h2>
    <%
        String th = request.getParameter("txtth");
        String gia = request.getParameter("txtgia");
        String sl = request.getParameter("txtsl");
        
        if (th != null && gia != null && sl != null) {
            CGioHang g = new CGioHang();
            
            // Neu mua hang lan dau
            if (session.getAttribute("gh") == null) {
                session.setAttribute("gh", g); // Tao gio
            }
            
            // Gian session: gh vao bien: g
            g = (CGioHang) session.getAttribute("gh");
            
            // Them hang vao bien: g
            g.Them(th, Integer.parseInt(gia), Integer.parseInt(sl));
            
            // Luu bien vao session
            session.setAttribute("gh", g);
        }

        // Hien thi do trong session: gh
        if (session.getAttribute("gh") != null) {
            CGioHang g = (CGioHang) session.getAttribute("gh");
            int sh = g.ds.size();
    %>
    <form method="post" action="Xoa.jsp" style="text-align: right; margin-bottom: 10px;">
		<button name="btn-delete-all" >
			Xóa tất cả
        </button>
    </form>
    
    <form method="post" action="Xoa.jsp">
            <table border='1' width='100%'>
                <%
                    for (int i = 0; i < sh; i++) { 
                %>
                    <tr>
                        <td><input type="checkbox" name="checkboxItem" value="<%=i %>"></td>
                        <td>
                            <%= g.ds.get(i).getTenhang() %>
                        </td>
                        <td>
                            <%= g.ds.get(i).getGia() %>
                        </td>
                        <td>
                            <%= g.ds.get(i).getSoluong() %>
                            <form method='post' action='Sua.jsp?th=<%= g.ds.get(i).getTenhang() %>'>
                                <input type='text' name='txtsua'>
                                <input type='submit' name='tt' value='Sua'>
                            </form>
                        </td>
                        <td>
                            <%= g.ds.get(i).getThanhtien() %>
                        </td>
                        <td>
                        	<form method="post" action="Xoa.jsp?index=<%= i %>">
                            	<button name="btn-delete-only">
                                Xóa
                            	</button>
                            </form>
                        </td>
                    </tr>
                <%
                    } 
                %>
                <button type="submit" name="btn-delete-selected">Xóa hàng đã chọn</button>
            </table>
      </form>
            <div align='right'>Tong tien: <%= g.Tongtien() %></div>
    <%
        }
    %>
</body>
</html>