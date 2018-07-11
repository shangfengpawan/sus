package com.tydic.scda.utils;

import com.tydic.scda.model.SusUrlRecord;
import com.tydic.scda.service.SusRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by lenovo on 2018/7/11.
 */
@Component
public class SusRecordUtils {
    @Autowired
    private SusRecordService susRecordService;

    public static SusRecordUtils susRecordUtils;
    public SusRecordUtils(){

    }

    @PostConstruct
    public void init() {
        susRecordUtils = this;
        susRecordUtils.susRecordService = this.susRecordService;
    }

    public static  List<SusUrlRecord> getSUsRecords(String shortUrl){
        return susRecordUtils.susRecordService.getSusUrlByShortUrl(shortUrl);
    }
}
