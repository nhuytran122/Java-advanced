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
import loaimodal.loaibo;

/**
 * Servlet implementation class capnhatgioController
 */
@WebServlet("/updategioController")
public class updategioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updategioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Chung.getDsLoai(request);
		
		HttpSession session = request.getSession();
		GioHangBo g = (GioHangBo) session.getAttribute("gh");
		if(request.getParameter("deleteSelected") != null){
            String[] selectedItems = request.getParameterValues("selectedItems");
            if (selectedItems != null) {
                for (String masach : selectedItems) {
                    g.Xoa(masach);
                }
            }
		}
		
		if(request.getParameter("btnSuaSL") != null){
			String mssua = request.getParameter("btnSuaSL");
			String slsua = request.getParameter(mssua);
			g.CapNhatSoLuong(mssua, Integer.parseInt(slsua));
		}
		
		if(request.getParameter("msxoa") != null){
			String msxoa = request.getParameter("msxoa");
			g.Xoa(msxoa);
		}
		
		if(request.getParameter("deleteAll") != null){
			g.ds.clear();
		}
		session.setAttribute("gh", g);
		response.sendRedirect("giohangController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
