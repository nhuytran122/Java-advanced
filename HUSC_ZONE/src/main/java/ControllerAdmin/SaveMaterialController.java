package ControllerAdmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MaterialModal.MaterialBo;

@WebServlet("/admin/save-material")
public class SaveMaterialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SaveMaterialController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	String tenloai = request.getParameter("txtTenLoai");
            String mota = request.getParameter("txtMoTa");
            MaterialBo mateBo = new MaterialBo();

            if(request.getParameter("btnAdd") != null) {
                mateBo.addMaterial(tenloai, mota);
            }
            if(request.getParameter("btnUpdate") != null) {
            	if(request.getParameter("mateID")  != null) {
                   	Long mateID = Long.parseLong(request.getParameter("mateID"));
                   	mateBo.updateMaterial(mateID, tenloai, mota);
              	}
            }
            response.sendRedirect("materials");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
