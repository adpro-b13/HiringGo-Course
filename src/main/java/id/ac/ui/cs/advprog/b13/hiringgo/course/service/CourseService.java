package id.ac.ui.cs.advprog.b13.hiringgo.course.service;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Lecturer;
import id.ac.ui.cs.advprog.b13.hiringgo.course.repository.CourseRepository;
import id.ac.ui.cs.advprog.b13.hiringgo.course.repository.LecturerRepository;
import id.ac.ui.cs.advprog.b13.hiringgo.course.service.observer.CourseObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final LecturerRepository lecturerRepository;
    private final List<CourseObserver> observers = new ArrayList<>();

    @Autowired
    public CourseService(CourseRepository courseRepository, LecturerRepository lecturerRepository) {
        this.courseRepository = courseRepository;
        this.lecturerRepository = lecturerRepository;
    }

    // Observer Pattern - Attach observer
    public void addObserver(CourseObserver observer) {
        observers.add(observer);
    }

    // Observer Pattern - Detach observer
    public void removeObserver(CourseObserver observer) {
        observers.remove(observer);
    }

    // Observer Pattern - Notify observers
    private void notifyObservers(Course course, String operation) {
        for (CourseObserver observer : observers) {
            observer.update(course, operation);
        }
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Optional<Course> getCourseByCourseCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode);
    }

    public Course createCourse(Course course) {
        if (courseRepository.existsByCourseCode(course.getCourseCode())) {
            throw new IllegalArgumentException("Course code already exists");
        }
        Course savedCourse = courseRepository.save(course);
        notifyObservers(savedCourse, "CREATE");
        return savedCourse;
    }

    public Optional<Course> updateCourse(Long id, Course courseDetails) {
        return courseRepository.findById(id).map(course -> {
            if (!course.getCourseCode().equals(courseDetails.getCourseCode()) &&
                    courseRepository.existsByCourseCode(courseDetails.getCourseCode())) {
                throw new IllegalArgumentException("Course code already exists");
            }

            course.setCourseCode(courseDetails.getCourseCode());
            course.setCourseName(courseDetails.getCourseName());
            course.setDescription(courseDetails.getDescription());

            Course updatedCourse = courseRepository.save(course);
            notifyObservers(updatedCourse, "UPDATE");
            return updatedCourse;
        });
    }

    public boolean deleteCourse(Long id) {
        return courseRepository.findById(id).map(course -> {
            courseRepository.delete(course);
            notifyObservers(course, "DELETE");
            return true;
        }).orElse(false);
    }

    public Optional<Course> addLecturerToCourse(Long courseId, Long lecturerId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        Optional<Lecturer> lecturerOpt = lecturerRepository.findById(lecturerId);

        if (courseOpt.isPresent() && lecturerOpt.isPresent()) {
            Course course = courseOpt.get();
            Lecturer lecturer = lecturerOpt.get();

            course.getLecturers().add(lecturer);
            Course updatedCourse = courseRepository.save(course);
            notifyObservers(updatedCourse, "UPDATE");
            return Optional.of(updatedCourse);
        }

        return Optional.empty();
    }

    public Optional<Course> removeLecturerFromCourse(Long courseId, Long lecturerId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        Optional<Lecturer> lecturerOpt = lecturerRepository.findById(lecturerId);

        if (courseOpt.isPresent() && lecturerOpt.isPresent()) {
            Course course = courseOpt.get();
            Lecturer lecturer = lecturerOpt.get();

            course.getLecturers().remove(lecturer);
            Course updatedCourse = courseRepository.save(course);
            notifyObservers(updatedCourse, "UPDATE");
            return Optional.of(updatedCourse);
        }

        return Optional.empty();
    }
}