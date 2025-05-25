package id.ac.ui.cs.advprog.b13.hiringgo.course.service;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import id.ac.ui.cs.advprog.b13.hiringgo.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Override
    public List<Course> getAll() {
        return repository.findAll();
    }

    @Override
    public Course create(Course mk) {
        if (repository.findById(mk.getId()).isPresent()) {
            throw new RuntimeException("Kode mata kuliah sudah terdaftar!");
        }
        return repository.save(mk);
    }

    @Override
    public Course update(UUID id, Course mk) {
        Course existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));
        existing.setNama(mk.getNama());
        existing.setDeskripsi(mk.getDeskripsi());
        existing.setDosenPengampu(mk.getDosenPengampu());
        return repository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Course getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Tidak ditemukan"));
    }

    // ✅ Tambahkan setter hanya untuk testing
    void setRepository(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    @Async
    public CompletableFuture<List<Course>> getAllAsync() {
        return CompletableFuture.completedFuture(repository.findAll());
    }

}
