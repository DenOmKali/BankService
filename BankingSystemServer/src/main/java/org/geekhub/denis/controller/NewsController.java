package org.geekhub.denis.controller;

import lombok.RequiredArgsConstructor;
import org.geekhub.denis.entity.NewsEntity;
import org.geekhub.denis.model.NewsRequestModel;
import org.geekhub.denis.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Apilat Denis
 * Date :28.04.2023
 * Time :13:18
 * Project Name :gh-hw-denis-apilat
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;

    @PostMapping("/addNews")
    public ResponseEntity<String> addNews(@RequestBody NewsRequestModel newsRequestModel) {
        return ResponseEntity.ok(newsService.saveNews(newsRequestModel));
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> getCount() {
        return ResponseEntity.ok(newsService.getCountNews());
    }

    @GetMapping("/all")
    public ResponseEntity<List<NewsEntity>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }


}
