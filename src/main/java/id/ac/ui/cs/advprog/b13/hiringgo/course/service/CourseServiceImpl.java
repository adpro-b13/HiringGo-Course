package id.ac.ui.cs.advprog.b13.hiringgo.course.service;

import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import id.ac.ui.cs.advprog.b13.hiringgo.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (repository.findByKode(mk.getKode()).isPresent()) {
            throw new RuntimeException("Kode mata kuliah sudah terdaftar!");
        }
        return repository.save(mk);
    }

    @Override
    public Course update(Long id, Course mk) {
        Course existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));
        existing.setNama(mk.getNama());
        existing.setDeskripsi(mk.getDeskripsi());
        existing.setDosenPengampu(mk.getDosenPengampu());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Course getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Tidak ditemukan"));
    }

    // ✅ Tambahkan setter hanya untuk testing
    void setRepository(CourseRepository repository) {
        this.repository = repository;
    }
}
