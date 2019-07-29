package com.potoyang.learn.securityoauth2.mapper;


import com.potoyang.learn.securityoauth2.config.BaseMapper;
import com.potoyang.learn.securityoauth2.entity.Client;
import org.springframework.stereotype.Repository;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/5/17 11:10
 * Modified:
 * Description:
 */
@MapperScan
@Repository
public interface ClientMapper extends BaseMapper<Client> {
    Client selectOneByClientId(String clientId);
}
