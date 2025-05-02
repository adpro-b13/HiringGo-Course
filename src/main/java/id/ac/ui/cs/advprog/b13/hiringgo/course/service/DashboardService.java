package id.ac.ui.cs.advprog.b13.hiringgo.course.service;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Lecturer;
import id.ac.ui.cs.advprog.b13.hiringgo.course.model.LecturerDashboard;
import id.ac.ui.cs.advprog.b13.hiringgo.course.repository.LecturerDashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DashboardService {
    private final LecturerDashboardRepository dashboardRepository;

    @Autowired
    public DashboardService(LecturerDashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public LecturerDashboard getLecturerDashboard(String nip) {
        return dashboardRepository.findByLecturerNip(nip)
                .orElseGet(() -> createNewLecturerDashboard(nip));
    }

    public LecturerDashboard createNewLecturerDashboard(String nip) {
        LecturerDashboard dashboard = LecturerDashboard.builder()
                .lecturerNip(nip)
                .totalCoursesTaught(0)
                .totalAcceptedStudents(0)
                .totalOpenVacancies(0)
                .build();
        return dashboardRepository.save(dashboard);
    }

    // This method is called by the Observer when a course is updated
    public void updateLecturerDashboards(Course course, String operation) {
        if (course != null && course.getLecturers() != null) {
            for (Lecturer lecturer : course.getLecturers()) {
                Optional<LecturerDashboard> dashboardOpt = dashboardRepository.findByLecturerNip(lecturer.getNip());
                LecturerDashboard dashboard;

                if (dashboardOpt.isPresent()) {
                    dashboard = dashboardOpt.get();
                } else {
                    dashboard = createNewLecturerDashboard(lecturer.getNip());
                }

                // Update dashboard based on operation
                switch (operation) {
                    case "CREATE":
                        dashboard.setTotalCoursesTaught(dashboard.getTotalCoursesTaught() + 1);
                        break;
                    case "UPDATE":
                        // Update logic depends on what changed in the course
                        break;
                    case "DELETE":
                        if (dashboard.getTotalCoursesTaught() > 0) {
                            dashboard.setTotalCoursesTaught(dashboard.getTotalCoursesTaught() - 1);
                        }
                        break;
                }

                dashboardRepository.save(dashboard);
            }
        }
    }

    // Method to update dashboard when a vacancy status changes
    public void updateVacancyStats(String lecturerNip, boolean isOpened, boolean isAccepted) {
        Optional<LecturerDashboard> dashboardOpt = dashboardRepository.findByLecturerNip(lecturerNip);
        LecturerDashboard dashboard;

        if (dashboardOpt.isPresent()) {
            dashboard = dashboardOpt.get();
        } else {
            dashboard = createNewLecturerDashboard(lecturerNip);
        }

        if (isOpened) {
            dashboard.setTotalOpenVacancies(dashboard.getTotalOpenVacancies() + 1);
        } else {
            if (dashboard.getTotalOpenVacancies() > 0) {
                dashboard.setTotalOpenVacancies(dashboard.getTotalOpenVacancies() - 1);
            }
        }

        if (isAccepted) {
            dashboard.setTotalAcceptedStudents(dashboard.getTotalAcceptedStudents() + 1);
        }

        dashboardRepository.save(dashboard);
    }
}