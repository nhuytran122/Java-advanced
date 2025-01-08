package Controller;

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
import UserModal.User;
import UserModal.UserBo;

@WebServlet("/save-profile")

public class SaveProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveProfileController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			if (!ControllerUtils.ensureUserLogin(session, response, request)) {
				return;
			}
			User user = ControllerUtils.getUserFromSession(session, response);

			UserBo userBo = new UserBo();

			response.setContentType("text/html; charset=utf-8");
			String name = "", phone = "", gender = "", imgName = "",
					oldImgName = "", uniqueName = "";
			boolean isUpdate = false, isUploaded = false, editInAdminPage = false;
			int done = 0;
			ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> fileItems = upload.parseRequest(request);

			// Lấy dữ liệu từ form
			for (FileItem fileItem : fileItems) {
				if (fileItem.isFormField()) {
					String fieldName = fileItem.getFieldName();
					String fieldValue = fileItem.getString("UTF-8");
					switch (fieldName) {
						case "txtHoten":
							name = fieldValue;
							break;
						case "txtSdt":
							phone = fieldValue;
							break;
						case "txtGioiTinh":
							gender = fieldValue;
							break;
						case "editInAdminPage":
							editInAdminPage = true;
							break;
						case "btn-save-edit-inf":
							isUpdate = true;
							break;
					}
				} else if (!fileItem.getName().isEmpty()) {
					uniqueName = System.currentTimeMillis() + "_" + fileItem.getName();
                    imgName = Constants.IMG_AVT_USER_FOLDER_PATH + uniqueName;  
                    isUploaded = true;
				}
			}

			// Lấy thông tin cũ nếu update
			if (isUpdate) {
				if (user != null) {
					if (isUploaded)
						oldImgName = user.getAvatar(); // Lấy ảnh cũ để xóa
					else
						imgName = user.getAvatar(); // không upload thì lấy lại data cũ
				}
			}

			done = userBo.updateUser(user.getUserID(), name, gender, phone, imgName);

			if (done == 1) {
				if (isUploaded) {
	            	String uploadedFileName = FileUtils.handleFileUpload(request, fileItems, imgName);
	            	// Xóa file cũ có update & có file cũ
	            	if (isUpdate && oldImgName!= null && !oldImgName.isEmpty()) {
	                	String oldFilePath = request.getServletContext().getRealPath("") + oldImgName;
	                	FileUtils.deleteOldFile(oldFilePath);
	            	}
				}
				User currentUser = userBo.getUserByID(user.getUserID());
				session.setAttribute("user", currentUser);
				
				if(user.getRoleID() == Constants.ROLE_ADMIN) {
					if(editInAdminPage) {
						ControllerUtils.forwardRequest(request, response, "/Admin/my-profile.jsp");
		                return;
					}
				}
				response.sendRedirect("user-profile");
				return;
			}
			response.sendRedirect("home");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
