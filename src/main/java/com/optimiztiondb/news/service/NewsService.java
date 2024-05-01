package com.optimiztiondb.news.service;

import com.optimiztiondb.news.model.News;
import com.optimiztiondb.news.repository.NewsRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import com.optimiztiondb.news.constant.mutableVariables;

import java.util.Optional;
import java.util.Random;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    mutableVariables mutableVariables;

    public Optional<News> getRandomNews() {
        Long lastId = mutableVariables.getNewsCountAll();
        if (lastId == null) {
            News news = newsRepository.findTopByOrderByIdDesc();
            lastId = news.getId();
            mutableVariables.setNewsCountAll(lastId);
        }
        Long randLong = getRandomNumber(lastId);
        if (randLong == 0) {
            randLong = 1L;
        }

        // Проверяем наличие данных в кэше после получения последней новости из базы данных
        Cache.ValueWrapper valueWrapper = cacheManager.getCache("news").get(randLong);
        if (valueWrapper != null) {
            return Optional.of((News) valueWrapper.get());
        }

        Optional<News> newsRandom = newsRepository.findById(randLong);
        if (newsRandom.isPresent()){
            cacheManager.getCache("news").put(newsRandom.get().getId(), newsRandom.get());
            return newsRandom;
        }
        else return getRandomNews();
    }

    @CachePut(cacheNames = "news", key = "#result.id")
    public News createNews(News news) {
        News newArticle = new News(news.getTitle(), news.getContent());
        News newsSaved = newsRepository.save(newArticle);
        mutableVariables.setNewsCountAll(newsSaved.getId());
        return newsSaved;
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

    public Long getRandomNumber(Long lastId) {
        Random random = new Random();
        return random.nextLong(lastId + 1);
    }
}
