package pe.partnertech.fenosys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FenosysApplication {

    public static void main(String[] args) {
        SpringApplication.run(FenosysApplication.class, args);
    }

}
