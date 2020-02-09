package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage {
    private ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void create(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user = (User) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String text = request.getParameter("text");

        Article article = new Article();
        if (user == null) {
            throw new ValidationException("You should enter to be able to write posts!");
        }

        article.setUserId(user.getId());
        if (title == null || title.isEmpty()) {
            throw new ValidationException("Title should not be empty!");
        }

        article.setTitle(title);

        if (text == null || text.isEmpty()) {
            throw new ValidationException("Text should not be empty!");
        }

        article.setText(text);

        articleService.create(article);

        request.getSession().setAttribute("message", "Your article successfully created!");
        throw new RedirectException("/index");
    }
}
