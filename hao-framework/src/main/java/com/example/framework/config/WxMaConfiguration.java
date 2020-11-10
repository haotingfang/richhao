package com.example.framework.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


/*微信小程序配置类*/
@Configuration
public class WxMaConfiguration {

    @Value("${wx.appid}")
    private String appId ;
    @Value("${wx.secret}")
    private String secret ;

   /* private static final String token = BaseConfig.getProperty("wechat.token");
    private static final String aesKey = BaseConfig.getProperty("wechat.aesKey");*/


/*
    private WxMaService wxMaService = null;
*/


    @Bean
    public WxMaService wxMaService(){
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(appId);
        config.setSecret(secret);
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(config);
        return wxMaService;
    }

 /*   public static WxMaService getWxMaService(){
        return wxMaService;
    }*/

}
