package com.potoyang.learn.securityjwt.mapper;

import com.potoyang.learn.securityjwt.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    /**
     * 根据loginName获取用户
     *
     * @param loginName
     * @return
     */
    SysUser selectByLoginName(String loginName);

    List<SysUser> selectUserByLoginNameAndUserPassword(@Param("loginName") String loginName,
                                                       @Param("userPassword") String userPassword);

    SysUser selectById(Integer id);
}
