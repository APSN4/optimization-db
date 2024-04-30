package com.optimiztiondb.news.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "news")
public class News implements Serializable {

    public News() {}
    public News(String title, String content) {
        this.title = title;
        this.content = content;
        this.rating = 0;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false, name = "title")
    private String title;

    @NotBlank(message = "Content is required")
    @Column(nullable = false, name = "content")
    private String content;

    @Column(nullable = false, name = "rating")
    private Integer rating;
}
