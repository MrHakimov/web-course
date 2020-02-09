package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyArticlesPage {
    private final ArticleService articleService = new ArticleService();

    public void action(HttpServletRequest request, Map<String, Object> view) {
        if (request.getSession().getAttribute("user") != null) {
            view.put("myArticles", articleService.findByUserId(((User) request.getSession().getAttribute("user")).getId()));
        }
    }

    private void changeArticleVisibility(HttpServletRequest request, Map<String, Object> view) {
        boolean hidden = Boolean.parseBoolean(request.getParameter("currentVisibility"));
        long articleId = Long.parseLong(request.getParameter("articleId"));
        User user = (User) request.getSession().getAttribute("user");
        if (articleService.find(articleId).getUserId() == user.getId()) {
            articleService.changeArticleVisibility(articleId, hidden);
            view.put("actualArticleVisibility", articleService.find(articleId).getHidden());
        }
    }
}
