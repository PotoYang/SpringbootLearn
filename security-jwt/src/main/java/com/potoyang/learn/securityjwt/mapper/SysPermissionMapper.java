package com.potoyang.learn.securityjwt.mapper;

import com.potoyang.learn.securityjwt.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 20:32
 * Modified By:
 * Description:
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    /**
     * 获得permission中的所有url
     *
     * @return
     */
    List<String> selectPermissionUrlAll();

    /**
     * 获取RoleName
     *
     * @param url
     * @return
     */
    List<String> selectPermissionRoleNameByUrl(String url);

    /**
     * 根据url获取权限列表
     *
     * @param url
     * @return
     */
    SysPermission selectPermissionByUrl(String url);

    /**
     * 根据id获取权限列表
     *
     * @param id
     * @return
     */
    List<SysPermission> selectSysPermissionsByParentId(Integer id);
}
