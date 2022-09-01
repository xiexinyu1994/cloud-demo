package cn.itcast.order;

import cn.itcast.feign.client.UserClient;
import cn.itcast.feign.config.FeignClientConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * order 服务启动类
 */
@EnableFeignClients(defaultConfiguration = FeignClientConfiguration.class, clients = UserClient.class)
@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * 注册远程调用实例
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 指定 服务调用的轮询规则是随机，这是针对所有服务
     * @return
     */
//    @Bean
//    public IRule iRuleRandom() {
//        return new RandomRule();
//    }

}