package servlet;

import dao.ItemDao;
import entity.Account;
import entity.Avatar;
import entity.Item;
import service.InventoryService;
import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {

    private static final long serialVersionUID = 5528038831661744385L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");
        long idAvatar = account.getAvatar().getId();
        req.setAttribute("allItems", InventoryService.getInstance().getAllItems(idAvatar));
        req.getRequestDispatcher("/WEB-INF/jsp/inventory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
