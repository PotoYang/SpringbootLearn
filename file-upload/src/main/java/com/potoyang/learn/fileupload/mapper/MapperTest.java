package com.potoyang.learn.fileupload.mapper;

import com.potoyang.learn.fileupload.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/25 15:03
 * Modified By:
 * Description:
 */
@Mapper
public interface MapperTest {
    @Select("select * from user_info")
    List<UserInfo> findAllUser();

}
