package com.potoyang.learn.fileupload.mapper;

import com.potoyang.learn.fileupload.entity.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author potoyang
 */
@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}