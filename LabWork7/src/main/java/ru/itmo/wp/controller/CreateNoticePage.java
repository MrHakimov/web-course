package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.NoticeCredentials;
import ru.itmo.wp.form.validator.NoticeCredentialsCreateValidator;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CreateNoticePage extends Page {
    private final NoticeService noticeService;
    private final NoticeCredentialsCreateValidator noticeCredentialsCreateValidator;

    public CreateNoticePage(NoticeService noticeService, NoticeCredentialsCreateValidator noticeCredentialsCreateValidator) {
        this.noticeService = noticeService;
        this.noticeCredentialsCreateValidator = noticeCredentialsCreateValidator;
    }

    @InitBinder("createNoticeForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(noticeCredentialsCreateValidator);
    }

    @GetMapping("/createNotice")
    public String createNoticeGet(Model model) {
        model.addAttribute("createNoticeForm", new NoticeCredentials());
        return "CreateNoticePage";
    }

    @PostMapping("/createNotice")
    public String createNoticePost(@Valid @ModelAttribute("createNoticeForm") NoticeCredentials createNoticeForm,
                                   BindingResult bindingResult,
                                   HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "CreateNoticePage";
        }

        noticeService.register(createNoticeForm);
        putMessage(httpSession, "Notice successfully created!");

        return "redirect:/";
    }
}
