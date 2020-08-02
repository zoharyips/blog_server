package com.zohar.blog.service;

import com.zohar.blog.mapper.ArticleMapper;
import com.zohar.blog.model.Article;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Project:      com.zohar.blog.service
 * Class:        blog
 * Description:
 * Time:         6/7/2020 9:00 PM
 *
 * @author zohar
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public Integer incrementFrequency(String title, String url) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("url", url);
        Article article = articleMapper.selectOneByExample(example);
        int res;
        if (article == null) {
            article = new Article(null, title, url, 1, new Date(), new Date());
            res = articleMapper.insert(article);
        } else {
            Integer frequency = article.getFrequency() + 1;
            article.setFrequency(frequency);
            article.setUpdateTime(new Date());
            res = articleMapper.updateByExample(article, example);
        }
        if (res != -1) {
            Example summaryRecord = new Example(Article.class);
            Example.Criteria summaryRecordCriteria = summaryRecord.createCriteria();
            summaryRecordCriteria.andEqualTo("id", 1);
            Article siteData = articleMapper.selectOneByExample(summaryRecord);
            if (siteData != null) {
                siteData.setFrequency(siteData.getFrequency() + 1);
                siteData.setUpdateTime(new Date());
                articleMapper.updateByExample(siteData, summaryRecord);
            }
        }
        return article.getFrequency();
    }

    @Override
    public Integer getFrequency(String url) {
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("url", url);
        Article article = articleMapper.selectOneByExample(example);
        return article == null ? -1 : article.getFrequency();
    }

    @Override
    public List<Article> getArticleList() {
        return articleMapper.selectAll();
    }

    @Override
    public Integer getAllFrequency() {
        Article siteData = articleMapper.selectByPrimaryKey(1);
        return siteData == null ? -1 : siteData.getFrequency();
    }
}
