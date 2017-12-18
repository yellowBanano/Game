package servlet;

import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator")
public class AdministratorServlet extends HttpServlet {
    private static final long serialVersionUID = 2135404147255431904L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("accounts", AccountService.getInstance().getAllAccounts());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(req, resp);
    }
}
