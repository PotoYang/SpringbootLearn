package com.potoyang.learn.unittestsample;

import com.potoyang.learn.unittestsample.model.ToDo;
import com.potoyang.learn.unittestsample.repository.ToDoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class UnitTestSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitTestSampleApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup(ToDoRepository toDoRepository) {
        return (args -> {
            toDoRepository.save(new ToDo("Remove unused imports.", true));
            toDoRepository.save(new ToDo("Clean the code.", true));
            toDoRepository.save(new ToDo("Build the artifacts.", false));
            toDoRepository.save(new ToDo("Deploy the jar file.", true));
            log.info("The sample data has been generated.");
        });
    }
}
