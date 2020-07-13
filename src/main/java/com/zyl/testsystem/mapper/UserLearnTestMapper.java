package com.zyl.testsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyl.testsystem.entity.UserLearnTest;
import com.zyl.testsystem.vo.UserLearnTestVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * InnoDB free: 48128 kB Mapper 接口
 * </p>
 *
 * @author tingfeng.jiang
 * @since 2020-06-16
 */
public interface UserLearnTestMapper extends BaseMapper<UserLearnTest> {

    @Select("select tui.name name,tult.* from t_user_info tui inner join t_user_learn_test tult on tui.id = tult.user_id")
    List<UserLearnTestVO> getList();

}
