package id.ac.ui.cs.advprog.b13.hiringgo.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "HiringGo - Course Management");
        return "home";
    }

    @GetMapping("/admin/courses")
    public String adminCourses(Model model) {
        model.addAttribute("pageTitle", "Admin - Course Management");
        return "admin/courses";
    }

    @GetMapping("/lecturer/dashboard")
    public String lecturerDashboard(Model model) {
        model.addAttribute("pageTitle", "Lecturer Dashboard");
        return "lecturer/dashboard";
    }
}