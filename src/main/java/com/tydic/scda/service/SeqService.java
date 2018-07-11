package com.tydic.scda.service;

import com.tydic.scda.mapper.SusSequenceMapper;
import com.tydic.scda.model.SusSequence;
import com.tydic.scda.model.SusSequenceExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lenovo on 2018/7/11.
 */
@Component
public class SeqService {
    private final static Logger logger = LoggerFactory.getLogger(SeqService.class);

    @Autowired
    private SusSequenceMapper susSequenceMapper;

    private Long currVal(String seqName){
        SusSequenceExample susSequenceExample = new SusSequenceExample();
        SusSequenceExample.Criteria criteria = susSequenceExample.createCriteria();
        criteria.andNameEqualTo(seqName);
        List<SusSequence> rptbaseSequenceList = susSequenceMapper.selectByExample(susSequenceExample);
        if(rptbaseSequenceList.size() == 0){
            logger.error("getSeq fail seqName:"+seqName);
            return -1l;
        }
        return rptbaseSequenceList.get(0).getCurrentValue();
    }

    public Long nextVal(String seqName){
        //先自加一
        susSequenceMapper.addCurrVal(seqName);

        //获取当前
        return currVal(seqName);
    }
}
