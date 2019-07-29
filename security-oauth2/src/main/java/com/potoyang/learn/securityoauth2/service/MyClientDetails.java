package com.potoyang.learn.securityoauth2.service;

import com.potoyang.learn.securityoauth2.entity.Client;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @date 2019/7/2 10:55
 * Modified:
 * Description:
 */
@Data
public class MyClientDetails implements ClientDetails {
    private static final long serialVersionUID = 2726257915896702838L;

    private Client client;

    MyClientDetails(Client client) {
        this.client = client;
    }

    @Override
    public String getClientId() {
        return client.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return client.getResourceIds() == null ? new LinkedHashSet<>() :
                StringUtils.commaDelimitedListToSet(client.getResourceIds());
    }

    @Override
    public boolean isSecretRequired() {
        return client.getSecretRequired();
    }

    @Override
    public String getClientSecret() {
        return client.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return client.getScoped();
    }

    @Override
    public Set<String> getScope() {
        return client.getScope() == null ? new LinkedHashSet<>() :
                StringUtils.commaDelimitedListToSet(client.getScope());
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return client.getAuthorizedGrantTypes() == null ? new LinkedHashSet<>() :
                StringUtils.commaDelimitedListToSet(client.getAuthorizedGrantTypes());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return client.getRegisteredRedirectUri() == null ? new LinkedHashSet<>() :
                StringUtils.commaDelimitedListToSet(client.getAuthorizedGrantTypes());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return (client.getAuthorities() == null || client.getAuthorities().trim().length() <= 0) ? new ArrayList<>() :
                AuthorityUtils.commaSeparatedStringToAuthorityList(client.getAuthorities());
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return client.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return client.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return client.getAutoApprove() == null ? false : client.getAutoApprove();
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return client.getAdditionalInformation() == null ? new LinkedHashMap<>() :
                client.getAdditionalInformation();
    }
}
