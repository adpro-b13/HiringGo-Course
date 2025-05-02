package id.ac.ui.cs.advprog.b13.hiringgo.course.repository;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Lecturer;

import java.util.List;
import java.util.Optional;

public interface LecturerRepository {
    List<Lecturer> findAll();
    Optional<Lecturer> findById(Long id);
    Optional<Lecturer> findByNip(String nip);
    Optional<Lecturer> findByEmail(String email);
    Lecturer save(Lecturer lecturer);
    void delete(Lecturer lecturer);
    boolean existsByNip(String nip);
    boolean existsByEmail(String email);
}