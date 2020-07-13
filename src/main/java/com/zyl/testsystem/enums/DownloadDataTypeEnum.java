package com.zyl.testsystem.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/21/021
 */

public enum DownloadDataTypeEnum implements IEnum<String> {

    DOWNLOAD_DATA_TYPE_ONE("1","导出用户信息"),
    DOWNLOAD_DATA_TYPE_TWO("2","导出用户学习和测试图片名称"),
    DOWNLOAD_DATA_TYPE_THREE("3","导出用户学习每张图片的时间"),
    DOWNLOAD_DATA_TYPE_FOUR("4","导出用户学习每张图片的时间"),
    DOWNLOAD_DATA_TYPE_FIVE("5","导出用户测试结果数据（正确图片正确率）"),
    DOWNLOAD_DATA_TYPE_SIX("6","导出用户测试结果数据（错误图片正确率）"),
    DOWNLOAD_DATA_TYPE_SEVEN("7","总表"),
    DOWNLOAD_DATA_TYPE_EIGHT("8","每个被试的学习阶段分表"),
    DOWNLOAD_DATA_TYPE_NINE("9","每个被试的测试阶段分表");
    private String value;
    private String desc;
    @Override
    public String getValue() {
        return this.value;
    }

    DownloadDataTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
