package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loaimodal.loaibo;
import sachmodal.sach;
import sachmodal.sachbo;

/**
 * Servlet implementation class sachController
 */
@WebServlet("/sachController")
public class sachController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sachController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//Lay loai ve
		loaibo lbo = new loaibo();
		//chuyen dsLoai sang tc.jsp de hien thi
		request.setAttribute("dsLoai", lbo.getLoai());
		
		sachbo sbo = new sachbo();
        ArrayList<sach> ds = sbo.getSach();
        String ml = request.getParameter("ml");
        String keySearch = request.getParameter("txtSearch");

        if(ml != null){
            ds = sbo.TimMa(ml);
        }
        if(keySearch != null){
            ds = sbo.Tim(keySearch);
        }
        request.setAttribute("ds", ds);
        
        RequestDispatcher rd = request.getRequestDispatcher("tc.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
