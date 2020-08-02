package com.zohar.blog.service;

import com.zohar.blog.model.Visitor;

import java.util.List;

/**
 * Project:      com.zohar.blog.service
 * Class:        blog
 * Description:
 * Time:         6/7/2020 10:46 PM
 *
 * @author zohar
 **/
public interface VisitorService {
    /**
     * 获取访客列表
     *
     * @return 访客列表
     */
    List<Visitor> getVisitorList();

    /**
     * 更新访客信息
     * 访客记录的唯一性由：浏览器指纹 + 用户 IP 确定，每个访客 1 min 内仅能更新一次访客记录
     *
     * @param fingerprint 访客指纹
     * @param ip          访客 IP
     * @return 更新后的访客访问次数
     */
    Boolean incrementFrequency(String fingerprint, String ip);

    /**
     * 本指纹访问次数
     *
     * @param fingerprint 指纹
     * @return 访问次数
     */
    Integer frequency(String fingerprint);
}
