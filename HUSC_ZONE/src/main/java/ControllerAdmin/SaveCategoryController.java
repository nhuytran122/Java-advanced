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

import CategoryModal.Category;
import CategoryModal.CategoryBo;
import CommonModal.Constants;

@WebServlet("/admin/save-category")
public class SaveCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SaveCategoryController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
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
			Long cateID = null;
			String cateName = "", imgName = "", 
					oldImgName = "";
			String uniqueName = ""; // Tên file ảnh sau khi xử lý
			boolean isUpdate = false, isUploaded = false;
            int done = 0;
            CategoryBo cateBo = new CategoryBo();
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> fileItems = upload.parseRequest(request);

            // Lấy dữ liệu từ form
            for (FileItem fileItem : fileItems) {
            	if (fileItem.isFormField()) {
                    String fieldName = fileItem.getFieldName();
                    String fieldValue = fileItem.getString("UTF-8");
                    switch (fieldName) {
	                    case "cateID": 
	                    	if (fieldValue != null && !fieldValue.isEmpty()) {
	                    		cateID = Long.parseLong(fieldValue);
	                        }
                    	break;
                        case "txtTenNganh": cateName = fieldValue; break;
                        case "btnUpdate": 
                        	isUpdate = true; 
                        	break;
                    }
            	}else if (!fileItem.getName().isEmpty()) {
                	uniqueName = System.currentTimeMillis() + "_" + fileItem.getName();
                	imgName = Constants.IMG_CATEGORY_FOLDER_PATH + uniqueName; 
                	isUploaded = true;
                }
            }

            // Lấy thông tin docs cũ nếu update
            if (isUpdate && cateID != null) {
            	Category oldCate = cateBo.getCategoryByID(cateID);
                if (oldCate != null) {
                	if(isUploaded) 
                		oldImgName = oldCate.getImage(); // Lấy file cũ để xử lý xóa
                	else 
                		imgName = oldCate.getImage(); // không upload thì lấy lại data cũ
                }
            }

            // Thêm hoặc cập nhật stt  
            if (isUpdate) {
                done = cateBo.updateCategory(cateID, cateName, imgName);
            } else {
                done = cateBo.addCategory(cateName, imgName);
            }
            
         // Chỉ xử lý upload file mới nếu add/update thành công
            if (done == 1) {
            	if(isUploaded) {
	                for (FileItem fileItem : fileItems) {
	                    if (!fileItem.isFormField() && !fileItem.getName().equals("")) {
	                    	String folderPath = request.getServletContext().getRealPath("") + Constants.IMG_CATEGORY_FOLDER_PATH;
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
            	response.sendRedirect("categories");
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
