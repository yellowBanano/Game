package servlet;

import service.NpcService;
import service.NpcSpellsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/npc-info")
public class NpcInfoServlet extends HttpServlet {
    private static final long serialVersionUID = -4838310793733837094L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idNpc = Long.valueOf(req.getParameter("id"));
        req.setAttribute("npc", NpcService.getInstance().getFullInfo(idNpc));
        req.setAttribute("spells", NpcSpellsService.getInstance().getAllSpell(idNpc));
        req.getRequestDispatcher("/WEB-INF/jsp/npc-info.jsp").forward(req, resp);
    }
}
