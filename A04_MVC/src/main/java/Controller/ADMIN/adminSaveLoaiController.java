package Controller.ADMIN;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loaimodal.loaibo;

@WebServlet("/adminSaveLoaiController")
public class adminSaveLoaiController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public adminSaveLoaiController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            HttpSession session = request.getSession();

            // Kiểm tra xem người dùng đã đăng nhập chưa
            if(session.getAttribute("ad") == null) {
                response.sendRedirect("adminloginController");
                return;
            }

            String maloai = request.getParameter("txtMaLoai");
            String tenloai = request.getParameter("txtTenLoai");
            loaibo lbo = new loaibo();
            int done = 0;

            if(request.getParameter("btnAdd") != null) {
                done = lbo.addLoai(maloai, tenloai);
            }

            if(request.getParameter("btnUpdate") != null) {
            	done = lbo.updateLoai(maloai, tenloai);
            }

            if(done != 0) {
                response.sendRedirect("adminLoaiController");
                return;
            }

            request.setAttribute("maLoai", maloai);
            request.setAttribute("tenLoai", tenloai);
            request.setAttribute("isInvalid", false); 

            RequestDispatcher rd = request.getRequestDispatcher("ADMIN/add_loai.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}