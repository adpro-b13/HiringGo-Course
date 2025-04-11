package id.ac.ui.cs.advprog.b13.hiringgo.course.model;

import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    private String id; // UUID
    private String nama;
    private String kode;
    private String deskripsi;
    private List<String> dosenPengampu; // daftar NIP atau nama dosen
}
