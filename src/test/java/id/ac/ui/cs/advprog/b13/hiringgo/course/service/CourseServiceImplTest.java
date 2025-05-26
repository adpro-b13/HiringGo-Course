package id.ac.ui.cs.advprog.b13.hiringgo.course.service;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Lecturer;
import id.ac.ui.cs.advprog.b13.hiringgo.course.repository.CourseRepository;
import id.ac.ui.cs.advprog.b13.hiringgo.course.repository.LecturerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private LecturerRepository lecturerRepository;
    @InjectMocks
    private CourseServiceImpl courseService;

    private Course course;
    private Lecturer lecturer;
    private UUID courseId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        courseId = UUID.randomUUID();
        lecturer = new Lecturer();
        lecturer.setId(UUID.randomUUID());
        lecturer.setNama("Dr. Smith");
        course = new Course();
        course.setId(courseId);
        course.setKode("CS101");
        course.setNama("Intro to CS");
        course.setDeskripsi("Basic course");
        course.setDosenPengampu(Collections.singletonList(lecturer));
    }

    @Test
    void testGetAllCourses() {
        when(courseRepository.findAll()).thenReturn(Collections.singletonList(course));
        List<Course> result = courseService.getAll();
        assertEquals(1, result.size());
        assertEquals(course, result.get(0));
    }

    @Test
    void testCreateCourseWithNewLecturer() {
        when(lecturerRepository.findByNama(anyString())).thenReturn(Optional.empty());
        when(lecturerRepository.save(any(Lecturer.class))).thenReturn(lecturer);
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        Course result = courseService.create(course);
        assertEquals(course, result);
        verify(lecturerRepository, times(1)).save(lecturer);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testCreateCourseWithExistingLecturer() {
        when(lecturerRepository.findByNama(anyString())).thenReturn(Optional.of(lecturer));
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        Course result = courseService.create(course);
        assertEquals(course, result);
        verify(lecturerRepository, never()).save(any(Lecturer.class));
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testUpdateCourse() {
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        Course updated = courseService.update(courseId, course);
        assertEquals(course, updated);
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testDeleteCourse() {
        doNothing().when(courseRepository).deleteById(courseId);
        courseService.delete(courseId);
        verify(courseRepository, times(1)).deleteById(courseId);
    }

    @Test
    void testGetCourseById() {
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        Course found = courseService.getById(courseId);
        assertEquals(course, found);
    }

    @Test
    void testGetCourseByIdNotFound() {
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> courseService.getById(courseId));
    }

    @Test
    void testCreateCourseWithEmptyLecturerList() {
        Course emptyLecturerCourse = new Course();
        emptyLecturerCourse.setDosenPengampu(new ArrayList<>());
        when(courseRepository.save(any(Course.class))).thenReturn(emptyLecturerCourse);
        Course result = courseService.create(emptyLecturerCourse);
        assertEquals(emptyLecturerCourse, result);
        verify(courseRepository, times(1)).save(emptyLecturerCourse);
    }

    @Test
    void testUpdateCourseNotFound() {
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> courseService.update(courseId, course));
    }

    @Test
    void testDeleteCourseWithNonExistentId() {
        doNothing().when(courseRepository).deleteById(courseId);
        // Should not throw
        assertDoesNotThrow(() -> courseService.delete(courseId));
        verify(courseRepository, times(1)).deleteById(courseId);
    }

    @Test
    void testSetRepository() {
        CourseRepository mockRepo = mock(CourseRepository.class);
        courseService.setRepository(mockRepo);
        assertNotNull(courseService);
    }

    @Test
    void testGetAllAsync() throws Exception {
        List<Course> courses = Collections.singletonList(course);
        when(courseRepository.findAll()).thenReturn(courses);
        assertEquals(courses, courseService.getAllAsync().get());
    }
}
