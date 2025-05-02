package id.ac.ui.cs.advprog.b13.hiringgo.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDashboard {
    private String lecturerNip;
    private int totalCoursesTaught;
    private int totalAcceptedStudents;
    private int totalOpenVacancies;
}