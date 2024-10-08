package com.edw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <pre>
 *     com.edw.Main
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 21 Mar 2023 20:07
 */
@SpringBootApplication

@ComponentScan({"com"})
@EntityScan("com")
@EnableJpaRepositories("com")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
