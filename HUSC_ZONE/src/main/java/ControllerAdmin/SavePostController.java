package ControllerAdmin;

import java.io.File;
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
import CommonModal.MethodCommon;
import StatusPostModal.StatusPost;
import StatusPostModal.StatusPostBo;
import UserModal.User;

@WebServlet("/admin/save-post")
public class SavePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SavePostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
            if (!MethodCommon.checkLoginAndAdminAccess(session, response, request)) {
                return; 
            }
            User user = MethodCommon.getUserFromSession(session, response);

			response.setContentType("text/html; charset=utf-8");
			Long sttID = null;
			String content = "", imgName = "", 
					oldImgName = "";
			String uniqueName = ""; // Tên file ảnh sau khi xử lý
			boolean isUpdate = false, isUploaded = false;
            int done = 0;
            StatusPostBo sttBo = new StatusPostBo();
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> fileItems = upload.parseRequest(request);

            // Lấy dữ liệu từ form
            for (FileItem fileItem : fileItems) {
            	if (fileItem.isFormField()) {
                    String fieldName = fileItem.getFieldName();
                    String fieldValue = fileItem.getString("UTF-8");
                    switch (fieldName) {
                        case "sttID": 
                        	if (fieldValue != null && !fieldValue.isEmpty()) {
                        		sttID = Long.parseLong(fieldValue);
                            }
                        	break;
                        case "txtContent": content = fieldValue; break;
                        case "btnUpdate": 
                        	isUpdate = true; 
                        	break;
                    }
            	}else if (!fileItem.getName().isEmpty()) {
                	uniqueName = System.currentTimeMillis() + "_" + fileItem.getName();
                	imgName = Constants.IMG_POST_FOLDER_PATH + uniqueName; 
                	isUploaded = true;
                }
            }

            // Lấy thông tin docs cũ nếu update
            if (isUpdate && sttID != null) {
            	StatusPost oldStt = sttBo.getStatusPostByID(sttID);
                if (oldStt != null) {
                	if(isUploaded) 
                		oldImgName = oldStt.getImagePath(); // Lấy file cũ để xử lý xóa
                	else 
                		imgName = oldStt.getImagePath(); // không upload thì lấy lại data cũ
                }
            }

            // Thêm hoặc cập nhật stt  
            if (isUpdate) {
                done = sttBo.updateStatusPost(sttID, content, imgName);
            } else {
                done = sttBo.addStatusPost(content, user.getUserID(), imgName);
            }
            
         // Chỉ xử lý upload file mới nếu add/update thành công
            if (done == 1) {
            	if(isUploaded) {
	                for (FileItem fileItem : fileItems) {
	                    if (!fileItem.isFormField() && !fileItem.getName().equals("")) {
	                    	String folderPath = request.getServletContext().getRealPath("") + Constants.IMG_POST_FOLDER_PATH;
	                        File dir = new File(folderPath);
	                        if (!dir.exists()) 
	                        	dir.mkdir();
	
	                        // Upload file mới
	                        File file = new File(folderPath + File.separator + uniqueName);
	                        fileItem.write(file);
	
	                        // Xóa file cũ nếu có khi update và có upload file mới
	                        if (isUpdate && !oldImgName.isEmpty()) {
	                        	String oldFilePath = request.getServletContext().getRealPath("") + oldImgName;
	                            File oldFile = new File(oldFilePath);
	                            if (oldFile.exists()) 
	                            	oldFile.delete();
	                        }
	                        break;
	                    }
	                }
            	}
            	response.sendRedirect("posts");
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
