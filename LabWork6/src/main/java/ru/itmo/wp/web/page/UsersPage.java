package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class UsersPage {
    private final UserService userService = new UserService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) {
        request.getSession().setAttribute("user", userService.find(Long.parseLong(request.getParameter("userId"))));
    }

    private void changeAdminStatus(HttpServletRequest request, Map<String, Object> view) {
        boolean admin = Boolean.parseBoolean(request.getParameter("admin"));
        long userId = Long.parseLong(request.getParameter("userId"));
        User user = (User) request.getSession().getAttribute("user");
        if (user.getAdmin() && user.getId() != userId) {
            userService.changeAdminStatus(userId, admin);
            view.put("actualAdminStatus", userService.find(userId).getAdmin());
        }
        view.put("actualAdminStatus", userService.find(userId).getAdmin());
    }
}
