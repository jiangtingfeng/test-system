package com.zyl.testsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zyl.testsystem.common.AppConstat;
import com.zyl.testsystem.entity.UserLearnTest;
import com.zyl.testsystem.service.UserInfoService;
import com.zyl.testsystem.service.UserLearnTestService;
import com.zyl.testsystem.vo.BaseException;
import com.zyl.testsystem.vo.RichResult;
import com.zyl.testsystem.vo.UserInfoVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jiangtingfeng
 * @description 用户信息控制层
 * @date 2020/6/14/014
 */
@Controller
@Slf4j
@RequestMapping(AppConstat.PATH_URL+"/user")
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserLearnTestService userLearnTestService;

    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(name = "super-token", value = "token", required = true, paramType = "header")
    public RichResult add(@RequestBody UserInfoVO userInfoVO, HttpServletRequest req) throws BaseException, Exception {
        boolean add = userInfoService.add(userInfoVO,req);
        return new RichResult(add);
    }

    @GetMapping("/get")
    @ResponseBody
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParam(name = "super-token", value = "token", required = true, paramType = "header")
    public RichResult get(@RequestParam("id") String id) throws BaseException {
        UserInfoVO userInfoVO = userInfoService.get(id);
        return new RichResult(userInfoVO);
    }

    @GetMapping("/selectTrail")
    @ApiOperation(value = "选择分组")
    public String test(HttpServletRequest req, Model model) throws BaseException {
        //判断该用户是否已经选择分组
        UserLearnTest userLearnTest = userLearnTestService.hasGroup(req);
        if (userLearnTest != null) {
             model.addAttribute("msg","你已经选者了相应的分组"+userLearnTest.getGroupNumber());
             return "studyPrepare";
        }
        return "selectTrial";
    }

    @GetMapping("/error")
    @ApiOperation(value = "同意错误页面")
    public String error() throws BaseException {
        return "error";
    }
}
