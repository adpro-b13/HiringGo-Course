package id.ac.ui.cs.advprog.b13.hiringgo.course.service;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import id.ac.ui.cs.advprog.b13.hiringgo.course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course createCourse(Course course) {
        course.setId(UUID.randomUUID().toString());
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(String id, Course updatedCourse) {
        updatedCourse.setId(id);
        return courseRepository.save(updatedCourse);
    }

    public void deleteCourse(String id) {
        courseRepository.delete(id);
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }
}
