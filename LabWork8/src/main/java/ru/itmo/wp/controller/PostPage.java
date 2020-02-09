package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @GetMapping("/post/{id}")
    public String post(@PathVariable long id, Model model, HttpSession httpSession) {
        Post currentPost = postService.findById(id);;
        if (currentPost == null) {
            putMessage(httpSession, "No such post");
            return "redirect:/";
        }

        model.addAttribute("post", currentPost);
        model.addAttribute("comments", currentPost.getComments());
        model.addAttribute("comment", new Comment());
        model.addAttribute("tags", currentPost.getTags());
        return "PostPage";
    }

    @Guest
    @PostMapping("post/{id}")
    public String post(@Valid @ModelAttribute("comment") Comment comment,
                       BindingResult bindingResult,
                       @PathVariable long id,
                       HttpSession httpSession,
                       Model model) {
        Post currentPost = postService.findById(id); //getPost(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", getUser(httpSession));
            model.addAttribute("post", currentPost);
            model.addAttribute("comments", currentPost.getComments());
            model.addAttribute("comment", comment);
            model.addAttribute("tags", currentPost.getTags());
            return "PostPage";
        }

        postService.writeComment(currentPost, getUser(httpSession), comment);
        putMessage(httpSession, "Your comment has been published successfully!");

        return "redirect:/post/" + id;
    }
}
