package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class tamController
 */
@WebServlet("/tamController")
public class tamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tamController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//tao ra doi tuong out
//		PrintWriter out = response.getWriter();
//		out.print("<html><body> Hello </body></html>");
		
		// Tao ra 1 session
		HttpSession session = request.getSession();
		session.setAttribute("mySession", "Thu 1 ty");
		
		//Tao ra 1 bien request
		request.setAttribute("b1", "Haha");
		String[] ds = {"Nga", "Minh", "Hung"};
		request.setAttribute("ds", ds);
		
		//Chuyen tiep ve trang
		RequestDispatcher rd = request.getRequestDispatcher("hehe.jsp");
		rd.forward(request, response);
		
		//response.sendRedirect("hehe.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
