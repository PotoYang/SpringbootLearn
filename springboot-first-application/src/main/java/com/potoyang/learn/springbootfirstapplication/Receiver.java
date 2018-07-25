package com.potoyang.learn.springbootfirstapplication;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/25 15:14
 * Modified By:
 * Description:
 */
@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message){
        System.out.println("Receiced <"+message+">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
