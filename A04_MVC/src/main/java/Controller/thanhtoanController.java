package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chitiethoadonmodal.CTHDbo;
import hoadonmodal.hoadonbo;
import khachhangmodal.khachhang;

@WebServlet("/thanhtoanController")
public class thanhtoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public thanhtoanController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("dsLoai", Chung.getDsLoai());
            HttpSession session = request.getSession();
            
            if(session.getAttribute("kh") == null) {
            	response.sendRedirect("loginController");
            	return;
            }
            else {
                khachhang kh = (khachhang) session.getAttribute("kh");
	            CTHDbo cthdbo = new CTHDbo();
	            hoadonbo hdbo = new hoadonbo();
	
	            if (request.getParameter("payNow") != null) {
	                Long maCTHD = Long.parseLong(request.getParameter("payNow"));
	                cthdbo.thanhtoanCTHD(maCTHD);
	            }
	            
	            if (request.getParameter("paySelected") != null) {
	                String[] selectedItems = request.getParameterValues("selectedItems");
	                if (selectedItems != null) {
	                    for (String maCTHD : selectedItems) {
	                        Long ma = Long.parseLong(maCTHD);
	                        cthdbo.thanhtoanCTHD(ma);
	                    }
	                }
	            }

	            if (request.getParameter("payAll") != null) {
	                cthdbo.thanhtoanAll(kh.getMakh());
	            }
	            
	            if (request.getParameter("deleteAll") != null) {
	                cthdbo.xoaAllCTHDNotPay(kh.getMakh());
	                
	                // hdbo.updateHDByKH(kh.getMakh());
	                response.sendRedirect("xacnhanController");
	                return;
	            }
	            
	            if (request.getParameter("deleteSelected") != null) {
	                String[] selectedItems = request.getParameterValues("selectedItems");
	                if (selectedItems != null) {
	                    for (String maCTHD : selectedItems) {
	                        Long ma = Long.parseLong(maCTHD);
	                        cthdbo.xoaCTHD(ma);
	                    }
	                }
	                // hdbo.updateHDByKH(kh.getMakh());
	                response.sendRedirect("xacnhanController");
	                return;
	            }
	            
	            // hdbo.updateHDByKH(kh.getMakh());
	            response.sendRedirect("lichSuMuaHangController");
            }
	            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
