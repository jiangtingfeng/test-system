package com.zyl.testsystem.vo;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * InnoDB free: 48128 kB
 * </p>
 *
 * @author tingfeng.jiang
 * @since 2020-06-21
 */
@Data
public class UserTestResultVO implements Serializable {
    private Long id;
    private Long userId;
    private String name;
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
