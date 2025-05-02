package id.ac.ui.cs.advprog.b13.hiringgo.course.repository;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> findAll();
    Optional<Course> findById(Long id);
    Optional<Course> findByCourseCode(String courseCode);
    List<Course> findByLecturerNip(String lecturerNip);
    Course save(Course course);
    void delete(Course course);
    boolean existsByCourseCode(String courseCode);
}