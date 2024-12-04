package Controller.ADMIN;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.Chung;
import sachmodal.sachbo;

@WebServlet("/adminUpdateSachController")
public class adminUpdateSachController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public adminUpdateSachController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");

            HttpSession session = request.getSession();

            if (session.getAttribute("ad") == null) {
                response.sendRedirect("adminloginController");
                return;
            }

            sachbo sbo = new sachbo();
            String ms = request.getParameter("idSach");
            if (request.getParameter("btnAddSach") != null) {
                request.setAttribute("dsLoai", Chung.getDsLoai());
                RequestDispatcher rd = request.getRequestDispatcher("ADMIN/add_book.jsp");
                rd.forward(request, response);
                return;
            }

            if (request.getParameter("btnUpdateSach") != null) {
                request.setAttribute("dsLoai", Chung.getDsLoai());
                request.setAttribute("book", sbo.getSach(ms));
                RequestDispatcher rd = request.getRequestDispatcher("ADMIN/update_book.jsp");
                rd.forward(request, response);
                return;
            }

            if (request.getParameter("btnDeleteSach") != null) {
                String anh = sbo.getSach(ms).getAnh();
                sbo.deleteSach(ms);

                // Lấy đường dẫn thư mục gốc của ứng dụng và kết hợp với thư mục chứa ảnh
                // String appPath =
                // "D:/HK7/JAVA_NANGCAO/Java-advanced/A04_MVC/src/main/webapp/";
                String appPath = request.getServletContext().getRealPath("") + File.separator + anh;
                File imageFile = new File(appPath);
                System.out.println("Path of image: " + imageFile.getAbsolutePath());
                if (imageFile.exists()) {
                    boolean isImageDeleted = imageFile.delete(); // Xóa ảnh
                    if (!isImageDeleted) {
                        System.out.println("Không thể xóa ảnh: " + anh);
                    }
                }
                response.sendRedirect("adminSachController");
                return;
            }

            if (request.getParameter("btnDetailSach") != null) {
                request.setAttribute("book", sbo.getSach(ms));
                RequestDispatcher rd = request.getRequestDispatcher("ADMIN/detail_book.jsp");
                rd.forward(request, response);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
