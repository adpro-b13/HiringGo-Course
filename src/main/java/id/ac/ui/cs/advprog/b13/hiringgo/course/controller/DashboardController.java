package id.ac.ui.cs.advprog.b13.hiringgo.course.controller;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.LecturerDashboard;
import id.ac.ui.cs.advprog.b13.hiringgo.course.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/lecturer")
    @PreAuthorize("hasRole('LECTURER')")
    public ResponseEntity<LecturerDashboard> getLecturerDashboard() {
        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserNip = authentication.getName();

        LecturerDashboard dashboard = dashboardService.getLecturerDashboard(currentUserNip);
        return ResponseEntity.ok(dashboard);
    }

    @GetMapping("/lecturer/{nip}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LecturerDashboard> getLecturerDashboardByNip(@PathVariable String nip) {
        LecturerDashboard dashboard = dashboardService.getLecturerDashboard(nip);
        return ResponseEntity.ok(dashboard);
    }
}