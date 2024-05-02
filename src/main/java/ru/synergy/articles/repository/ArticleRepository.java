package ru.synergy.articles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.synergy.articles.models.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitle(String title);

    List<Article> findByTitleAndRatingGreaterThanOrderByRatingAsc(String title, int rating);

    List<Article> findByTitleAndRatingGreaterThanOrderByRatingDesc(String title, int rating);

}