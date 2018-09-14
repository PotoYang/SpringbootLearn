package com.potoyang.learn.shirojwt.mapper;

import com.potoyang.learn.shirojwt.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

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
    Set<String> selectPermissionUrlByLoginName(String loginName);
}
