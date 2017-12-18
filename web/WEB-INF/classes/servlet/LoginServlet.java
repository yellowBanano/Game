package servlet;

import entity.Account;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String ADMIN_EMAIL = "admin@admin.com";
    private static final String ADMIN_PASSWORD = "admin";
    private static final long serialVersionUID = -3135292633844839557L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        List<String> messages = new ArrayList<>();

        if (email == null || email.isEmpty()) {
            messages.add("Please enter email");
        } else if (!RegistrationServlet.validate(email)) {
            messages.add("Email is not correct, please try again.");
        }
        if (password == null || password.isEmpty()) {
            messages.add("Please enter password");
        }

        if (messages.isEmpty()) {
            if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {
                req.getSession().setAttribute("admin", "true");
            }
            Account account = AccountService.getInstance().find(email, password);
            if (account != null) {
                req.getSession().setAttribute("account", account);
                resp.sendRedirect(req.getContextPath() + "/main");
                return;
            } else {
                messages.add("Unknown login, please try again");
            }
        }
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }
}

