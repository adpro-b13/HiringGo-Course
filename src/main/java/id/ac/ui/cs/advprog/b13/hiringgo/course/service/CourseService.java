package id.ac.ui.cs.advprog.b13.hiringgo.course.service;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAll();
    Course create(Course mk);
    Course update(Long id, Course mk);
    void delete(Long id);
    Course getById(Long id);
}
