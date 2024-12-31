package ControllerAdmin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.MethodCommon;
import MaterialModal.Material;
import MaterialModal.MaterialBo;

@WebServlet("/admin/materials")
public class ShowMaterialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowMaterialController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            HttpSession session = request.getSession();
            if (!MethodCommon.checkLoginAndAdminAccess(session, response, request)) {
                return; 
            }
            
            int page = 1;
            int pageSize = 9;
            String searchValue = "";
            int rowCount = 0;
            
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            if (request.getParameter("txtSearch") != null) {
                searchValue = request.getParameter("txtSearch");
            }
            
            MaterialBo mateBo = new MaterialBo();
            
            ArrayList<Material> dsMates = null;
            dsMates = mateBo.getListMaterialByCondition(page, pageSize, searchValue);
            rowCount = mateBo.countMaterialsByCondition(searchValue);
            int pageCount = MethodCommon.calculatePageCount(rowCount, pageSize);
            
            request.setAttribute("dsMates", dsMates);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);

            RequestDispatcher rd = request.getRequestDispatcher("/Admin/show-materials.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
