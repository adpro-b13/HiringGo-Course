package id.ac.ui.cs.advprog.b13.hiringgo.course.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(unique = true)
    private String kode;

    private String nama;

    private String deskripsi;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<Lecturer> dosenPengampu;

}
