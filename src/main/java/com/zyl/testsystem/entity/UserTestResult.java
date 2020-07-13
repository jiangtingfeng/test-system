package com.zyl.testsystem.entity;


import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * InnoDB free: 48128 kB
 * </p>
 *
 * @author tingfeng.jiang
 * @since 2020-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_test_result")
@ApiModel(value="UserTestResult对象", description="InnoDB free: 48128 kB")
public class UserTestResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    @ApiModelProperty(value = "测试正确的图片字符串")
    private String rightImage;

    @ApiModelProperty(value = "回答错误的图片字符串")
    private String wrongImage;

    @ApiModelProperty(value = "正确率")
    private BigDecimal rightRate;

    @ApiModelProperty(value = "1:正确图片记录2：假图片记录")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
