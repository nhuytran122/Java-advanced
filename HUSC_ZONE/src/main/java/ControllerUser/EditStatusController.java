package ControllerUser;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import StatusPostModal.StatusPostBo;
import UserModal.User;

@WebServlet("/edit-status")
public class EditStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditStatusController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");

            HttpSession session = request.getSession();
            User user = null;
            
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login");
                return;
            }
            else {
				user = (User)(session.getAttribute("user"));
			}

            StatusPostBo sttBo = new StatusPostBo();
            Long sttID = null;
            if (request.getParameter("sttID") != null)
            	sttID = Long.parseLong(request.getParameter("sttID"));
            
            if (request.getParameter("editStt") != null ) {
                request.setAttribute("stt", sttBo.getStatusPostByID(sttID));
                RequestDispatcher rd = request.getRequestDispatcher("User/update-status.jsp");
                rd.forward(request, response);
                return;
            }
            

            if (request.getParameter("btnDeleteStt") != null) {
                String oldPath = sttBo.getStatusPostByID(sttID).getImagePath();

                sttBo.deleteStatusPost(sttID);

                String oldImgPath = request.getServletContext().getRealPath("") + oldPath;
                File oldImg = new File(oldImgPath);
                System.out.println("Path of image: " + oldImg.getAbsolutePath());
                if (oldImg.exists()) {
                    boolean isImageDeleted = oldImg.delete(); // Xóa file
                    if (!isImageDeleted) {
                        System.out.println("Không thể xóa ảnh: " + oldPath);
                    }
                }
                response.sendRedirect("user-profile");
                return;
            }

//            if (request.getParameter("btnDetailStt") != null) {
//                request.setAttribute("book", sttBo.getStatusPostByID(sttID));
//                RequestDispatcher rd = request.getRequestDispatcher("ADMIN/detail_status.jsp");
//                rd.forward(request, response);
//                return;
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
