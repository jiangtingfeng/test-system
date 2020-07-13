package com.zyl.testsystem.controller;

import com.zyl.testsystem.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * @author jiangtingfeng
 * @description  导出Excle表
 * @date 2020/6/21/021
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/")
    @ApiOperation("跳转到excel页面")
    public String excel(){
        return "redirect:/excel.html";
    }


    @GetMapping("/get")
    @ApiOperation("跳转到excel页面")
    public String get(@RequestParam("type") String type, HttpServletResponse response, Model model) throws Exception{
        //可以添加获取列表数据的条件
        boolean b = excelService.downloadResource(type, response);
        if (!b) {
            model.addAttribute("msg","无数据进行导出！");
        }
        return "excel";
    }

}
