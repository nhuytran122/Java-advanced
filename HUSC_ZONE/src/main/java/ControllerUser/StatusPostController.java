package ControllerUser;

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
import V_DetailsPostModal.DetailsPost;
import V_DetailsPostModal.DetailsPostBo;

@WebServlet("/status-post")
public class StatusPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StatusPostController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (!ControllerUtils.ensureUserLogin(session, response, request)) {
    	        return;
    	    }
            
            int page = ControllerUtils.getPage(request);
            String searchValue = ControllerUtils.getSearchValue(request);
            int rowCount = 0;
            
            Long typeSearchID = Constants.SEARCH_POST;
            if (request.getParameter("typeSearchID") != null) {
            	typeSearchID = Long.parseLong(request.getParameter("typeSearchID"));
            }
            
            DetailsPostBo sttBo = new DetailsPostBo();
            UserBo userBo = new UserBo();
            
            ArrayList<DetailsPost> dsPosts = null;
            ArrayList<User> dsUsers = null;
            if(typeSearchID == Constants.SEARCH_POST) {
            	dsPosts = sttBo.getPostsByConditions(page, Constants.PAGE_SIZE, searchValue);
            	rowCount = sttBo.getCountPostsByConditions(searchValue);
            }
            else {
            	dsUsers = userBo.getListUserByCondition(page, Constants.PAGE_SIZE, searchValue);
            	rowCount = userBo.countUsersByCondition(searchValue);
            }
            int pageCount = MethodCommon.calculatePageCount(rowCount, Constants.PAGE_SIZE);
            
            request.setAttribute("dsPosts", dsPosts);
            request.setAttribute("dsUsers", dsUsers);
            ControllerUtils.setPaginationAttributes(request, page, pageCount);
            
            ControllerUtils.forwardRequest(request, response, "User/show-status.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
