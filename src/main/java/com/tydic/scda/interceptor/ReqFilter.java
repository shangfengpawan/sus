package com.tydic.scda.interceptor;


import com.tydic.scda.model.SusUrlRecord;
import com.tydic.scda.utils.SusRecordUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2018/7/10.
 */

public class ReqFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hrequest = (HttpServletRequest) request;
        HttpServletResponse hresponse = (HttpServletResponse)response;
        //获取请求地址
        String url =hrequest.getRequestURL().toString();
        String uri = hrequest.getRequestURI().toString();
        System.out.println("url:"+url);
        System.out.println("uri:"+uri);
        String[] allowUrls =new String[]{"/getSusShortUrl.do","/susErrPage.do"};

//        Map<String,String> urlMapping = new HashMap<String, String>();
//        urlMapping.put("11aaAA","http://192.168.0.140:8888/portal_web/home");

        boolean flag = false;
        for (String strUrl : allowUrls) {
            if(url.contains(strUrl))
            {
                flag =  true;
            }
        }
        if(flag){
            chain.doFilter(request, response);
            return;
        }else{


            String tmpShortUrl = uri.substring(5,uri.length());
            System.out.println("shortUrl:"+tmpShortUrl);
            List<SusUrlRecord> susUrlRecords = new ArrayList<>();
            if(tmpShortUrl.length() > 0){
                try {
                    susUrlRecords = SusRecordUtils.getSUsRecords(tmpShortUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                    hresponse.sendRedirect("/sus/susErrPage.do");
                }
            }

            if(susUrlRecords.size() > 0){
                String targetUrl = susUrlRecords.get(0).getSrcUrl();
                hresponse.setStatus(301);
                hresponse.setHeader("Location",targetUrl);
                hresponse.sendRedirect(targetUrl);
            }else{
                hresponse.sendRedirect("/sus/susErrPage.do");
            }


        }

//        chain.doFilter(request, response);
        return;
    }

    @Override
    public void destroy() {

    }
}
