package id.ac.ui.cs.advprog.b13.hiringgo.course.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer {
    private Long id;
    private String nip;
    private String fullName;
    private String email;
    private Set<Course> courses = new HashSet<>();
}