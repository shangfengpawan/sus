package com.tydic.scda.service;

import com.tydic.scda.mapper.SusUrlRecordMapper;
import com.tydic.scda.model.SusUrlRecord;
import com.tydic.scda.model.SusUrlRecordExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2018/7/11.
 */
@Service("susRecordService")
public class SusRecordService {
    private final static Logger logger = LoggerFactory.getLogger(SusRecordService.class);

    @Autowired
    private SusUrlRecordMapper susUrlRecordMapper;

    public int insert(SusUrlRecord record) throws Exception{
        return susUrlRecordMapper.insert(record);
    }

    public SusUrlRecord getSusUrlRecord(Long id){
        return susUrlRecordMapper.selectByPrimaryKey(id);
    }

    public List<SusUrlRecord> getSusUrlByShortUrl(String shortUrl){
        SusUrlRecordExample susUrlRecordExample = new SusUrlRecordExample();
        SusUrlRecordExample.Criteria criteria = susUrlRecordExample.createCriteria();
        criteria.andShortUrlEqualTo(shortUrl);
        List<SusUrlRecord> susUrlRecords = susUrlRecordMapper.selectByExample(susUrlRecordExample);
        return susUrlRecords;
    }

}
