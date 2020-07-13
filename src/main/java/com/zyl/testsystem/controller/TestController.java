package com.zyl.testsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/20/020
 */
@Controller
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "studyStage";
    }

}
