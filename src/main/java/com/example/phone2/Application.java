package com.example.phone2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(ContactRepository repository) {
        return (args) -> {

            repository.save(new Contact("Jack", "Bauer", "604-788-5659"));

            for (Contact contact : repository.findAll()) {
                log.info(contact.toString());
            }
        };
    }

}
