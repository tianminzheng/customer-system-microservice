package org.geekbang.projects.cs.frontend.chat;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication(scanBasePackages = {"org.geekbang.projects.cs.frontend.chat.*", "org.geekbang.projects.cs.security.*"})
@MapperScan("org.geekbang.projects.cs.frontend.chat.mapper")
@EnableJpaRepositories(basePackages = "org.geekbang.projects.cs.security.repository")
@EntityScan(basePackages = "org.geekbang.projects.cs.security.domain")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
