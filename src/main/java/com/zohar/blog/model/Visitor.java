package com.zohar.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
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
    /**
     * 同一用户在该时间间隔内重复访问不计入统计次数：1 min
     */
    public static final int UPDATE_INTERVAL = 60000;

    private static final long serialVersionUID = 1L;

    @Id
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