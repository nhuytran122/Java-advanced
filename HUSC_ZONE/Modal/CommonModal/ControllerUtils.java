package CommonModal;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUtils {

    public static int getPage(HttpServletRequest request) {
        String pageParam = request.getParameter("page");
        return (pageParam != null) ? Integer.parseInt(pageParam) : 1;
    }

    public static String getSearchValue(HttpServletRequest request) {
        if (request.getParameter("btn-search") != null && request.getParameter("txtSearch") != null) {
            return request.getParameter("txtSearch");
        }
        return "";
    }

    public static void setPaginationAttributes(HttpServletRequest request, int page, int pageCount) {
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("currentPage", page);
    }

    public static void forwardRequest(HttpServletRequest request, HttpServletResponse response, String path) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);
    }
}
