package id.ac.ui.cs.advprog.b13.hiringgo.course.controller;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import id.ac.ui.cs.advprog.b13.hiringgo.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import java.util.List;

@RestController
@RequestMapping("/admin/matakuliah")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping("/list")
    public ResponseEntity<List<Course>> getAll() {
        List<Course> listCourse = service.getAll();
        return ResponseEntity.status(200).body(listCourse);
    }

    @PostMapping("/add")
    public ResponseEntity<Course> create(@RequestBody Course mk) {
        Course course = service.create(mk);
        return ResponseEntity.status(201).body(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable UUID id, @RequestBody Course mk) {
        Course updatedCourse = service.update(id, mk);
        return ResponseEntity.status(201).body(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable UUID id) {
        Course course = service.getById(id);
        return ResponseEntity.status(200).body(course);
    }

    @GetMapping("/async")
    public CompletableFuture<List<Course>> getAllAsync() {
        return service.getAllAsync().thenApplyAsync(list -> list);
    }
}
