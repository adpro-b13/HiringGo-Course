package id.ac.ui.cs.advprog.b13.hiringgo.course.model;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    @Test
    void testGettersAndSetters() {
        Course course = new Course();
        UUID id = UUID.randomUUID();
        course.setId(id);
        course.setKode("CS101");
        course.setNama("Intro to CS");
        course.setDeskripsi("Basic course");
        Lecturer lecturer = new Lecturer();
        lecturer.setId(UUID.randomUUID());
        lecturer.setNama("Dr. Smith");
        List<Lecturer> lecturers = Collections.singletonList(lecturer);
        course.setDosenPengampu(lecturers);

        assertEquals(id, course.getId());
        assertEquals("CS101", course.getKode());
        assertEquals("Intro to CS", course.getNama());
        assertEquals("Basic course", course.getDeskripsi());
        assertEquals(lecturers, course.getDosenPengampu());
    }

    @Test
    void testEqualsAndHashCode() {
        Course course1 = new Course();
        Course course2 = new Course();
        UUID id = UUID.randomUUID();
        course1.setId(id);
        course2.setId(id);
        course1.setKode("CS101");
        course2.setKode("CS101");
        assertEquals(course1, course2);
        assertEquals(course1.hashCode(), course2.hashCode());
    }

    @Test
    void testToString() {
        Course course = new Course();
        course.setId(UUID.randomUUID());
        course.setKode("CS101");
        course.setNama("Intro to CS");
        course.setDeskripsi("Basic course");
        assertTrue(course.toString().contains("CS101"));
        assertTrue(course.toString().contains("Intro to CS"));
    }
}

