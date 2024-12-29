package ControllerAdmin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import DocumentModal.Document;
import DocumentModal.DocumentBo;
import UserModal.User;

@WebServlet("/admin/save-docs")
public class SaveDocsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveDocsController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
//TODO: Làm auth
//			HttpSession session = request.getSession();
//			User user = null;
//
//			if(session.getAttribute("user") == null) {
//            	response.sendRedirect("login");
//            	return;
//            }
//			else {
//				user = (User)(session.getAttribute("user"));
//			}

			response.setContentType("text/html; charset=utf-8");
			Long docID = null;
			String tenDocs = "", moTa = "", fileName = "", 
					maNganh = "", maLoai= "", oldFileName = "", uniqueName = "";
			boolean isUpdate = false, isUploaded = false;
            int done = 0;
            DocumentBo docBo = new DocumentBo();
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
            	//TODO: 
//                done = docBo.addDocument(tenDocs, moTa, fileName, Long.parseLong(maNganh), 
//                		Long.parseLong(maLoai), user.getUserID());
            	done = docBo.addDocument(tenDocs, moTa, fileName, Long.parseLong(maNganh), 
                		Long.parseLong(maLoai), 5L);
            }
            
         // Chỉ xử lý upload file mới nếu add/update thành công
            if (done == 1) {
            	if(isUploaded) {
	                for (FileItem fileItem : fileItems) {
	                    if (!fileItem.isFormField() && !fileItem.getName().equals("")) {
	                    	String folderPath = request.getServletContext().getRealPath("") + Constants.DOCS_FOLDER_PATH;
	                        File dir = new File(folderPath);
	                        if (!dir.exists()) 
	                        	dir.mkdir();
	
	                        // Upload file mới
	                        File file = new File(folderPath + File.separator + uniqueName);
	                        fileItem.write(file);
	
	                        // Xóa file cũ nếu có khi update và có upload file mới
	                        if (isUpdate && !oldFileName.isEmpty()) {
	                        	String oldFilePath = request.getServletContext().getRealPath("") + oldFileName;
	                            File oldFile = new File(oldFilePath);
	                            if (oldFile.exists()) 
	                            	oldFile.delete();
	                        }
	                        break;
	                    }
	                }
            	}
            	response.sendRedirect("docs");
                return;
            }

            // Nếu add thất bại
//            request.setAttribute("isInvalid", true);
//            request.setAttribute("docID", docID);
//            request.setAttribute("tenDocs", tenDocs);
//            request.setAttribute("moTa", moTa);
//            request.setAttribute("maNganh", maNganh);
//            request.setAttribute("maLoai", maLoai);
//            request.setAttribute("fileName", fileName);

            RequestDispatcher rd = request.getRequestDispatcher("/Admin/add-docs.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
