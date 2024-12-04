package Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cartmodal.GioHangBo;
import cartmodal.Hang;
import chitiethoadonmodal.CTHDbo;
import hoadonmodal.hoadonbo;
import khachhangmodal.khachhang;
import lichsumodal.lichsubo;

@WebServlet("/xacnhanController")
public class xacnhanController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public xacnhanController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            request.setAttribute("dsLoai", Chung.getDsLoai());

            if (session.getAttribute("kh") == null) {
                response.sendRedirect("loginController");
            } else {
                GioHangBo g = (GioHangBo) session.getAttribute("gh");
                khachhang kh = (khachhang) session.getAttribute("kh");

                if (g != null && g.ds != null) {
                    hoadonbo hdbo = new hoadonbo();
                    CTHDbo cthdbo = new CTHDbo();

                    hdbo.themHoaDon(kh.getMakh());
                    long maxHD = hdbo.getMaxHD();
                    for (Hang hang : g.ds) {
                        cthdbo.themCTHD(hang.getMasach(), hang.getSoluong(), maxHD);
                    }

                    g.ds.clear();
                    session.setAttribute("gh", g);
                }

                lichsubo lsbo = new lichsubo();
                request.setAttribute("listLS", lsbo.getLichsu(kh.getMakh(), false));
                RequestDispatcher rd = request.getRequestDispatcher("pending-confirm-order.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
