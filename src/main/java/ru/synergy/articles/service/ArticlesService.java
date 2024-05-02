package ru.synergy.articles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.synergy.articles.exceptions.ArticleNotFoundException;
import ru.synergy.articles.models.Article;
import ru.synergy.articles.repository.ArticleRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ArticlesService {
    @Autowired
    private ArticleRepository repository;
    public List<Article> getArticles() {
        return repository.findAll();
    }

    public void create(Article article) {
        repository.save(article);
    }

    public void upRating(long articleId) {
      Article article = repository.findById(articleId).orElseThrow(ArticleNotFoundException::new);
      article.setRating(article.getRating() + 1);
      repository.save(article);
    }

    public void downRating(long articleId) {
        Article article = repository.findById(articleId).orElseThrow(ArticleNotFoundException::new);
        article.setRating(article.getRating() - 1);
        repository.save(article);
    }

    public List<Article> getByTitleRatingDown(String title, int rating) {
        return repository.findByTitleAndRatingGreaterThanOrderByRatingDesc(title, rating);
    }

    public List<Article> getByTitleRatingUp(String title, int rating) {
        return repository.findByTitleAndRatingGreaterThanOrderByRatingAsc(title, rating);
    }
}
