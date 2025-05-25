package id.ac.ui.cs.advprog.b13.hiringgo.course.service;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import id.ac.ui.cs.advprog.b13.hiringgo.course.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseServiceImplTest {

    private CourseRepository courseRepository;
    private CourseServiceImpl courseService;

    private Course sampleCourse;

    @BeforeEach
    public void setUp() {
        courseRepository = Mockito.mock(CourseRepository.class);
        courseService = new CourseServiceImpl();
        courseService.setRepository(courseRepository);
        sampleCourse = new Course();
        sampleCourse.setId(UUID.randomUUID());
        sampleCourse.setKode("CS123");
        sampleCourse.setNama("Pemrograman");
        sampleCourse.setDeskripsi("Deskripsi");
    }

    @Test
    public void testGetAllCourses() {
        when(courseRepository.findAll()).thenReturn(List.of(sampleCourse));
        List<Course> result = courseService.getAll();
        assertEquals(1, result.size());
        assertEquals("CS123", result.getFirst().getKode());
    }

    @Test
    public void testCreateCourse_Success() {
        when(courseRepository.findByKode("CS123")).thenReturn(Optional.empty());
        when(courseRepository.save(any(Course.class))).thenReturn(sampleCourse);

        Course result = courseService.create(sampleCourse);
        assertEquals("CS123", result.getKode());
    }

    @Test
    public void testCreateCourse_KodeAlreadyExists() {
        when(courseRepository.findByKode("CS123")).thenReturn(Optional.of(sampleCourse));
        RuntimeException ex = assertThrows(RuntimeException.class, () -> courseService.create(sampleCourse));
        assertEquals("Kode mata kuliah sudah terdaftar!", ex.getMessage());
    }

    @Test
    public void testUpdateCourse_Success() {
        when(courseRepository.findById(any())).thenReturn(Optional.of(sampleCourse));
        when(courseRepository.save(any(Course.class))).thenReturn(sampleCourse);

        Course input = new Course();
        input.setNama("Rekayasa Perangkat Lunak");
        input.setDeskripsi("Update Deskripsi");
        input.setDosenPengampu(Collections.emptyList());

        Course result = courseService.update(sampleCourse.getId(), input);
        assertEquals("Rekayasa Perangkat Lunak", result.getNama());
    }

    @Test
    public void testUpdateCourse_NotFound() {
        when(courseRepository.findById(any())).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                courseService.update(sampleCourse.getId(), sampleCourse));
        assertEquals("Data tidak ditemukan", ex.getMessage());
    }

    @Test
    public void testDeleteCourse() {
        when(courseRepository.findById(any())).thenReturn(Optional.of(sampleCourse));
        Course course = courseService.getById(sampleCourse.getId());
        if (course != null) {
            courseService.delete(course.getId());
        }
        verify(courseRepository, times(1)).deleteById(sampleCourse.getId());
    }

    @Test
    public void testGetById_Success() {
        when(courseRepository.findById(any())).thenReturn(Optional.of(sampleCourse));
        Course result = courseService.getById(sampleCourse.getId());
        assertEquals("CS123", result.getKode());
    }

    @Test
    public void testGetById_NotFound() {
        when(courseRepository.findById(any())).thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                courseService.getById(sampleCourse.getId()));
        assertEquals("Tidak ditemukan", ex.getMessage());
    }
}
