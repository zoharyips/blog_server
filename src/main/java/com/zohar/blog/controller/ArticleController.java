package com.zohar.blog.controller;

import com.zohar.blog.model.Article;
import com.zohar.blog.service.ArticleService;
import com.zohar.blog.service.VisitorService;
import com.zohar.blog.util.WebServletUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Project:      com.zohar.blog.controller
 * Class:        blog
 * Description:
 * Time:         6/7/2020 8:30 PM
 *
 * @author zohar
 **/
@RestController
@RequestMapping("article")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
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

    @GetMapping(path = "frequency")
    public Integer allFrequency() {
        return articleService.getAllFrequency();
    }

    @PostMapping(path = "frequency")
    public Integer incrementFrequency(HttpServletRequest request) {
        String ip = WebServletUtil.getIP(request);
        String fingerprint = request.getParameter("fingerprint");
        String title       = request.getParameter("title");
        String url         = request.getParameter("url");
        visitorService.incrementFrequency(fingerprint, ip);
        return articleService.incrementFrequency(title, url);
    }
}
