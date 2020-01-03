package com.potoyang.learn.shopee.http;

import com.potoyang.learn.shopee.config.MyConfig;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

@Configuration
public class HttpClientConfig {

    private final MyConfig myConfig;

    @Autowired
    public HttpClientConfig(MyConfig myConfig) {
        this.myConfig = myConfig;
    }

    @Bean
    public HttpRequestRetryHandler httpRequestRetryHandler() {
        // 请求重试
        return (exception, executionCount, context) -> {
            // 如果重试次数超过了retryTime,则不再重试请求
            if (executionCount >= myConfig.getRetryTime()) {
                return false;
            }
            // 服务端断掉客户端的连接异常
            if (exception instanceof NoHttpResponseException) {
                return true;
            }
            // time out 超时重试
            if (exception instanceof InterruptedIOException) {
                return true;
            }
            // Unknown host
            if (exception instanceof UnknownHostException) {
                return false;
            }
            // SSL handshake exception
            if (exception instanceof SSLException) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            return !(request instanceof HttpEntityEnclosingRequest);
        };
    }

    /**
     * 首先实例化一个连接池管理器，设置最大连接数、并发连接数
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        // 最大连接数
        httpClientConnectionManager.setMaxTotal(myConfig.getConnMaxTotal());
        // 并发数
        httpClientConnectionManager.setDefaultMaxPerRoute(myConfig.getMaxPerRoute());
        return httpClientConnectionManager;
    }

    /**
     * 实例化连接池，设置连接池管理器。 这里需要以参数形式注入上面实例化的连接池管理器
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(
            @Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager) {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(httpClientConnectionManager).setConnectionManagerShared(true);
        // 设置重试handler
        httpClientBuilder.setRetryHandler(httpRequestRetryHandler());

        return httpClientBuilder;
    }

    /**
     * 注入连接池，用于获取httpClient
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(
            @Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }

    /**
     * Builder是RequestConfig的一个内部类 通过RequestConfig的custom方法来获取到一个Builder对象
     * 设置builder的连接信息 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
     */
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(myConfig.getConnTimeout())
                .setConnectionRequestTimeout(myConfig.getConnRequestTimeout())
                .setSocketTimeout(myConfig.getSocketTimeout());
    }

    /**
     * 使用builder构建一个RequestConfig对象
     */
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }

    /**
     * 开一个守护线程定时清理过期和空闲的连接
     */
    @Bean
    public IdleConnectionEvictor getIdleConnectionEvictor(
            @Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager) {
        IdleConnectionEvictor evictor = new IdleConnectionEvictor(httpClientConnectionManager, 5, TimeUnit.SECONDS);
        evictor.start();
        return evictor;
    }

}
