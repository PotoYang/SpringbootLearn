package com.potoyang.learn.elk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/27 16:26
 * Modified By:
 * Description:
 */
@Component
public class ScheduleJob {

    private static Logger logger = LoggerFactory.getLogger(ScheduleJob.class);

    @Scheduled(cron = "0/2 * * * * ?")
    public void TestELK() {
        logger.info("this is info");
    }
}
