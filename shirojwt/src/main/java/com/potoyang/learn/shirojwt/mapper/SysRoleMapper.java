package com.potoyang.learn.shirojwt.mapper;

import com.potoyang.learn.shirojwt.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 20:29
 * Modified By:
 * Description:
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    Set<String> selectSysRoleNameByUserName(String loginName);

    List<String> selectSysRoleNameByUserId(Integer userId);
}
