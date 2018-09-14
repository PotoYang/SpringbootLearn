package com.potoyang.learn.shirojwt.mapper;

import com.potoyang.learn.shirojwt.entity.SysRole;
import com.potoyang.learn.shirojwt.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 20:16
 * Modified By:
 * Description:
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    int checkUserEnabled(String loginName);

    SysUser selectSysUserByLoginName(String loginName);
}