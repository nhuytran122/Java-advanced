package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import khachhangmodal.khachhang;
import lichsumodal.lichsubo;

/**
 * Servlet implementation class lichsuController
 */
@WebServlet("/lichsuController")
public class lichsuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lichsuController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        try {
			request.setAttribute("dsLoai", Chung.getDsLoai());
			HttpSession session = request.getSession();
			if(session.getAttribute("kh") == null) {
            	response.sendRedirect("loginController");
            }
			else {
				khachhang kh = (khachhang)session.getAttribute("kh");
				lichsubo lsbo = new lichsubo();
				request.setAttribute("listLS", lsbo.getLichsu(kh.getMakh()));
				
				RequestDispatcher rd = request.getRequestDispatcher("order-history.jsp");
			    rd.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
