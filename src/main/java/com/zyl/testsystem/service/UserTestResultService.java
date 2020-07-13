package com.zyl.testsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyl.testsystem.entity.UserTestResult;
import com.zyl.testsystem.mapper.UserTestResultMapper;
import com.zyl.testsystem.vo.UserTestResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/21/021
 */
@Service
public class UserTestResultService extends ServiceImpl<UserTestResultMapper, UserTestResult> {
    @Autowired
    private UserTestResultMapper userTestResultMapper;
    public List<UserTestResultVO> getlist(Integer value) {
        return userTestResultMapper.getList(value);
    }

    public UserTestResult getByUserId(Long userId,Integer type) {
        QueryWrapper<UserTestResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("type",type);
        List<UserTestResult> userTestResults = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(userTestResults)) {
            return null;
        }
        return userTestResults.get(0);
    }
}
