package TranNhuYController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TranNhuYthongkeModal.TranNhuY_K45G_thongke;
import TranNhuYthongkeModal.TranNhuY_K45G_thongkebo;
import TranNhuYtintucModal.TranNhuY_K45G_tintuc;
import TranNhuYtintucModal.TranNhuY_K45G_tintucbo;

/**
 * Servlet implementation class TranNhuY_K45G_thongKeController
 */
@WebServlet("/TranNhuY_K45G_thongKeController")
public class TranNhuY_K45G_thongKeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TranNhuY_K45G_thongKeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");


            ArrayList<TranNhuY_K45G_thongke> ds = new ArrayList<TranNhuY_K45G_thongke>(); 
            TranNhuY_K45G_thongkebo tkbo = new TranNhuY_K45G_thongkebo();
            ds = tkbo.thongKeSoLuongTinTheoLoai();

            request.setAttribute("dsThongKe", ds);

            RequestDispatcher rd = request.getRequestDispatcher("thongke.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
