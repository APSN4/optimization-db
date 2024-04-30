package com.optimiztiondb.news.controller;

import com.optimiztiondb.news.enums.NewsEnum;
import com.optimiztiondb.news.model.News;
import com.optimiztiondb.news.service.NewsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("/random")
    public ResponseEntity<Object> getRandomNewsBody() {
        News randNews = newsService.getRandomNews();
        if (randNews != null) {
            return new ResponseEntity<>(randNews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(randNews, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createNews(@RequestBody News news) {
        News result = newsService.createNews(news);
        if (result.getId() != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteNews(@RequestBody News news) {
        Boolean result = newsService.deleteNews(news.getId());
        if (result) {
            return new ResponseEntity<>(news, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(news, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
