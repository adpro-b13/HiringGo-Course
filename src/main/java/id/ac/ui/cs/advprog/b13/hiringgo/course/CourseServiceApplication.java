package id.ac.ui.cs.advprog.b13.hiringgo.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync


public class CourseServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }

}
