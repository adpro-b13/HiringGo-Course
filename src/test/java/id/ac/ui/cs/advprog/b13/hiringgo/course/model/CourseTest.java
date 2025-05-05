package id.ac.ui.cs.advprog.b13.hiringgo.course.model;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    @Test
    public void testCourseProperties() {
        Course course = new Course();
        course.setId(1L);
        course.setKode("CS101");
        course.setNama("Pemrograman Lanjut");
        course.setDeskripsi("Mata kuliah lanjutan pemrograman.");

        assertEquals(1L, course.getId());
        assertEquals("CS101", course.getKode());
        assertEquals("Pemrograman Lanjut", course.getNama());
        assertEquals("Mata kuliah lanjutan pemrograman.", course.getDeskripsi());
    }

    @Test
    public void testCourseWithLecturers() {
        Lecturer lecturer1 = new Lecturer();
        lecturer1.setId(1L);
        lecturer1.setNama("Dr. Rizky");

        Lecturer lecturer2 = new Lecturer();
        lecturer2.setId(2L);
        lecturer2.setNama("Prof. Andi");

        List<Lecturer> lecturers = new ArrayList<>();
        lecturers.add(lecturer1);
        lecturers.add(lecturer2);

        Course course = new Course();
        course.setDosenPengampu(lecturers);

        assertEquals(2, course.getDosenPengampu().size());
        assertTrue(course.getDosenPengampu().contains(lecturer1));
        assertTrue(course.getDosenPengampu().contains(lecturer2));
    }
}
