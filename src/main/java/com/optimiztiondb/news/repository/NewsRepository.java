package com.optimiztiondb.news.repository;

import com.optimiztiondb.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    News findTopByOrderByIdDesc();

    Optional<News> findById(Long randomNumber);
}
