package com.zyl.testsystem.service;

import com.zyl.testsystem.entity.UserInfo;
import com.zyl.testsystem.entity.UserLearnTest;
import com.zyl.testsystem.entity.UserTestResult;
import com.zyl.testsystem.enums.AnswerTypeEnum;
import com.zyl.testsystem.enums.DownloadDataTypeEnum;
import com.zyl.testsystem.enums.PartEnum;
import com.zyl.testsystem.util.ExcelUtils;
import com.zyl.testsystem.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/21/021
 */
@Slf4j
@Service
public class ExcelService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserLearnTestService userLearnTestService;

    @Autowired
    private UserLearnImageTimeService userLearnImageTimeService;

    @Autowired
    private UserTestResultService userTestResultService;

    /**
     * @Description: 五种方式下载Excel数据  5个方法实现
     * @Author: jiangtingfeng
     * 1.获取相应表中的数据
     * 2.使用LinkedHashMap指定表列名和数据的关系
     * 3.向客户端写出流
     * 4.客户端完成下载Excel数据功能
     */
    public boolean downloadResource(String type, HttpServletResponse response) throws Exception {
        if (type.equals(DownloadDataTypeEnum.DOWNLOAD_DATA_TYPE_ONE.getValue())) {
            List<UserInfo> list = userInfoService.list();
            if (!CollectionUtils.isEmpty(list)){
                downloadUserInfo(list,response);
                return true;
            } else {
                log.info("无被试数据！");
                return false;
            }
        }else if (type.equals(DownloadDataTypeEnum.DOWNLOAD_DATA_TYPE_TWO.getValue())) {
            List<UserLearnTestVO> list = userLearnTestService.getList();
            if (!CollectionUtils.isEmpty(list)){
                downloadUserLearnTest(list,response);
                return true;
            } else {
                log.info("无被试学习和测试图片数据！");
                return false;
            }
        }else if (type.equals(DownloadDataTypeEnum.DOWNLOAD_DATA_TYPE_THREE.getValue())) {
            List<UserLearnImageTimeVO> list = userLearnImageTimeService.getList(PartEnum.PART_ONE.getValue());
            if (!CollectionUtils.isEmpty(list)){
                downloadUserLearnImageTime(list,response,PartEnum.PART_ONE.getValue());
                return true;
            } else {
                log.info("无被试学习每张图片的时间数据！");
                return false;
            }
        }else if (type.equals(DownloadDataTypeEnum.DOWNLOAD_DATA_TYPE_FOUR.getValue())) {
            List<UserLearnImageTimeVO> list = userLearnImageTimeService.getList(PartEnum.PART_TWO.getValue());
            if (!CollectionUtils.isEmpty(list)){
                downloadUserLearnImageTime(list,response,PartEnum.PART_TWO.getValue());
                return true;
            } else {
                log.info("无被试测试每张图片的时间数据！");
                return false;
            }
        }else if (type.equals(DownloadDataTypeEnum.DOWNLOAD_DATA_TYPE_FIVE.getValue())) {
            List<UserTestResultVO> list = userTestResultService.getlist(AnswerTypeEnum.PART_ONE.getValue());
            if (!CollectionUtils.isEmpty(list)){
                downloadUserTestResult(list,response,AnswerTypeEnum.PART_ONE.getValue());
                return true;
            } else {
                log.info("无被试测试结果数据！(正确图片)");
                return false;
            }
        } else if (type.equals(DownloadDataTypeEnum.DOWNLOAD_DATA_TYPE_SIX.getValue())){
            List<UserTestResultVO> list = userTestResultService.getlist(AnswerTypeEnum.PART_TWO.getValue());
            if (!CollectionUtils.isEmpty(list)){
                downloadUserTestResult(list,response,AnswerTypeEnum.PART_TWO.getValue());
                return true;
            } else {
                log.info("无被试测试结果数据！(错误图片)");
                return false;
            }
        } else if(type.equals(DownloadDataTypeEnum.DOWNLOAD_DATA_TYPE_SEVEN.getValue())) {
            List<TotalExcelVO> list = userInfoService.getList();
            if (!CollectionUtils.isEmpty(list)){
                downloadTotalExcel(list,response,AnswerTypeEnum.PART_TWO.getValue());
                return true;
            } else {
                log.info("无被试测试结果数据！(错误图片)");
                return false;
            }
        } else if(type.equals(DownloadDataTypeEnum.DOWNLOAD_DATA_TYPE_EIGHT.getValue())) {
            List<UserLearnImageTimeVO> list = userLearnImageTimeService.getList(PartEnum.PART_ONE.getValue());
            list.forEach(l->{
                String[] split = l.getImageUrl().split("/");
                String imageName = getImage(split[2]);
                l.setImageUrl(imageName);
            });
            if (!CollectionUtils.isEmpty(list)){
                downloadLearnExcel(list,response,AnswerTypeEnum.PART_TWO.getValue());
                return true;
            } else {
                log.info("无被试测试结果数据！(错误图片)");
                return false;
            }
        } else if(type.equals(DownloadDataTypeEnum.DOWNLOAD_DATA_TYPE_NINE.getValue())) {
            List<UserLearnImageTimeVO> list = userLearnImageTimeService.getList(PartEnum.PART_TWO.getValue());
            List<TestStageVo> stageVoList = new ArrayList<>(list.size());
            if (!CollectionUtils.isEmpty(list)){
                list.forEach(l->{
                    TestStageVo testStageVo = new TestStageVo();
                    testStageVo.setId(l.getUserId());
                    UserTestResult right = userTestResultService.getByUserId(l.getUserId(),AnswerTypeEnum.PART_ONE.getValue());
                    UserTestResult wrong = userTestResultService.getByUserId(l.getUserId(),AnswerTypeEnum.PART_TWO.getValue());
                    String[] split = l.getImageUrl().split("/");
                    String imageName =  split[2];
                    String numberString = split[2].substring(0, imageName.indexOf("."));
                    Integer number = Integer.parseInt(numberString);
                    if (number > 850) {
                        getResult(wrong,testStageVo,imageName);
                    } else {
                        getResult(right,testStageVo,imageName);
                    }
                    testStageVo.setImageName(getImage(imageName));
                    testStageVo.setTime(l.getLearnTime());
                    stageVoList.add(testStageVo);
                });
                downloadTestExcel(stageVoList,response);
                return true;
            } else {
                log.info("无被试测试结果数据！(错误图片)");
                return false;
            }
        } else {
            return false;
        }
    }


    public void getResult(UserTestResult right,TestStageVo testStageVo,String imageName) {
        String rightImage = right.getRightImage();
        UserLearnTest userLearnTest = userLearnTestService.getByUserId(right.getUserId());
        String userLearnImageName = userLearnTest.getUserLearnImageName();
        if (rightImage.contains(imageName)) {
            testStageVo.setResult("正确");
            if (userLearnImageName.contains(imageName)) {
                testStageVo.setTrueChoose("见过");
                testStageVo.setChoose("见过");
            } else {
                testStageVo.setTrueChoose("没见过");
                testStageVo.setChoose("没见过");
            }
        } else {
            testStageVo.setResult("错误");
            if (userLearnImageName.contains(imageName)) {
                testStageVo.setTrueChoose("见过");
                testStageVo.setChoose("没见过");
            } else {
                testStageVo.setTrueChoose("没见过");
                testStageVo.setChoose("见过");
            }
        }
    }

    public String getImage(String imageName) {
        if (imageName.length() < 7) {
            int i = 7 - imageName.length();
            StringBuilder stringBuilder = new StringBuilder();
            for (int j=0; j<i; j++) {
                stringBuilder.append("0");
            }
            stringBuilder.append(imageName);
            return stringBuilder.toString();
        } else {
            return imageName;
        }
    }


    private void downloadTestExcel(List<TestStageVo> stageVoList, HttpServletResponse response) throws Exception {
        Map<String, String> topMap = new LinkedHashMap<>();
        topMap.put("id","被试编号");
        topMap.put("imageName","图片名称");
        topMap.put("choose","被试选择");
        topMap.put("trueChoose","正确选择");
        topMap.put("result","结果");
        topMap.put("time","反应时(ms)");
        String excelName = "每个被试的测试阶段分表";
        ExcelUtils.listToExcel(stageVoList, topMap, excelName, response);
    }

    private void downloadLearnExcel(List<UserLearnImageTimeVO> list, HttpServletResponse response, Integer value) throws Exception {
        Map<String, String> topMap = new LinkedHashMap<>();
        topMap.put("userId","被试编号");
        topMap.put("imageUrl","图片名称");
        topMap.put("learnTime","被试按键反应时(ms)");
        String excelName = "每个被试的学习阶段分表";
        ExcelUtils.listToExcel(list, topMap, excelName, response);
    }

    private void downloadTotalExcel(List<TotalExcelVO> list, HttpServletResponse response, Integer value) throws Exception {
        Map<String, String> topMap = new LinkedHashMap<>();
        topMap.put("id","被试编号");
        topMap.put("groupNumber","分组号");
        topMap.put("name","姓名");
        topMap.put("sex","性别");
        topMap.put("age","年龄");
        topMap.put("rightRate","总正确率（%）（保留2位小数）存在的图片");
        topMap.put("wrongRate","总正确率（%）（保留2位小数）不存在的图片");
        String  excelName = "总表";
        ExcelUtils.listToExcel(list, topMap, excelName, response);
    }

    private void downloadUserTestResult(List<UserTestResultVO> list, HttpServletResponse response,Integer type) throws Exception {
        Map<String, String> topMap = new LinkedHashMap<>();
        topMap.put("id","序号");
        topMap.put("userId","用户序号");
        topMap.put("name","用户名称");
        topMap.put("rightImage","回答正确的图片信息");
        topMap.put("wrongImage","回答错误的图片信息");
        topMap.put("rightRate","正确率（%）（保留2位小数）");
        topMap.put("createTime","创建时间");
        topMap.put("updateTime","更新时间");
        String excelName = "";
        if (type == 1) {
            excelName = "被试测试结果数据(正确图片)";
        } else {
            excelName = "被试测试结果数据(错误图片)";
        }
        ExcelUtils.listToExcel(list, topMap, excelName, response);
    }



    private void downloadUserLearnImageTime(List<UserLearnImageTimeVO> list, HttpServletResponse response,Integer type) throws Exception {
        Map<String, String> topMap = new LinkedHashMap<>();
        topMap.put("id","序号");
        topMap.put("userId","用户序号");
        topMap.put("name","用户名称");
        topMap.put("imageUrl","学习图片的url");
        topMap.put("learnTime","学习图片的时间（毫秒）");
        topMap.put("createTime","创建时间");
        topMap.put("updateTime","更新时间");
        String excelName = "";
        if (type == 1) {
            excelName = "被试学习每张图片的时间数据";
        } else {
            excelName = "被试测试每张图片的时间数据";
        }
        ExcelUtils.listToExcel(list, topMap, excelName, response);
    }

    private void downloadUserLearnTest(List<UserLearnTestVO> list, HttpServletResponse response) throws Exception {
        Map<String, String> topMap = new LinkedHashMap<>();
        topMap.put("id","序号");
        topMap.put("userId","用户序号");
        topMap.put("name","用户名称");
        topMap.put("userLearnImageName","学习图片名称字符串");
        topMap.put("userLearnImage","学习图片路径字符串");
        topMap.put("userTestImageName","测试图片名称字符串");
        topMap.put("userTestImage","测试图片路径字符串");
        topMap.put("groupNumber","分组信息（用户选择的分组）");
        topMap.put("createTime","创建时间");
        topMap.put("updateTime","更新时间");
        String excelName = "被试学习和测试图片数据";
        ExcelUtils.listToExcel(list, topMap, excelName, response);
    }

    private void downloadUserInfo(List<UserInfo> list,HttpServletResponse response) throws Exception {
        Map<String, String> topMap = new LinkedHashMap<>();
        topMap.put("id","序号");
        topMap.put("name","姓名");
        topMap.put("sex","性别");
        topMap.put("age","年龄");
        topMap.put("createTime","创建时间");
        topMap.put("updateTime","更新时间");
        String excelName = "被试信息数据";
        ExcelUtils.listToExcel(list, topMap, excelName, response);
    }

}
