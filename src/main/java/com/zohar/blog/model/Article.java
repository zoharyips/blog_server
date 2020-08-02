package com.zohar.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

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

    @Id
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
    private Date createTime;
    /**
     * 最近被访问时间
     */
    private Date updateTime;
}