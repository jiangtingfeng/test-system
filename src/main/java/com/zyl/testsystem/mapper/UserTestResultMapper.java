package com.zyl.testsystem.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyl.testsystem.entity.UserTestResult;
import com.zyl.testsystem.vo.UserTestResultVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * InnoDB free: 48128 kB Mapper 接口
 * </p>
 *
 * @author tingfeng.jiang
 * @since 2020-06-21
 */
public interface UserTestResultMapper extends BaseMapper<UserTestResult> {

    @Select("select *from t_user_info tui inner join t_user_test_result tutr on tui.id = tutr.user_id where tutr.type = #{type}")
    List<UserTestResultVO> getList(@Param("type") Integer type);
}
