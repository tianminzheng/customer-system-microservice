package org.geekbang.projects.cs.frontend.ticket;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = "org.geekbang.projects.cs.frontend.ticket.*")
@MapperScan("org.geekbang.projects.cs.frontend.ticket.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
