package com.potoyang.learn.securityoauth2.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/5/20 09:41
 * Modified:
 * Description:
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
