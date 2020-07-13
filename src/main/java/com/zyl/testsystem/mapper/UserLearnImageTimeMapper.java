package com.zyl.testsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyl.testsystem.entity.UserLearnImageTime;
import com.zyl.testsystem.vo.UserLearnImageTimeVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * InnoDB free: 48128 kB Mapper 接口
 * </p>
 *
 * @author tingfeng.jiang
 * @since 2020-06-17
 */
public interface UserLearnImageTimeMapper extends BaseMapper<UserLearnImageTime> {

    @Insert("<script>" +
            "insert into t_user_learn_image_time (user_id,image_url,learn_time,type,create_time,update_time) values " +
            "<foreach collection='list' item='obj' separator=','>" +
                "(#{obj.userId},#{obj.imageUrl},#{obj.learnTime},#{obj.type},#{obj.createTime},#{obj.updateTime})" +
            "</foreach>" +
            "</script>")
    Integer insertBatch(@Param("list")List<UserLearnImageTime> list);

    @Select("select *from t_user_info tui inner join t_user_learn_image_time tulit on tui.id = tulit.user_id where tulit.type = #{step}")
    List<UserLearnImageTimeVO> getList(@Param("step")Integer step);
}
