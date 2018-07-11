package com.tydic.scda.utils;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.*;

/**
 * Created by lenovo on 2018/1/30.
 */
@Configuration
public class ApplicationConfigurer {
    private static String modelName = "sus";
    @Bean
    public static PropertyPlaceholderConfigurer properties() {
        final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setIgnoreResourceNotFound(true);
        final List<Resource> resourceLst = new ArrayList<Resource>();
        resourceLst.add(new ClassPathResource("application.properties"));
        ppc.setLocations(resourceLst.toArray(new Resource[]{}));
        Properties properties = new Properties();
        Map<String,Object> props = new HashMap<>();
        try {
            props = loadConf(modelName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        properties.putAll(props);
        ppc.setProperties(properties);
        return ppc;
    }

    private static Map<String,Object> loadConf(String modelName) throws InterruptedException {
        Map<String,Object> ret = new HashMap<>();
        ZkUtils zkUtils = new ZkUtils();
//        String zkServerUrl = "192.168.0.135:2181";
        String zkServerUrl = "conf.center.server:2181";
        ZooKeeper zk = null;
        try {
            zk= zkUtils.getInstance(zkServerUrl);
            List<String> props = zk.getChildren("/confCenter/"+modelName, false);
            System.out.println(props.toString());
            for(String key:props){
                byte[] tmp = zk.getData("/confCenter/"+modelName+"/"+key,false,null);
                String value = new String(tmp);
                ret.put(key,value);
            }

            System.out.println(ret.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (KeeperException e){
            e.printStackTrace();
        } finally{
            zk.close();
        }
        return ret;
    }
}
