package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.domain.User;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;

@Controller
public class UserPage extends Page {
    @GetMapping("user/{id}")
    public String user(Model model, @Pattern(regexp = "[0-9]+", message = "Expected numbers") @PathVariable String id) {
        User user = findUserById(Long.parseLong(id));
        if (user != null) {
            model.addAttribute("viewedUser", user);
        }
        return "UserPage";
    }
}
