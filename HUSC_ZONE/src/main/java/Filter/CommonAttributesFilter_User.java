package Filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import CommonModal.MethodCommon;
import UserModal.User;

import java.io.IOException;

@WebFilter("/*") 
public class CommonAttributesFilter_User implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String requestURI = httpRequest.getRequestURI();
            httpRequest.setCharacterEncoding("utf-8");
            httpRequest.setCharacterEncoding("utf-8");
            // Bỏ qua các yêu cầu bắt đầu bằng /admin
            if (!requestURI.startsWith("/admin")) {
                httpRequest.setAttribute("listCates", MethodCommon.getListCates());
                httpRequest.setAttribute("listMates", MethodCommon.getListMates());

                HttpSession session = httpRequest.getSession(false); 
                if (session != null) {
                    User user = (User) session.getAttribute("user");
                    if (user != null) {
                        Long userID = user.getUserID();
                        if (userID != null) {
                            httpRequest.setAttribute("listNoti", MethodCommon.getListNotis(userID));
                        }
                    }
                }
            }

            // Tiếp tục xử lý request
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {}
}
