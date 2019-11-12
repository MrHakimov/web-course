package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {
    private final UserService userService = new UserService();
    private HttpServletRequest request;

    private void putUser(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            view.put("user", user);
        }
    }

    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    public void setMessage(String message) {
        request.getSession().setAttribute("message", message);
    }

    public String getMessage() {
        return (String) request.getSession().getAttribute("message");
    }

    public void setUser(User user) {
        request.getSession().setAttribute("user", user);
    }

    public User getUser(User user) {
        return (User) request.getSession().getAttribute("user");
    }

    public void before(HttpServletRequest request, Map<String, Object> view) {
        this.request = request;
        view.put("userCount", userService.findCount());
        putUser(request, view);
        putMessage(request, view);
    }

    public void after(HttpServletRequest request, Map<String, Object> view) {
    }

    protected void action() {
        // No operations
    }
}
