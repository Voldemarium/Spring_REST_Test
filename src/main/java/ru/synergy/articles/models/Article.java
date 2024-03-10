package ru.synergy.articles.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String category;
    @Column
    private int rating;

    public Article(String title, String description, String category, int rating) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.rating = rating;
    }
}
