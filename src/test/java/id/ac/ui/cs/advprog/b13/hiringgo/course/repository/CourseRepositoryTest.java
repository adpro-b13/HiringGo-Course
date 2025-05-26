package id.ac.ui.cs.advprog.b13.hiringgo.course.repository;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseRepositoryTest {
    @Mock
    private CourseRepository courseRepository;

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
    }

    @Test
    void testFindByKode() {
        when(courseRepository.findByKode("CS101")).thenReturn(Optional.of(course));
        Optional<Course> found = courseRepository.findByKode("CS101");
        assertTrue(found.isPresent());
        assertEquals(course, found.get());
    }
}

