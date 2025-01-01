package ControllerUser;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import CommonModal.Constants;
import CommonModal.ControllerUtils;
import CommonModal.FileUtils;
import CommonModal.MethodCommon;
import DocumentModal.Document;
import DocumentModal.DocumentBo;
import UserModal.User;

@WebServlet("/edit-docs")
public class EditDocsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditDocsController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (!MethodCommon.ensureUserLogin(session, response, request)) {
    	        return;
    	    }
            User user = MethodCommon.getUserFromSession(session, response);

            DocumentBo docBo = new DocumentBo();
            Long docID = 0L;
            if (request.getParameter("docID") != null) {
                docID = Long.parseLong(request.getParameter("docID"));
	            if (request.getParameter("btnDeleteDoc") != null) {
	            	String filePath = docBo.getDocumentByID(docID).getFilePath();
	            	FileUtils.deleteFile(request, filePath);
	            	docBo.deleteDocument(docID);
	            	response.sendRedirect("docs-of-user");
	                return;
	            }
            }
            
            response.setContentType("text/html; charset=utf-8");
			String tenDocs = "", moTa = "", fileName = "", 
					maNganh = "", maLoai= "", oldFileName = "", uniqueName = "";
			boolean isUpdate = false, isUploaded = false;
            int done = 0;
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> fileItems = upload.parseRequest(request);

            // Lấy dữ liệu từ form
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    String fieldName = fileItem.getFieldName();
                    String fieldValue = fileItem.getString("UTF-8");
                    switch (fieldName) {
                        case "txtDocID": 
                        	if (fieldValue != null && !fieldValue.isEmpty()) {
                                docID = Long.parseLong(fieldValue);
                            }
                        	break;
                        case "txtTenDocs": tenDocs = fieldValue; break;
                        case "txtMoTa": moTa = fieldValue; break;
                        case "txtMaNganh": maNganh = fieldValue; break;
                        case "txtMaLoai": maLoai = fieldValue; break;
                        case "btnUpdate": 
                        	isUpdate = true; 
                        	break;
                    }
                } else if (!fileItem.getName().isEmpty()) {
                	uniqueName = System.currentTimeMillis() + "_" + fileItem.getName();
                	fileName = Constants.DOCS_FOLDER_PATH + uniqueName; 
                	isUploaded = true;
                }
            }

            // Lấy thông tin docs cũ nếu update
            if (isUpdate && docID != null) {
                Document oldDoc = docBo.getDocumentByID(docID);
                if (oldDoc != null) {
                	if(isUploaded) 
                		oldFileName = oldDoc.getFilePath(); // Lấy file cũ để xử lý xóa
                	else 
                		fileName = oldDoc.getFilePath(); // không upload thì lấy lại data cũ
                }
            }

            // Thêm hoặc cập nhật doc  
            if (isUpdate) {
                done = docBo.updateDocument(docID, tenDocs, moTa, fileName,
                		Long.parseLong(maNganh), Long.parseLong(maLoai));
            } else {
                done = docBo.addDocument(tenDocs, moTa, fileName, Long.parseLong(maNganh), 
                		Long.parseLong(maLoai), user.getUserID());
            }
            
         // Chỉ xử lý upload file mới nếu add/update thành công
            if (done == 1) {
                if (isUploaded) {
                    String uploadedFileName = FileUtils.handleFileUpload(request, fileItems, fileName);

                    // Xóa file cũ có update & có file cũ
                    if (isUpdate && !oldFileName.isEmpty()) {
                        String oldFilePath = request.getServletContext().getRealPath("") + oldFileName;
                        FileUtils.deleteOldFile(oldFilePath);
                    }
                }
                response.sendRedirect("docs-of-user");
                return;
            }
            // Nếu add thất bại
//            request.setAttribute("isInvalid", true);
            
            ControllerUtils.forwardRequest(request, response, "User/add-docs.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
