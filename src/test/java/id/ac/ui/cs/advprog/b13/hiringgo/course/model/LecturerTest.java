package id.ac.ui.cs.advprog.b13.hiringgo.course.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LecturerTest {

    @Test
    public void testLecturerProperties() {
        Lecturer lecturer = new Lecturer();
        lecturer.setId(10L);
        lecturer.setNama("Dr. Budi");

        assertEquals(10L, lecturer.getId());
        assertEquals("Dr. Budi", lecturer.getNama());
    }

    @Test
    public void testLecturerEquality() {
        Lecturer l1 = new Lecturer();
        l1.setId(1L);
        l1.setNama("A");

        Lecturer l2 = new Lecturer();
        l2.setId(1L);
        l2.setNama("A");

        assertEquals(l1, l2); // Karena pakai @Data dari Lombok, equals dan hashCode otomatis
    }
}
