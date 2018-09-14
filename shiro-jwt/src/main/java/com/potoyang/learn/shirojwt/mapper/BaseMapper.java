package com.potoyang.learn.shirojwt.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/10 10:44
 * Modified By:
 * Description:
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
