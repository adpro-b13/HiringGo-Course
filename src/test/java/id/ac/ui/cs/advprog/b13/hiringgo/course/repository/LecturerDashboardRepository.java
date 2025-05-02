package id.ac.ui.cs.advprog.b13.hiringgo.course.repository;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.LecturerDashboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LecturerDashboardRepositoryImplTest {

    private LecturerDashboardRepository dashboardRepository;
    private LecturerDashboard testDashboard;

    @BeforeEach
    void setUp() {
        dashboardRepository = new LecturerDashboardRepositoryImpl();

        testDashboard = LecturerDashboard.builder()
                .lecturerNip("123456")
                .totalCoursesTaught(2)
                .totalAcceptedStudents(5)
                .totalOpenVacancies(3)
                .build();
    }

    @Test
    void testSave() {
        LecturerDashboard savedDashboard = dashboardRepository.save(testDashboard);

        assertNotNull(savedDashboard);
        assertEquals("123456", savedDashboard.getLecturerNip());
        assertEquals(2, savedDashboard.getTotalCoursesTaught());
    }

    @Test
    void testFindByLecturerNip() {
        dashboardRepository.save(testDashboard);

        Optional<LecturerDashboard> foundDashboard = dashboardRepository.findByLecturerNip("123456");

        assertTrue(foundDashboard.isPresent());
        assertEquals("123456", foundDashboard.get().getLecturerNip());
        assertEquals(2, foundDashboard.get().getTotalCoursesTaught());
    }

    @Test
    void testFindAll() {
        dashboardRepository.save(testDashboard);

        LecturerDashboard anotherDashboard = LecturerDashboard.builder()
                .lecturerNip("654321")
                .totalCoursesTaught(1)
                .totalAcceptedStudents(3)
                .totalOpenVacancies(2)
                .build();
        dashboardRepository.save(anotherDashboard);

        List<LecturerDashboard> allDashboards = dashboardRepository.findAll();

        assertEquals(2, allDashboards.size());
    }

    @Test
    void testDelete() {
        dashboardRepository.save(testDashboard);

        dashboardRepository.delete(testDashboard);

        Optional<LecturerDashboard> deletedDashboard = dashboardRepository.findByLecturerNip("123456");
        assertFalse(deletedDashboard.isPresent());
    }
}