package com.optimiztiondb.news.controller;

import com.optimiztiondb.news.enums.NewsEnum;
import com.optimiztiondb.news.model.News;
import com.optimiztiondb.news.service.NewsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("/random")
    public ResponseEntity<Object> getRandomNewsBody() {
        JSONObject randNews = newsService.getRandomNews();
        if (randNews.get("status").equals(NewsEnum.SUCCESS)) {
            return new ResponseEntity<>(randNews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(randNews, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
