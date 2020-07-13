package com.zyl.testsystem.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author jiangtingfeng
 * @description
 * @date 2020/6/21/021
 */
@Data
public class UserLearnImageTimeVO {

    private Long id;

    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "图片Url")
    private String imageUrl;

    @ApiModelProperty(value = "学习时长单位毫秒")
    private String learnTime;

    @ApiModelProperty(value = "1:学习阶段；2:测试阶段")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
