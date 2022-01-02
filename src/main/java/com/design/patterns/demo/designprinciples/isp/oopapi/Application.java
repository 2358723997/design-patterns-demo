package com.design.patterns.demo.designprinciples.isp.oopapi;

import com.design.patterns.demo.designprinciples.isp.oopapi.config.KafkaConfig;
import com.design.patterns.demo.designprinciples.isp.oopapi.config.MysqlConfig;
import com.design.patterns.demo.designprinciples.isp.oopapi.config.RedisConfig;
import com.design.patterns.demo.designprinciples.isp.oopapi.configsource.ConfigSource;
import com.design.patterns.demo.designprinciples.isp.oopapi.configsource.ZookeerConfigSource;
import com.design.patterns.demo.designprinciples.isp.oopapi.handler.ScheduledUpdater;
import com.design.patterns.demo.designprinciples.isp.oopapi.handler.SimpleHttpServer;

/**
 * Application类
 *
 * 背景：假设我们的项目中用到了三个外部系统：Redis、MySQL、Kafka。每个系统都对应一系列配置信息，
 *      比如地址、端口、访问超时时间等。为了在内存中存储这些配置信息，供项目中的其他模块来使用，我
 *      们分别设计实现了三个 Configuration 类：RedisConfig、MysqlConfig、KafkaConfig
 *
 * 需求：
 *      1.希望支持 Redis 和 Kafka 配置信息的热更新。所谓“热更新（hot update）”就是，如果在配
 *      置中心中更改了配置信息，我们希望在不用重启系统的情况下，能将最新的配置信息加载到内存中（也就
 *      是 RedisConfig、KafkaConfig 类中）。
 *
 *      2.监控功能需求。通过命令行来查看 Zookeeper 中的配置信息是比较麻烦的。所以，我们希望能有一
 *      种更加方便的配置信息查看方式。我们可以在项目中开发一个内嵌的 SimpleHttpServer，输出项目的
 *      配置信息到一个固定的 HTTP 地址，比如：http://127.0.0.1:2389/config 。我们只需要在浏览
 *      器中输入这个地址，就可以显示出系统的配置信息。不过，出于某些原因，我们只想暴露 MySQL 和 Redis
 *      的配置信息。
 *
 * @author wangjixue
 * @date 12/26/21 11:16 PM
 */
public class Application {

    private static ConfigSource configSource = new ZookeerConfigSource();

    private static final RedisConfig redisConfig = new RedisConfig(configSource);
    private static final KafkaConfig kafkaConfig = new KafkaConfig(configSource);
    private static final MysqlConfig mySqlConfig = new MysqlConfig(configSource);


    public static void main(String[] args) {
        ScheduledUpdater redisConfigUpdater = new ScheduledUpdater(redisConfig,300,300);
        redisConfigUpdater.run();

        ScheduledUpdater kafkaConfigUpdater = new ScheduledUpdater(kafkaConfig,60,60);
        kafkaConfigUpdater.run();

        SimpleHttpServer simpleHttpServer = new SimpleHttpServer("127.0.0.1",2389);
        simpleHttpServer.addViewer("/config",redisConfig);
        simpleHttpServer.addViewer("/config",mySqlConfig);
    }
}
