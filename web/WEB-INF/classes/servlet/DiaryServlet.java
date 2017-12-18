package servlet;

import entity.Account;
import service.InventoryService;
import service.NpcService;
import service.NpcSpellsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/notes")
public class DiaryServlet extends HttpServlet {
    private static final long serialVersionUID = -8278731933049009887L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allNpc", NpcService.getInstance().getAllNpcs());
        req.getRequestDispatcher("/WEB-INF/jsp/bestiary.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
