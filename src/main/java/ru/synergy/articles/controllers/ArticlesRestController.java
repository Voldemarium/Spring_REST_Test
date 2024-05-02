package ru.synergy.articles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.synergy.articles.models.Article;
import ru.synergy.articles.service.ArticlesService;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticlesRestController {
    @Autowired
    private ArticlesService service;

    @GetMapping()
    public List<Article> getArticles() {
        return service.getArticles();
    }

    @PostMapping()
    public void createArticle(@RequestBody Article article) {
        service.create(article);
    }

    @PostMapping("/{articleId}/rating/up")
    public void upRating(@PathVariable long articleId) {
        service.upRating(articleId);
    }

    @PostMapping("/{articleId}/rating/down")
    public void downRating(@PathVariable long articleId) {
        service.downRating(articleId);
    }

    @GetMapping("/title/rating/up")
    public List<Article> getByTitleRatingUp(@RequestParam String title) {
        return service.getByTitleRatingUp(title, 0);
    }

    @GetMapping("/title/rating/down")
    public List<Article> getByTitleRatingDown(@RequestParam String title) {
        return service.getByTitleRatingDown(title, 0);
    }
}
