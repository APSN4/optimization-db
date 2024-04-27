package com.optimiztiondb.news.service;

import com.optimiztiondb.news.enums.NewsEnum;
import com.optimiztiondb.news.model.News;
import com.optimiztiondb.news.repository.NewsRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;

    public JSONObject getRandomNews() {
        News news = newsRepository.findTopByOrderByIdDesc();
        Long lastId = news.getId();
        Optional<News> newsRandom = Optional.ofNullable(newsRepository.findById(getRandomNumber(lastId)));
        if (newsRandom.isPresent()) {
            return new JSONObject().put("id", newsRandom.get().getId())
                    .put("title", newsRandom.get().getTitle())
                    .put("content", newsRandom.get().getContent())
                    .put("status", NewsEnum.SUCCESS);
        } else {
            return new JSONObject().put("status", NewsEnum.FAIL);
        }
    }

    protected Long getRandomNumber(Long lastId) {
        Random random = new Random();
        return random.nextLong(lastId);
    }
}
