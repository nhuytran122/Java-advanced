package ControllerAdmin;

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
import CommonModal.ControllerUtils;
import CommonModal.FileUtils;

@WebServlet("/admin/edit-category")
public class EditCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditCategoryController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		  HttpSession session = request.getSession();
          if (!ControllerUtils.checkLoginAndAdminAccess(session, response, request)) {
              return; 
          }
          CategoryBo cateBo = new CategoryBo();
          Long cateID = 0L;
          if (request.getParameter("cateID") != null)
        	  cateID = Long.parseLong(request.getParameter("cateID"));
          
          if (request.getParameter("btnDeleteCate") != null) {
        	  handleDeleteCategory(request, response, cateBo, cateID);
              return;
          }
          
          response.setContentType("text/html; charset=utf-8");
		  String cateName = "", imgName = "", 
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
          	} else if (!fileItem.getName().isEmpty()) {
              	uniqueName = System.currentTimeMillis() + "_" + fileItem.getName();
              	imgName = Constants.IMG_CATEGORY_FOLDER_PATH + uniqueName; 
              	isUploaded = true;
              }
          }

          if (isUpdate && cateID != null) {
          	Category oldCate = cateBo.getCategoryByID(cateID);
              if (oldCate != null) {
              	if(isUploaded) 
              		oldImgName = oldCate.getImage(); 
              	else 
              		imgName = oldCate.getImage(); 
              }
          }

          if (isUpdate) {
              done = cateBo.updateCategory(cateID, cateName, imgName);
          } else {
              done = cateBo.addCategory(cateName, imgName);
          }
          
          if (done == 1) {
        	  if(isUploaded) {
                  String uploadedFileName = FileUtils.handleFileUpload(request, fileItems, imgName);
                  if (isUpdate && !oldImgName.isEmpty()) {
                      String oldFilePath = request.getServletContext().getRealPath("") + oldImgName;
                      FileUtils.deleteOldFile(oldFilePath);
                  }
          	}
          }
          response.sendRedirect("categories");
              return;
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
	
	private void handleDeleteCategory(HttpServletRequest request, HttpServletResponse response, CategoryBo cateBo, Long cateID)
	          throws IOException {
	      try {
	    	String filePath = cateBo.getCategoryByID(cateID).getImage();
          	FileUtils.deleteFile(request, filePath);
          	cateBo.deleteCategory(cateID);
          	response.sendRedirect("categories");
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
