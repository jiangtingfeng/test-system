package com.zyl.testsystem.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyl.testsystem.common.AppConstat;
import com.zyl.testsystem.dto.ImageLearnDTO;
import com.zyl.testsystem.entity.UserLearnImageTime;
import com.zyl.testsystem.entity.UserLearnTest;
import com.zyl.testsystem.entity.UserTestResult;
import com.zyl.testsystem.enums.AnswerEnum;
import com.zyl.testsystem.enums.AnswerTypeEnum;
import com.zyl.testsystem.enums.PartEnum;
import com.zyl.testsystem.mapper.UserLearnImageTimeMapper;
import com.zyl.testsystem.mapper.UserTestResultMapper;
import com.zyl.testsystem.util.CommonUtil;
import com.zyl.testsystem.vo.UserLearnImageTimeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/17/017
 */
@Service
public class UserLearnImageTimeService extends ServiceImpl<UserLearnImageTimeMapper, UserLearnImageTime> {

    @Autowired
    private UserLearnTestService userLearnTestService;
    @Autowired
    private UserLearnImageTimeMapper userLearnImageTimeMapper;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserTestResultMapper userTestResultMapper;

    public boolean save(ImageLearnDTO imageLearnDTO) {
        UserLearnImageTime userLearnImageTime = new UserLearnImageTime();
        BeanUtils.copyProperties(imageLearnDTO, userLearnImageTime);
        userLearnImageTime.setUserId(Long.parseLong(imageLearnDTO.getUserId()));
        int insert = userLearnImageTimeMapper.insert(userLearnImageTime);
        if (insert != 1) {
            return false;
        }
        return true;
    }

    public boolean addLearnTime(HttpServletRequest req, String[] timeList, Integer step) throws IOException {
        Long userId = (Long) req.getSession().getAttribute(AppConstat.USER_ID);
        List<UserLearnImageTime> userLearnTests = new ArrayList<>(timeList.length);
        UserLearnTest userLearnTest = userLearnTestService.getUserLearnTest(userId).get(0);
        List<String> imageList = null;
        if (step == 1) {
            imageList = objectMapper.readValue(userLearnTest.getUserLearnImage(), new TypeReference<List<String>>() {});
        } else {
            imageList = objectMapper.readValue(userLearnTest.getUserTestImage(), new TypeReference<List<String>>() {});
        }
        for (int i = 0; i < timeList.length; i++) {
            UserLearnImageTime userLearnImageTime = new UserLearnImageTime();
            userLearnImageTime.setUserId(userId);
            userLearnImageTime.setImageUrl(imageList.get(i));
            userLearnImageTime.setLearnTime(timeList[i]);
            userLearnImageTime.setCreateTime(LocalDateTime.now());
            userLearnImageTime.setUpdateTime(LocalDateTime.now());
            userLearnImageTime.setType(step);
            userLearnTests.add(userLearnImageTime);
        }
        Integer integer = userLearnImageTimeMapper.insertBatch(userLearnTests);
        if (integer <= 0) {
            return false;
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean addTestTime(HttpServletRequest req, String[] timeList, String[] answerList) throws IOException {
        //保存图片的测试时间
        boolean b = addLearnTime(req, timeList, PartEnum.PART_TWO.getValue());
        if (!b) {
            return false;
        }
        //筛选出对与错的图片
        Long userId = (Long) req.getSession().getAttribute(AppConstat.USER_ID);
        UserLearnTest userLearnTest = userLearnTestService.getUserLearnTest(userId).get(0);
        List<String> learnImage = objectMapper.readValue(userLearnTest.getUserLearnImageName(), new TypeReference<List<String>>() {
        });
        List<String> testImage = objectMapper.readValue(userLearnTest.getUserTestImage(), new TypeReference<List<String>>() {
        });
        //正的图片记录回答正确的
        List<String> trueRightImage = new ArrayList<>();
        //正的图片记录回答错误的
        List<String> trueWrongImage = new ArrayList<>();
        //假的图片记录回答正确的
        List<String> fakeRightImage = new ArrayList<>();
        //假的图片记录回答错误的
        List<String> fakeWrongImage = new ArrayList<>();
        for (int i=0; i<answerList.length; i++) {
            String imageName = CommonUtil.getImageName(testImage.get(i));
            String _index = imageName.substring(0, imageName.indexOf("."));
            Integer index = Integer.parseInt(_index);
            if (index >= AppConstat.LOCAL_FAKE_START) {
                //假的图片记录
                if (answerList[i].equals(AnswerEnum.ANSWER_NO.getValue())) {
                    fakeRightImage.add(imageName);
                }else {
                    fakeWrongImage.add(imageName);
                }
            }else {
                //真的图片记录
                if (learnImage.contains(imageName)) {
                    if (answerList[i].equals(AnswerEnum.ANSWER_YES.getValue())){
                        trueRightImage.add(imageName);
                    }else {
                        trueWrongImage.add(imageName);
                    }
                }else {
                    if (answerList[i].equals(AnswerEnum.ANSWER_NO.getValue())){
                        trueRightImage.add(imageName);
                    }else {
                        trueWrongImage.add(imageName);
                    }
                }
            }
        }
        boolean trueSave = save(userId, trueRightImage, trueWrongImage, AnswerTypeEnum.PART_ONE.getValue());
        boolean fakeSave = save(userId, fakeRightImage, fakeWrongImage, AnswerTypeEnum.PART_TWO.getValue());
        if (trueSave && fakeSave) {
            return true;
        }
        return false;
    }

    /**
     * @Description: 保存测试情况方法
     * @Author: jiangtingfeng
     */
    public boolean save(Long userId,List<String> trueRightImage,List<String> trueWrongImage,Integer answerType) throws JsonProcessingException {
        UserTestResult result =new UserTestResult();
        result.setType(answerType);
        BigDecimal rateBigDecimal = null;
        if (answerType == 1) {
            double rate = trueRightImage.size()/ Double.valueOf(AppConstat.LOCAL_TRUE_SIZE);
            BigDecimal bg = new BigDecimal(rate*100);
            rateBigDecimal = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            double rate = trueRightImage.size() / Double.valueOf(AppConstat.LOCAL_FAKE_SIZE);
            BigDecimal bg = new BigDecimal(rate*100);
            rateBigDecimal = bg.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        System.out.println(rateBigDecimal.doubleValue());
        result.setRightRate(rateBigDecimal);
        result.setUserId(userId);
        String rightImageString = objectMapper.writeValueAsString(trueRightImage);
        String wrongImageString = objectMapper.writeValueAsString(trueWrongImage);
        result.setRightImage(rightImageString);
        result.setWrongImage(wrongImageString);
        int insert = userTestResultMapper.insert(result);
        if (insert != 1) {
            return false;
        }
        return true;
    }


    public List<UserLearnImageTimeVO> getList(Integer step) {
        return userLearnImageTimeMapper.getList(step);
    }
}