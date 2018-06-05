package com.msms.tmtest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TmTestApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(TmTestApplication.class, args);
    }

    @Override
    public void run(String... arg0) {
    }
}
