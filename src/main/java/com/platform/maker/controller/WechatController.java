//package com.platform.maker.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import me.chanjar.weixin.common.api.WxConsts;
//import me.chanjar.weixin.mp.api.WxMpService;
//import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/wechat")
//@Slf4j
//public class WechatController {
//
//    @Autowired
//    private WxMpService wxMpService;
//
//    @GetMapping("/authorize")
//    public void authorize(@RequestParam("returnUrl")String returnUrl) {
//        //1.配置
//        //2.调用方法
//        String url = "http://makeredu.natapp1.cc/maker/wechat/userInfo";
//        String result = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope,"")；
//    }
//}
