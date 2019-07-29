package com.potoyang.learn.securityoauth2.entity;

import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @date 2019/7/2 10:37
 * Modified:
 * Description:
 */
@Data
public class Client implements Serializable {
    private static final long serialVersionUID = 2637836864708936506L;

    private Integer id;

    private String clientId;

    private String resourceIds;

    private Boolean secretRequired;

    private String clientSecret;

    private String originalSecret;

    private Boolean scoped;

    private String scope;

    private String authorizedGrantTypes;

    private String registeredRedirectUri;

    private String authorities;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private Boolean autoApprove;

    @Transient
    private Map<String, Object> additionalInformation;
}
