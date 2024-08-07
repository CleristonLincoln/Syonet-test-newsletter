package br.com.syonet.newsletter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SyonetTestNewsletterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyonetTestNewsletterApplication.class, args);
    }

}
