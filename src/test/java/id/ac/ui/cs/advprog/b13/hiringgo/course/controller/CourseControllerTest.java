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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
    void testGetAllCoursesThrowsException() {
        when(courseService.getAll()).thenThrow(new RuntimeException("error"));
        assertThrows(RuntimeException.class, () -> courseController.getAll());
    }

    @Test
    void testCreateCourse() {
        when(courseService.create(any(Course.class))).thenReturn(course);
        ResponseEntity<Course> response = courseController.create(course);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(course, response.getBody());
    }

    @Test
    void testCreateCourseThrowsException() {
        when(courseService.create(any(Course.class))).thenThrow(new RuntimeException("error"));
        assertThrows(RuntimeException.class, () -> courseController.create(course));
    }

    @Test
    void testCreateCourseWithNullBody() {
        when(courseService.create(null)).thenThrow(new RuntimeException("Null body"));
        assertThrows(RuntimeException.class, () -> courseController.create(null));
    }

    @Test
    void testUpdateCourse() {
        when(courseService.update(eq(courseId), any(Course.class))).thenReturn(course);
        ResponseEntity<Course> response = courseController.update(courseId, course);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(course, response.getBody());
    }

    @Test
    void testUpdateCourseThrowsException() {
        when(courseService.update(eq(courseId), any(Course.class))).thenThrow(new RuntimeException("error"));
        assertThrows(RuntimeException.class, () -> courseController.update(courseId, course));
    }

    @Test
    void testUpdateCourseWithNullBody() {
        when(courseService.update(eq(courseId), isNull())).thenThrow(new RuntimeException("Null body"));
        assertThrows(RuntimeException.class, () -> courseController.update(courseId, null));
    }

    @Test
    void testUpdateCourseWithNullId() {
        UUID nullId = null;
        when(courseService.update(isNull(), any(Course.class))).thenThrow(new RuntimeException("Null id"));
        assertThrows(RuntimeException.class, () -> courseController.update(nullId, course));
    }

    @Test
    void testDeleteCourse() {
        doNothing().when(courseService).delete(courseId);
        ResponseEntity<Void> response = courseController.delete(courseId);
        assertEquals(204, response.getStatusCodeValue());
        verify(courseService, times(1)).delete(courseId);
    }

    @Test
    void testDeleteCourseThrowsException() {
        doThrow(new RuntimeException("error")).when(courseService).delete(courseId);
        assertThrows(RuntimeException.class, () -> courseController.delete(courseId));
    }

    @Test
    void testDeleteCourseWithNullId() {
        UUID nullId = null;
        doThrow(new RuntimeException("Null id")).when(courseService).delete(isNull());
        assertThrows(RuntimeException.class, () -> courseController.delete(nullId));
    }

    @Test
    void testGetCourseById() {
        when(courseService.getById(courseId)).thenReturn(course);
        ResponseEntity<Course> response = courseController.getById(courseId);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(course, response.getBody());
    }

    @Test
    void testGetCourseByIdThrowsException() {
        when(courseService.getById(courseId)).thenThrow(new RuntimeException("error"));
        assertThrows(RuntimeException.class, () -> courseController.getById(courseId));
    }

    @Test
    void testGetCourseByIdWithNullId() {
        UUID nullId = null;
        when(courseService.getById(isNull())).thenThrow(new RuntimeException("Null id"));
        assertThrows(RuntimeException.class, () -> courseController.getById(nullId));
    }

    @Test
    void testGetAllAsync() throws ExecutionException, InterruptedException {
        List<Course> courses = Collections.singletonList(course);
        CompletableFuture<List<Course>> completedFuture = CompletableFuture.completedFuture(courses);
        when(courseService.getAllAsync()).thenReturn(completedFuture);

        CompletableFuture<List<Course>> result = courseController.getAllAsync();

        assertNotNull(result);
        assertEquals(1, result.get().size());
        assertEquals(course, result.get().get(0));
        verify(courseService, times(1)).getAllAsync();
    }

    @Test
    void testGetAllAsyncThrowsException() {
        CompletableFuture<List<Course>> failedFuture = new CompletableFuture<>();
        failedFuture.completeExceptionally(new RuntimeException("Async error"));
        when(courseService.getAllAsync()).thenReturn(failedFuture);

        CompletableFuture<List<Course>> result = courseController.getAllAsync();

        assertNotNull(result);
        assertTrue(result.isCompletedExceptionally());
        verify(courseService, times(1)).getAllAsync();
    }

    @Test
    void testGetAllAsyncEmptyList() throws ExecutionException, InterruptedException {
        List<Course> emptyCourses = Collections.emptyList();
        CompletableFuture<List<Course>> completedFuture = CompletableFuture.completedFuture(emptyCourses);
        when(courseService.getAllAsync()).thenReturn(completedFuture);

        CompletableFuture<List<Course>> result = courseController.getAllAsync();

        assertNotNull(result);
        assertTrue(result.get().isEmpty());
        verify(courseService, times(1)).getAllAsync();
    }
}