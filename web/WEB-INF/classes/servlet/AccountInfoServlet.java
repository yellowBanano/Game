package servlet;

import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account-info")
public class AccountInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 312413225801304353L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idAccount = Long.valueOf(req.getParameter("id"));
        req.setAttribute("account", AccountService.getInstance().getFullInfo(idAccount));
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/account-info.jsp").forward(req, resp);
    }
}
