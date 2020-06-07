package com.zohar.blog.service;

import com.zohar.blog.model.Article;

import java.util.List;

/**
 * Project:      com.zohar.blog.service
 * Class:        blog
 * Description:
 * Time:         6/7/2020 8:59 PM
 *
 * @author zohar
 **/
public interface ArticleService {
    /**
     * 文章访问量加一，如果该文章未被记录，则增加记录
     *
     * @param title 文章标题
     * @param url   文章链接
     * @return 更新后的访问量
     */
    Integer incrementFrequency(String title, String url);

    /**
     * 获取文章访问量
     *
     * @param url 文章链接
     * @return 文章访问量
     */
    Integer getFrequency(String url);

    /**
     * 获取所有记录
     *
     * @return 记录列表
     */
    List<Article> getArticleList();

    /**
     * 获取所有访问次数
     *
     * @return 总访问次数
     */
    Integer getAllFrequency();
}
