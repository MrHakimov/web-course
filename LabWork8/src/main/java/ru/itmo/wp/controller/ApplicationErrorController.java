package ru.itmo.wp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ApplicationErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpSession httpSession) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            String errorMessage;

            switch (statusCode) {
                case 400:
                    errorMessage = "ERROR 400: Bad request!";
                    break;
                case 401:
                    errorMessage = "ERROR 401: Not authorized!";
                    break;
                case 404:
                    errorMessage = "ERROR 404: Page not found!";
                    break;
                case 500:
                    errorMessage = "ERROR 500: Internal server error!";
                    break;
                default:
                    errorMessage = "ERROR " + statusCode;
            }

            putMessage(httpSession, errorMessage);
            return "redirect:/";
        }

        return getErrorPath();
    }

    private void putMessage(HttpSession httpSession, String message) {
        httpSession.setAttribute("message", message);
    }

    @Override
    public String getErrorPath() {
        return "redirect:/";
    }
}
