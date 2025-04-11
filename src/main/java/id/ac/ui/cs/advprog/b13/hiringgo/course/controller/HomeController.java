package id.ac.ui.cs.advprog.b13.hiringgo.course.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to HiringGo-Course!";
    }
}


