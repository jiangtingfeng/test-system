package com.zyl.testsystem.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * InnoDB free: 48128 kB
 * </p>
 *
 * @author tingfeng.jiang
 * @since 2020-06-16
 */
@Data
public class UserLearnTestVO implements Serializable {
    private Long id;
    @ApiModelProperty(value = "用户Id")
    private Long userId;
    @ApiModelProperty(value = "用户名称")
    private String name;
    @ApiModelProperty(value = "学习图片名称字符串")
    private String userLearnImageName;
    @ApiModelProperty(value = "学习图片路径字符串")
    private String userLearnImage;
    @ApiModelProperty(value = "测试图片名称字符串")
    private String userTestImageName;
    @ApiModelProperty(value = "测试图片路径字符串")
    private String userTestImage;
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "分组信息")
    private String groupNumber;
}
