package CommonModal;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DocumentModal.Document;
import DocumentModal.DocumentBo;
import StatusPostModal.StatusPost;
import StatusPostModal.StatusPostBo;

public class ActionsCommonUtils {
	public static void handleDeleteDocument(HttpServletRequest request, HttpServletResponse 
			response, DocumentBo docBo, Long docID, String redirectUrl)
	        throws IOException {
	    try {
	        String filePath = docBo.getDocumentByID(docID).getFilePath();
	        FileUtils.deleteFile(request, filePath);
	        docBo.deleteDocument(docID);

	        response.sendRedirect(redirectUrl);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static int addOrUpdateDocument(HttpServletRequest request, DocumentBo docBo, 
			Long userID) throws Exception {
        Long docID = null;
        String tenDocs = "", moTa = "", fileName = "", maNganh = "", maLoai = "", oldFileName = "", uniqueName = "";
        boolean isUpdate = false, isUploaded = false;
        int done = 0;

        // Lấy dữ liệu từ form
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
            	String originalFileName = fileItem.getName();
                String sanitizedFileName = MethodCommon.normalizeFileName(originalFileName);
                
                uniqueName = System.currentTimeMillis() + "_" + sanitizedFileName;
                fileName = Constants.DOCS_FOLDER_PATH + uniqueName; 
                isUploaded = true;
            }
        }

        // Lấy thông tin tài liệu cũ nếu là update
        if (isUpdate && docID != null) {
            Document oldDoc = docBo.getDocumentByID(docID);
            if (oldDoc != null) {
                if (isUploaded) 
                    oldFileName = oldDoc.getFilePath(); // Lấy file cũ để xử lý xóa
                else 
                    fileName = oldDoc.getFilePath(); // không upload thì lấy lại data cũ
            }
        }

        if (isUpdate) {
            done = docBo.updateDocument(docID, tenDocs, moTa, fileName, Long.parseLong(maNganh), Long.parseLong(maLoai));
        } else {
            done = docBo.addDocument(tenDocs, moTa, fileName, Long.parseLong(maNganh), Long.parseLong(maLoai), userID);
        }

        // Chỉ xử lý upload file mới nếu add/update thành công
        if (done == 1) {
        	if(isUploaded) {
        		String uploadedFileName = FileUtils.handleFileUpload(request, fileItems, fileName);
                // Xóa file cũ có update & có file cũ
                if (isUpdate && !oldFileName.isEmpty()) {
                    String oldFilePath = request.getServletContext().getRealPath("") + oldFileName;
                    FileUtils.deleteOldFile(oldFilePath);
                }
        	}
        }
        return done;
	}

	public static void handleDeletePost(HttpServletRequest request, HttpServletResponse 
			response, StatusPostBo postBo, Long postID, String redirectUrl)
	          throws IOException {
	      try {
	      	String filePath = postBo.getStatusPostByID(postID).getImagePath();
	      	postBo.deleteStatusPost(postID);
	          
	          String appPath = request.getServletContext().getRealPath("") + filePath;
	          File fileDocs = new File(appPath);
	          System.out.println("Path of image: " + fileDocs.getAbsolutePath());
	          if (fileDocs.exists()) {
	              boolean isImageDeleted = fileDocs.delete(); // Xóa file
	              if (!isImageDeleted) {
	                  System.out.println("Không thể xóa file: " + filePath);
	              }
	          }
	          response.sendRedirect(redirectUrl);
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
		}

	public static int addOrUpdatePost(HttpServletRequest request, StatusPostBo sttBo, 
			Long userID) throws Exception {
		Long postID = null;
		String content = "", imgName = "", 
				oldImgName = "";
		String uniqueName = ""; 
		boolean isUpdate = false, isUploaded = false, addInPage = false;
        int done = 0;
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> fileItems = upload.parseRequest(request);

        // Lấy dữ liệu từ form
        for (FileItem fileItem : fileItems) {
        	if (fileItem.isFormField()) {
                String fieldName = fileItem.getFieldName();
                String fieldValue = fileItem.getString("UTF-8");
                switch (fieldName) {
                	case "btnPostInPage":
                		addInPage = true;
                		break;
                    case "postID": 
                    	if (fieldValue != null && !fieldValue.isEmpty()) {
                    		postID = Long.parseLong(fieldValue);
                        }
                    	break;
                    case "txtContent": content = fieldValue; break;
                    case "btnUpdate": 
                    	isUpdate = true; 
                    	break;
                }
        	}else if (!fileItem.getName().isEmpty()) {
        		String originalFileName = fileItem.getName();
        	    String sanitizedFileName = MethodCommon.normalizeFileName(originalFileName);
        	    uniqueName = System.currentTimeMillis() + "_" + sanitizedFileName;
        	    
            	imgName = Constants.IMG_POST_FOLDER_PATH + uniqueName; 
            	isUploaded = true;
            }
        }

        // Lấy thông tin docs cũ nếu update
        if (isUpdate && postID != null) {
        	StatusPost oldStt = sttBo.getStatusPostByID(postID);
            if (oldStt != null) {
            	if(isUploaded) 
            		oldImgName = oldStt.getImagePath(); // Lấy file cũ để xử lý xóa
            	else 
            		imgName = oldStt.getImagePath(); 
            }
        }

        // Thêm hoặc cập nhật stt  
        if (isUpdate) {
            done = sttBo.updateStatusPost(postID, content, imgName);
        } else {
            done = sttBo.addStatusPost(content, userID, imgName);
        }
        
     // Chỉ xử lý upload file mới nếu add/update thành công
        if (done == 1) {
        	if(isUploaded) {
                String uploadedFileName = FileUtils.handleFileUpload(request, fileItems, imgName);

                // Xóa file cũ có update & có file cũ
                if (isUpdate && !oldImgName.isEmpty()) {
                    String oldFilePath = request.getServletContext().getRealPath("") + oldImgName;
                    FileUtils.deleteOldFile(oldFilePath);
                }
        	}
        	if(addInPage) {
        		return 1;
        	}
            return 2;
        }
       return 0; 
	}
}
