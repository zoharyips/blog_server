package com.zohar.blog.mapper;

import com.zohar.blog.model.Article;
import org.springframework.stereotype.Component;

/**
 * @author zohar
 */
@Component
public interface ArticleMapper extends tk.mybatis.mapper.common.Mapper<Article> {
}