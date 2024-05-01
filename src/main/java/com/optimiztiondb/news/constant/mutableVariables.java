package com.optimiztiondb.news.constant;

import org.springframework.stereotype.Component;

@Component
public class mutableVariables {
    private Long newsCountAll;

    public Long getNewsCountAll(){
        return newsCountAll;
    }

    public void setNewsCountAll(Long newsCountAll){
        this.newsCountAll = newsCountAll;
    }
}
