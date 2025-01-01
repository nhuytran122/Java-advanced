package ControllerAdmin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CategoryModal.Category;
import CategoryModal.CategoryBo;
import CommonModal.Constants;
import CommonModal.ControllerUtils;
import CommonModal.MethodCommon;

@WebServlet("/admin/categories")
public class ShowCategoryControlller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowCategoryControlller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            HttpSession session = request.getSession();
            if (!ControllerUtils.checkLoginAndAdminAccess(session, response, request)) {
                return; 
            }
            int page = ControllerUtils.getPage(request);
            String searchValue = ControllerUtils.getSearchValue(request);
            int rowCount = 0;
            
            CategoryBo cateBo = new CategoryBo();
            ArrayList<Category> dsCates = cateBo.getListCategoriesByCondition(page, Constants.PAGE_SIZE, searchValue);
            rowCount = cateBo.countCategoriesByCondition(searchValue);
            int pageCount = MethodCommon.calculatePageCount(rowCount, Constants.PAGE_SIZE);
            
            request.setAttribute("dsCates", dsCates);
            ControllerUtils.setPaginationAttributes(request, page, pageCount);
            ControllerUtils.forwardRequest(request, response, "/Admin/show-categories.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
