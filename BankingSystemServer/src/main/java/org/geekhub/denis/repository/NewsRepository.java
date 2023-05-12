package org.geekhub.denis.repository;

import org.geekhub.denis.entity.NewsEntity;

import java.util.List;

/**
 * @author Apilat Denis
 * Date :28.04.2023
 * Time :13:05
 * Project Name :gh-hw-denis-apilat
 */

public interface NewsRepository {
    Integer countNews();
    <S extends NewsEntity> void saveNews(S newsEntity);
    int deleteNewsById(Long id);
    List<NewsEntity> findAllNews();
}