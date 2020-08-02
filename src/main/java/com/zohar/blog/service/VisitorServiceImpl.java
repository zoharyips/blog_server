package com.zohar.blog.service;

import com.zohar.blog.mapper.VisitorMapper;
import com.zohar.blog.model.Visitor;
import com.zohar.blog.util.TimeUtil;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Project:      com.zohar.blog.service
 * Class:        blog
 * Description:
 * Time:         6/7/2020 10:46 PM
 *
 * @author zohar
 **/
@Service
@Log
public class VisitorServiceImpl implements VisitorService {

    private final VisitorMapper visitorMapper;

    public VisitorServiceImpl(VisitorMapper visitorMapper) {
        this.visitorMapper = visitorMapper;
    }

    @Override
    public List<Visitor> getVisitorList() {
        return visitorMapper.selectAll();
    }

    @Override
    public Boolean incrementFrequency(String fingerprint, String ip) {
        /* 根据浏览器指纹查询记录 */
        Example example = new Example(Visitor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fingerprint", fingerprint);
        List<Visitor> visitors = visitorMapper.selectByExample(example);


        System.out.println(visitors.size());
        Visitor visitor = null;
        /* 若无记录，或 IP 进行更改，直接创建该指纹对应的记录 */
        for (Visitor record : visitors) {
            if (!record.getIp().equals(ip)) {
                continue;
            }
            visitor = record;
        }
        if (visitor == null) {
            visitor = new Visitor(null, fingerprint, ip, 1, new Date(), new Date());
            return visitorMapper.insert(visitor) != -1;
        }

        /* 若存在记录，根据时间判断是否需要更新记录 */
        System.out.println(TimeUtil.msInterval(new Date(), visitor.getUpdateTime()));
        if (TimeUtil.msInterval(new Date(), visitor.getUpdateTime()) > Visitor.UPDATE_INTERVAL) {
            visitor.setFrequency(visitor.getFrequency() + 1);
            visitor.setUpdateTime(new Date());
            System.out.println(visitor);
            int res = visitorMapper.updateByPrimaryKey(visitor);
            log.info(String.valueOf(res));
            return true;
        }
        return false;
    }

    @Override
    public Integer frequency(String fingerprint) {
        Example example = new Example(Visitor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fingerprint", fingerprint);
        List<Visitor> visitors = visitorMapper.selectByExample(example);
        int res = 0;
        for (Visitor visitor : visitors) {
            res += visitor.getFrequency();
        }
        return res;
    }
}
