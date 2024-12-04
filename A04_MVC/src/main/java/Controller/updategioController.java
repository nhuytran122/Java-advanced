package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cartmodal.GioHangBo;

@WebServlet("/updategioController")
public class updategioController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public updategioController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("dsLoai", Chung.getDsLoai());

            HttpSession session = request.getSession();
            GioHangBo g = (GioHangBo) session.getAttribute("gh");
            if (g == null) {
                g = new GioHangBo(); // Chỉ tạo mới nếu chưa có giỏ hàng
                session.setAttribute("gh", g);
            }

            // Xử lý xóa và cập nhật số lượng
            if (request.getParameter("deleteSelected") != null) {
                String[] selectedItems = request.getParameterValues("selectedItems");
                if (selectedItems != null) {
                    for (String masach : selectedItems) {
                        g.Xoa(masach);
                    }
                }
            }

            if (request.getParameter("btnSuaSL") != null) {
                String mssua = request.getParameter("btnSuaSL");
                String slsua = request.getParameter(mssua);
                g.CapNhatSoLuong(mssua, Integer.parseInt(slsua));
            }

            if (request.getParameter("msxoa") != null) {
                String msxoa = request.getParameter("msxoa");
                g.Xoa(msxoa);
            }

            if (request.getParameter("deleteAll") != null) {
                g.ds.clear();
            }
            session.setAttribute("gh", g);
            
            response.sendRedirect("giohangController");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
