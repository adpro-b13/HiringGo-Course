package id.ac.ui.cs.advprog.b13.hiringgo.course.controller;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import id.ac.ui.cs.advprog.b13.hiringgo.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

import java.util.List;

@RestController
@RequestMapping("/admin/matakuliah")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public List<Course> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Course create(@RequestBody Course mk) {
        return service.create(mk);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course mk) {
        return service.update(id, mk);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/async")
    public CompletableFuture<List<Course>> getAllAsync() {
    return service.getAllAsync();
    }
}
