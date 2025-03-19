package bjit.academy.jpa_demo;

import bjit.academy.jpa_demo.controller.StudentController;
import bjit.academy.jpa_demo.model.Student;
import bjit.academy.jpa_demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setEmail("test@example.com");

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/student/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"email\":\"test@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void testFindStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setEmail("test@example.com");

        when(studentRepository.findByEmail("test@example.com")).thenReturn(student);

        mockMvc.perform(get("/student/findByEmail")
                .param("email", "test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    // Add more test cases for other methods
}