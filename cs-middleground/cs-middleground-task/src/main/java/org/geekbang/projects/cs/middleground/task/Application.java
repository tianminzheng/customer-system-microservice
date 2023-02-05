package org.geekbang.projects.cs.middleground.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.geekbang.projects.cs")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
