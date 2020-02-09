package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.form.validator.TagValidator;
import ru.itmo.wp.repository.TagRepository;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class WritePostPage extends Page {
    private final UserService userService;
    private final TagValidator tagValidator;
    private final TagRepository tagRepository;

    private Set<Tag> parseTags(String tagsString) {
        String[] myTagsString = tagsString.split("\\s+");
        Set<String> myTagsSet = new TreeSet<>(Arrays.asList(myTagsString));
        Set<Tag> myTags = new HashSet<>();
        for (String tag : myTagsSet) {
            Tag currentTag = tagRepository.findByName(tag);
            if (currentTag == null) {
                currentTag = new Tag(tag);
            }

            myTags.add(currentTag);
        }

        return myTags;
    }

    public WritePostPage(UserService userService, TagValidator tagValidator, TagRepository tagRepository) {
        this.userService = userService;
        this.tagValidator = tagValidator;
        this.tagRepository = tagRepository;
    }

    @InitBinder("post")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(tagValidator);
    }

    @AnyRole({Role.Name.WRITER, Role.Name.ADMIN})
    @GetMapping("/writePost")
    public String writePostGet(Model model) {
        model.addAttribute("post", new Post());
        return "WritePostPage";
    }

    @AnyRole({Role.Name.WRITER, Role.Name.ADMIN})
    @PostMapping("/writePost")
    public String writePostPost(@Valid @ModelAttribute("post") Post post,
                                BindingResult bindingResult,
                                HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "WritePostPage";
        }

        if (!post.getTagsString().isEmpty()) {
            Set<Tag> myTags = parseTags(post.getTagsString());
        }

        userService.writePost(getUser(httpSession), post);
        putMessage(httpSession, "You published new post");

        return "redirect:/posts";
    }
}
