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

import CommonModal.MethodCommon;
import Controller.Chung;
import DocumentModal.DocumentBo;
import sachmodal.sachbo;

@WebServlet("/edit-docs")
public class EditDocsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditDocsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            
            request.setAttribute("listCates", MethodCommon.getListCates());
            request.setAttribute("listMates", MethodCommon.getListMates());
            
            HttpSession session = request.getSession();
           
            if(session.getAttribute("user") == null) {
            	response.sendRedirect("login");
            	return;
            }

            DocumentBo docBo = new DocumentBo();
            Long docID = 0L;
            if(request.getParameter("docID") != null)
            	docID = Long.parseLong(request.getParameter("docID"));
            
            if (request.getParameter("btnAddDoc") != null) {
            	RequestDispatcher rd = request.getRequestDispatcher("User/create-docs.jsp");
                rd.forward(request, response);
                return;
            }
            
            if (request.getParameter("btnUpdateDoc") != null) {
            	request.setAttribute("doc", docBo.getDocument(docID));
            	RequestDispatcher rd = request.getRequestDispatcher("ADMIN/update_book.jsp");
                rd.forward(request, response);
                return;
            }
            
            
            if (request.getParameter("btnDeleteDoc") != null) {
                String anh = docBo.getDocument(docID).getFilePath();
                
                    docBo.deleteDocument(docID) ;
                    
                    // Lấy đường dẫn thư mục gốc của ứng dụng và kết hợp với thư mục chứa ảnh
                    String appPath =  "D:/HK7/JAVA_NANGCAO/Java-advanced/HUSC_ZONE/src/main/webapp/";
                    File imageFile = new File(appPath + "/" + anh);
                    System.out.println("Path of image: " + imageFile.getAbsolutePath());
                    if (imageFile.exists()) {
                        boolean isImageDeleted = imageFile.delete(); // Xóa ảnh
                        if (!isImageDeleted) {
                            System.out.println("Không thể xóa ảnh: " + anh);
                        }
                    }
                    response.sendRedirect("my-profile");
                    return;
            }
            
            if (request.getParameter("btnDetailSach") != null) {
            	request.setAttribute("book", docBo.getDocument(docID));
            	RequestDispatcher rd = request.getRequestDispatcher("ADMIN/detail_book.jsp");
                rd.forward(request, response);
                return;
            }
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
