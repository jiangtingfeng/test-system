package com.zyl.testsystem.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/25/025
 */

public enum  GroupEnum implements IEnum<Integer> {

    GROUP_ONE(1,"第一组"),
    GROUP_TWO(2,"第二组"),
    GROUP_THREE(3,"第三组"),
    GROUP_FOUR(4,"第四组"),
    GROUP_FIVE(5,"第五组");

    private Integer value;
    private String groupName;

    @Override
    public Integer getValue() {
        return null;
    }

    GroupEnum(Integer value, String groupName) {
        this.value = value;
        this.groupName = groupName;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
