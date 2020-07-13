package com.zyl.testsystem.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jiangtingfeng
 * @description 总表
 * @date 2020/6/26/026
 */
@Data
public class TotalExcelVO {
    @ApiModelProperty("被试编号")
    private Long id;
    @ApiModelProperty("分组号")
    private String groupNumber;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("总正确率")
    private BigDecimal rightRate;
    @ApiModelProperty("错误图片总正确率")
    private BigDecimal wrongRate;
}
