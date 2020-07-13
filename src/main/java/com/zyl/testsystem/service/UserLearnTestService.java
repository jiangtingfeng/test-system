package com.zyl.testsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyl.testsystem.common.AppConstat;
import com.zyl.testsystem.entity.UserLearnTest;
import com.zyl.testsystem.mapper.UserLearnTestMapper;
import com.zyl.testsystem.vo.GroupListVO;
import com.zyl.testsystem.vo.ImageVO;
import com.zyl.testsystem.vo.UserLearnTestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/16/016
 */
@Service
public class UserLearnTestService extends ServiceImpl<UserLearnTestMapper, UserLearnTest> {
    @Autowired
    private UserLearnTestMapper userLearnTestMapper;

    ObjectMapper objectMapper = new ObjectMapper();

    public boolean add(GroupListVO group, HttpServletRequest req,String groupNumber) throws JsonProcessingException {
        //redisUtil.get()
        Long userId = (Long)req.getSession().getAttribute(AppConstat.USER_ID);
        List<UserLearnTest> userLearnTests = getUserLearnTest(userId);
        if (!CollectionUtils.isEmpty(userLearnTests)) {
            return true;
        }
        group.setUserId(userId);
        String imageTestListString = objectMapper.writeValueAsString(group.getImageTestList());
        //String imageTestListString = JSONArray.toJSON(group.getImageTestList()).toString();
        String imageLearnListString = objectMapper.writeValueAsString(group.getImageLearnList());
        //String imageLearnListString = JSONArray.toJSON(group.getImageLearnList()).toString();
        UserLearnTest userLearnTest = new UserLearnTest();
        String imageLearnNameListString = objectMapper.writeValueAsString(group.getImageLearnNameList());
        String imageTestNameListString = objectMapper.writeValueAsString(group.getImageTestNameList());
        userLearnTest.setUserId(userId);
        userLearnTest.setGroupNumber(groupNumber);
        userLearnTest.setUserTestImage(imageTestListString);
        userLearnTest.setUserLearnImage(imageLearnListString);
        userLearnTest.setUserLearnImageName(imageLearnNameListString);
        userLearnTest.setUserTestImageName(imageTestNameListString);
        int insert = userLearnTestMapper.insert(userLearnTest);
        if (insert != 1) {
            return false;
        }
        return true;
    }

    public ImageVO getGroup(HttpServletRequest req, Integer number, String type) throws IOException {
        Long userId = (Long)req.getSession().getAttribute(AppConstat.USER_ID);
        List<UserLearnTest> userLearnTestList = getUserLearnTest(userId);
        ImageVO imageLearnVO = toImageLearnVO(userId, userLearnTestList,number,type);
        imageLearnVO.setUserId(userId);
        return imageLearnVO;
    }


    /**
     * @Description: 这个是学习图片对象返回
     * @Author: jiangtingfeng
     */
    public List<ImageVO> getGroupList(HttpServletRequest req,Integer step) throws IOException {
        Long userId = (Long)req.getSession().getAttribute(AppConstat.USER_ID);
        List<UserLearnTest> userLearnTestList = getUserLearnTest(userId);
        if (CollectionUtils.isEmpty(userLearnTestList)) {
            return null;
        }
        UserLearnTest userLearnTest = userLearnTestList.get(0);
        List<String> imageList = null;
        if (step == 1) {
            imageList = objectMapper.readValue(userLearnTest.getUserLearnImage(), new TypeReference<List<String>>() {});
        } else {
            imageList = objectMapper.readValue(userLearnTest.getUserTestImage(), new TypeReference<List<String>>() {});
        }
        int i = 1;
        List<ImageVO> imageVOS = new ArrayList<>(imageList.size());
        for (String s:imageList) {
            ImageVO imageVO = new ImageVO();
            imageVO.setNumber(i++);
            imageVO.setImageUrl(s);
            imageVOS.add(imageVO);
        }
        return imageVOS;
    }


    public List<ImageVO> getGroupListByUserId(Long userId, Integer step) throws IOException {
        List<UserLearnTest> userLearnTestList = getUserLearnTest(userId);
        if (CollectionUtils.isEmpty(userLearnTestList)) {
            return null;
        }
        UserLearnTest userLearnTest = userLearnTestList.get(0);
        List<String> imageList = null;
        if (step == 1) {
            imageList = objectMapper.readValue(userLearnTest.getUserLearnImage(), new TypeReference<List<String>>() {});
        } else {
            imageList = objectMapper.readValue(userLearnTest.getUserTestImage(), new TypeReference<List<String>>() {});
        }
        int i = 1;
        List<ImageVO> imageVOS = new ArrayList<>(imageList.size());
        for (String s:imageList) {
            ImageVO imageVO = new ImageVO();
            imageVO.setNumber(i++);
            imageVO.setImageUrl(s);
            imageVOS.add(imageVO);
        }
        return imageVOS;
    }




    private ImageVO toImageLearnVO(Long userId, List<UserLearnTest> userLearnTestList, Integer number,String type) throws IOException {
        ImageVO imageLearnVO = new ImageVO();
        UserLearnTest userLearnTest = userLearnTestList.get(0);
        List<String> imageList = null;
        //显示第一章图片
        if ("1".equals(type)) {
            imageList = objectMapper.readValue(userLearnTest.getUserLearnImage(), new TypeReference<List<String>>(){});
            imageLearnVO.setUserId(userId);
            imageLearnVO.setImageUrl(imageList.get(number));
        } else {
            imageList = objectMapper.readValue(userLearnTest.getUserTestImage(), new TypeReference<List<String>>(){});
            imageLearnVO.setUserId(userId);
            imageLearnVO.setImageUrl(imageList.get(number));
        }
        return imageLearnVO;
    }


    public List<UserLearnTest> getUserLearnTest(Long userId){
        QueryWrapper<UserLearnTest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_Id",userId);
        List<UserLearnTest> userLearnTests = userLearnTestMapper.selectList(queryWrapper);
        return userLearnTests;
    }

    public UserLearnTest hasGroup(HttpServletRequest req) {
        Long userId = (Long)req.getSession().getAttribute(AppConstat.USER_ID);
        List<UserLearnTest> userLearnTest = getUserLearnTest(userId);
        if (!CollectionUtils.isEmpty(userLearnTest)) {
            return userLearnTest.get(0);
        }else {
            return null;
        }
    }


    public List<UserLearnTestVO> getList() {
        return userLearnTestMapper.getList();
    }

    public UserLearnTest getByUserId(Long userId) {
        QueryWrapper<UserLearnTest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserLearnTest> userLearnTests = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(userLearnTests)) {
            return null;
        }
        return userLearnTests.get(0);
    }
}
