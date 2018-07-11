package com.tydic.scda.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lenovo on 2018/7/11.
 */
@Configuration
public class SusConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${sus.host.url}")
    private String susHostUrl;

    public String getSusHostUrl() {
        return susHostUrl;
    }

    public void setSusHostUrl(String susHostUrl) {
        this.susHostUrl = susHostUrl;
    }
}
