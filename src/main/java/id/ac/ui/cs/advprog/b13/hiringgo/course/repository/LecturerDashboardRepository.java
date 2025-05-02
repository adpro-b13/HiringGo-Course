package id.ac.ui.cs.advprog.b13.hiringgo.course.repository;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.LecturerDashboard;

import java.util.List;
import java.util.Optional;

public interface LecturerDashboardRepository {
    List<LecturerDashboard> findAll();
    Optional<LecturerDashboard> findByLecturerNip(String nip);
    LecturerDashboard save(LecturerDashboard dashboard);
    void delete(LecturerDashboard dashboard);
}