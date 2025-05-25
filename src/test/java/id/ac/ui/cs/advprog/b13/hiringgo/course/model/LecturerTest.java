package id.ac.ui.cs.advprog.b13.hiringgo.course.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LecturerTest {

    @Test
    public void testLecturerProperties() {
        Lecturer lecturer = new Lecturer();
        lecturer.setId(UUID.randomUUID());
        lecturer.setNama("Dr. Budi");

        assertEquals("Dr. Budi", lecturer.getNama());
    }

    @Test
    public void testLecturerEquality() {
        Lecturer l1 = new Lecturer();
        l1.setId(UUID.randomUUID());
        l1.setNama("A");

        Lecturer l2 = new Lecturer();
        l2.setId(UUID.randomUUID());
        l2.setNama("A");

        assertNotEquals(l1, l2);
    }
}
