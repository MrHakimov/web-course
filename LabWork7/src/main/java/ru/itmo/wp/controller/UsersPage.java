package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.StatusCredentials;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String changeStatus(Model model, @Valid @ModelAttribute("registerForm") StatusCredentials changeStatusForm, BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.findAll());
            return "UsersPage";
        }

        User user;
        try {
            changeStatusForm.setId(Long.parseLong(changeStatusForm.getStrId()));
            System.out.println(changeStatusForm.getStrId());
            user = userService.findById(changeStatusForm.getId());
        } catch (NumberFormatException e) {
            putMessage(httpSession, "Incorrect query!");
            return "redirect:/users/all";
        }

        if (user == null) {
            putMessage(httpSession, "Incorrect query!");
            return "redirect:/users/all";
        } else if (user.isDisabled() == changeStatusForm.getDisabled()) {
            putMessage(httpSession, "User was already " + (user.isDisabled() ? "disabled" : "enabled") + "!");
            return "redirect:/users/all";
        }

        userService.changeStatus(user, changeStatusForm.getDisabled());

//        setUser(httpSession, userService.findByLoginAndPassword(enterForm.getLogin(), enterForm.getPassword()));
//        putMessage(httpSession, "Hello, " + getUser(httpSession).getLogin());

        return "redirect:/users/all";
    }
}
