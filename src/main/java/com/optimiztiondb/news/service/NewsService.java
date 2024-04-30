package com.optimiztiondb.news.service;

import com.optimiztiondb.news.enums.NewsEnum;
import com.optimiztiondb.news.model.News;
import com.optimiztiondb.news.repository.NewsRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;

    @CachePut(cacheNames = "news", unless = "#result.id == null", key = "#result.id")
    public News getRandomNews() {
        News news = newsRepository.findTopByOrderByIdDesc();
        Long lastId = news.getId();
        Optional<News> newsRandom = newsRepository.findById(getRandomNumber(lastId));
        if (newsRandom.isPresent()) return newsRandom.get();
        else return getRandomNews();
    }

    @CachePut(cacheNames = "news", key = "#result.id")
    public News createNews(News news) {
        News newArticle = new News(news.getTitle(), news.getContent());
        return newsRepository.saveAndFlush(newArticle);
    }

    @CacheEvict(cacheNames = "news", key = "#id")
    public Boolean deleteNews(Long id) {
        try {
            newsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected Long getRandomNumber(Long lastId) {
        Random random = new Random();
        return random.nextLong(lastId + 1);
    }
}
