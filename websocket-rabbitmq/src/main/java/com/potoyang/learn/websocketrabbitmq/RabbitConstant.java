package com.potoyang.learn.websocketrabbitmq;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/8 15:45
 * Modified By:
 * Description:
 */
public class RabbitConstant {
    /**
     * 交换机名称
     */
    public final static String EXCHANGE = "exchange_test";
    /**
     * 队列
     */
    public final static String QUEUE_TRANSACTION = "queue_transaction";
    public final static String QUEUE_CONTRACT = "queue_contract";
    public final static String QUEUE_QUALIFICATION = "queue_qualification";
    /**
     * 路由key
     */
    public final static String RK_TRANSACTION = "transaction";
    public final static String RK_CONTRACT = "contract";
    public final static String RK_QUALIFICATION = "qualification";
}
