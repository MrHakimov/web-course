package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class LogoutPage extends Page {
    private void action(HttpServletRequest request) throws ValidationException {
        request.getSession().removeAttribute("user");

        request.getSession().setAttribute("message", "Good bye. Hope to see you soon!");

        Event event = new Event();
        User user = (User) request.getSession().getAttribute("user");
        event.setUserId(user.getId());
        event.setType(Event.Type.ENTER);

        EventService eventService = new EventService();
        eventService.register(event);
        throw new RedirectException("/index");
    }
}
