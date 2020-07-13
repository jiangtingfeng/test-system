package com.zyl.testsystem.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jiangtingfeng
 * @description 初始化容器
 * @date 2020/6/16/016
 */
@Data
public class GroupListVO {
    private Long userId;
    List<String> imageLearnList;
    List<String> imageTestList;
    List<String> imageLearnNameList;
    List<String> imageTestNameList;
}
