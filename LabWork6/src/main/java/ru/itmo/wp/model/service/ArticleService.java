package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
    public void create(Article article) {
        articleRepository.save(article);
    }

    public void changeArticleVisibility(long articleId, boolean hidden) {
        articleRepository.changeArticleVisibility(articleRepository.find(articleId), hidden);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article find(long id) {
        return articleRepository.find(id);
    }

    public List<Article> findByUserId(long userId) {
        return articleRepository.findByUserId(userId);
    }
}
