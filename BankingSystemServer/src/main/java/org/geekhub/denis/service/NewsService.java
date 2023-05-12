package org.geekhub.denis.service;

import lombok.RequiredArgsConstructor;
import org.geekhub.denis.entity.NewsEntity;
import org.geekhub.denis.model.NewsRequestModel;
import org.geekhub.denis.repository.NewsRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Apilat Denis
 * Date :28.04.2023
 * Time :13:17
 * Project Name :gh-hw-denis-apilat
 */

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepositoryImpl newsRepository;

    public Integer getCountNews() {
        return newsRepository.countNews();
    }

    public String saveNews(NewsRequestModel newsRequestModel) {
        NewsEntity newsEntity = NewsEntity.builder()
                .title(newsRequestModel.getTitle())
                .content(newsRequestModel.getContent())
                .imageUrl(newsRequestModel.getImageUrl())
                .build();
        newsRepository.saveNews(newsEntity);
        return "NEWS ADDED!";
    }

    public List<NewsEntity> getAllNews() {
        return new ArrayList<>(newsRepository.findAllNews());
    }

}


