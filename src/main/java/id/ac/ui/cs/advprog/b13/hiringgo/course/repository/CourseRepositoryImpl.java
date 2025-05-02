package id.ac.ui.cs.advprog.b13.hiringgo.course.repository;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    private final Map<Long, Course> courses = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(courses.get(id));
    }

    @Override
    public Optional<Course> findByCourseCode(String courseCode) {
        return courses.values().stream()
                .filter(course -> course.getCourseCode().equals(courseCode))
                .findFirst();
    }

    @Override
    public List<Course> findByLecturerNip(String lecturerNip) {
        return courses.values().stream()
                .filter(course -> course.getLecturers().stream()
                        .anyMatch(lecturer -> lecturer.getNip().equals(lecturerNip)))
                .collect(Collectors.toList());
    }

    @Override
    public Course save(Course course) {
        if (course.getId() == null) {
            course.setId(idCounter.incrementAndGet());
        }
        courses.put(course.getId(), course);
        return course;
    }

    @Override
    public void delete(Course course) {
        courses.remove(course.getId());
    }

    @Override
    public boolean existsByCourseCode(String courseCode) {
        return courses.values().stream()
                .anyMatch(course -> course.getCourseCode().equals(courseCode));
    }
}