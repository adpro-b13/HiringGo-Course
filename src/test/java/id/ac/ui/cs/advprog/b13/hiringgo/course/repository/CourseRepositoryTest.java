//package id.ac.ui.cs.advprog.b13.hiringgo.course.repository;
//
//import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
//import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Lecturer;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CourseRepositoryImplTest {
//
//    private CourseRepository courseRepository;
//    private Course testCourse;
//    private Lecturer testLecturer;
//
//    @BeforeEach
//    void setUp() {
//        courseRepository = new CourseRepositoryImpl();
//
//        testLecturer = Lecturer.builder()
//                .id(1L)
//                .nip("123456")
//                .fullName("Test Lecturer")
//                .email("lecturer@example.com")
//                .build();
//
//        Set<Lecturer> lecturers = new HashSet<>();
//        lecturers.add(testLecturer);
//
//        testCourse = Course.builder()
//                .courseCode("CS101")
//                .courseName("Introduction to Computer Science")
//                .description("Basic programming concepts")
//                .lecturers(lecturers)
//                .build();
//    }
//
//    @Test
//    void testSave() {
//        Course savedCourse = courseRepository.save(testCourse);
//
//        assertNotNull(savedCourse);
//        assertNotNull(savedCourse.getId());
//        assertEquals("CS101", savedCourse.getCourseCode());
//    }
//
//    @Test
//    void testFindById() {
//        Course savedCourse = courseRepository.save(testCourse);
//
//        Optional<Course> foundCourse = courseRepository.findById(savedCourse.getId());
//
//        assertTrue(foundCourse.isPresent());
//        assertEquals(savedCourse.getId(), foundCourse.get().getId());
//        assertEquals("CS101", foundCourse.get().getCourseCode());
//    }
//
//    @Test
//    void testFindByCourseCode() {
//        courseRepository.save(testCourse);
//
//        Optional<Course> foundCourse = courseRepository.findByCourseCode("CS101");
//
//        assertTrue(foundCourse.isPresent());
//        assertEquals("CS101", foundCourse.get().getCourseCode());
//        assertEquals("Introduction to Computer Science", foundCourse.get().getCourseName());
//    }
//
//    @Test
//    void testFindByLecturerNip() {
//        courseRepository.save(testCourse);
//
//        List<Course> foundCourses = courseRepository.findByLecturerNip("123456");
//
//        assertFalse(foundCourses.isEmpty());
//        assertEquals(1, foundCourses.size());
//        assertEquals("CS101", foundCourses.get(0).getCourseCode());
//    }
//
//    @Test
//    void testFindAll() {
//        courseRepository.save(testCourse);
//
//        Course anotherCourse = Course.builder()
//                .courseCode("CS102")
//                .courseName("Data Structures")
//                .description("Advanced data structures")
//                .build();
//        courseRepository.save(anotherCourse);
//
//        List<Course> allCourses = courseRepository.findAll();
//
//        assertEquals(2, allCourses.size());
//    }
//
//    @Test
//    void testExistsByCourseCode() {
//        courseRepository.save(testCourse);
//
//        boolean exists = courseRepository.existsByCourseCode("CS101");
//        boolean notExists = courseRepository.existsByCourseCode("CS999");
//
//        assertTrue(exists);
//        assertFalse(notExists);
//    }
//
//    @Test
//    void testDelete() {
//        Course savedCourse = courseRepository.save(testCourse);
//
//        courseRepository.delete(savedCourse);
//
//        Optional<Course> deletedCourse = courseRepository.findById(savedCourse.getId());
//        assertFalse(deletedCourse.isPresent());
//    }
//}