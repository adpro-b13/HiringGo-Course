package id.ac.ui.cs.advprog.b13.hiringgo.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.b13.hiringgo.course.model.Course;
import id.ac.ui.cs.advprog.b13.hiringgo.course.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
@Import(CourseControllerTest.CourseServiceTestConfig.class)
public class CourseControllerTest {

    @TestConfiguration
    static class CourseServiceTestConfig {
        @Bean
        public CourseService courseService() {
            return Mockito.mock(CourseService.class);
        }
    }

    @Autowired
    private CourseService courseService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Course sampleCourse;

    @BeforeEach
    void setUp() {
        sampleCourse = new Course();
        sampleCourse.setId(UUID.randomUUID());
        sampleCourse.setKode("CS123");
        sampleCourse.setNama("Pemrograman Lanjut");
        sampleCourse.setDeskripsi("Belajar lanjutan Java");

        when(courseService.getAll()).thenReturn(Collections.singletonList(sampleCourse));
        when(courseService.getById(any())).thenReturn(sampleCourse);
        when(courseService.create(any(Course.class))).thenReturn(sampleCourse);
        when(courseService.update(any(), any(Course.class))).thenReturn(sampleCourse);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/admin/matakuliah/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].kode").value("CS123"));
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get("/admin/matakuliah/" + sampleCourse.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nama").value("Pemrograman Lanjut"));
    }

    @Test
    void testCreate() throws Exception {
        mockMvc.perform(post("/admin/matakuliah/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleCourse)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.kode").value("CS123"));
    }

    @Test
    void testUpdate() throws Exception {
        mockMvc.perform(put("/admin/matakuliah/" + sampleCourse.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleCourse)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.deskripsi").value("Belajar lanjutan Java"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/admin/matakuliah/" + sampleCourse.getId()))
                .andExpect(status().isNoContent());

        verify(courseService).delete(sampleCourse.getId());
    }
}
