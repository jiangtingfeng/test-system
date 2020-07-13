package com.zyl.testsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.zyl.testsystem.common.AppConstat;
import com.zyl.testsystem.entity.UserInfo;
import com.zyl.testsystem.enums.GroupEnum;
import com.zyl.testsystem.mapper.UserInfoMapper;
import com.zyl.testsystem.util.DepartGroup;
import com.zyl.testsystem.vo.GroupListVO;
import com.zyl.testsystem.vo.TotalExcelVO;
import com.zyl.testsystem.vo.UserInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/14/014
 */
@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    private UserLearnTestService userLearnTestService;
    @Autowired
    private ThymeleafServiceImpl thymeleafService;

    @Transactional(rollbackFor = Exception.class)
    public boolean add(UserInfoVO userInfoVO,HttpServletRequest req) throws IOException {
        //判断该用户是否填写过
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",userInfoVO.getName()).eq("age",userInfoVO.getAge()).eq("sex",userInfoVO.getSex());
        List<UserInfo> userInfos = userInfoMapper.selectList(queryWrapper);
        //记录下用户的Id
        if (!CollectionUtils.isEmpty(userInfos)) {
            req.getSession().setAttribute("userId",userInfos.get(0).getId());
            return true;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoVO,userInfo);
        int insert = userInfoMapper.insert(userInfo);
        if (insert != 1) {
            return false;
        }
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(60*60);
        session.setAttribute(AppConstat.USER_ID,userInfo.getId());
        //进行分组
        //获取跳转页面的路径
        List<UserInfo> userInfoList = userInfoMapper.selectList(null);
        int number = 1;
        if (!CollectionUtils.isEmpty(userInfoList)) {
            number = userInfoList.size();
        }
        String groupNumber = getGroupNumber(number);
        GroupListVO group = DepartGroup.getGroup(groupNumber);
        group.setUserId(userInfo.getId());
        //将该用户的学习图库和测试图库保存入库
        userLearnTestService.add(group, req,groupNumber);
        //生成该用户的学习图片模板
        thymeleafService.createHtml(AppConstat.FILE_LEARN_PREFIX+userInfo.getId(),1);
        //生成该用户的测试图片模板
        thymeleafService.createHtml(AppConstat.FILE_TEST_PREFIX+userInfo.getId(),2);
        return true;
    }

    private String getGroupNumber(int number) {
        int index = number % 5;
        if (index == 1) {
            return GroupEnum.GROUP_ONE.getGroupName();
        } else if (index == 2) {
            return GroupEnum.GROUP_TWO.getGroupName();
        } else if (index == 3) {
            return GroupEnum.GROUP_THREE.getGroupName();
        } else if (index == 4) {
            return GroupEnum.GROUP_FOUR.getGroupName();
        } else if (index == 0) {
            return GroupEnum.GROUP_FIVE.getGroupName();
        } else {
            return "";
        }
    }

    public UserInfoVO get(String _id) {
        UserInfoVO userInfoVO = new UserInfoVO();
        UserInfo userInfo = userInfoMapper.selectById(Long.parseLong(_id));
        BeanUtils.copyProperties(userInfo,userInfoVO);
        return userInfoVO;
    }

    public boolean login(UserInfoVO userInfoVO,HttpServletRequest request, HttpServletResponse response) {
        //生成token
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",userInfoVO.getName());
        List<UserInfo> userInfos = userInfoMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(userInfos)) {
            return false;
        }
        HttpSession session = request.getSession();
        return true;
    }

    public List<TotalExcelVO> getList() {
        return userInfoMapper.getList();
    }
}
