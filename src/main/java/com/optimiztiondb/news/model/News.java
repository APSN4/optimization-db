package com.optimiztiondb.news.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class News {

    public News() {}
    public News(String title, String content) {
        this.title = title;
        this.content = content;
        this.rating = 0;
    }

    @Id
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "content")
    private String content;

    @Column(nullable = false, name = "rating")
    private Integer rating;
}
