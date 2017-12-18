package servlet;

import entity.Account;
import service.AvatarSpellsService;
import service.InventoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/spells")
public class AvatarSpellServlet extends HttpServlet {
    private static final long serialVersionUID = -1896050216294753292L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");
        long idAvatar = account.getAvatar().getId();
        req.setAttribute("allSpells", AvatarSpellsService.getInstance().getAllSpell(idAvatar));
        req.getRequestDispatcher("/WEB-INF/jsp/spell-book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
