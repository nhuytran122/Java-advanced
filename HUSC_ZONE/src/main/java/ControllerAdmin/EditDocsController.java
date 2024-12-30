package ControllerAdmin;

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
import DocumentModal.DocumentBo;
import V_DetailsDocModal.DetailsDocBo;

@WebServlet("/admin/edit-docs")
public class EditDocsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditDocsController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//            HttpSession session = request.getSession();
//            MethodCommon.ensureUserIsLoggedIn(session, response);

            DocumentBo docBo = new DocumentBo();
            Long docID = 0L;
            if (request.getParameter("docID") != null)
                docID = Long.parseLong(request.getParameter("docID"));
            
            if (request.getParameter("btnAddDoc") != null) {
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/add-docs.jsp");
                rd.forward(request, response);
                return;
            }

            if (request.getParameter("btnUpdateDoc") != null) {
                request.setAttribute("doc", docBo.getDocumentByID(docID));
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/update-docs.jsp");
                rd.forward(request, response);
                return;
            }

            if (request.getParameter("btnDeleteDoc") != null) {
            	handleDeleteDocument(request, response, docBo, docID);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void handleDeleteDocument(HttpServletRequest request, HttpServletResponse response, DocumentBo docBo, Long docID)
            throws IOException {
        try {
        	String filePath = docBo.getDocumentByID(docID).getFilePath();
            docBo.deleteDocument(docID);
            
            String appPath = request.getServletContext().getRealPath("") + filePath;
            File fileDocs = new File(appPath);
            System.out.println("Path of image: " + fileDocs.getAbsolutePath());
            if (fileDocs.exists()) {
                boolean isImageDeleted = fileDocs.delete(); // Xóa file
                if (!isImageDeleted) {
                    System.out.println("Không thể xóa file: " + filePath);
                }
            }
            response.sendRedirect("docs");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
