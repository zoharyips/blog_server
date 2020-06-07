package com.zohar.blog.controller;

import com.zohar.blog.model.Article;
import com.zohar.blog.service.ArticleService;
import com.zohar.blog.service.VisitorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project:      com.zohar.blog.controller
 * Class:        blog
 * Description:
 * Time:         6/7/2020 8:30 PM
 *
 * @author zohar
 **/
@RestController
@RequestMapping("/article")
@CrossOrigin(origins = "*")
public class ArticleController {

    private final ArticleService articleService;
    private final VisitorService visitorService;

    public ArticleController(ArticleService articleService, VisitorService visitorService) {
        this.articleService = articleService;
        this.visitorService = visitorService;
    }

    @GetMapping
    public List<Article> index() {
        return articleService.getArticleList();
    }

    @GetMapping(path = "/frequency")
    public Integer allFrequency() {
        return articleService.getAllFrequency();
    }

    @PostMapping(path = "/frequency")
    public Integer incrementFrequency(String url, String title, String fingerprint, String ip) {
        Boolean res = visitorService.incrementFrequency(fingerprint, ip);
        return res ? articleService.incrementFrequency(title, url) : articleService.getFrequency(url);
    }
}
