package servlet;

import entity.Account;
import service.AvatarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = -2303598067860001117L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        long idAvatar = account.getAvatar().getId();

        session.setAttribute("idAvatar", idAvatar);
        req.setAttribute("avatarData", AvatarService.getInstance().getFullInfo(idAvatar));
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
    }
}
