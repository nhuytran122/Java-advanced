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
import CommonModal.MethodCommon;
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

			User user = MethodCommon.getUserFromSession(session, response);
			if (user == null) {
    	        response.sendRedirect("login"); 
				return;
			}

			UserBo userBo = new UserBo();

			response.setContentType("text/html; charset=utf-8");
			String name = "", phone = "", gender = "", imgName = "",
					oldImg = "", uniqueName = "";
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
						case "txtHoten":
							name = fieldValue;
							break;
						case "txtSdt":
							phone = fieldValue;
							break;
						case "txtGioiTinh":
							gender = fieldValue;
							break;
						case "btn-save-edit-inf":
							isUpdate = true;
							break;
					}
				} else if (!fileItem.getName().isEmpty()) {
					uniqueName = System.currentTimeMillis() + "_" + fileItem.getName();
                    imgName = Constants.IMG_AVT_USER_FOLDER_PATH + uniqueName;  // Đảm bảo là đường dẫn URL tương đối
                    isUploaded = true;
				}
			}

			// Lấy thông tin docs cũ nếu update
			if (isUpdate) {
				if (user != null) {
					if (isUploaded)
						oldImg = user.getAvatar(); // Lấy ảnh cũ để xóa
					else
						imgName = user.getAvatar(); // không upload thì lấy lại data cũ
				}
			}

			done = userBo.updateUser(user.getUserID(), name, gender, phone, imgName);

			// Chỉ xử lý upload file mới nếu add/update thành công
			if (done == 1) {
				if (isUploaded) {
					for (FileItem fileItem : fileItems) {
						if (!fileItem.isFormField() && !fileItem.getName().equals("")) {
							String folderPath = request.getServletContext().getRealPath("") + Constants.IMG_AVT_USER_FOLDER_PATH;
							File dir = new File(folderPath);
							if (!dir.exists())
								dir.mkdir();

							// Upload file mới
							File file = new File(folderPath + File.separator + uniqueName);
							fileItem.write(file);

							// Xóa ảnh cũ nếu có
							if (!oldImg.isEmpty() && isUpdate) {
								String oldFolder = request.getServletContext().getRealPath("") + oldImg;
								File oldFile = new File(oldFolder);
								if (oldFile.exists())
									oldFile.delete();
							}
							break;
						}
					}
					User currentUser = userBo.getUserByID(user.getUserID());
					session.setAttribute("user", currentUser);
					
					if(user.getRoleID() == Constants.ROLE_ADMIN) {
						RequestDispatcher rd = request.getRequestDispatcher("/Admin/my-profile.jsp");
		                rd.forward(request, response);
		                return;
					}
					
					response.sendRedirect("user-profile");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
