package org.ere.contest.cfredisservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CfRedisServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CfRedisServiceApplication.class, args);
    }

}
