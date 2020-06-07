package com.zohar.blog.service;

import com.zohar.blog.mapper.VisitorMapper;
import com.zohar.blog.model.Visitor;
import com.zohar.blog.util.ModelUtil;
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
        Example example = new Example(Visitor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fingerprint", fingerprint);
        Visitor visitor = visitorMapper.selectOneByExample(example);
        if (visitor == null) {
            visitor = new Visitor(null, fingerprint, ip, 1, new Date(), new Date());
            return visitorMapper.insert(visitor) != -1;
        }
        Integer frequency = visitor.getFrequency();
        if (!ip.equals(visitor.getIp())
                || ModelUtil.updateTimeSecondInterval(new Date(), visitor.getUpdateTime()) > Visitor.UPDATE_INTERVAL) {
            visitor.setIp(ip);
            visitor.setFrequency(frequency + 1);
            visitor.setUpdateTime(new Date());
            visitorMapper.updateByExample(visitor, example);
            return true;
        }
        return false;
    }

    @Override
    public Integer frequency(String fingerprint) {
        Example example = new Example(Visitor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("fingerprint", fingerprint);
        Visitor visitor = visitorMapper.selectOneByExample(example);
        return visitor == null ? -1 : visitor.getFrequency();
    }
}
