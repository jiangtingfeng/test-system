package com.zyl.testsystem.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/26/026
 */
@Data
public class TestStageVo {
    @ApiModelProperty("被试编号")
    private Long id;
    @ApiModelProperty("图片名称")
    private String imageName;
    @ApiModelProperty("被试选择")
    private String choose;
    @ApiModelProperty("正确选择")
    private String trueChoose;
    @ApiModelProperty("结果")
    private String result;
    @ApiModelProperty("反应时(ms)")
    private String time;
}
