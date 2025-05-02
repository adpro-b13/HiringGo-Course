package id.ac.ui.cs.advprog.b13.hiringgo.course.repository;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Lecturer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LecturerRepositoryImpl implements LecturerRepository {
    private final Map<Long, Lecturer> lecturers = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    @Override
    public List<Lecturer> findAll() {
        return new ArrayList<>(lecturers.values());
    }

    @Override
    public Optional<Lecturer> findById(Long id) {
        return Optional.ofNullable(lecturers.get(id));
    }

    @Override
    public Optional<Lecturer> findByNip(String nip) {
        return lecturers.values().stream()
                .filter(lecturer -> lecturer.getNip().equals(nip))
                .findFirst();
    }

    @Override
    public Optional<Lecturer> findByEmail(String email) {
        return lecturers.values().stream()
                .filter(lecturer -> lecturer.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Lecturer save(Lecturer lecturer) {
        if (lecturer.getId() == null) {
            lecturer.setId(idCounter.incrementAndGet());
        }
        lecturers.put(lecturer.getId(), lecturer);
        return lecturer;
    }

    @Override
    public void delete(Lecturer lecturer) {
        lecturers.remove(lecturer.getId());
    }

    @Override
    public boolean existsByNip(String nip) {
        return lecturers.values().stream()
                .anyMatch(lecturer -> lecturer.getNip().equals(nip));
    }

    @Override
    public boolean existsByEmail(String email) {
        return lecturers.values().stream()
                .anyMatch(lecturer -> lecturer.getEmail().equals(email));
    }
}