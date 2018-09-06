package com.potoyang.learn.securityjwt.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 20:15
 * Modified By:
 * Description:
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
