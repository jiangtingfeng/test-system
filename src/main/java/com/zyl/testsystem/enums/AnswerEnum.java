package com.zyl.testsystem.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author jiangtingfeng
 * @description 是否回答
 * @date 2020/6/21/021
 */
public enum AnswerEnum implements IEnum<String> {
    ANSWER_YES("是"),
    ANSWER_NO("否");
    private String value;
    @Override
    public String getValue() {
        return this.value;
    }

    AnswerEnum(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
