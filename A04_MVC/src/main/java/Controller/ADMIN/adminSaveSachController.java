package Controller.ADMIN;

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

import Controller.Chung;
import loaimodal.loaibo;
import sachmodal.sachbo;

@WebServlet("/adminSaveSachController")
public class adminSaveSachController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminSaveSachController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            HttpSession session = request.getSession();

            if(session.getAttribute("ad") == null) {
                response.sendRedirect("adminloginController");
                return;
            }
            
            request.setCharacterEncoding("utf-8");
    		response.setCharacterEncoding("utf-8");
    		response.setContentType("text/html; charset=utf-8");
    		String masach = "", tensach = "", soluong = "", gia = "", 
	                tacgia = "", sotap = "", anh = "", maloai = "";
    		boolean isInvalid = false;
    		
    		 int done = 0;
    		 sachbo sbo = new sachbo();
    		
    		// Kiểm tra nếu không có dữ liệu gửi lên
//    		if (request.getContentLength() <= 0) {
//    			RequestDispatcher rd = request.getRequestDispatcher("ADMIN/add_book.jsp");
//    			rd.forward(request, response);
//    			return;
//    		}
    		DiskFileItemFactory factory = new DiskFileItemFactory();
    		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
    		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
    		String uniqueName = "";
    		// String dirUrl1 = request.getServletContext().getRealPath("") + File.separator + "abc";
            
            List<FileItem> fileItems = upload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				if (!fileItem.isFormField()) {
                    String nameimg = fileItem.getName();
                    if (!nameimg.equals("")) {
                        uniqueName = System.currentTimeMillis() + "_" + nameimg;
                        anh = "image_sach/" + uniqueName;  
                    }
                }
				else {
                    
			        String fieldName = fileItem.getFieldName();
			        String fieldValue = fileItem.getString("UTF-8"); 
			        switch (fieldName) {
			            case "txtMaSach":
			                masach = fieldValue;
			                break;
			            case "txtTenSach":
			                tensach = fieldValue;
			                break;
			            case "txtSoLuong":
			                soluong = fieldValue;
			                break;
			            case "txtGia":
			                gia = fieldValue;
			                break;
			            case "txtMaloai":
			                maloai = fieldValue;
			                break;
			            case "txtTacGia":
			                tacgia = fieldValue;
			                break;
			            case "txtSoTap":
			                sotap = fieldValue;
			                break;
			            case "fileAnh":
			            	String nameimg = fileItem.getName();
			                if (!nameimg.equals("")) {
			                    anh = "image_sach/" + uniqueName;
			                }
			                break;
			            case "btnAdd":
			                done = sbo.addSach(masach, tensach, tacgia, Long.parseLong(soluong), Long.parseLong(gia), anh, maloai, sotap);
			                break;
			            case "btnUpdate":
			                done = sbo.updateSach(masach, tensach, tacgia, Long.parseLong(soluong), Long.parseLong(gia), anh, maloai, sotap);
			                break;
			            default:
			                break;
			        }
				}
			}

            if(done == 1) {
            	// Chỉ upload ảnh nếu thêm/cập nhật thành công
                for (FileItem fileItem : fileItems) {
                    if (!fileItem.isFormField()) {
                        String nameimg  = fileItem.getName(); // Lấy tên file gốc
                        if (!nameimg.equals("")) {
                            // Thực hiện upload file lên thư mục
                        	//String dirUrl = request.getServletContext().getRealPath("") + File.separator + "image_sach";

                            String dirUrl = "D:/HK7/JAVA_NANGCAO/Java-advanced/A04_MVC/src/main/webapp/image_sach";
                            File dir = new File(dirUrl);
                            if (!dir.exists()) {
                                dir.mkdir();
                            }
                            String filePath = dirUrl + File.separator + uniqueName;
                            File file = new File(filePath);
                            try {
                                fileItem.write(file);
                                break;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                response.sendRedirect("adminSachController");
                return;
            }
            else 
            	isInvalid = true;
            
            request.setAttribute("maSach", masach);
            request.setAttribute("tenSach", tensach);
            request.setAttribute("soLuong", soluong);
            request.setAttribute("gia", gia);
            request.setAttribute("maloai", maloai);
            request.setAttribute("tacGia", tacgia);
            request.setAttribute("soTap", sotap);
            request.setAttribute("anh", anh);
            
            request.setAttribute("isInvalid", isInvalid);

            request.setAttribute("dsLoai", Chung.getDsLoai());
            RequestDispatcher rd = request.getRequestDispatcher("ADMIN/add_book.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
