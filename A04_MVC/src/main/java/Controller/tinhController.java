package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class tinhController
 */
@WebServlet("/tinhController")
public class tinhController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tinhController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		float a = 0, b = 0, kq = 0;
		String tmpa = request.getParameter("txta");
        String tmpb = request.getParameter("txtb");
        
        String btncong = request.getParameter("btncong");
        String btntru = request.getParameter("btntru");
        String btnnhan = request.getParameter("btnnhan");
        String btnchia = request.getParameter("btnchia");

        boolean valuesPresent = (tmpa != null && !tmpa.isEmpty()) && (tmpb != null && !tmpb.isEmpty());
        if(valuesPresent){
            a = Float.parseFloat(tmpa);
            b = Float.parseFloat(tmpb);
            
            if(btncong != null)
            	kq = a + b;
            else if(btntru != null)
            	kq = a - b;
            else if(btnnhan != null)
            	kq = a*b;
            else {
            	if(b == 0){
            		out = response.getWriter();
            		out.println("b phải khác 0");
            		kq = 0;
            	}
            	else 
            		kq = a/b;
            }
        }
        else{
			if(btncong != null || btntru != null || btnnhan != null || btnchia != null)
				out.println("Chưa nhập a, b");
		}

        request.setAttribute("tmpa", tmpa);
        request.setAttribute("tmpb", tmpb);
        request.setAttribute("kq", kq);
        RequestDispatcher rd = request.getRequestDispatcher("calculator.jsp");
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
