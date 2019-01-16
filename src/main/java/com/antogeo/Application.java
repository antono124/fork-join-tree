package com.antogeo;

import com.antogeo.pojo.Node;
import com.antogeo.service.ParseService;
import com.antogeo.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Autowired
    private TreeService treeService;

    @Autowired
    private ParseService parseService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            Node<String> root = treeService.getRoot("1");

            long startTime, stopTime, elapsedTime;

            startTime = System.currentTimeMillis();

            parseService.parseTree(root);

            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            System.out.println("");
            System.out.println("Duration without parallelization : " + elapsedTime);

            startTime = System.currentTimeMillis();

            parseService.parseTreeInParallel(root);

            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            System.out.println("");
            System.out.println("Duration with parallelization : " + elapsedTime);
        };
    }
}