package com.butch.securityzuul6101.config;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局负载均衡策略:
 * 
 * 只能注入一个，
 * 被ComponentScan扫描到就默认所有服务生效。
 * 
 * 如果想要指定服务那么就不能自动注入,得放置在自动扫描外，
 * 在启动类使用注解@RibbonClient(name = "serviceName", configuration = xxxConfig.class)注入并指定服务.
 */
@Configuration
public class RibbonGlobalLoadBalancingConfiguration {
    /**
     * 最低并发
     */
    @Bean
    public IRule bestAvailableRule(){
        return new BestAvailableRule();
    }

    //响应时间权重
    // @Bean
    public IRule weightedResponseTimeRule(){
        return new WeightedResponseTimeRule();
    }

    //轮询
    // @Bean
    public IRule roundRobinRule(){
        return new RoundRobinRule();
    }

    //随机
    // @Bean
    public IRule randomRule(){
        return new RandomRule();
    }
}