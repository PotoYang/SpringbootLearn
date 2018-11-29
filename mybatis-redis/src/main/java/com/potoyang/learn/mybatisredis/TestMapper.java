package com.potoyang.learn.mybatisredis;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/20 11:54
 * Modified:
 * Description:
 */
@Mapper
public interface TestMapper {
    List<Test> selectAll();

    Test selectTestById(Integer id);
}
