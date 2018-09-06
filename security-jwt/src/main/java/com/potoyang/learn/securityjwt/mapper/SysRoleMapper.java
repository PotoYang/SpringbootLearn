package com.potoyang.learn.securityjwt.mapper;

import com.potoyang.learn.securityjwt.entity.SysRole;
import com.potoyang.learn.securityjwt.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    /**
     * 根据id获取SysUserRole
     *
     * @param id
     * @return
     */
    List<SysUserRole> selectByUserId(Integer id);

    /**
     * 根据id获取roleName
     *
     * @param id
     * @return
     */
    String selectRoleNameById(Integer id);
}
