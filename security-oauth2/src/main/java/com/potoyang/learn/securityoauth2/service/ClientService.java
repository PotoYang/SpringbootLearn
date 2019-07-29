package com.potoyang.learn.securityoauth2.service;

import com.potoyang.learn.securityoauth2.entity.Client;
import com.potoyang.learn.securityoauth2.mapper.ClientMapper;
import org.springframework.stereotype.Service;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @date 2019/7/2 10:35
 * Modified:
 * Description:
 */
@Service
public class ClientService {
    private final ClientMapper clientMapper;

    public ClientService(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }


    public Client getById(String clientId) {
        return clientMapper.selectOneByClientId(clientId);
    }
}
