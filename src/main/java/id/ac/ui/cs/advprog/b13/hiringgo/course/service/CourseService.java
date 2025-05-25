package id.ac.ui.cs.advprog.b13.hiringgo.course.service;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface CourseService {
    List<Course> getAll(); // tetap dipertahankan
    CompletableFuture<List<Course>> getAllAsync(); // async version
    Course create(Course mk);
    Course update(UUID id, Course mk);
    void delete(UUID id);
    Course getById(UUID id);
}
