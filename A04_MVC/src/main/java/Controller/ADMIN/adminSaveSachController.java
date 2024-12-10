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
import sachmodal.sach;
import sachmodal.sachbo;

@WebServlet("/adminSaveSachController")
public class adminSaveSachController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public adminSaveSachController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            HttpSession session = request.getSession();

            if (session.getAttribute("ad") == null) {
                response.sendRedirect("adminloginController");
                return;
            }

            String masach = "", tensach = "", soluong = "0", gia = "0",
                    tacgia = "", sotap = "", maloai = "", anh = "", oldAnh = "";
            boolean isUpdate = false, isUploaded = false;
            int done = 0;
            sachbo sbo = new sachbo();
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> fileItems = upload.parseRequest(request);
            String uniqueName = ""; // Tên file ảnh sau khi xử lý

            // Lấy dữ liệu từ form
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
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
                        // case "txtNgayNhap": ngaynhap = fieldValue; break;
                        case "btnUpdate":
                            isUpdate = true;
                            break;
                    }
                } else if (!fileItem.getName().isEmpty()) {
                    // Upload ảnh mới
                    uniqueName = System.currentTimeMillis() + "_" + fileItem.getName();
                    anh = "image_sach/" + uniqueName;
                    isUploaded = true;
                }
            }
            
            if (isUpdate && !masach.isEmpty()) {
                sach sachCu = sbo.getSach(masach);
                if (sachCu != null) {
                    if (isUploaded)
                        oldAnh = sachCu.getAnh(); // Lấy ảnh cũ để xóa
                    else
                        anh = sachCu.getAnh(); // không upload thì lấy lại data cũ
                }

            }

            // Thêm hoặc cập nhật sách
            if (isUpdate) {
            	if (isUploaded)
            		done = sbo.updateSach(masach, tensach, tacgia, Long.parseLong(soluong),
                        Long.parseLong(gia), anh, maloai, sotap);
            	else
            		done = sbo.updateSachWithoutUpload(masach, tensach, tacgia, Long.parseLong(soluong),
                        Long.parseLong(gia), maloai, sotap);
            } else {
                done = sbo.addSach(masach, tensach, tacgia, Long.parseLong(soluong),
                        Long.parseLong(gia), anh, maloai, sotap);
            }

            // Chỉ xử lý upload ảnh mới nếu add/update thành công & CÓ UPLOAD
            if (done == 1) {
                if (isUploaded) {
                    for (FileItem fileItem : fileItems) {
                        if (!fileItem.isFormField() && !fileItem.getName().equals("")) {
                            String folderPath = request.getServletContext().getRealPath("") + File.separator
                                    + "image_sach";
                            // String folderPath4= request.getServletContext().getRealPath("/image_sach");
                            File dir = new File(folderPath);
                            if (!dir.exists())
                                dir.mkdir();

                            // Upload ảnh mới
                            File file = new File(folderPath + File.separator + uniqueName);
                            fileItem.write(file);

                            // Xóa ảnh cũ khi update và có upload ảnh mới
                            if (isUpdate && !oldAnh.isEmpty()) {
                                String filePath = request.getServletContext().getRealPath("") + File.separator + oldAnh;
                                File oldFile = new File(filePath);
                                if (oldFile.exists())
                                    oldFile.delete();
                            }
                            break;
                        }
                    }
                }
                response.sendRedirect("adminSachController");
                return;
            }

            // Nếu add thất bại
            request.setAttribute("isInvalid", true);
            request.setAttribute("maSach", masach);
            request.setAttribute("tenSach", tensach);
            request.setAttribute("soLuong", soluong);
            request.setAttribute("gia", gia);
            request.setAttribute("maloai", maloai);
            request.setAttribute("tacGia", tacgia);
            request.setAttribute("soTap", sotap);
            request.setAttribute("anh", anh);

            request.setAttribute("dsLoai", Chung.getDsLoai());
            RequestDispatcher rd = request.getRequestDispatcher("ADMIN/add_book.jsp");
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
