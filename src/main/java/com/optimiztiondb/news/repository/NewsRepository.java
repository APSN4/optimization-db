package com.optimiztiondb.news.repository;

import com.optimiztiondb.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<Long, News> {
    News findTopByOrderByIdDesc();

    News findById(Long randomNumber);
}
