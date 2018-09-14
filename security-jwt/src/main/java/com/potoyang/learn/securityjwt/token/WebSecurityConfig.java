package com.potoyang.learn.securityjwt.token;

import com.potoyang.learn.securityjwt.security.ExampleFilterSecurityInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/4 10:30
 * Modified By:
 * Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String TAG = WebSecurityConfig.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private ExampleFilterSecurityInterceptor exampleFilterSecurityInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.info(TAG + " enter configure,cardCouponsFilterSecurityInterceptor==null: "
                + (exampleFilterSecurityInterceptor == null));
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exampleFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

    @Override
    public void configure(WebSecurity web) {
        // 请求放行配置
        web.ignoring().antMatchers("/", "/error", "/static/css/**", "/static/fonts/**",
                "/static/js/**", "/static/config.js", "/static/index.html",
                "/index.html", "/swagger-resources/**", "/swagger-ui.html",
                "/swagger**", "/v2/api-docs", "/configuration/ui",
                "/configuration/security", "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        LOGGER.info(TAG + " enter configure");
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
