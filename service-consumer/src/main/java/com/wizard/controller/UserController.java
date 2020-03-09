package com.wizard.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wizard.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("user")
@DefaultProperties(defaultFallback = "defaultFallback")//定义全局熔断方法名
public class UserController {
    //原始方式
    @Autowired
    private RestTemplate restTemplate;

    //eureka方式 注意別导错包 org.springframework.cloud.client.discovery.DiscoveryClient;
    //@Autowired
    //private DiscoveryClient discoveryClient;

    @GetMapping
    @ResponseBody
    //@HystrixCommand(fallbackMethod = "queryUserByIdFallback")//定义局部熔断方法，参数列表和返回值必须一致
    @HystrixCommand //设置全局熔断也需要这个定义在方法上，并不是每个方法都需要熔断
    public String queryUserById(@RequestParam(value="id",defaultValue="0") Long id) {
        //原始方式
        //return this.restTemplate.getForObject("http://localhost:8081/provider/user/"+id, User.class);

        //eureka方式
        //List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        //ServiceInstance instance = instances.get(0);
        //return this.restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/provider/user/"+id, User.class);

        //ribbon方式
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.restTemplate.getForObject("http://service-provider/provider/user/"+id, String.class);


    }

    public String queryUserByIdFallback(Long id) {
        return "服务器正忙，请稍后再试";
    }

    public String defaultFallback(){
        return "服务器正忙，请稍后再试";
    }
}
