package com.zyl.testsystem.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyl.testsystem.dto.ImageLearnDTO;
import com.zyl.testsystem.entity.UserLearnImageTime;
import com.zyl.testsystem.mapper.UserLearnImageTimeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/17/017
 */
@Service
public class UserLearnImageTimeService extends ServiceImpl<UserLearnImageTimeMapper, UserLearnImageTime> {

    @Autowired
    private UserLearnImageTimeMapper userLearnImageTimeMapper;
    public boolean save(ImageLearnDTO imageLearnDTO) {
        UserLearnImageTime userLearnImageTime = new UserLearnImageTime();
        BeanUtils.copyProperties(imageLearnDTO,userLearnImageTime);
        userLearnImageTime.setUserId(Long.parseLong(imageLearnDTO.getUserId()));
        int insert = userLearnImageTimeMapper.insert(userLearnImageTime);
        if (insert != 1) {
            return false;
        }
        return true;
    }
}
