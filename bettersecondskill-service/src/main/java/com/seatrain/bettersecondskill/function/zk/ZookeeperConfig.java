package com.seatrain.bettersecondskill.function.zk;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ZookeeperConfig {

  @Value("${zookeeper.address}")
  private String connectString;

  @Value("${zookeeper.timeout}")
  private int timeout;

  @Bean
  public ZooKeeper zkClient() {
    ZooKeeper zooKeeper = null;

    try {
      long startTime = System.currentTimeMillis();
      final CountDownLatch countDownLatch = new CountDownLatch(1);

      zooKeeper = new ZooKeeper(connectString, timeout, watchedEvent -> {
        if (KeeperState.SyncConnected == watchedEvent.getState()) {
          System.out.println("【初始化ZooKeeper连接成功....】");
          countDownLatch.countDown();
        }
      });

      countDownLatch.await();
      long endTime = System.currentTimeMillis();
      log.info("【初始化ZooKeeper连接状态....】={}, 总共花费时间={}s", zooKeeper.getState(), (endTime - startTime) / 1000);

    } catch (Exception e) {
      log.error("初始化ZooKeeper连接异常....】={}", e);
    }

    return zooKeeper;
  }

}
