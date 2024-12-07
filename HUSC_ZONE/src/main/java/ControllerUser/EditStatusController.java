package ControllerUser;

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
import StatusPostModal.StatusPost;
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

			String content = "", imgName = "", 
					oldImgName = "";
			String uniqueName = ""; // Tên file ảnh sau khi xử lý
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
                        case "txtSttID": 
                        	if (fieldValue != null && !fieldValue.isEmpty()) {
                        		sttID = Long.parseLong(fieldValue);
                            }
                        	break;
                        case "txtContent": content = fieldValue; break;
                        case "btnUpdate": 
                        	isUpdate = true; 
                        	break;
                    }
                } else if (!fileItem.getName().isEmpty()) {
                	uniqueName = System.currentTimeMillis() + "_" + fileItem.getName();
                	imgName = "../images/status_images/" + uniqueName;
                	isUploaded = true;
                }
            }

            // Lấy thông tin stt cũ nếu update
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
	
	                        // Xóa ảnh cũ khi update và có upload ảnh mới
	                        if (isUpdate && !oldImgName.isEmpty()) {
	                        	String filePath = request.getServletContext().getRealPath("") + oldImgName;
	                            File oldFile = new File(filePath);
	                            if (oldFile.exists()) 
	                            	oldFile.delete();
	                        }
	                        break;
	                    }
	                }
            	}
                response.sendRedirect("status-post");
                return;
            }
            
            //TODO: above
            if (request.getParameter("btnUpdateStt") != null) {
                request.setAttribute("stt", sttBo.getStatusPostByID(sttID));
                RequestDispatcher rd = request.getRequestDispatcher("User/update_status.jsp");
                rd.forward(request, response);
                return;
            }

            if (request.getParameter("btnDeleteStt") != null) {
                String filePath = sttBo.getStatusPostByID(sttID).getImagePath();

                sttBo.deleteStatusPost(sttID);

                String appPath = request.getServletContext().getRealPath("")
                        + File.separator + "status_images" + File.separator + filePath;
                File fileDocs = new File(appPath);
                System.out.println("Path of image: " + fileDocs.getAbsolutePath());
                if (fileDocs.exists()) {
                    boolean isImageDeleted = fileDocs.delete(); // Xóa file
                    if (!isImageDeleted) {
                        System.out.println("Không thể xóa file: " + filePath);
                    }
                }
                response.sendRedirect("user-profile");
                return;
            }

            if (request.getParameter("btnDetailStt") != null) {
                request.setAttribute("book", sttBo.getStatusPostByID(sttID));
                RequestDispatcher rd = request.getRequestDispatcher("ADMIN/detail_status.jsp");
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
