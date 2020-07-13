package com.zyl.testsystem.controller;

import com.zyl.testsystem.common.AppConstat;
import com.zyl.testsystem.vo.BaseException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiangtingfeng
 * @description 全局异常
 * @date 2020/6/20/020
 */
@RestController
@RequestMapping(AppConstat.PATH_URL)
public class GlobalExceptionController {

    @GetMapping("/error")
    @ApiOperation(value = "同意错误页面")
    public String error() throws BaseException {
        return "error";
    }

}
