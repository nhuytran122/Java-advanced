package CommonModal;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;

public class FileUtils {
	public static void deleteFile(HttpServletRequest request, String filePath) {
        try {
            // Lấy đường dẫn đầy đủ từ servlet context + path truyền vào
            String appPath = request.getServletContext().getRealPath("") + filePath;
            File file = new File(appPath);
            System.out.println("Path of file: " + file.getAbsolutePath());

            if (file.exists()) {
                boolean isFileDeleted = file.delete();
                if (!isFileDeleted) {
                    System.out.println("Không thể xóa file: " + filePath);
                }
            } else {
                System.out.println("File không tồn tại: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static String handleFileUpload(HttpServletRequest request, List<FileItem> fileItems, String uniqueName) throws Exception {
	    for (FileItem fileItem : fileItems) {
	        if (!fileItem.isFormField() && !fileItem.getName().isEmpty()) {
	        	String folderPath = request.getServletContext().getRealPath("");
	            String fullPath = folderPath + File.separator + uniqueName; 
	            File dir = new File(folderPath);
	            if (!dir.exists()) {
	                dir.mkdirs();
	            }

	            File file = new File(fullPath);
	            fileItem.write(file);
	            return uniqueName; 
	        }
	    }
	    return null;
	}

    // Xóa file cũ nếu có
    public static void deleteOldFile(String oldFilePath) {
        if (oldFilePath != null && !oldFilePath.isEmpty()) {
            File oldFile = new File(oldFilePath);
            if (oldFile.exists()) {
                oldFile.delete();
            }
        }
    }
	
}
