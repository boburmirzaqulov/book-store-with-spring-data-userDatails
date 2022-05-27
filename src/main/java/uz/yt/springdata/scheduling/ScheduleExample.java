package uz.yt.springdata.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
public class ScheduleExample {
    
    private final static Logger logger = LoggerFactory.getLogger(ScheduleExample.class);

    @Scheduled(cron = "* * * * * *")
    public void print() throws InterruptedException {
        logger.info("Time1: {}", new Date());
        Thread.sleep(1000);
    }

//    @Scheduled()
//    void print2() throws InterruptedException {
//        logger.info("Time2: {}", new Date());
//        Thread.sleep(1000);
//    }
//
//    @Scheduled(fixedDelay = 2000)
//    void print3() throws InterruptedException {
//        logger.info("Time3: {}", new Date());
//        Thread.sleep(1000);
//    }
//
//    @Scheduled(fixedDelay = 2000)
//    void print4() throws InterruptedException {
//        logger.info("Time4: {}", new Date());
//        Thread.sleep(1000);
//    }
//
//    @Scheduled(fixedDelay = 2000)
//    void print5() throws InterruptedException {
//        logger.info("Time5: {}", new Date());
//        Thread.sleep(1000);
//    }
//
//    @Scheduled(fixedDelay = 2000)
//    void print6() throws InterruptedException {
//        logger.info("Time6: {}", new Date());
//        Thread.sleep(1000);
//    }
}
