package TranNhuYController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import TranNhuYtintucModal.TranNhuY_K45G_tintuc;
import TranNhuYtintucModal.TranNhuY_K45G_tintucbo;

/**
 * Servlet implementation class TranNhuY_K45G_tintucController
 */
@WebServlet("/TranNhuY_K45G_tintucController")
public class TranNhuY_K45G_tintucController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TranNhuY_K45G_tintucController() {
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

            TranNhuY_K45G_tintucbo ttbo = new TranNhuY_K45G_tintucbo();

            ArrayList<TranNhuY_K45G_tintuc> ds = new ArrayList<TranNhuY_K45G_tintuc>(); 

            String searchValue = request.getParameter("txtSearch");

            if (searchValue != null) {
                ds = ttbo.searchTin(searchValue);
            } else {
                ds = ttbo.getFullTin(); 
            }

            request.setAttribute("ds", ds);
            request.setAttribute("searchKeyword", searchValue); 

            RequestDispatcher rd = request.getRequestDispatcher("tc.jsp");
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
