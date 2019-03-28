package cn.ccwfun;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication  //标识为springBoot服务
@EnableEurekaServer //开启注册中心服务
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
        System.out.print("EurekaApplication Success----------------------->");
    }
}
