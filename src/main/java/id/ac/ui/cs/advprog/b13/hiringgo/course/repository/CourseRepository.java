package id.ac.ui.cs.advprog.b13.hiringgo.course.repository;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CourseRepository {
    private final Map<String, Course> courseMap = new HashMap<>();

    public Course save(Course course) {
        courseMap.put(course.getId(), course);
        return course;
    }

    public List<Course> findAll() {
        return new ArrayList<>(courseMap.values());
    }

    public Optional<Course> findById(String id) {
        return Optional.ofNullable(courseMap.get(id));
    }

    public void delete(String id) {
        courseMap.remove(id);
    }
}
