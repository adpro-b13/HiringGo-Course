package id.ac.ui.cs.advprog.b13.hiringgo.course.repository;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.LecturerDashboard;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class LecturerDashboardRepositoryImpl implements LecturerDashboardRepository {
    private final Map<String, LecturerDashboard> dashboards = new ConcurrentHashMap<>();

    @Override
    public List<LecturerDashboard> findAll() {
        return new ArrayList<>(dashboards.values());
    }

    @Override
    public Optional<LecturerDashboard> findByLecturerNip(String nip) {
        return Optional.ofNullable(dashboards.get(nip));
    }

    @Override
    public LecturerDashboard save(LecturerDashboard dashboard) {
        dashboards.put(dashboard.getLecturerNip(), dashboard);
        return dashboard;
    }

    @Override
    public void delete(LecturerDashboard dashboard) {
        dashboards.remove(dashboard.getLecturerNip());
    }
}