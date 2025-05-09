package id.ac.ui.cs.advprog.b13.hiringgo.course.repository;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @DisplayName("Should save and find course by kode")
    public void testSaveAndFindByKode() {
        Course course = new Course();
        course.setKode("CS001");
        course.setNama("Algoritma");
        course.setDeskripsi("Belajar dasar algoritma");

        courseRepository.save(course);

        Optional<Course> result = courseRepository.findByKode("CS001");

        assertTrue(result.isPresent());
        assertEquals("Algoritma", result.get().getNama());
    }

    @Test
    @DisplayName("Should return empty if kode not found")
    public void testFindByKodeNotFound() {
        Optional<Course> result = courseRepository.findByKode("TIDAKADA");
        assertTrue(result.isEmpty());
    }
}
