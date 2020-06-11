package com.seatrain.bettersecondskill.function.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
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

    Watcher watcher;

    try {
      zooKeeper = new ZooKeeper(connectString, timeout, new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
          if (KeeperState.SyncConnected == watchedEvent.getState()) {
            System.out.println("【初始化ZooKeeper连接成功....】");
          }
        }
      });
    } catch (Exception e) {
      log.error("初始化ZooKeeper连接异常....】={}", e);
    }

    return zooKeeper;
  }

}
