package com.zohar.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * visitor
 *
 * @author zohar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitor implements Serializable {
    public static final int UPDATE_INTERVAL = 180000;

    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 用户指纹
     */
    private String fingerprint;
    /**
     * 用户IP
     */
    private String ip;
    /**
     * 访问次数
     */
    private Integer frequency;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最近访问时间
     */
    private Date updateTime;
}