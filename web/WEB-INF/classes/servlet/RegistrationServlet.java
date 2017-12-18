package servlet;

import dto.account.CreateNewAccountDto;
import dto.avatar.CreateNewAvatarDto;
import service.AccountService;
import service.AvatarService;
import service.ClassService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final long serialVersionUID = 7062445126971058229L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allClasses", ClassService.getInstance().getAllClass());
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String avatarName = req.getParameter("avatarName");
        String gender = req.getParameter("gender");
        String chosenClass = req.getParameter("chosenClass");
        List<String> messages = new ArrayList<>();

        if (email == null || email.isEmpty()) {
            messages.add("Please enter email");
        } else if (!validate(email)) {
            messages.add("Email is not correct, please try again.");
        }

        if (login == null || login.isEmpty()) {
            messages.add("Please enter login");
        } else if (isLoginExist(login)) {
            messages.add("This login is not available, please try again");
        }

        if (password == null || password.isEmpty()) {
            messages.add("Please enter password");
        }

        if (avatarName == null || avatarName.isEmpty()) {
            messages.add("Please enter your avatar name");
        }

        if (messages.isEmpty()) {
            CreateNewAvatarDto newAvatarDto = new CreateNewAvatarDto(avatarName, gender, Long.valueOf(chosenClass));
            long avatarId = AvatarService.getInstance().createNewAvatar(newAvatarDto);
            CreateNewAccountDto newAccountDto = new CreateNewAccountDto(email, login, password, avatarId);
            AccountService.getInstance().createNewAccount(newAccountDto);
            resp.sendRedirect(req.getContextPath() + "/login");
        }
        doGet(req, resp);
    }

    public static boolean validate(String emailStr) {
        return VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr).find();
    }

    private static boolean isLoginExist(String login) {
        return AccountService.getInstance().getAllAccounts().stream().anyMatch(account -> account.getLogin().equals(login));
    }
}