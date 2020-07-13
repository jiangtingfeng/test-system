package com.zyl.testsystem.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/21/021
 */

public enum AnswerTypeEnum implements IEnum<Integer> {
    PART_ONE(1,"正确图片记录"),
    PART_TWO(2,"假图片记录");

    private Integer value;
    private String desc;

    @Override
    public Integer getValue() {
        return this.value;
    }

    AnswerTypeEnum(Integer value, String desc) {
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
