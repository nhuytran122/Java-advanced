package Controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cartmodal.GioHangBo;
import cartmodal.Hang;
import chitiethoadonmodal.CTHD;
import chitiethoadonmodal.CTHDbo;
import hoadonmodal.hoadon;
import hoadonmodal.hoadonbo;
import khachhangmodal.khachhang;
import khachhangmodal.khachhangbo;

@WebServlet("/xacnhanController")
public class xacnhanController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public xacnhanController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            request.setAttribute("dsLoai", Chung.getDsLoai());
            GioHangBo g = (GioHangBo) session.getAttribute("gh");
            String userID = (String) session.getAttribute("userId");
            String pass = (String) session.getAttribute("userPass");
            khachhang kh = new khachhangbo().checkLogin(userID, pass);

            if (g != null && kh != null && !g.ds.isEmpty()) {

                hoadonbo hdbo = new hoadonbo();
                CTHDbo cthdbo = new CTHDbo();
                
                long maHoaDon = hdbo.themHoaDon(kh.getMakh());
                for (Hang hang : g.ds) {
                    cthdbo.themCTHD(hang.getMasach(), hang.getSoluong(), maHoaDon);
                }

                g.ds.clear();
                session.setAttribute("gh", g);
                
                session.setAttribute("invoiceCreated", true);
            }

            hoadonbo hdbo = new hoadonbo();
            CTHDbo cthdbo = new CTHDbo();
            ArrayList<hoadon> dsHoaDon = hdbo.getListHoaDon(kh.getMakh());
            if (dsHoaDon == null) {
                dsHoaDon = new ArrayList<>();
            }

            ArrayList<ArrayList<CTHD>> dsChiTietHoaDon = new ArrayList<>();
            for (hoadon order : dsHoaDon) {
                ArrayList<CTHD> dsCTHD = cthdbo.getListCTHD(order.getMaHoaDon());
                dsChiTietHoaDon.add(dsCTHD != null ? dsCTHD : new ArrayList<>()); 
            }

            request.setAttribute("dsHoaDon", dsHoaDon);
            request.setAttribute("dsChiTietHoaDon", dsChiTietHoaDon);

            RequestDispatcher rd = request.getRequestDispatcher("order-history.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
