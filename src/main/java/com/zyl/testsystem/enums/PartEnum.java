package com.zyl.testsystem.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/21/021
 */

public enum PartEnum implements IEnum<Integer> {
    PART_ONE(1,"学习阶段"),
    PART_TWO(2,"测试阶段");

    private Integer value;
    private String desc;

    @Override
    public Integer getValue() {
        return this.value;
    }

    PartEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
