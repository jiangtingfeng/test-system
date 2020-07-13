package com.zyl.testsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zyl.testsystem.common.AppConstat;
import com.zyl.testsystem.dto.ImageLearnDTO;
import com.zyl.testsystem.dto.TimeList;
import com.zyl.testsystem.enums.PartEnum;
import com.zyl.testsystem.service.UserLearnImageTimeService;
import com.zyl.testsystem.service.UserLearnTestService;
import com.zyl.testsystem.util.DepartGroup;
import com.zyl.testsystem.vo.BaseException;
import com.zyl.testsystem.vo.GroupListVO;
import com.zyl.testsystem.vo.ImageVO;
import com.zyl.testsystem.vo.RichResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author jiangtingfeng
 * @description 控制用户选择测试组
 * @date 2020/6/16/016
 */
@Controller
@Slf4j
@RequestMapping(AppConstat.PATH_URL+"/selectGroup")
public class GroupController {

    @Autowired
    private UserLearnTestService userLearnTestService;
    @Autowired
    private UserLearnImageTimeService userLearnImageTimeService;

   /* @GetMapping("/select")
    @ResponseBody
    @ApiOperation(value = "选择分组")
    public RichResult selectGroup(@RequestParam("groupNumber") String groupNumber, HttpServletRequest req) throws BaseException, JsonProcessingException {
        //获取跳转页面的路径
        GroupListVO group = DepartGroup.getGroup(groupNumber);
           //将该用户的学习图库和测试图库保存入库
        boolean add = userLearnTestService.add(group, req,groupNumber);
        if (add) {
            return new RichResult(add);
        } else {
            return new RichResult(500, "分组选择失败！");
        }
    }*/

    @GetMapping("/studyPrepare")
    @ApiOperation(value = "准备开始测试！")
    public String studyPrepare(HttpServletRequest req,Model model) throws BaseException {
        Long userId = (Long)req.getSession().getAttribute(AppConstat.USER_ID);
        String htmlName = AppConstat.FILE_LEARN_PREFIX+userId+".html";
        model.addAttribute("htmlName",htmlName);
        return "studyPrepare";
    }

    @GetMapping("/testPrepare")
    @ApiOperation(value = "准备开始测试！")
    public String testPrepare() throws BaseException {
        return "testPrepare";
    }

    @GetMapping("/end")
    @ApiOperation(value = "准备开始测试！")
    public String end() throws BaseException {
        return "end";
    }

    @GetMapping("/relaxing")
    @ApiOperation(value = "准备开始测试！")
    public String relaxing(HttpServletRequest req,Model model) throws BaseException {
        Long userId = (Long)req.getSession().getAttribute(AppConstat.USER_ID);
        String htmlName = AppConstat.FILE_TEST_PREFIX+userId+".html";
        model.addAttribute("htmlName",htmlName);
        return "relaxing";
    }



    @GetMapping("/startTest")
    @ApiOperation(value = "开始测试！")
    public String startTest(HttpServletRequest req, Model model) throws BaseException, IOException {
        List<ImageVO> imageVOS  = userLearnTestService.getGroupList(req, PartEnum.PART_ONE.getValue());
        model.addAttribute(AppConstat.GROUP_OBJ_STRNING,imageVOS);
        return "studyStage";
    }

    @GetMapping("/startTestPartTwo")
    @ApiOperation(value = "开始测试！")
    public String startTestPartTwo(HttpServletRequest req, Model model) throws BaseException, IOException {
        List<ImageVO> imageVOS  = userLearnTestService.getGroupList(req,PartEnum.PART_TWO.getValue());
        model.addAttribute(AppConstat.GROUP_OBJ_STRNING,imageVOS);
        return "testStage";
    }

    @PostMapping("/saveLearnTime")
    @ApiOperation(value = "保存学习图片时间")
    public String saveLearnTime(HttpServletRequest req, Model model,String[] timeList) throws BaseException, IOException {
        boolean b = userLearnImageTimeService.addLearnTime(req, timeList,PartEnum.PART_ONE.getValue());
        if (b) {
            return "redirect:/relaxing.html";
        }else {
            return "error";
        }
    }


    @PostMapping("/saveTestTime")
    @ApiOperation(value = "保存人员测试图片时间")
    public String saveTestTime(HttpServletRequest req, Model model,String[] timeList,String[] answerList) throws BaseException, IOException {
        boolean b = userLearnImageTimeService.addTestTime(req, timeList ,answerList);
        if (b) {
            return "redirect:/end.html";
        }else {
            return "error";
        }
    }

    @GetMapping("/error")
    @ApiOperation(value = "同意错误页面")
    public String error() throws BaseException {
        return "error";
    }

}
