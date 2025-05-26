package id.ac.ui.cs.advprog.b13.hiringgo.course.model;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class LecturerTest {
    @Test
    void testGettersAndSetters() {
        Lecturer lecturer = new Lecturer();
        UUID id = UUID.randomUUID();
        lecturer.setId(id);
        lecturer.setNama("Dr. Smith");
        assertEquals(id, lecturer.getId());
        assertEquals("Dr. Smith", lecturer.getNama());
    }

    @Test
    void testEqualsAndHashCode() {
        Lecturer lecturer1 = new Lecturer();
        Lecturer lecturer2 = new Lecturer();
        UUID id = UUID.randomUUID();
        lecturer1.setId(id);
        lecturer2.setId(id);
        lecturer1.setNama("Dr. Smith");
        lecturer2.setNama("Dr. Smith");
        assertEquals(lecturer1, lecturer2);
        assertEquals(lecturer1.hashCode(), lecturer2.hashCode());
    }

    @Test
    void testToString() {
        Lecturer lecturer = new Lecturer();
        lecturer.setId(UUID.randomUUID());
        lecturer.setNama("Dr. Smith");
        assertTrue(lecturer.toString().contains("Dr. Smith"));
    }
}

