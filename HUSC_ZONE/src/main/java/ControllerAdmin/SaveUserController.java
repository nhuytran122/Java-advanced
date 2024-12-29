package ControllerAdmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CommonModal.Constants;
import UserModal.UserBo;

@WebServlet("/admin/save-user")
public class SaveUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SaveUserController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserBo userBo = new UserBo();
            String txtRoleID = request.getParameter("txtVaiTro");

            if (request.getParameter("btnAdd") != null) {
                handleAddUser(request, response, userBo, txtRoleID);
            } else if (request.getParameter("btnUpdate") != null) {
                handleUpdateUser(request, response, userBo, txtRoleID);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleAddUser(HttpServletRequest request, HttpServletResponse response, UserBo userBo, String txtRoleID)
            throws NumberFormatException, Exception {
        String txtHoten = request.getParameter("txtHoten");
        String txtLoginId = request.getParameter("txtLoginId");
        String txtSdt = request.getParameter("txtSdt");
        String txtGioiTinh = request.getParameter("txtGioiTinh");
        String txtPassword = request.getParameter("txtPassword");

        boolean isInvalid = false;
        boolean isDuplicate = false;

        if (txtHoten.trim().isEmpty() || txtGioiTinh.trim().isEmpty() || txtPassword.trim().isEmpty() || txtRoleID.isEmpty()) {
            isInvalid = true;
        } else {
            int add = userBo.addUser(txtHoten, txtPassword, txtGioiTinh, txtLoginId, txtSdt, Long.parseLong(txtRoleID));
            if (add <= 0) {
                isDuplicate = true;
            } else {
                response.sendRedirect("users");
                return;
            }
        }

        request.setAttribute("isInvalid", isInvalid);
        request.setAttribute("isDuplicate", isDuplicate);

        RequestDispatcher rd = request.getRequestDispatcher("/Admin/add-user.jsp");
        rd.forward(request, response);
    }

    private void handleUpdateUser(HttpServletRequest request, HttpServletResponse response, UserBo userBo, String txtRoleID)
            throws Exception {
        if (request.getParameter("userID") != null) {
            Long userID = Long.parseLong(request.getParameter("userID"));
            boolean isInvalid = false;

            if (txtRoleID.isEmpty()) {
                isInvalid = true;
                request.setAttribute("isInvalid", isInvalid);
                request.setAttribute("user", userBo.getUserByID(userID));
                RequestDispatcher rd = request.getRequestDispatcher("/Admin/update-user.jsp");
                rd.forward(request, response);
                return;
            }

            String isLockedParam = request.getParameter("isUsing");
            boolean isUsing = "true".equals(isLockedParam);
            userBo.updateStatusAndRoleUser(userID, isUsing, Long.parseLong(txtRoleID));

            response.sendRedirect("users");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
