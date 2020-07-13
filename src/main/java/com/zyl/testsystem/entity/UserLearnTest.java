package com.zyl.testsystem.entity;

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
 * @since 2020-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_user_learn_test")
@ApiModel(value="UserLearnTest对象", description="InnoDB free: 48128 kB")
public class UserLearnTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户Id")
    private Long userId;
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
