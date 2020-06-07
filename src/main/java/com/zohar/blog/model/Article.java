package com.zohar.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * articles
 *
 * @author zohar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章链接
     */
    private String url;
    /**
     * 被访问次数
     */
    private Integer frequency;
    /**
     * 首次被访问时间
     */
    private String createTime;
    /**
     * 最近被访问时间
     */
    private String updateTime;
}