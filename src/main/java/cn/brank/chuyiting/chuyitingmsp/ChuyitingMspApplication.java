package cn.brank.chuyiting.chuyitingmsp;

import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableServiceComb
public class ChuyitingMspApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChuyitingMspApplication.class, args);
    }

}
