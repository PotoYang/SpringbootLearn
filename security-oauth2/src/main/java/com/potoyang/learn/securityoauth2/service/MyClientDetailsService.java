package com.potoyang.learn.securityoauth2.service;

import com.potoyang.learn.securityoauth2.entity.Client;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @date 2019/7/2 10:34
 * Modified:
 * Description:
 */
@Configuration
public class MyClientDetailsService implements ClientDetailsService {

    private final ClientService clientService;

    public MyClientDetailsService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientService.getById(clientId);
        if (client == null) {
            throw new ClientRegistrationException("ClientId not exist.");
        }
        return new MyClientDetails(client);
    }
}
