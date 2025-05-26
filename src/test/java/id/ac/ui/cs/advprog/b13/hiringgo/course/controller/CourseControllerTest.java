package id.ac.ui.cs.advprog.b13.hiringgo.course.controller;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import id.ac.ui.cs.advprog.b13.hiringgo.course.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseControllerTest {
    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private Course course;
    private UUID courseId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        courseId = UUID.randomUUID();
        course = new Course();
        course.setId(courseId);
        course.setKode("CS101");
        course.setNama("Intro to CS");
        course.setDeskripsi("Basic course");
        course.setDosenPengampu(new ArrayList<>());
    }

    @Test
    void testGetAllCourses() {
        List<Course> courses = Collections.singletonList(course);
        when(courseService.getAll()).thenReturn(courses);
        ResponseEntity<List<Course>> response = courseController.getAll();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testCreateCourse() {
        when(courseService.create(any(Course.class))).thenReturn(course);
        ResponseEntity<Course> response = courseController.create(course);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(course, response.getBody());
    }

    @Test
    void testUpdateCourse() {
        when(courseService.update(eq(courseId), any(Course.class))).thenReturn(course);
        ResponseEntity<Course> response = courseController.update(courseId, course);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(course, response.getBody());
    }

    @Test
    void testDeleteCourse() {
        doNothing().when(courseService).delete(courseId);
        ResponseEntity<Void> response = courseController.delete(courseId);
        assertEquals(204, response.getStatusCodeValue());
        verify(courseService, times(1)).delete(courseId);
    }

    @Test
    void testGetCourseById() {
        when(courseService.getById(courseId)).thenReturn(course);
        ResponseEntity<Course> response = courseController.getById(courseId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(course, response.getBody());
    }
}

