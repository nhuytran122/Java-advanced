package ControllerAdmin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CommonModal.Constants;
import CommonModal.ControllerUtils;
import CommonModal.MethodCommon;
import UserModal.User;
import UserModal.UserBo;

@WebServlet("/admin/users")
public class ShowUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowUserController() {
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
            
            UserBo userBo = new UserBo();
            
            ArrayList<User> dsUsers = null;
            dsUsers = userBo.getListUserByCondition(page, Constants.PAGE_SIZE, searchValue);
            rowCount = userBo.countUsersByCondition(searchValue);
            int pageCount = MethodCommon.calculatePageCount(rowCount, Constants.PAGE_SIZE);
            
            request.setAttribute("dsUsers", dsUsers);
            ControllerUtils.setPaginationAttributes(request, page, pageCount);
            ControllerUtils.forwardRequest(request, response, "/Admin/show-users.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
