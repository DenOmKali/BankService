package org.geekhub.denis.repository;

import org.geekhub.denis.entity.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Apilat Denis
 * Date :28.04.2023
 * Time :13:12
 * Project Name :gh-hw-denis-apilat
 */

@Repository
public class NewsRepositoryImpl implements NewsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public static final Date CURRENT_DATE = new Date();

    @Override
    public Integer countNews() {
        return jdbcTemplate
                .queryForObject("SELECT COUNT(*) FROM news", Integer.class);
    }

    @Override
    public <S extends NewsEntity> void saveNews(S newsEntity) {
        jdbcTemplate.update(
                "INSERT INTO news (title, content, img_url, date) VALUES(?,?,?,?)",
                newsEntity.getTitle(),
                newsEntity.getContent(),
                newsEntity.getImageUrl(),
                CURRENT_DATE);
    }

    @Override
    public int deleteNewsById(Long id) {
        return jdbcTemplate.update(
                "DELETE FROM news WHERE id = ?",
                id);
    }

    @Override
    public List<NewsEntity> findAllNews() {
        return jdbcTemplate.query(
                "SELECT * FROM news",
                (rs, rowNum) ->
                        new NewsEntity(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getString("content"),
                                rs.getString("img_url"),
                                rs.getDate("date")
                        )
        );
    }

}