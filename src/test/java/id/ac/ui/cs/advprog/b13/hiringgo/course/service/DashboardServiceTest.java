package id.ac.ui.cs.advprog.b13.hiringgo.course.service;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Lecturer;
import id.ac.ui.cs.advprog.b13.hiringgo.course.model.LecturerDashboard;
import id.ac.ui.cs.advprog.b13.hiringgo.course.repository.LecturerDashboardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock
    private LecturerDashboardRepository dashboardRepository;

    private DashboardService dashboardService;
    private LecturerDashboard testDashboard;
    private Course testCourse;
    private Lecturer testLecturer;

    @BeforeEach
    void setUp() {
        dashboardService = new DashboardService(dashboardRepository);

        testDashboard = LecturerDashboard.builder()
                .lecturerNip("123456")
                .totalCoursesTaught(2)
                .totalAcceptedStudents(5)
                .totalOpenVacancies(3)
                .build();

        testLecturer = Lecturer.builder()
                .id(1L)
                .nip("123456")
                .fullName("Test Lecturer")
                .email("lecturer@example.com")
                .build();

        Set<Lecturer> lecturers = new HashSet<>();
        lecturers.add(testLecturer);

        testCourse = Course.builder()
                .id(1L)
                .courseCode("CS101")
                .courseName("Introduction to Computer Science")
                .description("Basic programming concepts")
                .lecturers(lecturers)
                .build();
    }

    @Test
    void testGetLecturerDashboard() {
        when(dashboardRepository.findByLecturerNip("123456")).thenReturn(Optional.of(testDashboard));

        LecturerDashboard dashboard = dashboardService.getLecturerDashboard("123456");

        assertNotNull(dashboard);
        assertEquals("123456", dashboard.getLecturerNip());
        assertEquals(2, dashboard.getTotalCoursesTaught());
        verify(dashboardRepository, times(1)).findByLecturerNip("123456");
    }

    @Test
    void testGetLecturerDashboardCreatesNewIfNotExists() {
        when(dashboardRepository.findByLecturerNip("123456")).thenReturn(Optional.empty());
        when(dashboardRepository.save(any(LecturerDashboard.class))).thenAnswer(invocation -> invocation.getArgument(0));

        LecturerDashboard dashboard = dashboardService.getLecturerDashboard("123456");

        assertNotNull(dashboard);
        assertEquals("123456", dashboard.getLecturerNip());
        assertEquals(0, dashboard.getTotalCoursesTaught());
        verify(dashboardRepository, times(1)).findByLecturerNip("123456");

        ArgumentCaptor<LecturerDashboard> dashboardCaptor = ArgumentCaptor.forClass(LecturerDashboard.class);
        verify(dashboardRepository, times(1)).save(dashboardCaptor.capture());
        assertEquals("123456", dashboardCaptor.getValue().getLecturerNip());
    }

    @Test
    void testCreateNewLecturerDashboard() {
        when(dashboardRepository.save(any(LecturerDashboard.class))).thenAnswer(invocation -> invocation.getArgument(0));

        LecturerDashboard dashboard = dashboardService.createNewLecturerDashboard("123456");

        assertNotNull(dashboard);
        assertEquals("123456", dashboard.getLecturerNip());
        assertEquals(0, dashboard.getTotalCoursesTaught());
        assertEquals(0, dashboard.getTotalAcceptedStudents());
        assertEquals(0, dashboard.getTotalOpenVacancies());

        ArgumentCaptor<LecturerDashboard> dashboardCaptor = ArgumentCaptor.forClass(LecturerDashboard.class);
        verify(dashboardRepository, times(1)).save(dashboardCaptor.capture());
        assertEquals("123456", dashboardCaptor.getValue().getLecturerNip());
    }

    @Test
    void testUpdateLecturerDashboardsForCreate() {
        when(dashboardRepository.findByLecturerNip("123456")).thenReturn(Optional.of(testDashboard));
        when(dashboardRepository.save(any(LecturerDashboard.class))).thenAnswer(invocation -> invocation.getArgument(0));

        dashboardService.updateLecturerDashboards(testCourse, "CREATE");

        ArgumentCaptor<LecturerDashboard> dashboardCaptor = ArgumentCaptor.forClass(LecturerDashboard.class);
        verify(dashboardRepository, times(1)).save(dashboardCaptor.capture());

        LecturerDashboard updatedDashboard = dashboardCaptor.getValue();
        assertEquals(3, updatedDashboard.getTotalCoursesTaught()); // Incremented from 2 to 3
    }

    @Test
    void testUpdateLecturerDashboardsForDelete() {
        when(dashboardRepository.findByLecturerNip("123456")).thenReturn(Optional.of(testDashboard));
        when(dashboardRepository.save(any(LecturerDashboard.class))).thenAnswer(invocation -> invocation.getArgument(0));

        dashboardService.updateLecturerDashboards(testCourse, "DELETE");

        ArgumentCaptor<LecturerDashboard> dashboardCaptor = ArgumentCaptor.forClass(LecturerDashboard.class);
        verify(dashboardRepository, times(1)).save(dashboardCaptor.capture());

        LecturerDashboard updatedDashboard = dashboardCaptor.getValue();
        assertEquals(1, updatedDashboard.getTotalCoursesTaught()); // Decremented from 2 to 1
    }

    @Test
    void testUpdateLecturerDashboardsCreatesNewDashboardIfNotExists() {
        when(dashboardRepository.findByLecturerNip("123456")).thenReturn(Optional.empty());
        when(dashboardRepository.save(any(LecturerDashboard.class))).thenAnswer(invocation -> invocation.getArgument(0));

        dashboardService.updateLecturerDashboards(testCourse, "CREATE");

        ArgumentCaptor<LecturerDashboard> dashboardCaptor = ArgumentCaptor.forClass(LecturerDashboard.class);
        verify(dashboardRepository, times(1)).save(dashboardCaptor.capture());

        LecturerDashboard createdDashboard = dashboardCaptor.getValue();
        assertEquals("123456", createdDashboard.getLecturerNip());
        assertEquals(1, createdDashboard.getTotalCoursesTaught()); // Started at 0, incremented to 1
    }

    @Test
    void testUpdateVacancyStats() {
        when(dashboardRepository.findByLecturerNip("123456")).thenReturn(Optional.of(testDashboard));
        when(dashboardRepository.save(any(LecturerDashboard.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Test opening a vacancy
        dashboardService.updateVacancyStats("123456", true, false);

        ArgumentCaptor<LecturerDashboard> dashboardCaptor = ArgumentCaptor.forClass(LecturerDashboard.class);
        verify(dashboardRepository, times(1)).save(dashboardCaptor.capture());

        LecturerDashboard updatedDashboard = dashboardCaptor.getValue();
        assertEquals(4, updatedDashboard.getTotalOpenVacancies()); // Incremented from 3 to 4
        assertEquals(5, updatedDashboard.getTotalAcceptedStudents()); // Unchanged

        // Reset mock
        reset(dashboardRepository);

        // Test closing a vacancy and accepting a student
        when(dashboardRepository.findByLecturerNip("123456")).thenReturn(Optional.of(testDashboard));
        when(dashboardRepository.save(any(LecturerDashboard.class))).thenAnswer(invocation -> invocation.getArgument(0));

        dashboardService.updateVacancyStats("123456", false, true);

        dashboardCaptor = ArgumentCaptor.forClass(LecturerDashboard.class);
        verify(dashboardRepository, times(1)).save(dashboardCaptor.capture());

        updatedDashboard = dashboardCaptor.getValue();
        assertEquals(2, updatedDashboard.getTotalOpenVacancies()); // Decremented from 3 to 2
        assertEquals(6, updatedDashboard.getTotalAcceptedStudents()); // Incremented from 5 to 6
    }
}