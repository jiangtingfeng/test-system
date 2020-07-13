package com.zyl.testsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyl.testsystem.entity.UserInfo;
import com.zyl.testsystem.vo.TotalExcelVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * InnoDB free: 48128 kB Mapper 接口
 * </p>
 *
 * @author tingfeng.jiang
 * @since 2020-06-14
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select tui.id id,tui.name name,tui.sex sex,tui.age age,tult.group_number groupNumber,tutr.right_rate rightRate,tutrw.right_rate wrongRate from t_user_info tui " +
            " inner join t_user_learn_test tult on tui.id = tult.user_id " +
            " inner join t_user_test_result tutr on tui.id = tutr.user_id " +
            " INNER JOIN t_user_test_result tutrw on tui.id = tutrw.user_id " +
            " where tutr.type = 1 and tutrw.type =2")
    List<TotalExcelVO> getList();



}
